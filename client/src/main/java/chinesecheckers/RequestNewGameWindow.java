package chinesecheckers;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestNewGameWindow extends Window {
    private ActionListener actionListener;
    private JFrame mainFrame;
    private GameInfo gameInfo = new GameInfo();

    private JTextField gameNameTextField = new JTextField();
    private JTextField numberOfBotsTextField = new JTextField();
    private JTextField numberOfPlayersTextField = new JTextField();


    RequestNewGameWindow(ActionListener actionListener, JFrame frame)
    {
        this.actionListener = actionListener;
        this.mainFrame = frame;

        ((AbstractDocument) numberOfBotsTextField.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern regEx = Pattern.compile("\\d+");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }
                int number = Integer.parseInt(text);
                if(number<0 || number>5 || numberOfBotsTextField.getText().length()>0) return;
                super.replace(fb, offset, length, text, attrs);
            }
        });
        ((AbstractDocument) numberOfPlayersTextField.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern regEx = Pattern.compile("\\d+");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }
                int number = Integer.parseInt(text);
                if(number<2 || number>6 || numberOfPlayersTextField.getText().length()>0) return;
                super.replace(fb, offset, length, text, attrs);
            }
        });
    }

    public void display() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().setBackground( java.awt.Color.DARK_GRAY );
        mainFrame.setLayout(new GridBagLayout());

        System.out.println("CLIENT: Drawing RequestNewGame window");

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

        //Number of players label
        JLabel numberOfPlayersLabel = new JLabel("Enter the number of players (including bots)");
        numberOfPlayersLabel.setBackground(Color.BLACK);
        numberOfPlayersLabel.setForeground(Color.white);
        numberOfPlayersLabel.setOpaque(true);
        numberOfPlayersLabel.setHorizontalAlignment(JLabel.CENTER);

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
        JPanel panel = new JPanel(new GridLayout(8, 1, 20, 1));
        panel.setBounds(0,0,500 ,500);
        panel.setPreferredSize(new Dimension(300, 350));
        panel.setBackground(Color.DARK_GRAY);

        panel.add(backBTN);
        panel.add(gameNameLabel);
        panel.add(gameNameTextField);
        panel.add(numberOfPlayersLabel);
        panel.add(numberOfPlayersTextField);
        panel.add(numberOfBotsLabel);
        panel.add(numberOfBotsTextField);
        panel.add(startBTN);


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
        gameInfo.maxNumberOfPlayers = Integer.parseInt(numberOfPlayersTextField.getText());
        System.out.print(numberOfBotsTextField.getText()+"\n");
        String numberOfBotsString = numberOfBotsTextField.getText();
        int numberOfBots = Integer.parseInt(numberOfBotsString);
        gameInfo.numberOfBots = numberOfBots;
        System.out.print("Name: "+gameInfo.name+" #Bots: "+ numberOfBots+"\n");
        return gameInfo;
    }
}
