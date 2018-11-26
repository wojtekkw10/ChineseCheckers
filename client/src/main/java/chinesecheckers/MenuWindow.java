package chinesecheckers;

import sun.awt.resources.awt;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuWindow extends Window{
    private ActionListener actionListener;
    private JFrame mainFrame;
    private ArrayList<JButton> buttons = new ArrayList<JButton>();

    MenuWindow(ActionListener actionListener, JFrame frame)
    {
        this.actionListener = actionListener;
        this.mainFrame = frame;
    }

    //TODO: zaimplentowac wyglad okna + jego wyswietlanie (trzeba wyczyscic okno i namalowac od nowa)

    public void display() {
        mainFrame.getContentPane().removeAll();

        GridBagLayout layout = new GridBagLayout();
        mainFrame.setLayout(layout);
        mainFrame.getContentPane().setBackground( Color.DARK_GRAY );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;

        JPanel upperPanel = new JPanel();
        upperPanel.setPreferredSize(new Dimension(200,200));
        upperPanel.setBackground(Color.DARK_GRAY);
        JPanel lowerPanel = new JPanel();
        lowerPanel.setPreferredSize(new Dimension(200,200));
        lowerPanel.setBackground(Color.DARK_GRAY);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainFrame.add(upperPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 20;
        mainFrame.add(lowerPanel, gbc);

        gbc.ipady = 40;
        gbc.ipadx = 100;

        JButton createNewGameBTN = new JButton("Create New Game");
        JButton joinGameBTN = new JButton("Join an existing game");
        JButton exitBTN = new JButton("Exit");

        createNewGameBTN.addActionListener(actionListener);
        joinGameBTN.addActionListener(actionListener);
        exitBTN.addActionListener(actionListener);

        createNewGameBTN.setPreferredSize(new Dimension(200,20));
        joinGameBTN.setPreferredSize(new Dimension(200,20));
        exitBTN.setPreferredSize(new Dimension(200,20));

        createNewGameBTN.setBackground(Color.BLACK);
        joinGameBTN.setBackground(Color.BLACK);
        exitBTN.setBackground(Color.BLACK);

        createNewGameBTN.setForeground(Color.white);
        joinGameBTN.setForeground(Color.white);
        exitBTN.setForeground(Color.white);

        joinGameBTN.setBackground(Color.BLACK);
        joinGameBTN.setForeground(Color.white);

        exitBTN.setBackground(Color.BLACK);
        exitBTN.setForeground(Color.white);

        JTextField usernameTextField = new JTextField(15);
        usernameTextField.setPreferredSize(new Dimension(200, 20));

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setPreferredSize(new Dimension(200, 10));
        usernameLabel.setHorizontalAlignment(JTextField.CENTER);
        usernameLabel.setForeground(Color.white);

        gbc.gridx = 1;
        gbc.gridy = 2;
        mainFrame.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        mainFrame.add(usernameTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        mainFrame.add(createNewGameBTN, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        mainFrame.add(joinGameBTN, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        mainFrame.add(exitBTN, gbc);

        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);

        //TODO: chyba gridlayout bedzie lepszy
    }

}
