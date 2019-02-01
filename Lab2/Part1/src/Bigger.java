public class Bigger {
    public static double whichIsBigger(Circle c, Rectangle r, Polygon p) {
        double circlePerimeter = Util.perimeter(c);
        double rectanglePerimeter = Util.perimeter(r);
        double polygonPerimeter = Util.perimeter(p);


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