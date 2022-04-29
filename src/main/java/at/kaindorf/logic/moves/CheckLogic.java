package at.kaindorf.logic.moves;

import at.kaindorf.logic.beans.Mill;
import at.kaindorf.logic.beans.Move;
import at.kaindorf.logic.beans.Position;
import at.kaindorf.logic.postitions.PositionData;

import java.io.IOException;
import java.util.*;

public class CheckLogic {
    protected final List<Position> positionList;
    private final Map<Integer, Set<Integer>> validMoves;
    private Map<Integer, List<Mill>> currentMills = new HashMap<>();

    public CheckLogic() throws IOException {
        PositionData positionData = new PositionData();
        this.positionList = positionData.initializeList();
        this.validMoves = positionData.initializeMap();
        currentMills.put(1,new ArrayList<>());
        currentMills.put(2, new ArrayList<>());
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
        if (pos.getAvailable()==0){
            return true;
        }
        return false;
//        return pos.getAvailable() == 0;
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
//       remove the current mills to in order to avoid getting the same mill
        for (Mill mill : currentMills.get(player)){
           nAvailablePositions.remove(mill.getPos1());
           nAvailablePositions.remove(mill.getPos2());
           nAvailablePositions.remove(mill.getPos3());
        }


//      List to store the Objects, which were found
//      Second (der beschissene Part): Find all mills with
//      thousands of for-loops :)
        List<Position> foundObjects = findXorYMills(nAvailablePositions,'x');
        if (foundObjects != null ){
            System.out.println("Horizontal Mill:");
            printMill(foundObjects);
            currentMills.get(player).add(convToMill(foundObjects));
        }
        foundObjects = findXorYMills(nAvailablePositions, 'y');
        if (foundObjects != null) {
            System.out.println("Vertical Mill:");
            printMill(foundObjects);
            currentMills.get(player).add(convToMill(foundObjects));
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
            if (Math.abs(position.getYCoord()-pos.getYCoord()) <=60 && Math.abs(position.getXCoord()-pos.getXCoord())<=60){
                return position;
            }
        }

        throw new Exception("no position");
    }

    private Mill convToMill(List<Position> positions){
        return new Mill(positions.get(0), positions.get(1), positions.get(2));
    }

    private void printMill(List<Position> printList){
            System.out.println("Mill is formed on:");
            for (Position position : printList) {
                System.out.println(position);
            }
    }

    public static void main(String[] args) {
    }

}
