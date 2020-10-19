package me.app17.guessinggame;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

        for (WinState state : WinState.values()
        ) {
            System.out.println(state + " " + state.ordinal());
        }

        System.out.println("=======================================");

        Mora player = new Mora(Mora.SCISSOR);
        Mora computer = new Mora(Mora.ROCK);
        System.out.println(Mora.getWinState(player, computer));

        System.out.println("=======================================");

        for (int i = 0; i < Mora.PAPER+1; i++) {
            player = new Mora(i);
            for (int j = 0; j < Mora.PAPER+1; j++) {
                computer = new Mora(j);
                System.out.println("玩家:"+player + " vs " + "電腦:"+computer);
                System.out.println(Mora.getWinState(player, computer));
            }
        }



        assertEquals(4, 2 + 2);
    }
}