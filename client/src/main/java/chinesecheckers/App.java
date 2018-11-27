package chinesecheckers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class App //implements WindowListener,ActionListener
{

    public static void main( String[] args ) {
        JFrame frame = new JFrame("Chinese Checkers");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize(new Dimension(1000,800));
        frame.setResizable(false);

        Server server = new Server();

        MainWindow mainWindow = new MainWindow(frame);
        mainWindow.start();

    }

    public static class MainWindow extends JFrame implements ActionListener{
        Board board = new RegularBoard();
        JFrame frame;
        FrameState state = FrameState.MENUWINDOW;

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
            //frame.addKeyListener(new BoardWindowKeyListener());
            //boardWindow.addKeyListener(new BoardWindowKeyListener());
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if( command.equals( "Create New Game" ))  {
                boardWindow.display();
                state = FrameState.BOARDWINDOW;
                System.out.print("Create New Game Button has been clicked\n");
            } else if( command.equals( "Join an existing game" ) ) {
                joinWindow.display();
                state = FrameState.JOINWINDOW;
                System.out.print("Join Button has been clicked\n");
            }
            else if( command.equals( "Back" ) ) {
                menuWindow.display();
                state = FrameState.MENUWINDOW;
                System.out.print("Back Button has been clicked\n");
            }
            else if( command.equals( "Back to the Game" ) )  {
                    boardWindow.display();
                    state = FrameState.BOARDWINDOW;
                    System.out.print("Back Button has been clicked\n");
            }
            else if( command.equals( "Back to Menu" ) )  {
                menuWindow.display();
                state = FrameState.MENUWINDOW;
                System.out.print("Back Button has been clicked\n");
            } else {
                System.exit(0);
            }

        }

        class BoardWindowKeyListener implements KeyEventDispatcher {
            /** Handle the key typed event from the text field. */
            @Override
            public boolean dispatchKeyEvent(final KeyEvent e) {
                System.out.print("hdhd\n");
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    pauseWindow.display();
                    state = FrameState.PAUSEWINDOW;


                }
                return false;
            }

            public void keyTyped(KeyEvent e) {

            }

            /** Handle the key-pressed event from the text field. */
            public void keyPressed(KeyEvent e) {

            }

            /** Handle the key-released event from the text field. */
            public void keyReleased(KeyEvent e) {
                System.out.print("Key has been pressed");
                if(e.getKeyCode()== KeyEvent.VK_ESCAPE) pauseWindow.display();
            }
        }


        class ResizeListener implements ComponentListener {
            public void componentResized(ComponentEvent e) {
                if(state.equals(FrameState.BOARDWINDOW)) boardWindow.display();
                System.out.print("Resizing\n");
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
