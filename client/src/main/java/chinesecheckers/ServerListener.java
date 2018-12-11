package chinesecheckers;

import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.*;

public class ServerListener extends Thread {
    public Server server;
    public BoardWindow boardWindow;
    static volatile public boolean isMyMove;
    public boolean finish;
    JFrame frame;

    public void run()
    {
        while(true)
        {
            Packet packet = server.listen();
            boardWindow.charBoard = packet.board;
            boardWindow.possibleMoves = packet.possibleMoves;
            isMyMove = packet.isMyMove;
            boardWindow.isMyMove = packet.isMyMove;
            System.out.println("ISMYMOVE: "+isMyMove);
            if(finish) break;
            boardWindow.display();
            boardWindow.repaint();
            //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.COMPONENT_MOVED));

        }
    }

}
