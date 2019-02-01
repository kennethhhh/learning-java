import java.awt.*;

public class Circle implements Shape {
    private Point center;
    private double radius;
    private Color color;

    public Circle(Point center, double radius, Color color){
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getArea(){
        return Math.PI*Math.pow(this.radius,2);
    }

    public double getPerimeter() {
        return 2*Math.PI*this.radius;
    }

    public void translate(Point p) {
        this.center.setLocation(this.center.getX()+p.getX(),this.center.getY()+p.getY());
    }

    public double getRadius(){return this.radius;}

    public void setRadius(double r){this.radius = r;}

    public Point getCenter(){return this.center;}
}
