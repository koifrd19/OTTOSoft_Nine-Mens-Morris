package at.kaindorf.mill.beans;

public enum GamePieceState {


    WHITE(1), BLACK(2), EMPTY(0);

    private final int available;


    GamePieceState(int available) {
        this.available = available;
    }

    public int getAvailable() {
        return available;
    }

    public String getColour(int player){
        return "";
    }
}
