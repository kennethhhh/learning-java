import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestCases
{
   public static final double DELTA = 0.00001;

   /*
    * This test is just to get you started.
    */
   @Test
   public void testGetX()
   {
      assertEquals(1.0, new Point(1.0, 2.0).getX(), DELTA);
   }

   @Test
   public void testGetY() {assertEquals(2.0, new Point(1.0,2.0).getY(), DELTA);}

   @Test
   public void testGetRadius() { assertEquals(1.0, new Point(1.0,0.0).getRadius(), DELTA);}
   public void testGetRadius2() { assertEquals(1.414213562, new Point(1.0,1.0).getRadius(), DELTA);}
   public void testGetRadius3() { assertEquals(1.414213562, new Point(-1.0,1.0).getRadius(), DELTA);}
   public void testGetRadius4() { assertEquals(1.414213562, new Point(-1.0,-1.0).getRadius(), DELTA);}
   public void testGetRadius5() { assertEquals(1.414213562, new Point(1.0,-1.0).getRadius(), DELTA);}

   @Test
   public void testGetAngle() { assertEquals(0.7853981633974483, new Point(1.0,1.0).getAngle(), DELTA);}
   public void testGetAngle2() { assertEquals(1.9936502529278375, new Point(100.0,-45.0).getAngle(), DELTA);}
   public void testGetAngle3() { assertEquals(-0.7853981633974483, new Point(-1.0,1.0).getAngle(), DELTA);}
   public void testGetAngle4() { assertEquals(-0.09966865249116204, new Point(-10.0,100.0).getAngle(), DELTA);}

   @Test
   public void testRotato90() { assertEquals(-2.0, new Point(1.0,2.0).rotate90().getX(), DELTA);}
   public void testRotato90B() { assertEquals(1.0, new Point(1.0,2.0).rotate90().getY(), DELTA);}
   public void testRotato90C() { assertEquals(-3.0, new Point(100.0,3.0).rotate90().getX(), DELTA);}
   public void testRotato90D() { assertEquals(100.0, new Point(100.0,3.0).rotate90().getY(), DELTA);}
   public void testRotato90E() { assertEquals(0.0, new Point(0.0,0.0).rotate90().getX(), DELTA);}
   public void testRotato90F() { assertEquals(0.0, new Point(0.0,0.0).rotate90().getY(), DELTA);}
   public void testRotato90G() { assertEquals(1.0, new Point(0.0,-1.0).rotate90().getX(), DELTA);}
   public void testRotato90H() { assertEquals(0.0, new Point(0.0,-1.0).rotate90().getY(), DELTA);}

   /*
    * The tests below here are to verify the basic requirements regarding
    * the "design" of your class.  These are to remain unchanged.
    */

   @Test
   public void testImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getX",
         "getY",
         "getRadius",
         "getAngle",
         "rotate90"
         );

      final List<Class> expectedMethodReturns = Arrays.asList(
         double.class,
         double.class,
         double.class,
         double.class,
         Point.class
         );

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0],
         new Class[0],
         new Class[0],
         new Class[0],
         new Class[0]
         );

      verifyImplSpecifics(Point.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
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
