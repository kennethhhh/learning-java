import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Stream {
    public static void main(String[] args){
        List<Point> listofPoints= new LinkedList<>();

        try{
            Scanner scanner=new Scanner(new File("initialPoints.txt"));


            //Adding the lines from initialPoints.txt to a list
            while (scanner.hasNext()){
                String next = scanner.nextLine();
                String[] nums = next.split(",");

                listofPoints.add(new Point(Double.valueOf(nums[0]),Double.valueOf(nums[1]),Double.valueOf(nums[2])));
                scanner.nextLine();
            }
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }

        for (Point x:listofPoints){
            System.out.println(x.getX()+", "+x.getY()+ ", "+ x.getZ());
        }
        //Remove all points that have a z value > 2.0.
        List<Point> z_list =  listofPoints.stream()
                              .filter(x -> x.getZ()<=2.0)
                              .collect(Collectors.toList());

        //Scale down all the points by 0.5
        List<Point> scaled = z_list.stream()
                        .map(x->new Point(x.getX()*0.5,x.getY()*0.5,x.getZ()*0.5))
                        .collect(Collectors.toList());

        //Translate all the points by {-150, -37}
        List<Point> translated = scaled.stream()
                        .map(x-> new Point(x.getX()-150,x.getY()-37,x.getZ()))
                        .collect(Collectors.toList());

        try{
            PrintStream out = new PrintStream("drawMe.txt");
            for (Point p : translated){
                out.println(p.getX()+", " + p.getY() + ", "+ p.getZ());
            }
        }
        catch (Exception e){System.out.println();}
    }
}
