package me.app17.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

        int source = 123;
        String pattern = "%010d"; // 格式化字串，整數，長度10，不足部分左邊補0
        String str = String.format(pattern, source);

        System.out.println(str); // 0000000123

        assertEquals(4, 2 + 2);
    }
}