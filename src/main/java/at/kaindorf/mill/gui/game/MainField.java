package at.kaindorf.mill.gui.game;

import at.kaindorf.mill.beans.GamePieceColour;
import at.kaindorf.mill.beans.Move;
import at.kaindorf.mill.beans.Piece;
import at.kaindorf.mill.beans.Position;
import at.kaindorf.mill.bl.check.Movement;
import at.kaindorf.mill.gui.menu.MenuDlg;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Random;

@Data
public class MainField extends JFrame{

    Movement movement;

    private int xBefore, yBefore, xAfter, yAfter;
    private int currentPlayer = 0;
    private LinkedList<Piece> pieceListPlayState = new LinkedList<>();
    private LinkedList<Piece> pieceListPlaced = new LinkedList<>();
    private Piece selectedPiece = null;
    private JFrame jFrame = new JFrame("OttoSoft's Nine Men's morris");;
    private Image imageWhite = null;
    private Image imageBlack = null;
    private Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/nussbaum_background.png"));

    private static boolean taking = false;
    public Piece getPiece(int x, int y){
        int xp = x/64;
        int yp = y/64;

        for (Piece p : pieceListPlayState){
            if (p.getXp() == xp && p.getYp() == yp){
                return p;
            }
        }
        return null;
    }


    public MainField() {
        MenuDlg menuDlg = new MenuDlg(this, true);
        menuDlg.setVisible(true);
        runLauncher();
    }

    public void runLauncher(){
        System.out.println("runLauncher();");
        try {
            movement = new Movement();
        } catch (IOException e) {
            System.out.println("Error during File read!");
            e.printStackTrace();
        }

        jFrame.setBounds(26, 26, 1680, 945); //1280/64 = 20 + 720/11
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);

        //Initializes Icons
        this.setImage();
        // Create White Pieces
        this.createWhitePieces();
        // Create Black Pieces
        this.createBlackPieces();
        jFrame.setContentPane(renderJPanel());
        this.setMouseListeners();

        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    private void createBlackPieces() {
        Piece black1 = new Piece(21,13, GamePieceColour.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black1);

        Piece black2 = new Piece(21,11, GamePieceColour.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black2);

        Piece black3 = new Piece(21,9, GamePieceColour.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black3);

        Piece black4 = new Piece(23,13, GamePieceColour.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black4);

        Piece black5 = new Piece(23,11, GamePieceColour.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black5);

        Piece black6 = new Piece(23,9, GamePieceColour.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black6);

        Piece black7 = new Piece(25,13, GamePieceColour.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black7);

        Piece black8 = new  Piece(25,11, GamePieceColour.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black8);

        Piece black9 = new  Piece(25,9, GamePieceColour.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black9);
    }

    private void createWhitePieces() {

        Piece white1 = new Piece(1,1, GamePieceColour.WHITE, pieceListPlayState, this);
        pieceListPlayState.add(white1);

        Piece white2 = new Piece(1,3, GamePieceColour.WHITE, pieceListPlayState, this);
        pieceListPlayState.add(white2);

        Piece white3 = new Piece(1,5, GamePieceColour.WHITE, pieceListPlayState, this);
        pieceListPlayState.add(white3);

        Piece white4 = new Piece(5,3, GamePieceColour.WHITE, pieceListPlayState, this);
        pieceListPlayState.add(white4);

        Piece white5 = new Piece(5,1, GamePieceColour.WHITE, pieceListPlayState, this);
        pieceListPlayState.add(white5);

        Piece white6 = new Piece(5,5, GamePieceColour.WHITE, pieceListPlayState, this);
        pieceListPlayState.add(white6);

        Piece white7 = new Piece(3,5, GamePieceColour.WHITE, pieceListPlayState, this);
        pieceListPlayState.add(white7);

        Piece white8 = new Piece(3,1, GamePieceColour.WHITE, pieceListPlayState,this);
        pieceListPlayState.add(white8);

        Piece white9 = new Piece(3,3, GamePieceColour.WHITE, pieceListPlayState,this);
        pieceListPlayState.add(white9);
    }

