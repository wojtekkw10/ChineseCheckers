package chinesecheckers;

//import sun.awt.resources.awt;

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

    //Creating the textField for Username
    JTextField usernameTextField = new JTextField(15);
    String username;

    MenuWindow(ActionListener actionListener, JFrame frame)
    {
        this.actionListener = actionListener;
        this.mainFrame = frame;
    }

    public void display() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().setBackground( java.awt.Color.DARK_GRAY );
        mainFrame.setLayout(new GridBagLayout());

        System.out.print("CLIENT: Drawing Menu window\n");

        //Creating the Create New Game Button
        JButton createNewGameBTN = new JButton("Create New Game");
        createNewGameBTN.addActionListener(actionListener);
        createNewGameBTN.setBackground(Color.BLACK);
        createNewGameBTN.setForeground(Color.white);

        //Creating the Join Game Button
        JButton joinGameBTN = new JButton("Join an existing game");
        joinGameBTN.addActionListener(actionListener);
        joinGameBTN.setBackground(Color.BLACK);
        joinGameBTN.setForeground(Color.white);

        //Creating the exit button
        JButton exitBTN = new JButton("Exit");
        exitBTN.addActionListener(actionListener);
        exitBTN.setBackground(Color.BLACK);
        exitBTN.setForeground(Color.white);

        //Creating the Username Label
        JLabel username = new JLabel("Username");
        username.setBackground(Color.BLACK);
        username.setForeground(Color.white);
        username.setOpaque(true);
        username.setHorizontalAlignment(JLabel.CENTER);

        //Creating the title Label
        JLabel title = new JLabel("Chinese Checkers");
        title.setBackground(Color.DARK_GRAY);
        title.setForeground(Color.white);
        title.setOpaque(true);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(10,10,50,10));
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));

        //Creating the Center Panel
        JPanel panel = new JPanel(new GridLayout(5, 1, 20, 1));
        panel.setBounds(0,0,500 ,500);
        panel.setPreferredSize(new Dimension(300, 200));
        panel.setBackground(Color.DARK_GRAY);
        panel.add(username);
        panel.add(usernameTextField);
        panel.add(createNewGameBTN);
        panel.add(joinGameBTN);
        panel.add(exitBTN);

        //Creating the main (full) window
        JPanel main = new JPanel();
        main.setBounds(0,0,1000,800);
        main.setLayout(new BorderLayout());
        main.add(title, BorderLayout.NORTH);
        main.add(panel, BorderLayout.CENTER);

        mainFrame.add(main);

        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);

    }

    String getUsername()
    {
        return usernameTextField.getText();
    }

}
