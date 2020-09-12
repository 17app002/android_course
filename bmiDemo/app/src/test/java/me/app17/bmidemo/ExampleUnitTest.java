package me.app17.bmidemo;

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
        String msg = String.format("您身高為:%.1f 體重:%.1f\nBMI指數為:%.2f", 100.5, 165.5, 32.5);
        System.out.println(msg);
        assertEquals(4, 2 + 2);
    }



}