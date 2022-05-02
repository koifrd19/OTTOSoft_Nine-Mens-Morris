package at.kaindorf.mill;

import at.kaindorf.logic.beans.GamePiece;
import at.kaindorf.logic.beans.Position;
import at.kaindorf.logic.moves.Movement;

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

public class MillGame {

    private Movement movement;

    private int xBefore, yBefore, xAfter, yAfter;
    private int currentPlayer= 2;
    private LinkedList<Piece> pieceListPlayState = new LinkedList<>();
    private Piece selectedPiece = null;
    private JFrame jFrame = new JFrame("OttoSoft's Nine Men's morris");;
    private Image imageWhite = null;
    private Image imageBlack = null;
    private Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/nussbaum_background.png"));

    public JFrame getJFrame() {
        return jFrame;
    }

    public Piece getPiece(int x, int y){
        int xp = x/64;
        int yp = y/64;

        for (Piece p : pieceListPlayState){
            if (p.xp == xp && p.yp == yp){
                return p;
            }
        }
        return null;
    }

    public int getxBefore(){
        return xBefore;
    }

    public int getyBefore(){
        return yBefore;
    }

    public int getxAfter() {
        return xAfter;
    }

    public int getyAfter() {
        return yAfter;
    }

    public MillGame() {runLauncher();}

    private void runLauncher(){
        System.out.println("runLauncher();");
        try {
            System.out.println("new Movement();");
            movement = new Movement();
        } catch (IOException e) {
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


        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    private void createBlackPieces() {
        Piece black1 = new Piece(21,13, GamePiece.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black1);

        Piece black2 = new Piece(21,11,GamePiece.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black2);

        Piece black3 = new Piece(21,9,GamePiece.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black3);

        Piece black4 = new Piece(23,13,GamePiece.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black4);

        Piece black5 = new Piece(23,11,GamePiece.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black5);

        Piece black6 = new Piece(23,9,GamePiece.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black6);

        Piece black7 = new Piece(25,13,GamePiece.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black7);

        Piece black8 = new  Piece(25,11,GamePiece.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black8);

        Piece black9 = new  Piece(25,9,GamePiece.BLACK, pieceListPlayState,this);
        pieceListPlayState.add(black9);
    }

    private void createWhitePieces() {

        Piece white1 = new Piece(1,1,GamePiece.WHITE, pieceListPlayState, this);
        pieceListPlayState.add(white1);

        Piece white2 = new Piece(1,3,GamePiece.WHITE, pieceListPlayState, this);
        pieceListPlayState.add(white2);

        Piece white3 = new Piece(1,5,GamePiece.WHITE, pieceListPlayState, this);
        pieceListPlayState.add(white3);

        Piece white4 = new Piece(5,3,GamePiece.WHITE, pieceListPlayState, this);
        pieceListPlayState.add(white4);

        Piece white5 = new Piece(5,1,GamePiece.WHITE, pieceListPlayState, this);
        pieceListPlayState.add(white5);

        Piece white6 = new Piece(5,5,GamePiece.WHITE, pieceListPlayState, this);
        pieceListPlayState.add(white6);

        Piece white7 = new Piece(3,5,GamePiece.WHITE, pieceListPlayState, this);
        pieceListPlayState.add(white7);

        Piece white8 = new Piece(3,1,GamePiece.WHITE, pieceListPlayState,this);
        pieceListPlayState.add(white8);

        Piece white9 = new Piece(3,3,GamePiece.WHITE, pieceListPlayState,this);
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

                System.out.println("Pressed: " +xBefore + " " + yBefore);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Position position;
                System.out.println("Released: " +e.getX() + " " + e.getY());
                if (selectedPiece != null) {
                    try {
                        if (selectedPiece.colour.getAvailable() != (currentPlayer % 2) + 1) {
                            throw new Exception(selectedPiece.colour.getAvailable() +" should place a token" );
                        }
                        position = movement.whichPosition(new Position(e.getX(),e.getY()));
                        if (movement.isValidPlace(position)) {
                            movement.place(new Position(position.getXCoord(), position.getYCoord()), (currentPlayer % 2) + 1);
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
                            throw new Exception("no valid place");
                        }
                    } catch (Exception ex) {
                        System.out.println("moving back");
                        selectedPiece.move(xBefore /64,yBefore/64);
                        jFrame.repaint();
                        ex.printStackTrace();
                    }
                }
            }

        });

        jFrame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedPiece != null){
                    selectedPiece.x = e.getX() - 40;
                    selectedPiece.y = e.getY() - 64;
                    jFrame.repaint();
                }
            }

        });

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
                    if (p.colour.getAvailable() == 1){
                        g2.drawImage(imageWhite, p.x, p.y, this);
                    }else
                        g2.drawImage(imageBlack, p.x, p.y, this);
                }

            }
        };
        return jPanel;
    }

    /*public static void main(String[] args) {
        new MillGame().runLauncher();
    }*/
}
