import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collections;

@RunWith(Parameterized.class)
public class MainHW6Test {
    @Parameterized.Parameters

    public static Collections<Object []> params(){
        return Arrays.asList(new Object[][]{
                {new int[]{5,7,6, 4, 8, 7}, new int[]{8, 7}},
                {new int[]{9, 8, 5, 4, 1, 2, 3}, new int[]{1, 2, 3}}
        });
    }

    private int[] in;
    private int[] out;

    public MainHW6Test(int[] in, int[] out) {
        this.in = in;
        this.out = out;
    }

    @Before
    MainHW6 main = new MainHW6();


    @Test
    public void method1Test(){
        Assert.assertArrayEquals(out, main.method1(in));
    }

}
