package base;

public class Vecteur {
    public double x;
    public double y;

    public Vecteur(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public Point translation(Point A) {
        return new Point(
                x+A.x,
                y+A.y
        );
    }

    public Vecteur mult(double lambda) {
        return new Vecteur(lambda*x,lambda*y);
    }

    public double produitScalaire(Vecteur s){

        return x*s.x + y*s.y;
    }

    public double produitVectoriel(Vecteur s){
        return x*s.y - y*s.x;
    }
}
