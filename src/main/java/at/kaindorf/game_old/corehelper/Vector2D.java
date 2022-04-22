package at.kaindorf.game_old.corehelper;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void multiply(double speed) {
        x *= speed;
        y *= speed;
    }

    // Berechnet Länge des Vektors
    public double length(){
        return Math.sqrt(x * x + y * y);
    }

    //Fixed Vektorlaenge happy Walzl
    public void normalize(){
        double length = length();

        x = x == 0 ? 0 : x / length;
        y = y == 0 ? 0 : y / length;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


}
