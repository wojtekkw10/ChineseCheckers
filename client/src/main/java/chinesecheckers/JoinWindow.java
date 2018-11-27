package chinesecheckers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class JoinWindow extends Window {
    private ActionListener actionListener;
    private JFrame mainFrame;


    JoinWindow(ActionListener actionListener, JFrame frame)
    {
        this.actionListener = actionListener;
        this.mainFrame = frame;
    }

    public void display() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().setBackground( java.awt.Color.DARK_GRAY );
        mainFrame.setLayout(new GridBagLayout());

        System.out.print("CLIENT: Drawing Join window\n");

        //Creating the Back Button
        JButton backBTN = new JButton("Back");
        backBTN.addActionListener(actionListener);
        backBTN.setBackground(Color.BLACK);
        backBTN.setForeground(Color.white);

        //Creating the button
        JButton game1BTN = new JButton("Game1");
        game1BTN.addActionListener(actionListener);
        game1BTN.setBackground(Color.BLACK);
        game1BTN.setForeground(Color.white);

        //Creating the button
        JButton game2BTN = new JButton("Game2");
        game2BTN.addActionListener(actionListener);
        game2BTN.setBackground(Color.BLACK);
        game2BTN.setForeground(Color.white);

        //Creating the Center Panel
        JPanel panel = new JPanel(new GridLayout(5, 1, 20, 1));
        panel.setBounds(0,0,500 ,500);
        panel.setPreferredSize(new Dimension(300, 200));
        panel.setBackground(Color.DARK_GRAY);
        panel.add(game1BTN);
        panel.add(game2BTN);
        panel.add(backBTN);

        //Creating the main (full) window
        JPanel main = new JPanel();
        main.setBounds(0,0,1000,800);
        main.setLayout(new BorderLayout());
        main.add(panel, BorderLayout.CENTER);

        mainFrame.add(main);

        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

}
