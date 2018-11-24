package chinesecheckers;

public class GameInfo {
    private int id;
    private int currentNumberOfPlayers;
    private int maxNumberOfPlayers;

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
