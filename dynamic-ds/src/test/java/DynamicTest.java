import com.doubleDimple.DynamicApplication;
import com.doubleDimple.entity.pojo.Price;
import com.doubleDimple.entity.pojo.Product;
import com.doubleDimple.service.PriceService;
import com.doubleDimple.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicApplication.class)
@Slf4j
public class DynamicTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private PriceService priceService;

    @Test
    public void testQuery(){
        final Product product = productService.getProductByPrimaryKey(11L);
        final Price price = priceService.getPriceByPrimaryKey(1L);
        log.info("price is:[{}]",price.toString());
        log.info("product is:[{}]",product.toString());
    }
}