    private void setMouseListeners(){
        jFrame.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                selectedPiece = getPiece(e.getX(), e.getY());
//                System.out.println("Coords: X:"+e.getX()+" Y: "+e.getY());

                xBefore = e.getX();
                yBefore = e.getY();

                System.out.println("Pressed: " +xBefore + " " + yBefore +" taking: " + taking);
//                if (taking){
//                    taking(e);
//                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Position position;
                System.out.println("Released: " +e.getX() + " " + e.getY());
                if (taking && selectedPiece!=null){
//                    System.out.println("taking");
                    taking(e);
                    taking = false;
                }
                else if (selectedPiece != null) {
                    if (currentPlayer <= 5){
                        placing(e);
                    }
                    else if(currentPlayer == 6){
                        JOptionPane.showMessageDialog(null, "Now get Moving !",
                                "Placing is over", JOptionPane.INFORMATION_MESSAGE);

                        System.out.println("moving!");
                        moving(e);
                        currentPlayer++;
                    }
                    else{
                        System.out.println("moving!");
                        moving(e);
                    }
                }
            }

        });

        jFrame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedPiece != null){
                    selectedPiece.setX(e.getX() - 40);
                    selectedPiece.setY(e.getY() - 64);
                    jFrame.repaint();
                }
            }

        });

    }

    private void placing(MouseEvent e){
        System.out.println("placing");

        Position position;
        try {
            if (selectedPiece.colour.getAvailable() != (currentPlayer % 2) + 1) {
                throw new Exception(selectedPiece.colour.getAvailable() +" should place a token" );
            }
            position = movement.whichPosition(new Position(e.getX(),e.getY()));
            if (movement.isValidPlace(position) && !pieceListPlaced.contains(selectedPiece)) {

                movement.place(new Position(position.getXCoord(), position.getYCoord()), (currentPlayer % 2) + 1);
                pieceListPlaced.add(selectedPiece);
                System.out.println(currentPlayer++);
                movement.printGameField();

                selectedPiece.move(position.getXCoord() /64, position.getYCoord() /64);

                jFrame.repaint();

                xBefore = xAfter;
                yBefore = yAfter;

                xAfter = e.getX();
                yAfter = e.getY();

                System.out.println("Released: " +xAfter + " " + yAfter);
            }
            else{
                throw new Exception("no valid place or piece already placed");
            }
        } catch (Exception ex) {
            System.out.println("moving back");
            selectedPiece.move(xBefore /64,yBefore/64);
            jFrame.repaint();
            ex.printStackTrace();
        }
    }

    private void moving(MouseEvent e){
        System.out.println("moving");

        Move move;
        Position start, destination;
            try {
                if (selectedPiece.colour.getAvailable() != (currentPlayer % 2) + 1) {
                    throw new Exception(selectedPiece.colour.getAvailable() + " should move a token");
                }

                destination = movement.whichPosition(new Position(e.getX(), e.getY()));
                start = movement.whichPosition(new Position(xBefore, yBefore));

                move = new Move(start.getPosNR(), destination.getPosNR());

                if (movement.isValidMove(move)) {

                    movement.move(start, destination, (currentPlayer % 2) + 1);

                    System.out.println("Player: " + currentPlayer++);

                    movement.printGameField();

                    selectedPiece.move(destination.getXCoord() / 64, destination.getYCoord() / 64);
                    jFrame.repaint();

                    xBefore = xAfter;
                    yBefore = yAfter;

                    xAfter = e.getX();
                    yAfter = e.getY();

                    System.out.println("Released: " + xAfter + " " + yAfter);
                } else {
                    throw new Exception("no valid move! start: " + start.getPosNR() + " destination: " + destination.getPosNR());
                }
            } catch (Exception ex) {
                System.out.println("moving back");
                selectedPiece.move(xBefore / 64, yBefore / 64);
                jFrame.repaint();
                ex.printStackTrace();
            }
//        }
    }

    private void taking(MouseEvent e){
//        System.out.println("taking");
        try {
//            System.out.println("TRY:");
            Position position = new Position(selectedPiece.getX(), selectedPiece.getY());
//            System.out.println("1");
            Random rand = new Random();
//            System.out.println("2");
            int i = selectedPiece.colour.getAvailable();
//            System.out.println("3 Player: " + i);
            if (i != currentPlayer){
                System.out.println("taking");
                System.out.println("Position Object of selected Piece: "+ position.toString());
                movement.take(movement.whichPosition(new Position(e.getX(),e.getY())));
                position.setAvailable(0);
                selectedPiece.move(rand.nextInt( 1350)+1620, rand.nextInt(110)+320);
                jFrame.repaint();
                movement.printGameField();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            xBefore = e.getX();
            yBefore = e.getY();
            System.out.println("Taking pressed: " +xBefore + " " + yBefore);
        }

    }

    public static void setTaking(boolean taking) {
        MainField.taking = taking;
    }

    public static boolean isTaking() {
        return taking;
    }

    private void setImage(){
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "token_white.png");
        Path path1 = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "token_black.png");

        try {
            imageWhite = ImageIO.read(new File(String.valueOf(path))).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
            imageBlack = ImageIO.read(new File(String.valueOf(path1))).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JPanel renderJPanel(){
        JPanel jPanel = new JPanel(){
            @Override
            public void paint(Graphics g) {

                Graphics2D g2 = (Graphics2D) g;

                RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHints(rh);


                g2.drawImage(img, 0, 0, jFrame.getWidth(), jFrame.getHeight(), this);


                for (Piece p:
                        pieceListPlayState) {
                    if (p.getColour().getAvailable() == 1){
                        g2.drawImage(imageWhite, p.getX(), p.getY(), this);
                    }else
                        g2.drawImage(imageBlack, p.getX(), p.getY(), this);
                }

            }
        };
        return jPanel;
    }

    public static void main(String[] args) {
        new MainField();
    }
}
