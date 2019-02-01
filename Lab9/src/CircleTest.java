public class CircleTest {
    public static void main(String[] args)
    {
        //Circle c = new Circle(-10);
        try
        {
            Circle c1 = new Circle(-10);
            System.out.println(c1);
        }
        catch (NegativeRadiusException e){
            System.out.println(e.getMessage() + ": " + e.radius());
        }
        catch (CircleException e) {
            System.out.println(e.getMessage());
            return;
        }
        finally {
            System.out.println("In finally.");
        }
        System.out.println("Done.");
    }
}
