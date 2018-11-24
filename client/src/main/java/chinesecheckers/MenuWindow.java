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
    private ArrayList<JButton> buttons = new ArrayList<JButton>();

    MenuWindow(ActionListener actionListener)
    {
        this.actionListener = actionListener;
    }

    //TODO: zaimplentowac wyglad okna + jego wyswietlanie (trzeba wyczyscic okno i namalowac od nowa)

    public void display(JFrame mainFrame) {
        GridBagLayout layout = new GridBagLayout();
        mainFrame.setLayout(layout);
        mainFrame.getContentPane().setBackground( Color.DARK_GRAY );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;

        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(200,200));
        p.setBackground(Color.DARK_GRAY);
        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(200,200));
        p2.setBackground(Color.DARK_GRAY);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainFrame.add(p, gbc);
        gbc.gridx = 1;
        gbc.gridy = 20;
        mainFrame.add(p2, gbc);

        gbc.ipady = 40;
        gbc.ipadx = 100;


        //gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton createNewGameBTN = new JButton("Create New Game");
        JButton joinGameBTN = new JButton("Join an existing game");
        JButton exitBTN = new JButton("Exit");
        createNewGameBTN.setPreferredSize(new Dimension(200,20));
        createNewGameBTN.addActionListener(actionListener);
        joinGameBTN.addActionListener(actionListener);
        exitBTN.addActionListener(actionListener);

        createNewGameBTN.setBackground(Color.BLACK);
        createNewGameBTN.setForeground(Color.white);

        joinGameBTN.setBackground(Color.BLACK);
        joinGameBTN.setForeground(Color.white);

        exitBTN.setBackground(Color.BLACK);
        exitBTN.setForeground(Color.white);

        gbc.gridx = 1;
        gbc.gridy = 3;
        mainFrame.add(createNewGameBTN, gbc);

        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainFrame.add(joinGameBTN, gbc);

        //gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.ipady = 100;
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainFrame.add(new JTextField(15), gbc);

        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 6;
        mainFrame.add(exitBTN, gbc);
        mainFrame.setVisible(true);

        //TODO: chyba gridlayout bedzie lepszy
    }

}
