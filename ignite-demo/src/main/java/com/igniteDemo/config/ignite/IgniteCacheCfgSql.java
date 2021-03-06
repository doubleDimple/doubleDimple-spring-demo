package com.igniteDemo.config.ignite;

import com.igniteDemo.helloworld.Person;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteJdbcThinDataSource;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.QueryEntity;
import org.apache.ignite.cache.store.jdbc.CacheJdbcPojoStoreFactory;
import org.apache.ignite.cache.store.jdbc.JdbcType;
import org.apache.ignite.cache.store.jdbc.JdbcTypeField;
import org.apache.ignite.cache.store.jdbc.dialect.BasicJdbcDialect;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.apache.ignite.springdata20.repository.config.EnableIgniteRepositories;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import static org.apache.ignite.cache.CacheMode.PARTITIONED;

@Configuration
@EnableIgniteRepositories
public class IgniteCacheCfgSql {


	private static boolean client_mode = false;

	private Ignite ignite;

	private IgniteCache igniteCache;


	@Bean
	public Ignite igniteInstance() throws SQLException {
		// Preparing IgniteConfiguration using Java APIs
		IgniteConfiguration cfg = new IgniteConfiguration();

		// The node will be started as a client node.
		cfg.setClientMode(true);

		// Classes of custom Java logic will be transferred over the wire from this app.
		cfg.setPeerClassLoadingEnabled(true);

		// Setting up an IP Finder to ensure the client can locate the servers.
		TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
		ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47509"));
		cfg.setDiscoverySpi(new TcpDiscoverySpi().setIpFinder(ipFinder));

		// Starting the node
		Ignite ignite = Ignition.start(cfg);

		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		cacheConfiguration.setSqlSchema("public");
		cacheConfiguration.setName("person");
		cacheConfiguration.setCacheMode(PARTITIONED);
		cacheConfiguration.setAtomicityMode(CacheAtomicityMode.ATOMIC);
		cacheConfiguration.setWriteBehindEnabled(true);
		// ????????????????????????????????????
		cacheConfiguration.setWriteBehindBatchSize(20);
		//??????????????????????????????????????????
		cacheConfiguration.setWriteBehindBatchSize(20);
		//?????????????????????????????????????????????????????????????????????
		cacheConfiguration.setWriteBehindFlushFrequency(10000);
		//?????????????????????????????????
		cacheConfiguration.setWriteBehindFlushThreadCount(10);
		cacheConfiguration.setReadThrough(true);
		cacheConfiguration.setWriteThrough(true);

		//????????????
		CacheJdbcPojoStoreFactory<Object, Object> storeFactory = new CacheJdbcPojoStoreFactory<>();
		storeFactory.setDataSourceBean("ds1DataSourceIgnite");
		storeFactory.setDialect(new BasicJdbcDialect());

		LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();
		//??????????????????
		JdbcTypeField[] jdbcTypeFields = new JdbcTypeField[10];
		for (int i = 0;i<jdbcTypeFields.length;i++){
			jdbcTypeFields[i] = new JdbcTypeField(4,"fieldName",Integer.class,"fieldName");
			fieldMap.put("fieldName","Integer");
		}

		//jdbc????????????
		JdbcType jdbcType = new JdbcType();
		jdbcType.setCacheName("person");
		jdbcType.setKeyType(Integer.class);
		jdbcType.setValueType(Person.class);
		jdbcType.setDatabaseTable("person");
		jdbcType.setKeyFields(new JdbcTypeField(4,"fieldName",Integer.class,"fieldName"));
		jdbcType.setValueFields(jdbcTypeFields);

		storeFactory.setTypes(jdbcType);
		cacheConfiguration.setBackups(1);
		cacheConfiguration.setCacheStoreFactory(storeFactory);

		//ignite????????????
		QueryEntity queryEntity = new QueryEntity();
		queryEntity.setTableName("person");
		queryEntity.setKeyType("java.lang.Integer");
		queryEntity.setValueType("person");
		//queryEntity.setKeyFieldName("id");
		queryEntity.setKeyFields(Collections.singleton("id"));
		queryEntity.setFields(fieldMap);

		cacheConfiguration.setQueryEntities(Collections.singleton(queryEntity));
		ignite.getOrCreateCache(cacheConfiguration);
		return ignite;
	}

	@ConditionalOnBean(Ignite.class)
	@Bean
	public IgniteCache igniteCache(){
		return igniteCache;
	}


}
