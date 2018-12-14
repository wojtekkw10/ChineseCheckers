package chinesecheckers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class App
{
    public static void main( String[] args ) {

        ArrayList<Game> listOfGames = new ArrayList<Game>();

        System.out.println("The Chinese Checkers server is running.");
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(9898);
        } catch (IOException e) {
            System.out.print("Cannot create socket");
        }

        try {
            while (true) {
                System.out.println("Waiting for player...");
                Socket newPlayerSocket = listener.accept();
                System.out.println("Client connected");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(newPlayerSocket.getInputStream()));
                System.out.println("Downloading request");

                String commandAsJSON = in.readLine();
                Command command = Command.fromJSON(commandAsJSON);
                Packet packet = Packet.fromJSON(command.content);

                System.out.println("Downloaded request");
                PrintWriter output = new PrintWriter(newPlayerSocket.getOutputStream(), true);

                switch(command.commandType) {
                    case REQUEST_ALL_GAMES: {
                        System.out.print("AllGamesRequested");
                        StringBuffer gameList = new StringBuffer();
                        for (int i = 0; i < listOfGames.size(); i++) {
                            gameList.append(i);
                            gameList.append(" ");
                            gameList.append(listOfGames.get(i).name);
                            gameList.append(" ");
                            gameList.append(listOfGames.get(i).numberOfBots);
                            gameList.append(" ");
                            gameList.append(listOfGames.get(i).getNumberOfPlayers());
                            gameList.append(" ");
                            gameList.append(listOfGames.get(i).numberOfPlayers);
                            gameList.append(" ");
                        }
                        output.println(gameList.toString());
                        newPlayerSocket.close();
                        break;
                    }
                    case REQUEST_NEW_GAME: {
                        System.out.print("New Game Requested");

                        listOfGames.add(new Game(packet.gameName, packet.numberOfBots));
                        int lastGameIndex = listOfGames.size()-1;
                        listOfGames.get(listOfGames.size() - 1).addPlayer(listOfGames.get(lastGameIndex).new Player(newPlayerSocket, packet.username, 'w'));
                        System.out.print("New game has been added. Name: " + packet.gameName + " Number of Bots: " + packet.numberOfBots + "\n");
                        System.out.print("Number of games: " + listOfGames.size() + "\n");
                        listOfGames.get(listOfGames.size()-1).numberOfBots = packet.numberOfBots;
                        listOfGames.get(listOfGames.size()-1).setMaxNumberOfPlayers(packet.numberOfPlayers);
                        System.out.println(packet.numberOfPlayers);
                        output.println("Welcome");
                        break;
                    }
                    case JOIN_A_GAME: {
                        int ID = Integer.parseInt(in.readLine());
                        String username = in.readLine();
                        int newPlayerID = listOfGames.get(ID).getNumberOfPlayers();
                        int numberOfPlayers = listOfGames.get(ID).numberOfPlayers;
                        Character color = 'w';
                        if(numberOfPlayers==2) color='b';
                        else if(numberOfPlayers==3)
                        {
                            if(newPlayerID==1) color='r';
                            else if(newPlayerID==2) color='y';
                        }
                        else if(numberOfPlayers==4)
                        {
                            if(newPlayerID==1) color='b';
                            else if(newPlayerID==2) color='y';
                            else if(newPlayerID==2) color='b';
                        }
                        else if(numberOfPlayers==6)
                        {
                            if(newPlayerID==1) color='r';
                            else if(newPlayerID==2) color='y';
                            else if(newPlayerID==3) color='b';
                            else if(newPlayerID==4) color='g';
                            else if(newPlayerID==5) color='c';
                            else color='w';
                        }

                        listOfGames.get(ID).addPlayer(listOfGames.get(ID).new Player(newPlayerSocket, username, color));
                        System.out.print("New Player: " + username + "\n");
                        System.out.print("Number OF Players in the Game: " + listOfGames.get(ID).getNumberOfPlayers() + "\n");
                        output.println("You have been added to the game");
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.print("SERVER: ERROR: couldn't connect");
        } finally {
            try {
                listener.close();
            } catch (IOException e) {
                System.out.print("SERVER: ERROR: couldn't close the connection");
            }
        }
    }

}
