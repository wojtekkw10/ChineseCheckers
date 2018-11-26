package chinesecheckers;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;

public class JoinWindow extends Window {
    private ActionListener actionListener;
    private JFrame mainFrame;

    JoinWindow(ActionListener actionListener, JFrame frame)
    {
        this.actionListener = actionListener;
        this.mainFrame = frame;
    }

    //TODO: zaimplentowac wyglad okna + jego wyswietlanie (trzeba wyczyscic okno i namalowac od nowa)
    public void display() {
        /*
        mainFrame.getContentPane().removeAll();

        GridBagLayout layout = new GridBagLayout();
        mainFrame.setLayout(layout);
        mainFrame.getContentPane().setBackground( java.awt.Color.DARK_GRAY );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;

        JPanel upperPanel = new JPanel();
        upperPanel.setPreferredSize(new Dimension(200,200));
        upperPanel.setBackground(java.awt.Color.DARK_GRAY);
        JPanel lowerPanel = new JPanel();
        lowerPanel.setPreferredSize(new Dimension(200,200));
        lowerPanel.setBackground(java.awt.Color.DARK_GRAY);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainFrame.add(upperPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 20;
        mainFrame.add(lowerPanel, gbc);

        gbc.ipady = 40;
        gbc.ipadx = 100;

        JButton backButton = new JButton("Back");

        backButton.addActionListener(actionListener);

        backButton.setPreferredSize(new Dimension(200,20));

        backButton.setBackground(java.awt.Color.BLACK);

        backButton.setForeground(java.awt.Color.white);

        JLabel gamesLabel = new JLabel("Available Games");
        gamesLabel.setPreferredSize(new Dimension(200, 10));
        gamesLabel.setHorizontalAlignment(JTextField.CENTER);
        gamesLabel.setForeground(Color.white);

        gbc.gridx = 1;
        gbc.gridy = 2;
        mainFrame.add(gamesLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        mainFrame.add(backButton, gbc);

        mainFrame.revalidate();
        mainFrame.repaint();

        mainFrame.setVisible(true);
        */

        //TODO: chyba gridlayout bedzie lepszy

        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().setBackground( java.awt.Color.DARK_GRAY );
        mainFrame.setLayout(new GridBagLayout());

        System.out.print("Drawing Join window");

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
