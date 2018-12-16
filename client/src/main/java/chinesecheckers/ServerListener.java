package chinesecheckers;

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
            Command command = new Command();
            if(server!=null) command = server.listen();
            try{ if(command.commandType==CommandType.QUIT) this.join(); break;  } catch(InterruptedException e){}
            Packet packet = Packet.fromJSON(command.content);
            boardWindow.charBoard = packet.board;
            boardWindow.possibleMoves = packet.possibleMoves;
            boardWindow.whoseTurnIsIt = packet.whoseTurnIsIt;
            isMyMove = packet.isMyMove;
            boardWindow.isMyMove = packet.isMyMove;
            boardWindow.yourColor = packet.yourColor;
            System.out.println("Current Player Color: "+packet.whoseTurnIsIt);
            System.out.println("ISMYMOVE: "+isMyMove);
            if(finish) break;
            boardWindow.display();
            boardWindow.repaint();

        }
    }

}
