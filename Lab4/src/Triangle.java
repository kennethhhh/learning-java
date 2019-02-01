import java.awt.*;

public class Triangle implements Shape {
    private Point a,b,c;
    private Color color;

    public Triangle(Point a, Point b, Point c, Color color){
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getArea(){
        double AB = Math.sqrt(Math.pow(a.getX()-b.getX(),2) + Math.pow(a.getY()-b.getY(),2));
        double BC = Math.sqrt(Math.pow(b.getX()-c.getX(),2) + Math.pow(b.getY()-c.getY(),2));
        double CA = Math.sqrt(Math.pow(c.getX()-a.getX(),2) + Math.pow(c.getY()-a.getY(),2));
        double S = (AB+BC+CA)/2;

        return Math.sqrt(S*(S-AB)*(S-BC)*(S-CA));
    }

    public double getPerimeter() {
        double AB = Math.sqrt(Math.pow(a.getX()-b.getX(),2) + Math.pow(a.getY()-b.getY(),2));
        double BC = Math.sqrt(Math.pow(b.getX()-c.getX(),2) + Math.pow(b.getY()-c.getY(),2));
        double CA = Math.sqrt(Math.pow(c.getX()-a.getX(),2) + Math.pow(c.getY()-a.getY(),2));
        return AB+BC+CA;
    }

    public void translate(Point p) {
        this.a.setLocation(this.a.getX()+p.getX(),this.a.getY()+p.getY());
        this.b.setLocation(this.b.getX()+p.getX(),this.b.getY()+p.getY());
        this.c.setLocation(this.c.getX()+p.getX(),this.c.getY()+p.getY());
    }

    public Point getVertexA(){return a;}

    public Point getVertexB(){return b;}

    public Point getVertexC(){return c;}
}
