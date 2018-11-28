package chinesecheckers;

public class GameInfo {
    int id;
    int currentNumberOfPlayers;
    private int maxNumberOfPlayers;
    int numberOfBots;
    String name;

    public void setCurrentNumberOfPlayers(int currentNumberOfPlayers) {
        this.currentNumberOfPlayers = currentNumberOfPlayers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMaxNumberOfPlayers(int maxNumberOfPlayers) {
        this.maxNumberOfPlayers = maxNumberOfPlayers;
    }

    public int getCurrentNumberOfPlayers() {
        return currentNumberOfPlayers;
    }

    public int getId() {
        return id;
    }

    public int getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }
}
