package chinesecheckers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class App implements WindowListener,ActionListener
{



    public static void main( String[] args ) {
        JFrame frame = new JFrame("Chinese Checkers");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize(new Dimension(1000,800));

        Server server = new Server();
        Board board = new RegularBoard();

        Window joinWindow = new JoinWindow(new JoinListener());
        Window menuWindow = new MenuWindow(new MenuListener());
        Window boardWindow = new BoardWindow(board);

        menuWindow.display(frame);

    }
    public static class JoinListener implements ActionListener{ //musi byc static albo w nowym pliku
        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }
    public static class MenuListener implements ActionListener{ //musi byc static albo w nowym pliku
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if( command.equals( "Create New Game" ))  {
                System.out.print("Create New Game Button has been clicked\n");
            } else if( command.equals( "Join an existing game" ) )  {
                System.out.print("Join Button has been clicked\n");
            } else {
                System.exit(0);
            }

        }
    }

    public void actionPerformed(ActionEvent e) {
        //TODO: obsluga inputu + logika + server
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}




}
