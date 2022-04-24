package at.kaindorf.logic.moves;

import at.kaindorf.logic.beans.Move;
import at.kaindorf.logic.beans.Position;
import at.kaindorf.logic.postitions.PositionData;

import java.io.IOException;
import java.util.*;

public class CheckLogic {
    private final List<Position> positionList;
    private final Map<Integer, Set<Integer>> validMoves;
    private Map<Integer, List<Position>> currentMills = new HashMap<>();

    public CheckLogic() throws IOException {
        PositionData positionData = new PositionData();
        this.positionList = positionData.initializeList();
        this.validMoves = positionData.initializeMap();
        currentMills.put(1,new ArrayList<Position>());
        currentMills.put(2, new ArrayList<Position>());
    }

    public boolean isValidMove(Move move){
//        Compare Moves(Number of Start, Number of Destination) based on the start field and destination field
//        if (validMoves.get(move.getStart()).contains(move.getDestination())){
//            return true;
//        }
        return validMoves.get(move.getStart()).contains(move.getDestination());
    }

    public boolean isValidPlace(Position pos){
//        Checks if the field has a token on it
//        if (pos.getAvailable()==0){
//            return true;
//        }
        return pos.getAvailable() == 0;
    }

    public boolean isMill(int player){
//      Based on the X or Y Components of a Position, the Method determines if a mill is formed
//      If 3 Components are equal true is returned

//        First: Only take the Positions where a token from player x
//        is placed to minimize the List to search
        List<Position> nAvailablePositions = new ArrayList<>();

        for (Position position : positionList){
            if (position.getAvailable()==player) {nAvailablePositions.add(position);}
        }
//        for (Position position : positionList){
//        }


//      List to store the Objects, which were found
//      Second (der beschissene Part): Find all mills with
//      thousands of for-loops :)
        List<Position> foundObjects = findXorYMills(nAvailablePositions,'x');
        if (foundObjects != null ){
            System.out.println("Horizontal Mill:");
            printMill(foundObjects);
            currentMills.get(player).addAll(foundObjects);
        }
        foundObjects = findXorYMills(nAvailablePositions, 'y');
        if (foundObjects != null) {
            System.out.println("Vertical Mill:");
            printMill(foundObjects);
            currentMills.get(player).addAll(foundObjects);
        }
        return false;
    }

//  Method to find vertical or horizontal mills
    private List<Position> findXorYMills(List<Position> nAvailablePositions, char xy){

        List<Position> copyList;

        List<Position> foundObjects = new ArrayList<>();

//      Look for every Position if there are tokens beside
        for (Position position : nAvailablePositions) {
//            Fill the copied List with content again
            copyList = new ArrayList<>(nAvailablePositions);
            foundObjects.add(position);
//            Remove this Position in order to avoid getting
//            the same Position twice
            copyList.remove(position);
//            Look for tokens on the same lane
            for (Position posit : copyList) {
                if (posit.getCoord(xy) == position.getCoord(xy)) {
                    foundObjects.add(posit);
                }
            }
//            Check if there are more than two tokens on a lane
//            (on a middle lane there can be 5)
            if (foundObjects.size()>2){
//                Check if the tokens are on the same lane
                if (isALane(foundObjects, xy)) return foundObjects;
            }
            foundObjects.clear();
        }



        return null;
    }

//  Method to determine if the three tokens are really next to each other
    private boolean isALane(List<Position> foundObjects, char xy){

//       check if the tokens are on the middle lane
//        if not there is a mill
        int pos = foundObjects.get(0).getPosNR();

        if (foundObjects.size() == 3 && pos != 1 && pos != 2 && pos != 5 ){
            pos = foundObjects.get(1).getPosNR();
            if (pos != 1 && pos != 2 && pos != 5){
                pos = foundObjects.get(2).getPosNR();
                if ( pos != 1 && pos != 2 && pos != 5 ){
                    return true;
                }
            }
        }
//        Divide into Left or Right -/ Upper or Lower Half
//        The midpoint of the field is (1200|675)
        List<Position> half1 = new ArrayList<>();
        List<Position> half2 = new ArrayList<>();
        if (xy == 'x'){
            for (Position position : foundObjects){
                if (position.getCoord('x') < 1200){
                    half1.add(position);
                }
                else if (position.getCoord('x') > 1200){
                    half2.add(position);
                }
            }
        }
        else if (xy == 'y'){
            for (Position position : foundObjects){
                if (position.getCoord('y') < 675){
                    half1.add(position);
                }
                else if (position.getCoord('y') > 675){
                    half2.add(position);
                }
            }
        }
//      Ensure that there are three tokens on a lane and with that
//      a mill is formed
        if (half1.size() == 3)return true;
        else if (half2.size() == 3) return true;
        else return false;
    }

    public Position whichPosition(Position pos) throws Exception {
//      Based on a Position Object(int x, int y),
//      the clicked field gets determined with a tolerance of 30 px
//      in every direction
        for (Position position : positionList){
            if (Math.abs(position.getYCoord()-pos.getYCoord()) <=30 && Math.abs(position.getXCoord()-pos.getXCoord())<=30){
                return position;
            }
        }

        throw new Exception("no position");
    }

    private void printMill(List<Position> printList){
            System.out.println("Mill is formed on:");
            for (Position position : printList) {
                System.out.println(position);
            }
    }

    public List<Position> getPositionList() {
        return positionList;
    }



    public static void main(String[] args) {
        List<Position> positions = List.of(
                new Position(1,1200,130),
                new Position(2,1200,310),
                new Position(5, 1200,490)
        );
        CheckLogic cl = null;
        try {
            cl = new CheckLogic();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(cl.isALane(positions,'y'));
    }

}
