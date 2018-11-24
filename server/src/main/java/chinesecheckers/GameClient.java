package chinesecheckers;

public abstract class GameClient {
    abstract void sendGameID();
    abstract void sendBoardState();
    abstract void downloadMove();

}