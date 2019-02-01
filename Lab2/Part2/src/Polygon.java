import java.util.*;

public class Polygon {
   private ArrayList<Point> vertices;

   public Polygon(List<Point> points) {
      vertices = new ArrayList<Point>();
      for (int i=0; i<points.size(); i++) {
         vertices.add(points.get(i));
      }
   }

   public List<Point> getPoints(){return vertices;}

   public double perimeter() {
      double totalperim = 0;
      List<Point> points = this.getPoints();

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

   private double whichIsBigger(Circle c, Rectangle r) {
      double circlePerimeter = c.perimeter();
      double rectanglePerimeter = r.perimeter();
      double polygonPerimeter = this.perimeter();


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
