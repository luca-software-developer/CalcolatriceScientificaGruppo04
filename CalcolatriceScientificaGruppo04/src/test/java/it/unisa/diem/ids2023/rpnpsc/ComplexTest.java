package it.unisa.diem.ids2023.rpnpsc;

import it.unisa.diem.ids2023.rpnpsc.exceptions.InvalidFormatException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComplexTest {

    public ComplexTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * UT-1.1.1 Test of getRealPart method, of class Complex.
     */
    @Test
    public void testGetRealPart() {
        System.out.println("getRealPart");
        Complex instance = new Complex(6.5, -4.8);
        double expResult = 6.5;
        double result = instance.getRealPart();
        assertEquals(expResult, result);
    }

    /**
     * UT-1.2.1 Test of setRealPart method, of class Complex.
     */
    @Test
    public void testSetRealPart() {
        System.out.println("setRealPart");
        double realPart = -8.4;
        Complex instance = new Complex(-7.3, 2.2);
        instance.setRealPart(realPart);
        assertEquals(instance.getRealPart(), realPart);
    }

    /**
     * UT-1.3.1 Test of getImaginaryPart method, of class Complex.
     */
    @Test
    public void testGetImaginaryPart() {
        System.out.println("getImaginaryPart");
        Complex instance = new Complex(1.4, -4.7);
        double expResult = -4.7;
        double result = instance.getImaginaryPart();
        assertEquals(expResult, result);
    }

    /**
     * UT-1.4.1 Test of setImaginaryPart method, of class Complex.
     */
    @Test
    public void testSetImaginaryPart() {
        System.out.println("setImaginaryPart");
        double imaginaryPart = 9.5;
        Complex instance = new Complex(-6.9, 8.4);
        instance.setImaginaryPart(imaginaryPart);
        assertEquals(instance.getImaginaryPart(), imaginaryPart);
    }

    /**
     * UT-1.5.1 Test of parse method #1, of class Complex.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParse1() throws Exception {
        System.out.println("parse #1");
        CharSequence text = "5.7";
        Complex expResult = new Complex(5.7);
        Complex result = Complex.parse(text);
        assertEquals(result.getRealPart(), expResult.getRealPart());
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart());
    }

    /**
     * UT-1.5.2 Test of parse method #2, of class Complex.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParse2() throws Exception {
        System.out.println("parse #2");
        CharSequence text = "+j";
        Complex expResult = new Complex(0, 1);
        Complex result = Complex.parse(text);
        assertEquals(result.getRealPart(), expResult.getRealPart());
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart());
    }

    /**
     * UT-1.5.3 Test of parse method #3, of class Complex.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParse3() throws Exception {
        System.out.println("parse #3");
        CharSequence text = "-j";
        Complex expResult = new Complex(0, -1);
        Complex result = Complex.parse(text);
        assertEquals(result.getRealPart(), expResult.getRealPart());
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart());
    }

    /**
     * UT-1.5.4 Test of parse method #4, of class Complex.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParse4() throws Exception {
        System.out.println("parse #4");
        CharSequence text = "-7.2j";
        Complex expResult = new Complex(0, -7.2);
        Complex result = Complex.parse(text);
        assertEquals(result.getRealPart(), expResult.getRealPart());
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart());
    }

    /**
     * UT-1.5.5 Test of parse method #5, of class Complex.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParse5() throws Exception {
        System.out.println("parse #5");
        CharSequence text = "-1.4-2.6j";
        Complex expResult = new Complex(-1.4, -2.6);
        Complex result = Complex.parse(text);
        assertEquals(result.getRealPart(), expResult.getRealPart());
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart());
    }

    /**
     * UT-1.5.6 Test of parse method #6, of class Complex.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParse6() throws Exception {
        System.out.println("parse #6");
        CharSequence text = "-2.5-3.7";
        assertThrows(InvalidFormatException.class, () -> {
            Complex.parse(text);
        });
    }

    /**
     * UT-1.6.1 Test of add method #1, of class Complex.
     */
    @Test
    public void testAdd1() {
        System.out.println("add #1");
        Complex other = new Complex(1.3, -0.6);
        Complex instance = new Complex(3.2, 2.6);
        Complex expResult = new Complex(4.5, 2.0);
        Complex result = instance.add(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.6.2 Test of add method #2, of class Complex.
     */
    @Test
    public void testAdd2() {
        System.out.println("add #2");
        Complex other = new Complex(0);
        Complex instance = new Complex(2.1, 1.4);
        Complex expResult = new Complex(2.1, 1.4);
        Complex result = instance.add(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.6.3 Test of add method #3, of class Complex.
     */
    @Test
    public void testAdd3() {
        System.out.println("add #3");
        Complex other = new Complex(-7.6);
        Complex instance = new Complex(3.9);
        Complex expResult = new Complex(-3.7);
        Complex result = instance.add(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.6.4 Test of add method #4, of class Complex.
     */
    @Test
    public void testAdd4() {
        System.out.println("add #4");
        Complex other = new Complex(0, -6.5);
        Complex instance = new Complex(0, 2.5);
        Complex expResult = new Complex(0, -4.0);
        Complex result = instance.add(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.7.1 Test of subtract method #1, of class Complex.
     */
    @Test
    public void testSubtract1() {
        System.out.println("subtract #1");
        Complex other = new Complex(3.2, 1.4);
        Complex instance = new Complex(3.9, -7.2);
        Complex expResult = new Complex(0.7, -8.6);
        Complex result = instance.subtract(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.7.2 Test of subtract method #2, of class Complex.
     */
    @Test
    public void testSubtract2() {
        System.out.println("subtract #2");
        Complex other = new Complex(0.9, -5.1);
        Complex instance = new Complex(0);
        Complex expResult = new Complex(-0.9, 5.1);
        Complex result = instance.subtract(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.7.3 Test of subtract method #3, of class Complex.
     */
    @Test
    public void testSubtract3() {
        System.out.println("subtract #3");
        Complex other = new Complex(4.1);
        Complex instance = new Complex(-2.1);
        Complex expResult = new Complex(-6.2);
        Complex result = instance.subtract(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.7.4 Test of subtract method #4, of class Complex.
     */
    @Test
    public void testSubtract4() {
        System.out.println("subtract #4");
        Complex other = new Complex(0, -9.8);
        Complex instance = new Complex(0, 4.7);
        Complex expResult = new Complex(0, 14.5);
        Complex result = instance.subtract(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.8.1 Test of multiply method #1, of class Complex.
     */
    @Test
    public void testMultiply1() {
        System.out.println("multiply #1");
        Complex other = new Complex(-3.9, 2.2);
        Complex instance = new Complex(6.5, -7.3);
        Complex expResult = new Complex(-9.29, 42.77);
        Complex result = instance.multiply(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.8.2 Test of multiply method #2, of class Complex.
     */
    @Test
    public void testMultiply2() {
        System.out.println("multiply #2");
        Complex other = new Complex(0);
        Complex instance = new Complex(-5.4, 6.1);
        Complex expResult = new Complex(0);
        Complex result = instance.multiply(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.8.3 Test of multiply method #3, of class Complex.
     */
    @Test
    public void testMultiply3() {
        System.out.println("multiply #3");
        Complex other = new Complex(-2.3);
        Complex instance = new Complex(9.1);
        Complex expResult = new Complex(-20.93);
        Complex result = instance.multiply(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.8.4 Test of multiply method #4, of class Complex.
     */
    @Test
    public void testMultiply4() {
        System.out.println("multiply #4");
        Complex other = new Complex(0, -4.6);
        Complex instance = new Complex(0, 3.8);
        Complex expResult = new Complex(17.48, 0);
        Complex result = instance.multiply(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.9.1 Test of divide method #1, of class Complex.
     */
    @Test
    public void testDivide1() {
        System.out.println("divide #1");
        Complex other = new Complex(14.68, 0);
        Complex instance = new Complex(0, -7.34);
        Complex expResult = new Complex(0, -0.5);
        Complex result = instance.divide(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.9.2 Test of divide method #2, of class Complex.
     */
    @Test
    public void testDivide2() {
        System.out.println("divide #2");
        Complex other = new Complex(0, -8.45);
        Complex instance = new Complex(0);
        Complex expResult = new Complex(0);
        Complex result = instance.divide(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.9.3 Test of divide method #3, of class Complex.
     */
    @Test
    public void testDivide3() {
        System.out.println("divide #3");
        Complex other = new Complex(2.26);
        Complex instance = new Complex(-5.11);
        Complex expResult = new Complex(-2.261061946902656);
        Complex result = instance.divide(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.9.4 Test of divide method #4, of class Complex.
     */
    @Test
    public void testDivide4() {
        System.out.println("divide #4");
        Complex other = new Complex(0, -1.45);
        Complex instance = new Complex(0, 3.89);
        Complex expResult = new Complex(-2.682758620689655);
        Complex result = instance.divide(other);
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.9.5 Test of divide method #5, of class Complex.
     */
    @Test
    public void testDivide5() {
        System.out.println("divide #5");
        Complex other = new Complex(0);
        Complex instance = new Complex(-6.78, -3.37);
        assertThrows(ArithmeticException.class, () -> {
            instance.divide(other);
        });
    }

    /**
     * UT-1.10.1 Test of getOpposite method #1, of class Complex.
     */
    @Test
    public void testGetOpposite1() {
        System.out.println("getOpposite #1");
        Complex instance = new Complex(0.44, -0.92);
        Complex expResult = new Complex(-0.44, 0.92);
        Complex result = instance.getOpposite();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.10.2 Test of getOpposite method #2, of class Complex.
     */
    @Test
    public void testGetOpposite2() {
        System.out.println("getOpposite #2");
        Complex instance = new Complex(0);
        Complex expResult = new Complex(0);
        Complex result = instance.getOpposite();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.10.3 Test of getOpposite method #3, of class Complex.
     */
    @Test
    public void testGetOpposite3() {
        System.out.println("getOpposite #3");
        Complex instance = new Complex(-3.26);
        Complex expResult = new Complex(3.26);
        Complex result = instance.getOpposite();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.10.4 Test of getOpposite method #4, of class Complex.
     */
    @Test
    public void testGetOpposite4() {
        System.out.println("getOpposite #4");
        Complex instance = new Complex(0, 1.31);
        Complex expResult = new Complex(0, -1.31);
        Complex result = instance.getOpposite();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.11.1 Test of getSquareRoot method #1, of class Complex.
     */
    @Test
    public void testGetSquareRoot1() {
        System.out.println("getSquareRoot #1");
        Complex instance = new Complex(0.16);
        Complex expResult = new Complex(0.4);
        Complex result = instance.getSquareRoot();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.11.2 Test of getSquareRoot method #2, of class Complex.
     */
    @Test
    public void testGetSquareRoot2() {
        System.out.println("getSquareRoot #2");
        Complex instance = new Complex(0, 0.64);
        Complex expResult = new Complex(0.565685424949238, 0.565685424949238);
        Complex result = instance.getSquareRoot();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.11.3 Test of getSquareRoot method #3, of class Complex.
     */
    @Test
    public void testGetSquareRoot3() {
        System.out.println("getSquareRoot #3");
        Complex instance = new Complex(0, -0.46);
        Complex expResult = new Complex(-0.479583152331272, 0.479583152331272);
        Complex result = instance.getSquareRoot();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.11.4 Test of getSquareRoot method #4, of class Complex.
     */
    @Test
    public void testGetSquareRoot4() {
        System.out.println("getSquareRoot #4");
        Complex instance = new Complex(7.68, 0);
        Complex expResult = new Complex(2.771281292110204);
        Complex result = instance.getSquareRoot();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.11.5 Test of getSquareRoot method #5, of class Complex.
     */
    @Test
    public void testGetSquareRoot5() {
        System.out.println("getSquareRoot #5");
        Complex instance = new Complex(6.58, -4.82);
        Complex expResult = new Complex(-2.714453800058155, 0.887839756177972);
        Complex result = instance.getSquareRoot();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.11.6 Test of getSquareRoot method #6, of class Complex.
     */
    @Test
    public void testGetSquareRoot6() {
        System.out.println("getSquareRoot #6");
        Complex instance = new Complex(-3.71);
        Complex expResult = new Complex(0, 1.926136028425822);
        Complex result = instance.getSquareRoot();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-1.12.1 Test of toString method #1, of class Complex.
     */
    @Test
    public void testToString1() {
        System.out.println("toString #1");
        Complex instance = new Complex(-1.24);
        String expResult = "-1.24";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * UT-1.12.2 Test of toString method #2, of class Complex.
     */
    @Test
    public void testToString2() {
        System.out.println("toString #2");
        Complex instance = new Complex(-2.35, 0.26);
        String expResult = "-2.35+0.26j";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * UT-1.12.3 Test of toString method #3, of class Complex.
     */
    @Test
    public void testToString3() {
        System.out.println("toString #3");
        Complex instance = new Complex(0, -1.32);
        String expResult = "-1.32j";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * UT-1.12.4 Test of toString method #4, of class Complex.
     */
    @Test
    public void testToString4() {
        System.out.println("toString #4");
        Complex instance = new Complex(0, 1);
        String expResult = "j";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * UT-1.12.5 Test of toString method #5, of class Complex.
     */
    @Test
    public void testToString5() {
        System.out.println("toString #5");
        Complex instance = new Complex(0, -1);
        String expResult = "-j";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
