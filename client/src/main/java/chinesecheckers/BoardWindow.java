package chinesecheckers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BoardWindow extends Window{
    private ActionListener actionListener;
    private Board board;
    private BufferedImage image;

    BoardWindow(Board board, ActionListener actionListener, JFrame frame)
    {
        this.actionListener = actionListener;
        this.board = board;
        this.frame = frame;

        try {
            image = ImageIO.read(new File("backtexture.jpg"));
        } catch (IOException ex) {
            System.out.print("CLIENT: ERROR: Couldn't find the backtexture.jpg image");
        }
    }

    void setBoard(Board board) {
        this.board = board;
    }

    void display()
    {
        frame.getContentPane().removeAll();
        frame.revalidate();

        //System.out.print("CLIENT: Drawing Board window\n");

        JPanel p = new BoardWindow(board, actionListener, frame);
        p.setBounds(100,0,760,800);
        frame.getContentPane().setBackground(Color.white);

        frame.add(p, null);

        frame.repaint();
        //frame.setVisible(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent ( g );

        g.setColor ( Color.RED );
        frame.getContentPane().removeAll();
        g.drawImage(image, 0, 0, this);

        board.getSampleBoard();
        ArrayList<PlayingSpace> PS = board.getBoard();

        for(int i=0; i<PS.size(); i++)
        {
            int x=PS.get(i).getX();
            int y=PS.get(i).getY();
            int radius = 24;

            g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
            //g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
        }

    }



}
