package chinesecheckers;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JoinWindow extends Window {
    private ActionListener actionListener;
    private JFrame mainFrame;

    JoinWindow(ActionListener actionListener, JFrame frame)
    {
        this.actionListener = actionListener;
        this.mainFrame = frame;
    }

    public void setIdList(ArrayList<GameInfo> gameList) {
        this.gameList = gameList;
    }

    public void display() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().setBackground( java.awt.Color.DARK_GRAY );
        mainFrame.setLayout(new GridBagLayout());

        //Creating the Back Button
        JButton backBTN = new JButton("Back");
        backBTN.addActionListener(actionListener);
        backBTN.setBackground(Color.BLACK);
        backBTN.setForeground(Color.white);

        //Creating the Center Panel
        JPanel panel = new JPanel(new GridLayout(2+gameList.size(), 1, 20, 1));
        panel.setBounds(0,0,500 ,100+50*gameList.size());
        panel.setPreferredSize(new Dimension(300, 100+50*gameList.size()));
        panel.setBackground(Color.DARK_GRAY);

        for(int i=0; i<gameList.size(); i++)
        {
            //Creating game buttons
            GameInfo gameInfo = gameList.get(i);

            JButton gameBTN = new JButton("ID: "+gameInfo.id+" "
                    +gameInfo.name+" Bots: "+gameInfo.numberOfBots+" Players: "+gameInfo.currentNumberOfPlayers+"/"+
                    gameInfo.maxNumberOfPlayers);
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
