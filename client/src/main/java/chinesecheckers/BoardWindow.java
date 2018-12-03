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

    public Character[][] charBoard = new Character[18][18];

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
        ((BoardWindow) p).charBoard = charBoard;
        p.setBounds(100,0,760,800);
        frame.getContentPane().setBackground(Color.white);

        frame.add(p, null);

        frame.repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent ( g );

        /*
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
        }
        */
        frame.getContentPane().removeAll();
        g.drawImage(image, 0, 0, this);

        //Mapowanie
        for(int i =0; i<charBoard.length; i++)
        {
            int x = 0;
            int y = 0;

            for(int j=0; j<charBoard[i].length; j++)
            {
                char k = charBoard[i][j];
                if(k == 'b') g.setColor ( Color.BLUE );
                else if(k == 'y') g.setColor ( Color.YELLOW );
                else if(k == 'c') { g.setColor ( Color.BLACK ); }
                else if(k == 'g') { g.setColor ( Color.GREEN ); System.out.println(i+" "+j);}
                else if(k == 'r') g.setColor ( Color.RED );
                else if(k == 'w') g.setColor ( Color.GRAY );
                int radius = 24;

                if(i==9 && j==10) { x=10; y=20; } //do testowania
                //White
                if(i==5 && j==1) { x=24; y=200; }
                else if(i==5 && j==2) { x=84; y=200; }
                else if(i==5 && j==3) { x=144; y=200; }
                else if(i==5 && j==4) { x=204; y=200; }
                else if(i==6 && j==2) { x=54; y=246; }
                else if(i==6 && j==3) { x=114; y=246; }
                else if(i==6 && j==4) { x=174; y=246; }
                else if(i==7 && j==3) { x=84; y=292; }
                else if(i==7 && j==4) { x=144; y=292; }
                else if(i==8 && j==4) { x=114; y=336; }
                //Black
                else if(i==10 && j==5) { x=114; y=428; }
                else if(i==11 && j==5) { x=84; y=474; }
                else if(i==11 && j==6) { x=144; y=474; }
                else if(i==12 && j==5) { x=54; y=520; }
                else if(i==12 && j==6) { x=114; y=520; }
                else if(i==12 && j==7) { x=174; y=520; }
                else if(i==13 && j==5) { x=24; y=566; }
                else if(i==13 && j==6) { x=84; y=566; }
                else if(i==13 && j==7) { x=144; y=566; }
                else if(i==13 && j==8) { x=204; y=566; }
                //green
                else if(i==14 && j==10) { x=290; y=612; }
                else if(i==14 && j==11) { x=350; y=612; }
                else if(i==14 && j==12) { x=410; y=612; }
                else if(i==14 && j==13) { x=470; y=612; }
                else if(i==15 && j==11) { x=320; y=658; }
                else if(i==15 && j==12) { x=380; y=658; }
                else if(i==15 && j==13) { x=440; y=658; }
                else if(i==16 && j==12) { x=350; y=704; }
                else if(i==16 && j==13) { x=410; y=704; }
                else if(i==17 && j==13) { x=380; y=750; }
                //...

                else {
                    x = 20-((int) ((i * 30) * Math.cos(Math.toRadians(90))) - (int) ((j * 46) * Math.sin(Math.toRadians(90))));
                    y = ((int) ((j * 46) * Math.cos(Math.toRadians(90))) + (int) ((i * 30) * Math.sin(Math.toRadians(90))));
                }


                if(k!=' ' && (k=='y'||k=='b'||k=='c'||k=='g'||k=='r'||k=='w'))
                    g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
            }
        }

    }



}
