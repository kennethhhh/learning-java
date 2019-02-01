import java.awt.*;
import java.util.ArrayList;

public class Rectangle implements Shape {
    private Point topLeftCorner;
    private double width;
    private double height;
    private Color color;

    public Rectangle(Point topLeftCorner, double width, double height, Color color){
        this.topLeftCorner = topLeftCorner;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getArea(){
        return this.width*this.height;
    }

    public double getPerimeter() {
        return 2*this.height+2*this.width;
    }

    public void translate(Point p) {
        this.topLeftCorner.setLocation(this.topLeftCorner.getX()+p.getX(),this.topLeftCorner.getY()+p.getY());
    }

    public double getWidth(){return this.width;}

    public void setWidth(double W){this.width = W;}

    public double getHeight(){return this.height;}

    public void setHeight(double H){this.height = H;}

    public Point getTopLeft(){return this.topLeftCorner;}


}
