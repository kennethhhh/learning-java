import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class PartTwoTestCases
{
   public static final double DELTA = 0.00001;

   @Test
   public void testCircleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getCenter", "getRadius", "perimeter");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Point.class, double.class, double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[0], new Class[0]);

      verifyImplSpecifics(Circle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testRectangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getTopLeft", "getBottomRight", "perimeter");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Point.class, Point.class, double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[0], new Class[0]);

      verifyImplSpecifics(Rectangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testPolygonImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getPoints", "perimeter");

      final List<Class> expectedMethodReturns = Arrays.asList(
         List.class, double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[0]);

      verifyImplSpecifics(Polygon.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testUtilCircle() {
      Circle y = new Circle(new Point(5,0),0);
      double d = y.perimeter();
      assertEquals(0, d, DELTA);
   }

   @Test
   public void testUtilCircle2() {
      Circle y = (new Circle(new Point(4.0,4.0),10.0));
      double d = y.perimeter();
      assertEquals(62.83185307, d, DELTA);
   }

   @Test
   public void testUtilCircle3() {
      Circle y = new Circle(new Point(-5.0,4.0),10.0);
      double d = y.perimeter();
      assertEquals(62.83185307, d, DELTA);
   }

   @Test
   public void testUtilRectangle() {
      Rectangle y = new Rectangle(new Point(0.0,4.0),new Point(4.0,0.0));
      double d = y.perimeter();
      assertEquals(16.0, d, DELTA);
   }

   @Test
   public void testUtilRectangle2() {
      Rectangle y = new Rectangle(new Point(0.0,5.0),new Point(4.0,0.0));
      double d = y.perimeter();
      assertEquals(18.0, d, DELTA);
   }

   @Test
   public void testUtilRectangle3() {
      Rectangle y = new Rectangle(new Point(4.0,4.0),new Point(0.0,0.0));
      double d = y.perimeter();
      assertEquals(16.0, d, DELTA);
   }

   @Test
   public void testPerimPoly() {
      List < Point >points = new ArrayList <>();
      points.add(new Point(0, 0));
      points.add(new Point(3,0));
      points.add(new Point(0,4));
      Polygon y = new Polygon(points);
      double d = y.perimeter();
      assertEquals(12.0, d, DELTA);
   }

   @Test
   public void testPerimPoly2() {
      List < Point >points = new ArrayList <>();
      points.add(new Point(-4, 0));
      points.add(new Point(4,0));
      points.add(new Point(4,4));
      points.add(new Point(-4,4));
      Polygon y = new Polygon(points);
      double d = y.perimeter();
      assertEquals(24.0, d, DELTA);
   }

   @Test
   public void testPerimPoly3() {
      List < Point >points = new ArrayList<>();
      points.add(new Point(-4, 0));
      points.add(new Point(4,0));
      points.add(new Point(5,4));
      points.add(new Point(0,6));
      points.add(new Point(-5,4));
      Polygon y = new Polygon(points);
      double d = y.perimeter();
      assertEquals(27.01654087, d, DELTA);
   }

   private static void verifyImplSpecifics(
      final Class<?> clazz,
      final List<String> expectedMethodNames,
      final List<Class> expectedMethodReturns,
      final List<Class[]> expectedMethodParameters)
      throws NoSuchMethodException
   {
      assertEquals("Unexpected number of public fields",
         0, Point.class.getFields().length);

      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()))
            .collect(Collectors.toList());

      assertEquals("Unexpected number of public methods",
         expectedMethodNames.size(), publicMethods.size());

      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodReturns.size());
      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodParameters.size());

      for (int i = 0; i < expectedMethodNames.size(); i++)
      {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
            expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }
}
