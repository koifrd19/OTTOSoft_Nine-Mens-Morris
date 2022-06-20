package at.kaindorf.mill.bl.check;

import at.kaindorf.mill.beans.Mill;
import at.kaindorf.mill.beans.Move;
import at.kaindorf.mill.beans.Position;
import at.kaindorf.mill.gui.game.MainField;
import at.kaindorf.mill.io_helper.postitions.PositionData;

import javax.swing.*;
import javax.swing.text.html.parser.Element;
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
        currentMills.put(1,new ArrayList<Mill>());
        currentMills.put(2, new ArrayList<Mill>());
    }

    public boolean isValidMove(Move move){
//        Compare Moves(Number of Start, Number of Destination) based on the start field and destination field
//        if (validMoves.get(move.getStart()).contains(move.getDestination())){
//            return true;
//        }
        return validMoves.get(move.getStart()).contains(move.getDestination());
    }

    public boolean isValidJump(Move move){
        return positionList.indexOf(move.getDestination()) == 0;
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


//      List to store the Objects, which were found
//      Second: (der beschissene Part): Find all mills with
//      thousands of for-loops :)

        /*List<Position> foundObjects = findXorYMills(nAvailablePositions,'x');
        manageCurrentMills(foundObjects, 'x', player);
//       manage the current mills to in order to avoid getting the same mill
        if (foundObjects != null){
            return true;
        }

        foundObjects = findXorYMills(nAvailablePositions, 'y');
        manageCurrentMills(foundObjects, 'y', player);
//       manage the current mills to in order to avoid getting the same mill
        if (foundObjects != null){
            return true;
        }*/

        List<Mill> foundXMills = findXorYMills(nAvailablePositions, 'x');
        manageCurrentMills(foundXMills,'x', player);
        /*if (foundXMills != null){
            return true;
        }*/

        List<Mill> foundYMills = findXorYMills(nAvailablePositions, 'y');
        manageCurrentMills(foundYMills,'y', player);
        /*if (foundYMills != null){
            return true;
        }*/

        return MainField.isTaking();
    }

//  Method to find vertical or horizontal mills
    private List<Mill> findXorYMills(List<Position> nAvailablePositions, char xy){

        System.out.println("findXorYMills on: "+ xy +"-axe");

        List<Mill> foundMills = new ArrayList<>();

        List<Position> copyList;
//            Fill the copied List with content again
        copyList = new ArrayList<>(nAvailablePositions);

        List<Position> foundObjects = new ArrayList<>();

        for (int i = 0; i < 5;i++) {
//      Look for every Position if there are tokens beside
            for (Position position : nAvailablePositions) {
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
                if (foundObjects.size() >= 3) {
//                Check if the tokens are on the same lane
                    if (isALane(foundObjects, xy)) foundMills.add(convToMill(foundObjects));
                }
                foundObjects.clear();
            }
            System.out.println("WAASSS");
            nAvailablePositions = new ArrayList<>(copyList);
            System.out.println("Ist denn hier los!");
        }
        if (foundMills.isEmpty()){
            return null;}

        return foundMills;
    }



