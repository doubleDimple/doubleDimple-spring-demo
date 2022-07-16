import com.doubleDimple.DynamicApplication;
import com.doubleDimple.entity.pojo.Price;
import com.doubleDimple.entity.pojo.Product;
import com.doubleDimple.service.PriceService;
import com.doubleDimple.service.ProductService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DynamicApplication.class)
public class DynamicTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicTest.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private PriceService priceService;

    @Test public void testQuery(){
        final Product product = productService.getProductByPrimaryKey(1L);
        final Price price = priceService.getPriceByPrimaryKey(1L);
        LOGGER.info("price is:[{}]",price.toString());
        LOGGER.info("product is:[{}]",product.toString());
    }
}
