import com.doubleDimple.RedssionApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedssionApplication.class})
public class RedisTest {


    @Resource
    private RedissonClient redissonClient;


    @Test
    public  void test(){
        String lockName = "redission___";
        RLock lock = redissonClient.getLock(lockName);

        try {
            boolean isLocked = lock.tryLock(3000, TimeUnit.MILLISECONDS);
            if (isLocked) {
                // TODO
            }
        } catch (Exception e) {
            lock.unlock();
        }
    }
}
