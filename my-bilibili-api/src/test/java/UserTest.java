import com.bilibili.MyBilibiliApplication;
import com.bilibili.util.RSAUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author rlz
 * @date 2023/5/9 18:02
 * @description
 */
@SpringBootTest(classes = MyBilibiliApplication.class)
public class UserTest {
    @Test
    public void userTest() throws Exception {
        String password = "123456";
            System.out.println(RSAUtil.encrypt(password));
    }
}
