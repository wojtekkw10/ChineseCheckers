package chinesecheckers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

abstract class Board {
    protected BufferedImage background;
    protected ArrayList<PlayingSpace> board = new ArrayList<PlayingSpace>();

    public abstract void getSampleBoard();

    public void setBackground(BufferedImage background) {
        this.background = background;
    }

    public void setBoard(ArrayList<PlayingSpace> board) {
        this.board = board;
    }

    public ArrayList<PlayingSpace> getBoard() {
        return board;
    }

    public BufferedImage getBackground() {
        return background;
    }

    public int getSize()
    {
        return board.size();
    }

}
