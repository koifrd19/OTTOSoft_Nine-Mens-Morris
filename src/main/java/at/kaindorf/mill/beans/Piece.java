package at.kaindorf.mill.beans;

import at.kaindorf.mill.gui.MainField;
import lombok.Data;

import java.util.LinkedList;

@Data
public class Piece {
    private int xp;
    private int yp;
    private int x;
    private int y;
    private GamePieceState colour;
    private LinkedList<Piece> ps;
    private MainField game;

    public Piece(int xp, int yp, GamePieceState colour, LinkedList<Piece> ps, MainField game) {
        this.xp = xp;
        this.yp = yp;
        x=xp*64 -22;
        y=yp*64 - 8;
        this.colour = colour;
        this.ps=ps;
        this.game = game;

        ps.add(this);
    }

    public void move(int xp,int yp){
        if(game.getPiece(xp * 64, yp * 64)!=null){
            //if(game.getPiece(xp64, yp64).colour!=colour){
            game.getPiece(xp * 64, yp * 64);
            //}else{
            x=this.xp * 64 - 22;
            y=this.yp * 64 - 8;
            return;
            //}
        }
        this.xp=xp;
        this.yp=yp;
        x=xp * 64 -22;
        y=yp * 64 - 8;
    }

}
