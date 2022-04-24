package at.kaindorf.logic.beans;

public class Position {

//    Overview of the numbering
private int posNR;
//    3--------------1--------------3
//    |              |              |
//    |     4--------2--------4     |
//    |     |        |        |     |
//    |     |    6---5---6    |     |
//    |     |    |       |    |     |
//    1-----2----5       5----2-----1
//    |     |    |       |    |     |
//    |     |    6---5---6    |     |
//    |     |        |        |     |
//    |     4--------2--------4     |
//    |              |              |
//    3--------------1--------------3
    private int XCoord;
    private int YCoord;
//    Variable to determine whether Player 1 (available=1), Player 2 (available=2) has placed the token on that position
//    If available=0 nobody placed a token on that position
    private int available;

    public Position(int posNR, int XCoord, int YCoord) {
        this.posNR = posNR;
        this.XCoord = XCoord;
        this.YCoord = YCoord;
        this.available = 0;
    }

    public Position(int XCoord, int YCoord) {
        this.XCoord = XCoord;
        this.YCoord = YCoord;
        this.available = 0;
    }

    public Position(String line) {
        String[] tokens = line.split(";");
        this.posNR = Integer.parseInt( tokens[0]);
        this.XCoord =  Integer.parseInt( tokens[1]);
        this.YCoord =  Integer.parseInt( tokens[2]);
        this.available = 0;
    }

    public int getPosNR() {
        return posNR;
    }

    public int getXCoord() {
        return XCoord;
    }

    public int getYCoord() {
        return YCoord;
    }

    public int getCoord(char xy){
        if (xy == 'x'){
            return XCoord;
        }
        return YCoord;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Position{" +
                "posNR=" + posNR +
                ", XCoord=" + XCoord +
                ", YCoord=" + YCoord +
                ", available=" + available +
                '}';
    }

    public static void main(String[] args) {

    }
}