//  Method to determine if the three tokens are really next to each other
    private boolean isALane(List<Position> foundObjects, char xy){

        System.out.println("isALane!");

        System.out.println(foundObjects.size() +" found Objects size");

        printList(foundObjects);

//       check if the tokens are on the middle lane
//        if not there is a mill

        List<Integer> posNrs = new ArrayList<>();

        for (Position position : foundObjects){
            posNrs.add(position.getPosNR());
        }

        List<Position> half1 = new ArrayList<>();
        List<Position> half2 = new ArrayList<>();

        if (posNrs.contains(1) && posNrs.contains(2) && posNrs.contains(5)) {
//        Divide into Left or Right -/ Upper or Lower Half
//        The midpoint of the field is (1200|675)
            if (xy == 'x') {
                for (Position position : foundObjects) {
                    if (position.getCoord('y') <= 500) {
                        half1.add(position);
                    } else if (position.getCoord('y') >= 500) {
                        half2.add(position);
                    }

                }
            } else if (xy == 'y') {
                for (Position position : foundObjects) {
                    if (position.getCoord('x') < 845) {
                        half1.add(position);
                    } else if (position.getCoord('x') > 845) {
                        half2.add(position);
                    }
                }

            }
        }
        else if (posNrs.contains(1) || posNrs.contains(2) || posNrs.contains(5)){

                if (posNrs.indexOf(1) == posNrs.lastIndexOf(1)){
                    if (posNrs.indexOf(2) == posNrs.lastIndexOf(2)){
                        if (posNrs.indexOf(5) == posNrs.lastIndexOf(5)){
                            return true;
                        }
                    }
                }
            return false;
            /*int[] ints = new int[3];
            ints[0] = 1;
            ints[1] = 2;
            ints[2]= 5;
            for (int i = 0; i < ints.length; i++) {
                int finalI = i;
                String str = ""+posNrs.get(0).intValue() +posNrs.get(1).intValue()+ posNrs.get(2).intValue();
                long x = str
                        .chars()
                        .filter(c -> c == ints[finalI])
                        .count();
                if (x != 1){
                    return false;
                }
            }*/
        }
        else{
            return true;
        }

        System.out.println("Half 1: ");
        for (Position position : half1){
            System.out.println(position.toString());
        }
        System.out.println("Half 2: ");
        for (Position position : half2){
            System.out.println(position.toString());
        }
//      Ensure that there are three tokens on a lane and with that
//      a mill is formed
        if (half1.size() == 3)return true;
            else if (half2.size() == 3) return true;
            else return false;
    }

   /* private void manageCurrentMills(List<Position> foundObjects, char xy, int player){
        if(foundObjects != null) {
            for (Mill mill : currentMills.get(player)) {
                if(convToMill(foundObjects).equals(mill)){
//                    If the found Object equals a current Mill, the Mill is already formed
                    return;
                }
            }
            System.out.println(xy+"-axe Mill");
            printMill(foundObjects);
            currentMills.get(player).add(convToMill(foundObjects));
            JOptionPane.showMessageDialog(null, "Please take a Stone!", "Mill is formed by Player " + player, JOptionPane.INFORMATION_MESSAGE);
            MainField.setTaking(true);
        }
    }*/

    private void manageCurrentMills(List<Mill> foundMills, char xy, int player){

        if(foundMills != null) {
            List<Mill> copyOfFoundMills = new ArrayList<>(foundMills);
            for (Mill foundMill : foundMills){
                for (Mill mill : currentMills.get(player)) {
                    if(foundMill.equals(mill)){
//                    If the found Object equals a current Mill, the Mill is already formed
                        System.out.println("Hello from the other siiiideeee!!");
                        copyOfFoundMills.remove(foundMill);
                    }
                }
            }
            if(copyOfFoundMills.size() != 0) {
                Mill newMill = copyOfFoundMills.get(0);
                System.out.println(xy + "-axe Mill");
                printMill(newMill);
                currentMills.get(player).add(newMill);
                JOptionPane.showMessageDialog(null, "Please take a Stone!", "Mill is formed by Player " + player, JOptionPane.INFORMATION_MESSAGE);
                MainField.setTaking(true);
            }
        }
    }

//    Function to delete split Mills out of currentMills Map
    public void deleteSplitMills(int player){
        List<Mill> millList = new ArrayList<>(currentMills.get(player));
        List<Mill> deleteMills = new ArrayList<>();
        System.out.println(player+ "'s current Mills: ");
        for (Mill mill : millList){
            System.out.println(mill.toString());
        }

        for (Mill mill : millList){
//            We ensure that every Mill is sill formed, if not it gets removed out of the current mills
//            With get Available we get the player that possesses the token
            if ( positionList.get(positionList.indexOf(mill.getPos1())).getAvailable()
                        == positionList.get(positionList.indexOf(mill.getPos2())).getAvailable()
                && positionList.get(positionList.indexOf(mill.getPos2())).getAvailable()
                        == positionList.get(positionList.indexOf(mill.getPos3())).getAvailable()
                && positionList.get(positionList.indexOf(mill.getPos3())).getAvailable()
                    == player){}
            else{
                deleteMills.add(mill);
            }

        }
        System.out.println(player+ "'s removed Mill: ");
        for (Mill mill : deleteMills){
            System.out.println(mill.toString());
        }
        millList.removeAll(deleteMills);
        currentMills.put(player, millList);
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

    public int getNoOfPlacedTokens(int player){
        int count = 0;
        for (Position position : positionList){
            if (position.getAvailable() == player){
                count++;
            }
        }
        return count;
    }

    private Mill convToMill(List<Position> positions){
        return new Mill(positions.get(0), positions.get(1), positions.get(2));
    }

    private void printMill(List<Position> printList){
            System.out.println("is formed on:");
            for (Position position : printList) {
                System.out.println(position);
            }
    }

    private void printMill(Mill mill){
        System.out.println("is formed on:"+ mill.toString());
        /*for (Position position : printList) {
            System.out.println(position);
        }*/
    }

    public void printList(List list){
        for(Object o : list){
            System.out.println(o.toString());
        }
    }

    public static void main(String[] args) {
    }

}
