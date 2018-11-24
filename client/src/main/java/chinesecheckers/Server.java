package chinesecheckers;

import java.util.ArrayList;

public class Server {
    private Board board;
    private boolean isMyMove;
    private ArrayList<GameInfo> listOfgames = new ArrayList<GameInfo>();
    private GameVariation gameVariation;

    //TODO: funkcja connect()
    public void connect(int id) {

    }

    //TODO: funkcja requestnewGame()
    public void requestNewGame() {

    }

    //TODO: funkcja uploadBoard
    public void uploadBoard(Board board) {

    }

    //TODO: funkcja downloadBoardState()
    public void downloadBoardState() {
        //board = ?;
        //myMove = ?
    }

    public Board getboard() {
        return board;
    }

    public boolean isMymove() {
        return isMyMove;
    }

    //TODO: funkcja downloadAllGames()
    public void downloadAllGames(){
        //listOfgames = ?
    }

    public ArrayList<GameInfo> getAllGames() {
        return listOfgames;
    }

    //TODO: funkcja join();
    public void join(String username) {

    }

    //TODO: funkcja downloadGameVariation();
    public void downloadGameVariation()
    {
        //gameVariation = ?;
    }

    public GameVariation getGameVariation() {
        return gameVariation;
    }
}

