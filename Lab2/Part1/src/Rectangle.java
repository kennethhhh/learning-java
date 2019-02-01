public class Rectangle {
    private final Point topLeft;
    private final Point bottomRight;

    public Rectangle(Point tL, Point bR){
        this.topLeft=tL;
        this.bottomRight=bR;
    }

    public Point getTopLeft(){return topLeft;}

    public Point getBottomRight(){return bottomRight;}
}
