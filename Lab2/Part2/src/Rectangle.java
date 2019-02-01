public class Rectangle {
    private final Point topLeft;
    private final Point bottomRight;

    public Rectangle(Point tL, Point bR){
        this.topLeft=tL;
        this.bottomRight=bR;
    }

    public Point getTopLeft(){return topLeft;}

    public Point getBottomRight(){return bottomRight;}

    public double perimeter() {
        double length = Math.abs(this.getBottomRight().getX() - this.getTopLeft().getX());
        double height = Math.abs(this.getTopLeft().getY() - this.getBottomRight().getY());
        return (2 * length + 2 * height);
    }

    private double whichIsBigger(Circle c, Polygon p) {
        double circlePerimeter = c.perimeter();
        double rectanglePerimeter = this.perimeter();
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
