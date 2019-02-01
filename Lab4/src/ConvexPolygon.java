import java.awt.*;

public class ConvexPolygon implements Shape {
    private Point[] vertices;
    private Color color;

    public ConvexPolygon(Point[] vertices, Color color){
        this.vertices=vertices;
        this.color=color;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getArea(){
        double determ1=0;
        double determ2=0;
        for (int i=0; i<this.vertices.length;i++){
            if (i==this.vertices.length-1){
                double temp = this.vertices[i].getX()*this.vertices[0].getY();
                determ1 += temp;
                double xd = this.vertices[i].getY()*this.vertices[0].getX();
                determ2 += xd;
            }
            else{
                double temp = this.vertices[i].getX()*this.vertices[i+1].getY();
                determ1 += temp;
                double xd = this.vertices[i].getY()*this.vertices[i+1].getX();
                determ2 += xd;
            }
        }
        return (determ1-determ2)*0.5;
    }

    public double getPerimeter() {
        double perimTotal=0;
        for (int i=0; i<this.vertices.length;i++){
            if (i==this.vertices.length-1){
                double distance=Math.sqrt(Math.pow(this.vertices[i].getX()-this.vertices[0].getX(),2)+Math.pow(this.vertices[i].getY()-this.vertices[0].getY(),2));
                perimTotal+=distance;
            }
            else {
                double distance = Math.sqrt(Math.pow(this.vertices[i].getX() - this.vertices[i + 1].getX(), 2) + Math.pow(this.vertices[i].getY() - this.vertices[i + 1].getY(), 2));
                perimTotal += distance;
            }
        }
        return perimTotal;
    }

    public void translate(Point p) {
        for (int i=0; i<this.vertices.length;i++){
            this.vertices[i].setLocation(vertices[i].getX()+p.getY(), vertices[i].getY()+p.getY());
        }
    }

    public Point getVertex(int index){
        return this.vertices[index];
    }
}
