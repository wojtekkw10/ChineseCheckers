package chinesecheckers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

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

        //Creating the Center Panel
        JPanel panel = new JPanel(new GridLayout(2+idList.size(), 1, 20, 1));
        panel.setBounds(0,0,500 ,100+50*idList.size());
        panel.setPreferredSize(new Dimension(300, 100+50*idList.size()));
        panel.setBackground(Color.DARK_GRAY);

        for(int i=0; i<idList.size(); i++)
        {
            //Creating game buttons
            JButton gameBTN = new JButton("Game "+i);
            gameBTN.addActionListener(actionListener);
            gameBTN.setBackground(Color.BLACK);
            gameBTN.setForeground(Color.white);
            panel.add(gameBTN);
        }

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
