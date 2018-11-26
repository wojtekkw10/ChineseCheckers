package chinesecheckers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

//TODO: README - Color nie wlasny, a z swing
//TODO: seperate JFrame for each window setup

public class App //implements WindowListener,ActionListener
{

    public static void main( String[] args ) {
        JFrame frame = new JFrame("Chinese Checkers");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize(new Dimension(1000,800));

        Server server = new Server();
        Board board = new RegularBoard();

        MainWindow mainWindow = new MainWindow(frame);
        mainWindow.start();

    }

    public static class MainWindow extends JFrame implements ActionListener {
        Board board = new RegularBoard();
        JFrame frame;

        Window joinWindow;
        Window menuWindow;
        Window boardWindow;

        MainWindow(JFrame frame)
        {
            this.frame = frame;
            joinWindow = new JoinWindow(this, frame);
            menuWindow = new MenuWindow(this, frame);
            boardWindow = new BoardWindow(board, this, frame);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if( command.equals( "Create New Game" ))  {
                //TODO: nie mozemy wykonywac boardwindow.display bo musimy nadpisac funkcje paint ktora jest w Jframe
                //boardWindow.display();
                this.repaint();
                System.out.print("Create New Game Button has been clicked\n");
            } else if( command.equals( "Join an existing game" ) ) {
                joinWindow.display();
                System.out.print("Join Button has been clicked\n");
            }
            else if( command.equals( "Back" ) )  {
                menuWindow.display();
                System.out.print("Back Button has been clicked\n");
            } else {
                System.exit(0);
            }

        }

        //TODO: w JFrame chyba nie mozna bezposrednio rysowac, trzeba JPanel
        @Override
        public void paint(Graphics g) {
            frame.getContentPane().removeAll();
            System.out.print("LOL\n");

            board.getSampleBoard();
            ArrayList<PlayingSpace> PS = board.getBoard();

            for(int i=0; i<PS.size(); i++)
            {
                int x=PS.get(i).getX();
                int y=PS.get(i).getY();
                int radius = 10;

                g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
            }

            //super.paint(g);
        }


        void start()
        {
            menuWindow.display();
        }

    }


}
