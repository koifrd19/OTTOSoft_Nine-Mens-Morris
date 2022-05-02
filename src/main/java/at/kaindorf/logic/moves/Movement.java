package at.kaindorf.logic.moves;

import at.kaindorf.logic.beans.Move;
import at.kaindorf.logic.beans.Position;

import java.io.IOException;
import java.util.List;


public class Movement extends CheckLogic {

    public Movement() throws IOException {
        super();
    }

    public void move(Position start, Position dest, int player) throws Exception {
        start = whichPosition(start);
        dest = whichPosition(dest);
        if (start.getAvailable() != player){
             throw new Exception("you are naughty ;) Don't take other player's tokens");
        }
        if (isValidMove(new Move(start.getPosNR(), dest.getPosNR()))){
            dest.setAvailable(start.getAvailable());
            start.setAvailable(0);
            if (isMill(player)) {

            }
            return;
        }
        throw new Exception("moving is not possible");
    }

    public void place(Position pos, int player) throws Exception {
        pos = whichPosition(pos);
        if (isValidPlace(pos)){
            pos.setAvailable(player);
            System.out.println("Player "+player+" successfully placed token on " + pos );
            isMill(player);
            return;
        }
        throw new Exception("placing is not possible");
    }

    public void take(Position pos) throws Exception {
        Position position = whichPosition(pos);
        positionList.get(positionList.indexOf(position)).setAvailable(0);
        System.out.println("Successfully taken token from " + position);

    }

    public void printGameField(){

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

        System.out.println("1  "+positionList.get(0).getAvailable()+"--------------"
                +positionList.get(1).getAvailable()+"--------------"+ positionList.get(2).getAvailable());

        System.out.println("2  |              |              |");

        System.out.println("3  |     " + positionList.get(8).getAvailable()+"--------"+ positionList.get(9).getAvailable()
                +"--------"+positionList.get(10).getAvailable()+"     |");

        System.out.println("4  |     |        |        |     |");

        System.out.println("5  |     |    "+positionList.get(16).getAvailable()+"---"+positionList.get(17).getAvailable()
                +"---"+positionList.get(18).getAvailable() +"    |     |");
        System.out.println("6  |     |    |       |    |     |");

        System.out.println("7  "+positionList.get(7).getAvailable()+ "-----"+positionList.get(15).getAvailable() +"----"
                +positionList.get(23).getAvailable()+"       "+positionList.get(19).getAvailable()+"----"
                +positionList.get(11).getAvailable()+"-----"+positionList.get(3).getAvailable());

        System.out.println("8  |     |    |       |    |     |");

        System.out.println("9  |     |    "+positionList.get(22).getAvailable()+"---"+positionList.get(21).getAvailable()
                +"---"+positionList.get(20).getAvailable()+"    |     |");

        System.out.println("10 |     |        |        |     |");

        System.out.println("11 |     "+positionList.get(14).getAvailable()+"--------"+positionList.get(13).getAvailable()
                +"--------"+positionList.get(12).getAvailable()+"     |");

        System.out.println("12 |              |              |");

        System.out.println("13 "+positionList.get(6).getAvailable()+"--------------"+positionList.get(5).getAvailable()
                +"--------------"+positionList.get(4).getAvailable());
    }

    public static void main(String[] args) {
        Movement movement = null;
        try {
            movement = new Movement();
        } catch (IOException e) {
            e.printStackTrace();
        }

        movement.printGameField();
    }
}
