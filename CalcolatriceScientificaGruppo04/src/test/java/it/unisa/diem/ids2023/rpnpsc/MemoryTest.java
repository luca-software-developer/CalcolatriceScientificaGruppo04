package it.unisa.diem.ids2023.rpnpsc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryTest {

    private final Memory instance;

    public MemoryTest() {
        instance = Memory.getInstance();
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
     * UT-3.1.1 Test of getInstance method, of class Memory.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Memory result = Memory.getInstance();
        assertTrue(result instanceof Memory);
    }

    /**
     * UT-3.2.1 Test of getVariable method #1, of class Memory.
     */
    @Test
    public void testGetVariable1() {
        System.out.println("getVariable #1");
        char variable = 'z';
        Complex result = instance.getVariable(variable);
        assertNull(result);
    }

    /**
     * UT-3.2.2 Test of getVariable method #2, of class Memory.
     */
    @Test
    public void testGetVariable2() {
        System.out.println("getVariable #2");
        char variable = 'z';
        Complex complex = new Complex(0.67);
        instance.setVariable(variable, complex);
        Complex result = instance.getVariable(variable);
        assertEquals(result.getRealPart(), complex.getRealPart());
        assertEquals(result.getImaginaryPart(), complex.getImaginaryPart());
    }

    /**
     * UT-3.2.3 Test of getVariable method #3, of class Memory.
     */
    @Test
    public void testGetVariable3() {
        System.out.println("getVariable #3");
        char variable = 'z';
        Complex complex = new Complex(0, -0.45);
        instance.setVariable(variable, complex);
        Complex result = instance.getVariable(variable);
        assertEquals(result.getRealPart(), complex.getRealPart());
        assertEquals(result.getImaginaryPart(), complex.getImaginaryPart());
    }

    /**
     * UT-3.3.1 Test of setVariable method #1, of class Memory.
     */
    @Test
    public void testSetVariable1() {
        System.out.println("setVariable #1");
        char variable = 'a';
        Complex complex = new Complex(-9.22, 3.68);
        instance.setVariable(variable, complex);
        assertEquals(instance.getVariable(variable).getRealPart(), complex.getRealPart());
        assertEquals(instance.getVariable(variable).getImaginaryPart(), complex.getImaginaryPart());
    }

    /**
     * UT-3.3.2 Test of setVariable method #2, of class Memory.
     */
    @Test
    public void testSetVariable2() {
        System.out.println("setVariable #2");
        char variable = 'x';
        Complex complex = new Complex(-4.23);
        instance.setVariable(variable, complex);
        assertEquals(instance.getVariable(variable).getRealPart(), complex.getRealPart());
        assertEquals(instance.getVariable(variable).getImaginaryPart(), complex.getImaginaryPart());
    }

    /**
     * UT-3.3.3 Test of setVariable method #3, of class Memory.
     */
    @Test
    public void testSetVariable3() {
        System.out.println("setVariable #3");
        char variable = 'y';
        Complex complex = new Complex(0, 2.56);
        instance.setVariable(variable, complex);
        assertEquals(instance.getVariable(variable).getRealPart(), complex.getRealPart());
        assertEquals(instance.getVariable(variable).getImaginaryPart(), complex.getImaginaryPart());
    }

}
