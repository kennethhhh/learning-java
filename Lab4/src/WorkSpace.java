import jdk.nashorn.internal.runtime.SharedPropertyMap;
import java.awt.*;


import java.util.ArrayList;
import java.util.List;

public class WorkSpace {
    private List<Shape> shapes;

    public WorkSpace(){
        this.shapes = new ArrayList<>();
    }

    public void add(Shape s){
        this.shapes.add(s);
    }

    public Shape get(int index){
        return this.shapes.get(index);
    }

    public int size(){
        return this.shapes.size();
    }

    public List<Circle> getCircles(){
        List<Circle> crcls = new ArrayList<Circle>();

        for (Shape i : this.shapes){
            if (i instanceof Circle){
                crcls.add((Circle)i);
            }
        }
        return crcls;
    }

    public List<Rectangle> getRectangles(){
        List<Rectangle> rct = new ArrayList<Rectangle>();

        for (Shape i : this.shapes){
            if (i instanceof Rectangle){
                rct.add((Rectangle) i);
            }
        }
        return rct;
    }

    public List<Triangle> getTriangles(){
        List<Triangle> tri = new ArrayList<Triangle>();

        for (Shape i : this.shapes){
            if (i instanceof Triangle){
                tri.add((Triangle) i);
            }
        }
        return tri;
    }

    public List<ConvexPolygon> getConvexPolygons(){
        List<ConvexPolygon> cp = new ArrayList<ConvexPolygon>();

        for (Shape i : this.shapes){
            if (i instanceof ConvexPolygon){
                cp.add((ConvexPolygon) i);
            }
        }
        return cp;
    }

    public List<Shape> getShapesByColor(Color color){
        List<Shape> samecolorshape_list= new ArrayList<Shape>();

        for (Shape i : this.shapes){
            if (i.getColor().equals(color)){
                samecolorshape_list.add(i);
            }
        }
        return samecolorshape_list;
    }

    public double getAreaOfAllShapes(){
        double total=0;
        for (Shape i: this.shapes){
            total+=i.getArea();
        }
        return total;
    }

    public double getPerimeterOfAllShapes(){
        double total=0;
        for (Shape i : this.shapes){
            total+=i.getPerimeter();
        }
        return total;
    }
}
