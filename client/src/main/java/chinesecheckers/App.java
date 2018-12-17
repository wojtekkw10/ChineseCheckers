package chinesecheckers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class App
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
        JFrame frame;
        FrameState state = FrameState.MENUWINDOW;
        Server server = new Server();
        ArrayList<GameInfo> gameList;
        volatile boolean isMyMove = false;
        boolean finish = false;
        ServerListener serverListener = new ServerListener();

        JoinWindow joinWindow;
        MenuWindow menuWindow;
        BoardWindow boardWindow;
        Window pauseWindow;
        RequestNewGameWindow requestNewGameWindow;



        MainWindow(JFrame frame)
        {
            this.frame = frame;
            this.frame.addMouseListener(new MouseEventListener());
            this.frame.addComponentListener(new ResizeListener());

            joinWindow = new JoinWindow(this, frame);
            menuWindow = new MenuWindow(this, frame);
            pauseWindow = new PauseWindow(this, frame);
            boardWindow = new BoardWindow(frame);
            requestNewGameWindow = new RequestNewGameWindow(this, frame);

            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new BoardWindowKeyListener());
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("Window closing");
                    server.quit();
                    System.exit(0);
                }
            });


            serverListener.boardWindow = boardWindow;
            serverListener.server = server;
            serverListener.frame = frame;
            serverListener.menuWindow = menuWindow;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if( command.equals( "Create New Game" ))  {
                requestNewGameWindow.display();
                state = FrameState.REQUESTNEWGAMEWINDOW;

            } else if( command.equals( "Join an existing game" ) ) {
                try{ server.connect(frame);}
                catch (IOException ex){System.out.print("CLIENT: ERROR: Couldn't connect to the server\n");}
                gameList = server.downloadAllGames();
                joinWindow.setIdList(gameList);
                joinWindow.display();
                state = FrameState.JOINWINDOW;
            }

            else if( command.equals( "Back" ) ) {
                menuWindow.display();
                state = FrameState.MENUWINDOW;
            }

            else if( command.equals( "Back to the Game" ) )  {
                boardWindow.display();
                state = FrameState.BOARDWINDOW;
            }

            else if( command.equals( "Back to Menu" ) ) {
                menuWindow.display();
                state = FrameState.MENUWINDOW;

                server.quit();

            }

            else if( command.equals( "Skip" ) )  {
                server.skip();
            }

            else if( command.startsWith( "ID" ) )  {
                int i = new Scanner(command).useDelimiter("\\D+").nextInt();

                try{ server.connect(frame);}
                catch (IOException ex){System.out.print("CLIENT: ERROR: Couldn't connect to the server\n");}

                for(int k=0; k<gameList.size(); k++)
                {
                    if(gameList.get(k).id==i) server.joinGame(i);
                }

                Packet packet = server.downloadBoardState();
                boardWindow.charBoard = packet.board;
                boardWindow.possibleMoves = packet.possibleMoves;
                boardWindow.isMyMove = packet.isMyMove;
                boardWindow.whoseTurnIsIt = packet.whoseTurnIsIt;
                boardWindow.yourColor = packet.yourColor;
                isMyMove = packet.isMyMove;

                System.out.println("IsMyMove: "+isMyMove);

                boardWindow.display();
                state = FrameState.BOARDWINDOW;

                ServerListener newServerListener = new ServerListener();
                newServerListener.server = serverListener.server;
                newServerListener.boardWindow = serverListener.boardWindow;
                newServerListener.menuWindow = serverListener.menuWindow;
                newServerListener.frame = this.frame;
                serverListener = newServerListener;
                serverListener.start();
            }
            else if( command.equals( "Start" ))  {
                try{ server.connect(frame);}
                catch (IOException ex){System.out.print("CLIENT: ERROR: Couldn't connect to the server\n");}


                GameInfo info = requestNewGameWindow.getGameInfo();
                server.requestNewGame(info.name, info.numberOfBots, info.maxNumberOfPlayers);

                Packet packet = server.downloadBoardState();
                boardWindow.charBoard = packet.board.clone();
                boardWindow.possibleMoves = packet.possibleMoves;
                isMyMove = packet.isMyMove;
                boardWindow.isMyMove = packet.isMyMove;
                boardWindow.whoseTurnIsIt = packet.whoseTurnIsIt;
                boardWindow.yourColor = packet.yourColor;
                System.out.println("IsMyMove: "+isMyMove);

                boardWindow.display();
                state = FrameState.BOARDWINDOW;
                System.out.print("CLIENT: Start Button has been clicked\n");

                ServerListener newServerListener = new ServerListener();
                newServerListener.server = serverListener.server;
                newServerListener.boardWindow = serverListener.boardWindow;
                newServerListener.menuWindow = serverListener.menuWindow;
                newServerListener.frame = this.frame;
                serverListener = newServerListener;
                serverListener.start();

            } else {
                System.exit(0);
            }

        }

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

        //Repaint the window when it's moved
        class ResizeListener implements ComponentListener {
            public void componentResized(ComponentEvent e) {
                if(state.equals(FrameState.BOARDWINDOW)) boardWindow.display();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                if(state.equals(FrameState.BOARDWINDOW)) boardWindow.display();
            }

            @Override
            public void componentShown(ComponentEvent e) { }

            @Override
            public void componentHidden(ComponentEvent e) { }

        }

        public class MouseEventListener implements MouseListener {

            public void mousePressed(MouseEvent e) { }

            public void mouseReleased(MouseEvent e) { }

            public void mouseEntered(MouseEvent e) { }

            public void mouseExited(MouseEvent e) { }

            public void mouseClicked(MouseEvent e) {

                if(boardWindow.isMyMove && state==FrameState.BOARDWINDOW)
                {
                    for(int i=0; i<18; i++)
                    {
                        for(int j=0; j<18; j++)
                        {
                            if(boardWindow.ovalBoard[i][j]!=null && boardWindow.charBoard[i][j]!=null)
                            {
                                //Sprawdzanie ktory przycisk zostal nacisniety
                                if ( boardWindow.ovalBoard[i][j].contains(e.getX()-108, e.getY()-30) ) {

                                    Field clicked = new Field();
                                    clicked.setX(i);
                                    clicked.setY(j);

                                    if(boardWindow.isPossibleMoveField(clicked))
                                    {
                                        Move move = new Move();
                                        move.oldField = boardWindow.clickedField;
                                        move.newField = clicked;
                                        server.uploadMove(move);

                                        Packet packet = server.downloadBoardState();
                                        boardWindow.charBoard = packet.board.clone();
                                        boardWindow.possibleMoves = packet.possibleMoves;
                                        isMyMove = packet.isMyMove;
                                        boardWindow.whoseTurnIsIt = packet.whoseTurnIsIt;
                                        boardWindow.yourColor = packet.yourColor;

                                    }

                                    //Ustaw do boardWindow zeby moglo wyrysowac possibleMoves
                                    boardWindow.clickedField.setX(i);
                                    boardWindow.clickedField.setY(j);

                                    boardWindow.display();
                                }
                            }
                        }
                    }
                }
            }
        }

        void start()
        {
            menuWindow.display();
        }

    }


}
