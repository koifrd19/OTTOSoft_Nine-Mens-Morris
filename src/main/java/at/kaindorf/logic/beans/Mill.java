package at.kaindorf.logic.beans;

public class Mill {

    private Position pos1;
    private Position pos2;
    private Position pos3;

    public Mill(Position pos1, Position pos2, Position pos3) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.pos3 = pos3;
    }

    public Position getPos1() {
        return pos1;
    }

    public Position getPos2() {
        return pos2;
    }

    public Position getPos3() {
        return pos3;
    }

    @Override
    public String toString() {
        return "Mill{" +
                "pos1=" + pos1 +
                ", pos2=" + pos2 +
                ", pos3=" + pos3 +
                '}';
    }
}
