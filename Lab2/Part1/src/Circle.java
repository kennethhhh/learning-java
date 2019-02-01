public class Circle {
    private final Point center;
    private final double radius;

    public Circle(Point c, double r){
        this.center=c;
        this.radius=r;
    }

    public Point getCenter(){return center;}

    public double getRadius(){return radius;}
}
