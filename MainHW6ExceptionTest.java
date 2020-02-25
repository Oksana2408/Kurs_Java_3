import org.junit.Test;

public class MainHW6ExceptionTest {
    @Test(expected = RuntimeException.class)

    public void test(){
        MainHW6 main = new MainHW6();
        main.method1(new int[]{1, 1, 3, 2, 1});
    }
}
