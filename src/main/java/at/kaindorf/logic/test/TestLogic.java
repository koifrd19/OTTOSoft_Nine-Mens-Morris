package at.kaindorf.logic.test;

import at.kaindorf.logic.beans.Position;
import at.kaindorf.logic.moves.Movement;

import java.util.Scanner;

public class TestLogic {



    /*public static void main(String[] args) {
//        Position position = new Position(1377,492);
        Movement movement = null;
//        Position position1= null;

        try {
            movement = new Movement();
//            position1 = movement.getCheckLogic().whichPosition(position);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println(position1);
//        System.out.println();
//
//        try {
//            movement.place(position1, 1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Scanner scanner = new Scanner(System.in);

//        Simple Game Test:

        for (int i = 1; i <= 5; i++) {
            movement.printGameField();
            System.out.println("Give Coordinates(X, Y) to Place Token: ");
            try {
                movement.place(new Position(scanner.nextInt(), scanner.nextInt()), (i % 2) + 1);
            } catch (Exception e) {
                e.printStackTrace();
                i--;
                System.out.println("Invaid Position! Try Again: ");
            }
        }

        System.out.println("Now start moving: ");

        Position startpos;

        for (int i = 1; i < 20; i++) {
            movement.printGameField();

            try {
                System.out.println("Player "+ ((i % 2)+1) +": give Coordinates of token to Select (X, Y): ");
                startpos = movement.whichPosition(new Position(scanner.nextInt(), scanner.nextInt()));
                System.out.println("now give Coordinates of destination to move token(X, Y):");
                movement.move(startpos, new Position(scanner.nextInt(), scanner.nextInt()),(i % 2)+1);
                movement.isMill((i % 2)+1);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Invalid Move! Try Again: ");
                i--;
            }

        }


    }*/



}
