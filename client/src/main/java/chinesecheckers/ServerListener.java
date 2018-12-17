package chinesecheckers;

import javax.swing.*;

public class ServerListener extends Thread {
    public Server server;
    public BoardWindow boardWindow;
    public MenuWindow menuWindow;
    JFrame frame;

    public void run()
    {
        while(true)
        {
            Command command = server.listen();
            try{ if(command.commandType==CommandType.QUIT) { menuWindow.display(); this.join(); } } catch(InterruptedException e){}

            Packet packet = Packet.fromJSON(command.content);
            boardWindow.charBoard = packet.board;
            boardWindow.possibleMoves = packet.possibleMoves;
            boardWindow.whoseTurnIsIt = packet.whoseTurnIsIt;
            boardWindow.isMyMove = packet.isMyMove;
            boardWindow.yourColor = packet.yourColor;
            System.out.println("Current Player Color: "+packet.whoseTurnIsIt);
            boardWindow.display();
            boardWindow.repaint();

        }
    }

}
