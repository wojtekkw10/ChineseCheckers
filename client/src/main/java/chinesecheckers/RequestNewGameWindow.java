package chinesecheckers;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class RequestNewGameWindow extends Window {
    private ActionListener actionListener;
    private JFrame mainFrame;
    GameInfo gameInfo = new GameInfo();

    JTextField gameNameTextField = new JTextField(15);
    JFormattedTextField numberOfBotsTextField;


    RequestNewGameWindow(ActionListener actionListener, JFrame frame)
    {
        this.actionListener = actionListener;
        this.mainFrame = frame;
    }

    public void display() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().setBackground( java.awt.Color.DARK_GRAY );
        mainFrame.setLayout(new GridBagLayout());

        System.out.print("CLIENT: Drawing RequestNewGame window\n");

        JLabel gameNameLabel = new JLabel("Enter the name for your game");
        gameNameLabel.setBackground(Color.BLACK);
        gameNameLabel.setForeground(Color.white);
        gameNameLabel.setOpaque(true);
        gameNameLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel numberOfBotsLabel = new JLabel("Enter the number of bots in your game");
        numberOfBotsLabel.setBackground(Color.BLACK);
        numberOfBotsLabel.setForeground(Color.white);
        numberOfBotsLabel.setOpaque(true);
        numberOfBotsLabel.setHorizontalAlignment(JLabel.CENTER);

        //Creating the textField for the number of bots
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(true);
        numberOfBotsTextField = new JFormattedTextField(formatter);

        //Creating the Start Button
        JButton startBTN = new JButton("Start");
        startBTN.addActionListener(actionListener);
        startBTN.setBackground(Color.BLACK);
        startBTN.setForeground(Color.white);

        //Creating the Back Button
        JButton backBTN = new JButton("Back");
        backBTN.addActionListener(actionListener);
        backBTN.setBackground(Color.BLACK);
        backBTN.setForeground(Color.white);

        //Creating the Center Panel
        JPanel panel = new JPanel(new GridLayout(6, 1, 20, 1));
        panel.setBounds(0,0,500 ,400);
        panel.setPreferredSize(new Dimension(300, 250));
        panel.setBackground(Color.DARK_GRAY);

        panel.add(startBTN);
        panel.add(gameNameLabel);
        panel.add(gameNameTextField);
        panel.add(numberOfBotsLabel);
        panel.add(numberOfBotsTextField);


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

    GameInfo getGameInfo()
    {
        System.out.print(gameNameTextField.getText()+"\n");
        gameInfo.name = gameNameTextField.getText();
        System.out.print(numberOfBotsTextField.getText()+"\n");
        String numberOfBotsString = numberOfBotsTextField.getText();
        int numberOfBots = Integer.parseInt(numberOfBotsString);
        gameInfo.numberOfBots = numberOfBots;
        System.out.print("Poddano: "+gameInfo.name+" #Bots: "+ numberOfBots+"\n");
        return gameInfo;
    }
}
