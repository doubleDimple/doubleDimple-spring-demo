import com.dpubleDimple.Application;
import com.dpubleDimple.entity.pojo.Price;
import com.dpubleDimple.entity.pojo.Product;
import com.dpubleDimple.service.PriceService;
import com.dpubleDimple.service.ProductService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@SpringBootTest(classes = Application.class)
public class DynamicTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicTest.class);

    @Resource
    private ProductService productService;

    @Autowired
    private PriceService priceService;

    @Test public void testQuery(){
        final Price price = this.priceService.getPriceByPrimaryKey(1L);
        final Product product = productService.getProductByPrimaryKey(1L);
        LOGGER.info("price is:[{}]",price.toString());
        LOGGER.info("product is:[{}]",product.toString());
    }
}
