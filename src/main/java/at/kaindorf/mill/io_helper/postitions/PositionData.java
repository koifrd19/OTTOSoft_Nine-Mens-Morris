package at.kaindorf.mill.io_helper.postitions;

import at.kaindorf.mill.beans.Position;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PositionData {

    public PositionData() {
    }

//    Method to read the valid_moves.txt file
    public Map<Integer, Set<Integer>> initializeMap() throws IOException {

        Map<Integer, Set<Integer>> validMoves = new HashMap<>();

        // File path is passed as parameter
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "valid_moves.txt");

        List<String> validMovesString = Files.lines(path)
                .skip(1)
                .map(String::new)
                .toList();

        for (String str : validMovesString){
            String[] tokens = str.split(";");
            validMoves.put(Integer.parseInt(tokens[0]), new HashSet<>());
            for (int i = 1; i < tokens.length; i++) {
                validMoves.get(Integer.parseInt(tokens[0])).add(Integer.parseInt(tokens[i]));
            }
        }

        return validMoves;
    }

//   Method to read the valid_moves.txt file
    public List<Position> initializeList() throws IOException {
        List<Position> positons;

        // File path is passed as parameter
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "positions_jFrame.txt");

        positons = Files.lines(path)
                .skip(1)
                .map(Position::new)
                .toList();

        return positons;
    }

    public static void main(String[] args) {
//        List<Position> positions = null;
        PositionData pd =  new PositionData();
//        try {
//              positions = pd.initializeList();
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }

        Map<Integer, Set<Integer>> validMoves = null;

        try {
            validMoves = pd.initializeMap();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i : validMoves.keySet()){
            System.out.println("Key : " +i);
            System.out.println("Set : ");
            for (int d : validMoves.get(i)){
                System.out.println(d);
            }
        }


//
//        for (Position position : positions){
//            System.out.println(position);
//        }


    }


}
