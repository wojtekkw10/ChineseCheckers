package chinesecheckers;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoardWindow extends Window{
    private ActionListener actionListener;
    private Board board;
    private JFrame mainFrame;

    BoardWindow(Board board, ActionListener actionListener, JFrame frame)
    {
        this.actionListener = actionListener;
        this.board = board;
        this.mainFrame = frame;
    }

    //TODO: zaimplentowac wyglad okna + jego wyswietlanie (trzeba wyczyscic okno i namalowac od nowa)

    void setBoard(Board board) {
        this.board = board;
    }

    void display()
    {
        mainFrame.getContentPane().removeAll();

        //drawBoard(mainFrame, getSampleBoard());
        //drawBoard(mainFrame);


        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);

    }
    /*
    void drawBoard(JFrame frame)
    {
        this.repaint();
        this.setVisible(true);

    }

    public void paint(Graphics g) {
        System.out.print("LOL");
        getSampleBoard();
        g.setColor(Color.yellow);

        for (int i = 0; i < board.getSize(); i++) {
            ArrayList<PlayingSpace> list = board.getBoard();
            PlayingSpace PS = list.get(i);
            drawCircleByCenter(g, PS.getX(), PS.getY(), 10);
        }
    }

    void drawCircleByCenter(Graphics g, int x, int y, int radius){
        //g.setColor(Color.LIGHT_GRAY);
        System.out.print("Drawing circle");
        g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
    }
    */

}
