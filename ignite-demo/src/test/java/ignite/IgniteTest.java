package ignite;

import com.igniteDemo.IgniteApplication;
import com.igniteDemo.cache.IgniteContext;
import com.igniteDemo.config.ignite.IgniteCacheCfgSql;
import com.igniteDemo.entity.pojo.Product;
import com.igniteDemo.helloworld.Person;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@SpringBootTest(classes = IgniteApplication.class)
public class IgniteTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(IgniteTest.class);

    @Autowired
    private IgniteCacheCfgSql igniteCacheCfgSql;


    @Test
    public void testIgniteSql() throws SQLException {
        Ignite ignite = igniteCacheCfgSql.igniteInstance();
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
    }


    @Test
    public void testIgniteString(){
         IgniteCache bean = IgniteContext.getBean();
         bean.put("123","456");
        final String o = (String) bean.get("123");
        LOGGER.info(o);
    }

    @Test
    public void testIgniteObject(){
        IgniteCache igniteCache = IgniteContext.getBean();
        Product product = new Product();
        product.setId(1L);
        product.setName("ignite测试");
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());

        igniteCache.put("product",product);


        final Product productCache = (Product)igniteCache.get("product");
        LOGGER.info("productCache:[{}]",productCache);

    }
}
