package at.kaindorf.mill.beans;

import at.kaindorf.mill.gui.game.MainField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.Set;

@Getter
@Setter
public class Piece {
    private int xp;
    private int yp;
    public int x;
    public int y;
    public GamePieceColour colour;
    private Set<Piece> ps;
    private MainField game;

    public Piece(int xp, int yp, GamePieceColour colour, Set<Piece> ps, MainField game) {
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

    @Override
    public String toString() {
        return "Piece{" +
                "xp=" + xp +
                ", yp=" + yp +
                ", x=" + x +
                ", y=" + y +
                ", colour=" + colour +
                ", ps=" + ps +
                ", game=" + game +
                '}';
    }
}
