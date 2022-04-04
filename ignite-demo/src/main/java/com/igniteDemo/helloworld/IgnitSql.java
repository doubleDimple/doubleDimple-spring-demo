package com.igniteDemo.helloworld;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.QueryEntity;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.cache.store.jdbc.CacheJdbcPojoStoreFactory;
import org.apache.ignite.cache.store.jdbc.JdbcType;
import org.apache.ignite.cache.store.jdbc.JdbcTypeField;
import org.apache.ignite.cache.store.jdbc.dialect.BasicJdbcDialect;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.apache.ignite.cache.CacheMode.PARTITIONED;

public class IgnitSql {
    private static final Logger LOGGER = LoggerFactory.getLogger(IgnitSql.class);

    public static void main(String[] args) {
        Ignite ignite = getIgnite();

        //handlerr select
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        IgniteCache<Integer, Person> cache = ignite.getOrCreateCache("person");
        FieldsQueryCursor<List<?>> cursor = cache.query(new SqlFieldsQuery("select * from person").setSchema("public"));
        Iterator<List<?>> iterator = cursor.iterator();
        while (iterator.hasNext()){
            List<?> next = iterator.next();
            int i= 0;
            for (;i < cursor.getColumnsCount();i++){
                objectObjectHashMap.put(cursor.getFieldName(i).toLowerCase(),next.get(i));
            }
        }

        LOGGER.info("result:[{}]",objectObjectHashMap);
        //insert
        IgniteCache<String, Map<String, Object>> cache1 = ignite.getOrCreateCache("person");
    }

    private static Ignite getIgnite() {
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
        // 持久化后写每批次写入数量
        cacheConfiguration.setWriteBehindBatchSize(20);
        //触发持久化后写的新增数据数量
        cacheConfiguration.setWriteBehindBatchSize(20);
        //持久化后写刷新频率，即每过多少毫秒就持久化一次
        cacheConfiguration.setWriteBehindFlushFrequency(10000);
        //持久化后写使用线程数量
        cacheConfiguration.setWriteBehindFlushThreadCount(10);
        cacheConfiguration.setReadThrough(true);
        cacheConfiguration.setWriteThrough(true);

        //持久化类
        CacheJdbcPojoStoreFactory<Object, Object> storeFactory = new CacheJdbcPojoStoreFactory<>();
        storeFactory.setDataSourceBean(getDatasource());
        storeFactory.setDialect(new BasicJdbcDialect());

        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();
        //构造字段列表
        JdbcTypeField[] jdbcTypeFields = new JdbcTypeField[10];
        for (int i = 0;i<jdbcTypeFields.length;i++){
            jdbcTypeFields[i] = new JdbcTypeField(4,"fieldName",Integer.class,"fieldName");
            fieldMap.put("fieldName","Integer");
        }

        //jdbc字段映射
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

        //ignite自动建表
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

    private static String getDatasource() {
         DruidDataSource druidDataSource = new DruidDataSource();
         return null;
    }
}
