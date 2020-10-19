package me.app17.guessinggame;


public class Mora {
    public static final int SCISSOR = 0;
    public static final int ROCK = 1;
    public static final int PAPER = 2;

    private int index;

    Mora(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        final String[] MORA_NAME = {"剪刀", "石頭", "布"};
        return MORA_NAME[index];
    }

    //回傳輸贏結果
    public static WinState getWinState(Mora player, Mora computer) {

        if (player.index == computer.index) {
            return WinState.EVEN;
        }

        if (player.index == Mora.SCISSOR && computer.index == Mora.PAPER) {
            return WinState.PLAYER_WIN;
        }

        if (computer.index == Mora.SCISSOR && player.index == Mora.PAPER) {
            return WinState.COMPUTER_WIN;
        }

        if (player.index > computer.index) {
            return WinState.PLAYER_WIN;
        }

        return WinState.COMPUTER_WIN;
    }

}
