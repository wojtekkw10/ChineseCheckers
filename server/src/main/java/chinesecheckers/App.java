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
        int clientNumber = 0;
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(9898);
        } catch (IOException e) {
            System.out.print("Cannot create socket");
        }

        Game defaultGame = new Game("Default", 0);
        //default game jako soobna klasa ktora dzidziczy po game - zeby ladnie wygladalo

        try {
            while (true) {
                //chyba tak zrobimy:
                System.out.print("Waiting for player...\n");
                Socket newPlayerSocket = listener.accept();
                System.out.print("Client connected\n");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(newPlayerSocket.getInputStream()));
                System.out.print("Downloading request\n");
                //String line = in.readLine();

                String commandAsJSON = in.readLine();
                System.out.print(commandAsJSON);
                Command command = Command.fromJSON(commandAsJSON);
                Packet packet = new Packet();
                packet = packet.fromJSON(command.content);

                System.out.print("Downloaded request\n");
                PrintWriter output = new PrintWriter(newPlayerSocket.getOutputStream(), true);


                //int ID = Integer.parseInt(line);
                //System.out.print("Input: "+ID+"\n");
                switch(command.commandType) {
                    case NINES: {
                        System.out.print("Received empty string\n");
                        //output.println("99999");
                        break;
                    }
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
                        }
                        output.println(gameList.toString());
                        newPlayerSocket.close();
                        //musimy zamknac bo nie mamy osobnego watku dla niego
                    }
                    case REQUEST_NEW_GAME: {
                        System.out.print("New Game Requested");
                        //String name = in.readLine();
                        //String numberOfBotsString = in.readLine();
                        //int numberOfBots = Integer.parseInt(numberOfBotsString);
                        //String username = in.readLine();

                        listOfGames.add(new Game(packet.gameName, packet.numberOfBots));
                        listOfGames.get(listOfGames.size() - 1).addPlayer(defaultGame.new Player(newPlayerSocket, packet.username));
                        System.out.print("New game has been added. Name: " + packet.gameName + " Number of Bots: " + packet.numberOfBots + "\n");
                        System.out.print("New Player: " + packet.username + "\n");
                        System.out.print("Number of games: " + listOfGames.size() + "\n");
                        Packet id = new Packet();
                        id.id = listOfGames.size();
                        command.content = id.toJSON();
                        output.println(command.toJSON());
                        //clientNumber++;
                    }
                    case JOIN_A_GAME: {
                        String username = in.readLine();
                        int ID = Integer.parseInt(command.content);
                        listOfGames.get(ID).addPlayer(defaultGame.new Player(newPlayerSocket, username));
                        System.out.print("New Player: " + username + "\n");
                        System.out.print("Number OF Players in the Game: " + listOfGames.get(ID).getNumberOfPlayers() + "\n");
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
