public class Circle {
    private final Point center;
    private final double radius;

    public Circle(Point c, double r){
        this.center=c;
        this.radius=r;
    }

    public Point getCenter(){return center;}

    public double getRadius(){return radius;}

    public double perimeter() {
        return (this.getRadius() * 2 * Math.PI);
    }

    private double whichIsBigger(Rectangle r, Polygon p) {
        double circlePerimeter = this.perimeter();
        double rectanglePerimeter = r.perimeter();
        double polygonPerimeter = p.perimeter();


        if (circlePerimeter > rectanglePerimeter) {
            if (circlePerimeter > polygonPerimeter) {
                return circlePerimeter;
            }
        }
        if (rectanglePerimeter > polygonPerimeter) {
            return rectanglePerimeter;
        }
        return polygonPerimeter;

    }
}
