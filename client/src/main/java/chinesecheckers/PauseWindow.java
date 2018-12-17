package chinesecheckers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class PauseWindow extends Window {
    private ActionListener actionListener;
    private JFrame mainFrame;

    PauseWindow(ActionListener actionListener, JFrame frame)
    {
        this.actionListener = actionListener;
        this.mainFrame = frame;
    }

    public void display() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().setBackground( java.awt.Color.DARK_GRAY );
        mainFrame.setLayout(new GridBagLayout());

        //Creating the Back Button
        JButton backBTN = new JButton("Back to the Game");
        backBTN.addActionListener(actionListener);
        backBTN.setBackground(Color.BLACK);
        backBTN.setForeground(Color.white);

        //Creating the Back Button
        JButton back_to_manuBTN = new JButton("Back to Menu");
        back_to_manuBTN.addActionListener(actionListener);
        back_to_manuBTN.setBackground(Color.BLACK);
        back_to_manuBTN.setForeground(Color.white);

        //Creating the button
        JButton skipBTN = new JButton("Skip");
        skipBTN.addActionListener(actionListener);
        skipBTN.setBackground(Color.BLACK);
        skipBTN.setForeground(Color.white);


        //Creating the Center Panel
        JPanel panel = new JPanel(new GridLayout(5, 1, 20, 1));
        panel.setBounds(0,0,500 ,500);
        panel.setPreferredSize(new Dimension(300, 200));
        panel.setBackground(Color.DARK_GRAY);
        panel.add(skipBTN);
        panel.add(backBTN);
        panel.add(back_to_manuBTN);

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
