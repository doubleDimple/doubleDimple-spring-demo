package ignite;



import com.igniteDemo.IgniteApplication;
import com.igniteDemo.cache.IgniteContext;
import com.igniteDemo.entity.pojo.Product;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.Query;
import org.apache.ignite.cache.query.SqlQuery;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest(classes = IgniteApplication.class)
public class IgniteTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(IgniteTest.class);

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
