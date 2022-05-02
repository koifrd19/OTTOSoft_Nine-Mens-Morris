package at.kaindorf.logic.beans;

public enum GamePiece {


    WHITE(1), BLACK(2), EMPTY(0);

    private final int available;


    GamePiece(int available) {
        this.available = available;
    }

    public int getAvailable() {
        return available;
    }

    public String getColour(int player){
        return "";
    }
}
