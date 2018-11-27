package chinesecheckers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;


//TODO: kominikacja na bazie: request od klienta - response od servera
public class App //implements WindowListener,ActionListener
{

    public static void main( String[] args ) {
        JFrame frame = new JFrame("Chinese Checkers");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize(new Dimension(1000,800));
        frame.setResizable(false);

        MainWindow mainWindow = new MainWindow(frame);
        mainWindow.start();

    }

    public static class MainWindow extends JFrame implements ActionListener{
        Board board = new RegularBoard();
        JFrame frame;
        FrameState state = FrameState.MENUWINDOW;
        Server server = new Server();
        ArrayList<Id> idList;

        Window joinWindow;
        Window menuWindow;
        Window boardWindow;
        Window pauseWindow;

        MainWindow(JFrame frame)
        {
            this.frame = frame;
            joinWindow = new JoinWindow(this, frame);
            menuWindow = new MenuWindow(this, frame);
            pauseWindow = new PauseWindow(this, frame);
            boardWindow = new BoardWindow(board, this, frame);
            frame.addComponentListener(new ResizeListener());
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new BoardWindowKeyListener());
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            //Create New Game
            if( command.equals( "Create New Game" ))  {
                try{ server.connect(frame);}
                catch (IOException ex){System.out.print("CLIENT: ERROR: Couldn't connect to the server\n");}
                server.requestNewGame();
                boardWindow.display();
                state = FrameState.BOARDWINDOW;
                System.out.print("CLIENT: Create New Game Button has been clicked\n");

            //Show available games
            } else if( command.equals( "Join an existing game" ) ) {
                try{ server.connect(frame);}
                catch (IOException ex){System.out.print("CLIENT: ERROR: Couldn't connect to the server\n");}
                idList = server.downloadAllGames();
                joinWindow.setIdList(idList);
                joinWindow.display();
                state = FrameState.JOINWINDOW;
                System.out.print("CLIENT: Join Button has been clicked\n");
            }

            //Go back
            else if( command.equals( "Back" ) ) {
                menuWindow.display();
                state = FrameState.MENUWINDOW;
                System.out.print("CLIENT: Back Button has been clicked\n");
            }

            //Go back to the game
            else if( command.equals( "Back to the Game" ) )  {
                boardWindow.display();
                state = FrameState.BOARDWINDOW;
                System.out.print("CLIENT: Back to the game Button has been clicked\n");
            }

            //Go back to menu
            else if( command.equals( "Back to Menu" ) ) {
                menuWindow.display();
                state = FrameState.MENUWINDOW;
                System.out.print("CLIENT: Back to Menu Button has been clicked\n");
            }

            //Skip this round
            else if( command.equals( "Skip" ) )  { //bede uzywal do testowania laczenia sie z serverem
                    System.out.print(server.getEmptyString()+"\n");
                    System.out.print("CLIENT: Skip Button has been clicked1\n");
            }

            //Join selected game
            else if( command.startsWith( "Game" ) )  {
                for(int i =0; i<idList.size(); i++)
                {
                    if(command.endsWith(idList.get(i).id+""))
                    {
                        try{ server.connect(frame);}
                        catch (IOException ex){System.out.print("CLIENT: ERROR: Couldn't connect to the server\n");}
                        server.joinGame(idList.get(i).id);

                    }
                }
            } else {
                System.exit(0);
            }

        }

        //Show pauseWindow when Escape Key pressed
        class BoardWindowKeyListener implements KeyEventDispatcher {
            @Override
            public boolean dispatchKeyEvent(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    pauseWindow.display();
                    state = FrameState.PAUSEWINDOW;

                }
                return false;
            }
        }

        //Reapint the window when it's moved
        class ResizeListener implements ComponentListener {
            public void componentResized(ComponentEvent e) {
                if(state.equals(FrameState.BOARDWINDOW)) boardWindow.display();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                if(state.equals(FrameState.BOARDWINDOW)) boardWindow.display();
            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }

        }

        void start()
        {
            menuWindow.display();
        }

    }


}
