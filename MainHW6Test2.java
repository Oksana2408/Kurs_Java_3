import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collections;

public class MainHW6Test2 {
    @RunWith(Parameterized.class)

        public static Collections<Object[]> params() {
            return Arrays.asList(new Object[][]{
                    {new int[]{1, 2, 1, 4, 1, 1, 1}, false},
                    {new int[]{1, 1, 1, 1, 1, 4}, true},
                    {new int[]{1, 1, 1, 1, 1, 1}, false}
            });
        }

        private int[] in;
        private boolean res;

        public MainHW6Test2(int[] in, boolean res) {
            this.in = in;
            this.res = res;
        }

        @Before
        MainHW6 main = new MainHW6();


        @Test
        public void method1Test() {
            Assert.assertEquals(res, main.method2(in));
        }
    }
}
