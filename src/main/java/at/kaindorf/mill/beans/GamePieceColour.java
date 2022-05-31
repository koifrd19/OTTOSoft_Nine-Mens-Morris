package at.kaindorf.mill.beans;

public enum GamePieceColour {


    WHITE(1), BLACK(2), EMPTY(0);

    private final int available;


    GamePieceColour(int available) {
        this.available = available;
    }

    public int getAvailable() {
        return available;
    }

    public String getColour(int player){
        return "";
    }
}
