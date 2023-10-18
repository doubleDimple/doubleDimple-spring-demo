import com.seataDemo.SeataApplication;
import com.seataDemo.entity.pojo.Price;
import com.seataDemo.entity.pojo.Product;
import com.seataDemo.service.PriceService;
import com.seataDemo.service.ProductService;
import com.seataDemo.tx.TransactionalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@lombok.extern.slf4j.Slf4j
@SpringBootTest(classes = SeataApplication.class)
public class DynamicTest {

    @Resource
    private ProductService productService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private TransactionalService transactionalService;

    @Test public void testQuery(){
        final Price price = this.priceService.getPriceByPrimaryKey(1L);
        final Product product = productService.getProductByPrimaryKey(1L);
        log.info("price is:[{}]",price.toString());
        log.info("product is:[{}]",product.toString());
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
