package chinesecheckers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardWindow extends Window{
    private ActionListener actionListener;
    private MouseListener mouseListener;
    private Board board;
    private BufferedImage image;

    public Character[][] charBoard = new Character[18][18];
    public Ellipse2D[][] ovalBoard = new Ellipse2D[18][18];
    public Ellipse2D[][] possibleMovesCirclesBoard = new Ellipse2D[18][18];
    public Map<Field, Field[]> possibleMoves;

    Field clickedField = new Field();

    BoardWindow(Board board, ActionListener actionListener, MouseListener mouseListener, JFrame frame)
    {
        this.actionListener = actionListener;
        this.board = board;
        this.frame = frame;
        this.mouseListener = mouseListener;
        this.addMouseListener(mouseListener);

        try {
            image = ImageIO.read(new File("backtexture.jpg"));
        } catch (IOException ex) {
            System.out.print("CLIENT: ERROR: Couldn't find the backtexture.jpg image");
        }

        this.setBounds(100,0,760,800);
        frame.add(this, null);

    }

    void setBoard(Board board) {
        this.board = board;
    }

    void display()
    {
        if(this.mouseListener!=null) frame.removeMouseListener(mouseListener);
        frame.getContentPane().removeAll();
        frame.revalidate();

        JPanel p = new BoardWindow(board, actionListener,mouseListener, frame);
        p.addMouseListener(mouseListener);
        frame.addMouseListener(mouseListener);

        ((BoardWindow) p).charBoard = charBoard;
        ((BoardWindow) p).ovalBoard = ovalBoard;
        ((BoardWindow) p).possibleMoves = possibleMoves;
        ((BoardWindow) p).clickedField = clickedField;
        ((BoardWindow) p).possibleMovesCirclesBoard = possibleMovesCirclesBoard;

        p.setBounds(100,0,760,800);
        frame.getContentPane().setBackground(Color.white);

        frame.repaint();
    }

    boolean isPossibleMoveField(Field field)
    {
        for(int i=0; i<possibleMovesCirclesBoard.length; i++)
        {
            for(int j=0; j<possibleMovesCirclesBoard[i].length; j++)
            {
                if(possibleMovesCirclesBoard[i][j]!=null)
                {
                    if(field.getX() == i && field.getY() == j) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent ( g );
        Graphics2D g2d = (Graphics2D) g;
        //frame.getContentPane().setBackground(Color.white);

        //Czyszczenie tablicy
        for(int i=0; i<possibleMovesCirclesBoard.length; i++)
        {
            for(int j=0; j<possibleMovesCirclesBoard[i].length; j++)
            {
                if(possibleMovesCirclesBoard[i][j]!=null)
                {
                    possibleMovesCirclesBoard[i][j] = null;
                }
            }
        }

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

        int x = 0;
        int y = 0;

        //Mapowanie pionow
        for(int i =0; i<charBoard.length; i++)
        {
            for(int j=0; j<charBoard[i].length; j++)
            {
                char k = charBoard[i][j];
                if(k == 'b') { g2d.setPaint ( Color.BLUE );  }
                else if(k == 'y') {g2d.setColor ( Color.YELLOW );}
                else if(k == 'c') { g2d.setColor ( Color.BLACK ); }
                else if(k == 'g') { g2d.setColor ( Color.GREEN );}
                else if(k == 'r') {g2d.setColor ( Color.RED );}
                else if(k == 'w') g2d.setColor ( Color.GRAY );
                int radius = 24;

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
                //Blue
                else if(i==10 && j==14) { x=650; y=428; }
                else if(i==11 && j==14) { x=620; y=474; }
                else if(i==11 && j==15) { x=680; y=474; }
                else if(i==12 && j==14) { x=590; y=520; }
                else if(i==12 && j==15) { x=650; y=520; }
                else if(i==12 && j==16) { x=710; y=520; }
                else if(i==13 && j==14) { x=560; y=564; }
                else if(i==13 && j==15) { x=620; y=564; }
                else if(i==13 && j==16) { x=680; y=564; }
                else if(i==13 && j==17) { x=740; y=564; }
                //Yellow
                else if(i==5 && j==10) { x=560; y=200; }
                else if(i==5 && j==11) { x=620; y=200; }
                else if(i==5 && j==12) { x=680; y=200; }
                else if(i==5 && j==13) { x=740; y=200; }
                else if(i==6 && j==11) { x=590; y=246; }
                else if(i==6 && j==12) { x=650; y=246; }
                else if(i==6 && j==13) { x=710; y=246; }
                else if(i==7 && j==12) { x=620; y=292; }
                else if(i==7 && j==13) { x=680; y=292; }
                else if(i==8 && j==13) { x=650; y=336; }
                //Red
                else if(i==1 && j==5) { x=380; y=16; }
                else if(i==2 && j==5) { x=350; y=62; }
                else if(i==2 && j==6) { x=410; y=62; }
                else if(i==3 && j==5) { x=320; y=108; }
                else if(i==3 && j==6) { x=380; y=108; }
                else if(i==3 && j==7) { x=440; y=108; }
                else if(i==4 && j==5) { x=290; y=154; }
                else if(i==4 && j==6) { x=350; y=154; }
                else if(i==4 && j==7) { x=410; y=154; }
                else if(i==4 && j==8) { x=470; y=154; }
                //Center
                else if(i==5 && j==5) { x=264; y=200; }
                else if(i==5 && j==6) { x=324; y=200; }
                else if(i==5 && j==7) { x=384; y=200; }
                else if(i==5 && j==8) { x=444; y=200; }
                else if(i==5 && j==9) { x=504; y=200; }
                else if(i==6 && j==5) { x=234; y=244; }
                else if(i==6 && j==6) { x=294; y=244; }
                else if(i==6 && j==7) { x=354; y=244; }
                else if(i==6 && j==8) { x=414; y=244; }
                else if(i==6 && j==9) { x=474; y=244; }
                else if(i==6 && j==10) { x=534; y=244; }
                else if(i==7 && j==5) { x=204; y=288; }
                else if(i==7 && j==6) { x=264; y=288; }
                else if(i==7 && j==7) { x=324; y=288; }
                else if(i==7 && j==8) { x=384; y=288; }
                else if(i==7 && j==9) { x=444; y=288; }
                else if(i==7 && j==10) { x=504; y=288; }
                else if(i==7 && j==11) { x=564; y=288; }
                else if(i==8 && j==5) { x=174; y=334; }
                else if(i==8 && j==6) { x=234; y=334; }
                else if(i==8 && j==7) { x=294; y=334; }
                else if(i==8 && j==8) { x=354; y=334; }
                else if(i==8 && j==9) { x=414; y=334; }
                else if(i==8 && j==10) { x=474; y=334; }
                else if(i==8 && j==11) { x=534; y=334; }
                else if(i==8 && j==12) { x=594; y=334; }
                else if(i==9 && j==5) { x=144; y=380; }
                else if(i==9 && j==6) { x=204; y=380; }
                else if(i==9 && j==7) { x=264; y=380; }
                else if(i==9 && j==8) { x=324; y=380; }
                else if(i==9 && j==9) { x=384; y=380; }
                else if(i==9 && j==10) { x=444; y=380; }
                else if(i==9 && j==11) { x=504; y=380; }
                else if(i==9 && j==12) { x=564; y=380; }
                else if(i==9 && j==13) { x=624; y=380; }
                else if(i==10 && j==6) { x=174; y=426; }
                else if(i==10 && j==7) { x=234; y=426; }
                else if(i==10 && j==8) { x=294; y=426; }
                else if(i==10 && j==9) { x=354; y=426; }
                else if(i==10 && j==10) { x=414; y=426; }
                else if(i==10 && j==11) { x=474; y=426; }
                else if(i==10 && j==12) { x=534; y=426; }
                else if(i==10 && j==13) { x=594; y=426; }
                else if(i==11 && j==7) { x=204; y=472; }
                else if(i==11 && j==8) { x=264; y=472; }
                else if(i==11 && j==9) { x=324; y=472; }
                else if(i==11 && j==10) { x=384; y=472; }
                else if(i==11 && j==11) { x=444; y=472; }
                else if(i==11 && j==12) { x=504; y=472; }
                else if(i==11 && j==13) { x=564; y=472; }
                else if(i==12 && j==8) { x=234; y=518; }
                else if(i==12 && j==9) { x=294; y=518; }
                else if(i==12 && j==10) { x=354; y=518; }
                else if(i==12 && j==11) { x=414; y=518; }
                else if(i==12 && j==12) { x=474; y=518; }
                else if(i==12 && j==13) { x=534; y=518; }
                else if(i==13 && j==9) { x=264; y=564; }
                else if(i==13 && j==10) { x=324; y=564; }
                else if(i==13 && j==11) { x=384; y=564; }
                else if(i==13 && j==12) { x=444; y=564; }
                else if(i==13 && j==13) { x=504; y=564; }

                else {

                    x = 20-((int) ((i * 30) * Math.cos(Math.toRadians(90))) - (int) ((j * 46) * Math.sin(Math.toRadians(90))));
                    y = ((int) ((j * 46) * Math.cos(Math.toRadians(90))) + (int) ((i * 30) * Math.sin(Math.toRadians(90))));

                }

                if(k!=' ' && !(k=='y'||k=='b'||k=='c'||k=='g'||k=='r'||k=='w')) {
                    //System.out.println(i+" "+j);
                    g2d.setColor(Color.CYAN);
                }



                //&& (k=='y'||k=='b'||k=='c'||k=='g'||k=='r'||k=='w')
                if(k!=' ' )
                {
                    //g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
                    ovalBoard[i][j] = new Ellipse2D.Double(x-radius, y-radius, 48, 48);
                }



            }


        }

        //Mapowanie possibleMoves
        for (Map.Entry<Field, Field[]> entry : possibleMoves.entrySet()) {
            //System.out.println(entry.getKey() + ":" + entry.getValue());

            Field[] moves = entry.getValue();
            Field field = entry.getKey();

            if(clickedField.getX() == field.getX() && clickedField.getY() == field.getY())
            {
                for(int l=0; l<moves.length; l++)
                {
                    int i = moves[l].getX();
                    int j = moves[l].getY();

                /*char k = charBoard[i][j];
                if(k == 'b') { g2d.setPaint ( Color.BLUE );  }
                else if(k == 'y') {g2d.setColor ( Color.YELLOW );}
                else if(k == 'c') { g2d.setColor ( Color.BLACK ); }
                else if(k == 'g') { g2d.setColor ( Color.GREEN );}
                else if(k == 'r') {g2d.setColor ( Color.RED );}
                else if(k == 'w') g2d.setColor ( Color.GRAY );
                */
                    g2d.setColor ( Color.MAGENTA );
                    int radius = 24;

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
                    //Blue
                    else if(i==10 && j==14) { x=650; y=428; }
                    else if(i==11 && j==14) { x=620; y=474; }
                    else if(i==11 && j==15) { x=680; y=474; }
                    else if(i==12 && j==14) { x=590; y=520; }
                    else if(i==12 && j==15) { x=650; y=520; }
                    else if(i==12 && j==16) { x=710; y=520; }
                    else if(i==13 && j==14) { x=560; y=564; }
                    else if(i==13 && j==15) { x=620; y=564; }
                    else if(i==13 && j==16) { x=680; y=564; }
                    else if(i==13 && j==17) { x=740; y=564; }
                    //Yellow
                    else if(i==5 && j==10) { x=560; y=200; }
                    else if(i==5 && j==11) { x=620; y=200; }
                    else if(i==5 && j==12) { x=680; y=200; }
                    else if(i==5 && j==13) { x=740; y=200; }
                    else if(i==6 && j==11) { x=590; y=246; }
                    else if(i==6 && j==12) { x=650; y=246; }
                    else if(i==6 && j==13) { x=710; y=246; }
                    else if(i==7 && j==12) { x=620; y=292; }
                    else if(i==7 && j==13) { x=680; y=292; }
                    else if(i==8 && j==13) { x=650; y=336; }
                    //Red
                    else if(i==1 && j==5) { x=380; y=16; }
                    else if(i==2 && j==5) { x=350; y=62; }
                    else if(i==2 && j==6) { x=410; y=62; }
                    else if(i==3 && j==5) { x=320; y=108; }
                    else if(i==3 && j==6) { x=380; y=108; }
                    else if(i==3 && j==7) { x=440; y=108; }
                    else if(i==4 && j==5) { x=290; y=154; }
                    else if(i==4 && j==6) { x=350; y=154; }
                    else if(i==4 && j==7) { x=410; y=154; }
                    else if(i==4 && j==8) { x=470; y=154; }
                    //Center
                    else if(i==5 && j==5) { x=264; y=200; }
                    else if(i==5 && j==6) { x=324; y=200; }
                    else if(i==5 && j==7) { x=384; y=200; }
                    else if(i==5 && j==8) { x=444; y=200; }
                    else if(i==5 && j==9) { x=504; y=200; }
                    else if(i==6 && j==5) { x=234; y=244; }
                    else if(i==6 && j==6) { x=294; y=244; }
                    else if(i==6 && j==7) { x=354; y=244; }
                    else if(i==6 && j==8) { x=414; y=244; }
                    else if(i==6 && j==9) { x=474; y=244; }
                    else if(i==6 && j==10) { x=534; y=244; }
                    else if(i==7 && j==5) { x=204; y=288; }
                    else if(i==7 && j==6) { x=264; y=288; }
                    else if(i==7 && j==7) { x=324; y=288; }
                    else if(i==7 && j==8) { x=384; y=288; }
                    else if(i==7 && j==9) { x=444; y=288; }
                    else if(i==7 && j==10) { x=504; y=288; }
                    else if(i==7 && j==11) { x=564; y=288; }
                    else if(i==8 && j==5) { x=174; y=334; }
                    else if(i==8 && j==6) { x=234; y=334; }
                    else if(i==8 && j==7) { x=294; y=334; }
                    else if(i==8 && j==8) { x=354; y=334; }
                    else if(i==8 && j==9) { x=414; y=334; }
                    else if(i==8 && j==10) { x=474; y=334; }
                    else if(i==8 && j==11) { x=534; y=334; }
                    else if(i==8 && j==12) { x=594; y=334; }
                    else if(i==9 && j==5) { x=144; y=380; }
                    else if(i==9 && j==6) { x=204; y=380; }
                    else if(i==9 && j==7) { x=264; y=380; }
                    else if(i==9 && j==8) { x=324; y=380; }
                    else if(i==9 && j==9) { x=384; y=380; }
                    else if(i==9 && j==10) { x=444; y=380; }
                    else if(i==9 && j==11) { x=504; y=380; }
                    else if(i==9 && j==12) { x=564; y=380; }
                    else if(i==9 && j==13) { x=624; y=380; }
                    else if(i==10 && j==6) { x=174; y=426; }
                    else if(i==10 && j==7) { x=234; y=426; }
                    else if(i==10 && j==8) { x=294; y=426; }
                    else if(i==10 && j==9) { x=354; y=426; }
                    else if(i==10 && j==10) { x=414; y=426; }
                    else if(i==10 && j==11) { x=474; y=426; }
                    else if(i==10 && j==12) { x=534; y=426; }
                    else if(i==10 && j==13) { x=594; y=426; }
                    else if(i==11 && j==7) { x=204; y=472; }
                    else if(i==11 && j==8) { x=264; y=472; }
                    else if(i==11 && j==9) { x=324; y=472; }
                    else if(i==11 && j==10) { x=384; y=472; }
                    else if(i==11 && j==11) { x=444; y=472; }
                    else if(i==11 && j==12) { x=504; y=472; }
                    else if(i==11 && j==13) { x=564; y=472; }
                    else if(i==12 && j==8) { x=234; y=518; }
                    else if(i==12 && j==9) { x=294; y=518; }
                    else if(i==12 && j==10) { x=354; y=518; }
                    else if(i==12 && j==11) { x=414; y=518; }
                    else if(i==12 && j==12) { x=474; y=518; }
                    else if(i==12 && j==13) { x=534; y=518; }
                    else if(i==13 && j==9) { x=264; y=564; }
                    else if(i==13 && j==10) { x=324; y=564; }
                    else if(i==13 && j==11) { x=384; y=564; }
                    else if(i==13 && j==12) { x=444; y=564; }
                    else if(i==13 && j==13) { x=504; y=564; }

                    else {

                        x = 20-((int) ((i * 30) * Math.cos(Math.toRadians(90))) - (int) ((j * 46) * Math.sin(Math.toRadians(90))));
                        y = ((int) ((j * 46) * Math.cos(Math.toRadians(90))) + (int) ((i * 30) * Math.sin(Math.toRadians(90))));

                    }

                /*if(k!=' ' && !(k=='y'||k=='b'||k=='c'||k=='g'||k=='r'||k=='w')) {
                    //System.out.println(i+" "+j);
                    g2d.setColor(Color.CYAN);
                }*/



                    //&& (k=='y'||k=='b'||k=='c'||k=='g'||k=='r'||k=='w')
                    //if(k!=' ' )
                    //{
                    //g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
                    possibleMovesCirclesBoard[i][j] = new Ellipse2D.Double(x-radius, y-radius, 36, 36);
                    //}
                }
            }






        }

        //Rysowanie
        for(int i=0; i<18; i++)
        {
            for(int j=0; j<18; j++)
            {
                if(ovalBoard[i][j]!=null){
                    char k = charBoard[i][j];
                    if(k == 'b') { g2d.setPaint ( Color.BLUE );  g2d.fill(ovalBoard[i][j]);}
                    else if(k == 'y') {g2d.setPaint ( Color.YELLOW );g2d.fill(ovalBoard[i][j]);}
                    else if(k == 'c') { g2d.setPaint ( Color.BLACK ); g2d.fill(ovalBoard[i][j]);}
                    else if(k == 'g') { g2d.setPaint ( Color.GREEN );g2d.fill(ovalBoard[i][j]);}
                    else if(k == 'r') {g2d.setPaint ( Color.RED );g2d.fill(ovalBoard[i][j]);}
                    else if(k == 'w') { g2d.setPaint ( Color.GRAY );g2d.fill(ovalBoard[i][j]);}
                }



            }
        }
        for (Map.Entry<Field, Field[]> entry : possibleMoves.entrySet()) {
            //System.out.println(entry.getKey() + ":" + entry.getValue());

            Field[] moves = entry.getValue();
            Field field = entry.getKey();

            Field clicked = clickedField;
            //System.out.println("Clicked Field: "+clicked.getX()+" "+clicked.getY());

            if(clicked.getX() == field.getX() && clicked.getY() == field.getY())
            {
                for(int l=0; l<moves.length; l++)
                {
                    int i = moves[l].getX();
                    int j = moves[l].getY();

                    if(possibleMovesCirclesBoard[i][j]!=null ){
                        //System.out.println("I: "+i+"J: "+j);
                        g2d.setColor ( Color.MAGENTA );
                        int x1 = (int)possibleMovesCirclesBoard[i][j].getCenterX();
                        int y1 = (int)possibleMovesCirclesBoard[i][j].getCenterY();
                        int thickness = 5;
                        int height = 46;
                        int width = 46;
                        int radius = 20;

                        Stroke oldStroke = g2d.getStroke();
                        Color oldColor = g2d.getColor();

                        g2d.setColor(Color.MAGENTA);
                        g2d.setStroke(new BasicStroke(thickness));
                        g2d.drawOval((x1 + thickness / 2)-radius, (y1 + thickness / 2)-radius, width - thickness,
                                height - thickness);
                        g2d.setStroke(new BasicStroke(thickness - 2));
                        g2d.drawOval((x1 + thickness / 2)-radius, (y1 + thickness / 2)-radius, width - thickness,
                                height - thickness);

                        g2d.setStroke(oldStroke);
                        g2d.setColor(oldColor);
                    }



                }
            }



        }

        /*
        //Testowanie
        System.out.println("PossibleMovesRightNow");
        for(int i=0; i<possibleMovesCirclesBoard.length; i++)
        {
            for(int j=0; j<possibleMovesCirclesBoard[i].length; j++)
            {
                if(possibleMovesCirclesBoard[i][j]!=null)
                {
                    System.out.println("X: "+i+" Y: "+j);
                }
            }
        }
        */


    }



}
