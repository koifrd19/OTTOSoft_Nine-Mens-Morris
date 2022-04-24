package at.kaindorf.logic.beans;

public class Move {

    private int start;
    private int destination;

    public Move(int start, int destination) {
        this.start = start;
        this.destination = destination;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }
}
