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

        //TODO: chyba gridlayout bedzie lepszy
    }

}
