public class Point {
   private double x;
   private double y;

   public Point(double x, double y) {
      this.x = x;
      this.y = y;
   }

   public double getX() {
      return x;
   }

   public double getY() {
      return y;
   }

   public double getRadius(){
      //Returns the distance from the origin to the point.
      return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
   }

   public double getAngle(){
    /*
        Returns the counterclockwise angle (in radians) from the x-axis.
        Remember your trigonometry - the tangent of and angle is opposite
        divided by adjacent.  And if you haven't learned about the atan2( )
        method in the Math library this is a good time.
    */
      return Math.atan2(y,x); //returns in radians
   }

   public Point rotate90(){
    /*
    Returns a newly created Point representing a 90-degree (counterclockwise)
    rotation of this point about the origin. Consider drawing a picture for a
    point not on an axis as a guide for how you might implement this operation
    (hint: there is a solution that does not require any sophisticated computations).
    If you take the Graphics class you'll learn that for a rotation around the
    origin by an angle T, x' = xcosT - ysinT and y' = xsinT + ycosT.
    */
      return new Point(-y,x);
   }
}

   
