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

    BoardWindow(Board board, ActionListener actionListener, JFrame frame)
    {
        this.actionListener = actionListener;
        this.board = board;
        this.frame = frame;
    }

    //TODO: zaimplentowac wyglad okna + jego wyswietlanie (trzeba wyczyscic okno i namalowac od nowa)

    void setBoard(Board board) {
        this.board = board;
    }

    void display()
    {
        frame.getContentPane().removeAll();

        JPanel p = new BoardWindow(board, actionListener, frame);
        p.setBounds(0,0,1000,800);

        frame.add(p, null);
        frame.repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent ( g );
        g.setColor ( Color.RED );
        frame.getContentPane().removeAll();

        board.getSampleBoard();
        ArrayList<PlayingSpace> PS = board.getBoard();

        for(int i=0; i<PS.size(); i++)
        {
            int x=PS.get(i).getX();
            int y=PS.get(i).getY();
            int radius = 100;

            g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
        }
    }

}
