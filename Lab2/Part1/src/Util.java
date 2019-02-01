import java.util.List;

public class Util {

    public static double perimeter(Circle c) {
        return (c.getRadius() * 2 * Math.PI);
    }

    public static double perimeter(Rectangle rectangle) {
        double length = Math.abs(rectangle.getBottomRight().getX() - rectangle.getTopLeft().getX());
        double height = Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY());
        return (2 * length + 2 * height);
    }

    public static double perimeter(Polygon p) {
        double totalperim = 0;
        List<Point> points = p.getPoints();

        for (int i = 0; i < points.size(); i++) {
            if (i == points.size() - 1) {
                double length = Math.abs(points.get(0).getX() - points.get(i).getX());
                double height = Math.abs(points.get(0).getY() - points.get(i).getY());
                double hypartners = Math.hypot(length, height);
                totalperim += hypartners;
            }
            else {
                double length = Math.abs(points.get(i + 1).getX() - points.get(i).getX());
                double height = Math.abs(points.get(i + 1).getY() - points.get(i).getY());
                double hypartners = Math.hypot(length, height);
                totalperim += hypartners;
            }
        }
        return totalperim;
    }
}