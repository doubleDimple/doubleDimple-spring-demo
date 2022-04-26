import com.seataDemo.SeataApplication;
import com.seataDemo.entity.pojo.Price;
import com.seataDemo.entity.pojo.Product;
import com.seataDemo.service.PriceService;
import com.seataDemo.service.ProductService;
import com.seataDemo.tx.TransactionalService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@SpringBootTest(classes = SeataApplication.class)
public class DynamicTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicTest.class);

    @Resource
    private ProductService productService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private TransactionalService transactionalService;

    @Test public void testQuery(){
        final Price price = this.priceService.getPriceByPrimaryKey(1L);
        final Product product = productService.getProductByPrimaryKey(1L);
        LOGGER.info("price is:[{}]",price.toString());
        LOGGER.info("product is:[{}]",product.toString());
    }

    @Test
    public void testDis(){
        Product product = new Product();
        product.setName("huawei");
        transactionalService.insertDis(product);
    }

    public static void main(String[] args) {
        String s = "123";
        System.out.println(s.replace("'", ""));
    }
}
