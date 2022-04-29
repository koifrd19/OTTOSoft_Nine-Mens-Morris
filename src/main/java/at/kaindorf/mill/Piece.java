package at.kaindorf.mill;

import java.util.LinkedList;

public class Piece {
    int xp;
    int yp;
    int x;
    int y;
    boolean isWhite;
    boolean isPlaced;
    LinkedList<Piece> ps;
    MillGame game;

    public Piece(int xp, int yp, boolean isWhite, LinkedList<Piece> ps, MillGame game) {
        this.xp = xp;
        this.yp = yp;
        x=xp*64 -22;
        y=yp*64 - 8;
        this.isWhite = isWhite;
        this.ps=ps;
        this.game = game;

        ps.add(this);
    }

    public void move(int xp,int yp){
        if(game.getPiece(xp*64, yp*64)!=null){
            if(game.getPiece(xp*64, yp*64).isWhite!=isWhite){
                game.getPiece(xp*64, yp*64);
            }else{
                x=this.xp*64;
                y=this.yp*64;
                return;
            }
        }
        this.xp=xp;
        this.yp=yp;
        x=xp*64 -22;
        y=yp*64 - 8;
    }

}
