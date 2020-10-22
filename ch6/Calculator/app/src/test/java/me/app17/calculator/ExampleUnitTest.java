package me.app17.calculator;

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

        //檢查小數點尾數是否是0
        String value=String.valueOf(35.0);
        String[] temp=value.split("\\.");
        String result=null;
        if(temp[1].equals("0")){
            result=temp[0];
        }

        System.out.println(result);
        assertEquals(4, 2 + 2);
    }
}