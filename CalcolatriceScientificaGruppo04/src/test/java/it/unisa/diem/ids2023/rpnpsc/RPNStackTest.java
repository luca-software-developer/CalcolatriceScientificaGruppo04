package it.unisa.diem.ids2023.rpnpsc;

import it.unisa.diem.ids2023.rpnpsc.exceptions.EmptyStackException;
import it.unisa.diem.ids2023.rpnpsc.exceptions.InsufficientArgumentsException;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RPNStackTest {

    public RPNStackTest() {
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
     * UT-2.1.1 Test of getObservableList method #1, of class RPNStack.
     */
    @Test
    public void testGetObservableList1() {
        System.out.println("getObservableList #1");
        RPNStack instance = new RPNStack();
        ObservableList<String> result = instance.getObservableList();
        assertTrue(result instanceof ObservableList);
    }

    /**
     * UT-2.1.2 Test of getObservableList method #2, of class RPNStack.
     */
    @Test
    public void testGetObservableList2() {
        System.out.println("getObservableList #2");
        RPNStack instance = new RPNStack();
        ObservableList<String> result = instance.getObservableList();
        assertTrue(result.isEmpty());
    }

    /**
     * UT-2.2.1 Test of isOperand method #1, of class RPNStack.
     */
    @Test
    public void testIsOperand1() {
        System.out.println("isOperand #1");
        String item = "-0.67j";
        boolean expResult = true;
        boolean result = RPNStack.isOperand(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.2.2 Test of isOperand method #2, of class RPNStack.
     */
    @Test
    public void testIsOperand2() {
        System.out.println("isOperand #2");
        String item = "+-";
        boolean expResult = false;
        boolean result = RPNStack.isOperand(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.3.1 Test of isBinaryOperator method #1, of class RPNStack.
     */
    @Test
    public void testIsBinaryOperator1() {
        System.out.println("isBinaryOperator #1");
        String item = "/";
        boolean expResult = true;
        boolean result = RPNStack.isBinaryOperator(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.3.2 Test of isBinaryOperator method #2, of class RPNStack.
     */
    @Test
    public void testIsBinaryOperator2() {
        System.out.println("isBinaryOperator #2");
        String item = "sqrt";
        boolean expResult = false;
        boolean result = RPNStack.isBinaryOperator(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.4.1 Test of isUnaryOperator method #1, of class RPNStack.
     */
    @Test
    public void testIsUnaryOperator1() {
        System.out.println("isUnaryOperator #1");
        String item = "+-";
        boolean expResult = true;
        boolean result = RPNStack.isUnaryOperator(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.4.2 Test of isUnaryOperator method #2, of class RPNStack.
     */
    @Test
    public void testIsUnaryOperator2() {
        System.out.println("isUnaryOperator #2");
        String item = "-";
        boolean expResult = false;
        boolean result = RPNStack.isUnaryOperator(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.5.1 Test of isOperator method #1, of class RPNStack.
     */
    @Test
    public void testIsOperator1() {
        System.out.println("isOperator #1");
        String item = "sqrt";
        boolean expResult = true;
        boolean result = RPNStack.isOperator(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.5.2 Test of isOperator method #2, of class RPNStack.
     */
    @Test
    public void testIsOperator2() {
        System.out.println("isOperator #2");
        String item = "*";
        boolean expResult = true;
        boolean result = RPNStack.isOperator(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.5.3 Test of isOperator method #3, of class RPNStack.
     */
    @Test
    public void testIsOperator3() {
        System.out.println("isOperator #3");
        String item = "-0.36-6.21j";
        boolean expResult = false;
        boolean result = RPNStack.isOperator(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.6.1 Test of isStackManipulation method #1, of class RPNStack.
     */
    @Test
    public void testIsStackManipulation1() {
        System.out.println("isStackManipulation #1");
        String item = "over";
        boolean expResult = true;
        boolean result = RPNStack.isStackManipulation(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.6.2 Test of isStackManipulation method #2, of class RPNStack.
     */
    @Test
    public void testIsStackManipulation2() {
        System.out.println("isStackManipulation #2");
        String item = "sqrt";
        boolean expResult = false;
        boolean result = RPNStack.isStackManipulation(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.6.3 Test of isStackManipulation method #3, of class RPNStack.
     */
    @Test
    public void testIsStackManipulation3() {
        System.out.println("isStackManipulation #3");
        String item = "-1.47+7.11j";
        boolean expResult = false;
        boolean result = RPNStack.isStackManipulation(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.7.1 Test of isVariableOperation method #1, of class RPNStack.
     */
    @Test
    public void testIsVariableOperation1() {
        System.out.println("isVariableOperation #1");
        String item = "-x";
        boolean expResult = true;
        boolean result = RPNStack.isVariableOperation(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.7.2 Test of isVariableOperation method #2, of class RPNStack.
     */
    @Test
    public void testIsVariableOperation2() {
        System.out.println("isVariableOperation #2");
        String item = ">y";
        boolean expResult = true;
        boolean result = RPNStack.isVariableOperation(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.7.3 Test of isVariableOperation method #3, of class RPNStack.
     */
    @Test
    public void testIsVariableOperation3() {
        System.out.println("isVariableOperation #3");
        String item = "z-";
        boolean expResult = false;
        boolean result = RPNStack.isVariableOperation(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.7.4 Test of isVariableOperation method #4, of class RPNStack.
     */
    @Test
    public void testIsVariableOperation4() {
        System.out.println("isVariableOperation #4");
        String item = "<<";
        boolean expResult = false;
        boolean result = RPNStack.isVariableOperation(item);
        assertEquals(expResult, result);
    }

    /**
     * UT-2.8.1 Test of solve method #1, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSolve1() throws Exception {
        System.out.println("solve #1");
        RPNStack instance = new RPNStack();
        assertThrows(InsufficientArgumentsException.class, () -> {
            instance.solve();
        });
    }

    /**
     * UT-2.8.2 Test of solve method #2, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSolve2() throws Exception {
        System.out.println("solve #2");
        RPNStack instance = new RPNStack();
        instance.push("0.46+0.11j");
        instance.push("-3.37j");
        instance.push("+");
        Complex expResult = new Complex(0.46, -3.26);
        Complex result = instance.solve();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-2.8.3 Test of solve method #3, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSolve3() throws Exception {
        System.out.println("solve #3");
        RPNStack instance = new RPNStack();
        instance.push("-1.73j");
        instance.push("4.88-5.94j");
        instance.push("-");
        Complex expResult = new Complex(-4.88, 4.21);
        Complex result = instance.solve();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-2.8.4 Test of solve method #4, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSolve4() throws Exception {
        System.out.println("solve #4");
        RPNStack instance = new RPNStack();
        instance.push("2.15+1.26j");
        instance.push("-6.68j");
        instance.push("*");
        Complex expResult = new Complex(8.4168, -14.362);
        Complex result = instance.solve();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-2.8.5 Test of solve method #5, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSolve5() throws Exception {
        System.out.println("solve #5");
        RPNStack instance = new RPNStack();
        instance.push("-4.57");
        instance.push("1.33-2.71j");
        instance.push("/");
        Complex expResult = new Complex(-0.666970262262702, -1.359014594535279);
        Complex result = instance.solve();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-2.8.6 Test of solve method #6, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSolve6() throws Exception {
        System.out.println("solve #6");
        RPNStack instance = new RPNStack();
        instance.push("-1.39j");
        instance.push("+-");
        Complex expResult = new Complex(0, 1.39);
        Complex result = instance.solve();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-2.8.7 Test of solve method #7, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSolve7() throws Exception {
        System.out.println("solve #7");
        RPNStack instance = new RPNStack();
        instance.push("0.65-3.37j");
        instance.push("sqrt");
        Complex expResult = new Complex(-1.428655495868592, 1.179430593920444);
        Complex result = instance.solve();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-2.8.8 Test of solve method #8, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSolve8() throws Exception {
        System.out.println("solve #8");
        RPNStack instance = new RPNStack();
        instance.push("0.67j");
        instance.push("-5.28");
        instance.push("-");
        instance.push("+-");
        instance.push("sqrt");
        Complex expResult = new Complex(-0.14549864443858, 2.302426949011296);
        Complex result = instance.solve();
        assertEquals(result.getRealPart(), expResult.getRealPart(), 1e-14);
        assertEquals(result.getImaginaryPart(), expResult.getImaginaryPart(), 1e-14);
    }

    /**
     * UT-2.8.9 Test of solve method #9, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSolve9() throws Exception {
        System.out.println("solve #9");
        RPNStack instance = new RPNStack();
        instance.push("-2.51+0.73j");
        instance.push("*");
        assertThrows(InsufficientArgumentsException.class, () -> {
            instance.solve();
        });
    }

    /**
     * UT-2.8.10 Test of solve method #10, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSolve10() throws Exception {
        System.out.println("solve #10");
        RPNStack instance = new RPNStack();
        instance.push("-7.61-2.25j");
        instance.push("0");
        instance.push("/");
        assertThrows(ArithmeticException.class, () -> {
            instance.solve();
        });
    }

    /**
     * UT-2.9.1 Test of isEmpty method #1, of class RPNStack.
     */
    @Test
    public void testIsEmpty1() {
        System.out.println("isEmpty #1");
        RPNStack instance = new RPNStack();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * UT-2.9.2 Test of isEmpty method #2, of class RPNStack.
     */
    @Test
    public void testIsEmpty2() {
        System.out.println("isEmpty #2");
        RPNStack instance = new RPNStack();
        instance.push("-0.22j");
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * UT-2.10.1 Test of push method #1, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testPush1() throws Exception {
        System.out.println("push #1");
        String item = "-5.44+3.12j";
        RPNStack instance = new RPNStack();
        instance.push(item);
        assertEquals(instance.top(), item);
    }

    /**
     * UT-2.10.2 Test of push method #2, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testPush2() throws Exception {
        System.out.println("push #2");
        String item = "+-";
        RPNStack instance = new RPNStack();
        instance.push(item);
        assertEquals(instance.top(), item);
    }

    /**
     * UT-2.11.1 Test of pop method #1, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testPop1() throws Exception {
        System.out.println("pop #1");
        RPNStack instance = new RPNStack();
        instance.push("0.75j");
        instance.push("-0.22+4.13j");
        instance.pop();
        assertEquals(instance.size(), 1);
        assertEquals(instance.top(), "0.75j");
    }

    /**
     * UT-2.11.2 Test of pop method #2, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testPop2() throws Exception {
        System.out.println("pop #2");
        RPNStack instance = new RPNStack();
        instance.push("-0.22+4.13j");
        String expResult = "-0.22+4.13j";
        String result = instance.pop();
        assertEquals(expResult, result);
    }

    /**
     * UT-2.11.3 Test of pop method #3, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testPop3() throws Exception {
        System.out.println("pop #3");
        RPNStack instance = new RPNStack();
        assertThrows(EmptyStackException.class, () -> {
            instance.pop();
        });
    }

    /**
     * UT-2.12.1 Test of top method #1, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testTop1() throws Exception {
        System.out.println("top #1");
        RPNStack instance = new RPNStack();
        instance.push("-1.46+7.31j");
        String expResult = "-1.46+7.31j";
        String result = instance.top();
        assertEquals(expResult, result);
    }

    /**
     * UT-2.12.2 Test of top method #2, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testTop2() throws Exception {
        System.out.println("top #2");
        RPNStack instance = new RPNStack();
        instance.push("-2.57+8.93j");
        instance.top();
        assertTrue(!instance.isEmpty());
    }

    /**
     * UT-2.12.3 Test of top method #3, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testTop3() throws Exception {
        System.out.println("top #3");
        RPNStack instance = new RPNStack();
        assertThrows(EmptyStackException.class, () -> {
            instance.top();
        });
    }

    /**
     * UT-2.13.1 Test of size method #1, of class RPNStack.
     */
    @Test
    public void testSize1() {
        System.out.println("size #1");
        RPNStack instance = new RPNStack();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * UT-2.13.2 Test of size method #2, of class RPNStack.
     */
    @Test
    public void testSize2() {
        System.out.println("size #2");
        RPNStack instance = new RPNStack();
        instance.push("1.23j");
        instance.push("0.55");
        instance.push("+-");
        int expResult = 3;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * UT-2.14.1 Test of clear method #1, of class RPNStack.
     */
    @Test
    public void testClear1() {
        System.out.println("clear #1");
        RPNStack instance = new RPNStack();
        instance.clear();
        assertTrue(instance.isEmpty());
    }

    /**
     * UT-2.14.2 Test of clear method #2, of class RPNStack.
     */
    @Test
    public void testClear2() {
        System.out.println("clear #2");
        RPNStack instance = new RPNStack();
        instance.push("0.45");
        instance.push("1.62j");
        instance.push("sqrt");
        instance.clear();
        assertTrue(instance.isEmpty());
    }

    /**
     * UT-2.15.1 Test of drop method #1, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDrop1() throws Exception {
        System.out.println("drop #1");
        RPNStack instance = new RPNStack();
        instance.push("0.32j");
        instance.push("-6.11+0.89j");
        instance.drop();
        assertEquals(instance.size(), 1);
        assertEquals(instance.top(), "0.32j");
    }

    /**
     * UT-2.15.2 Test of drop method #2, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDrop2() throws Exception {
        System.out.println("drop #2");
        RPNStack instance = new RPNStack();
        instance.push("-6.11+0.89j");
        String expResult = "-6.11+0.89j";
        String result = instance.drop();
        assertEquals(expResult, result);
    }

    /**
     * UT-2.15.3 Test of drop method #3, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDrop3() throws Exception {
        System.out.println("drop #3");
        RPNStack instance = new RPNStack();
        assertThrows(EmptyStackException.class, () -> {
            instance.drop();
        });
    }

    /**
     * UT-2.16.1 Test of dup method #1, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDup1() throws Exception {
        System.out.println("dup #1");
        RPNStack instance = new RPNStack();
        instance.push("-0.89j");
        instance.dup();
        String lastItem = instance.pop();
        assertEquals(instance.top(), lastItem);
    }

    /**
     * UT-2.16.2 Test of dup method #2, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDup2() throws Exception {
        System.out.println("dup #2");
        RPNStack instance = new RPNStack();
        assertThrows(EmptyStackException.class, () -> {
            instance.dup();
        });
    }

    /**
     * UT-2.17.1 Test of swap method #1, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSwap1() throws Exception {
        System.out.println("swap #1");
        RPNStack instance = new RPNStack();
        instance.push("-0.33j");
        instance.push("0.68");
        instance.swap();
        String lastItem = instance.pop();
        assertEquals(lastItem, "-0.33j");
        assertEquals(instance.top(), "0.68");
    }

    /**
     * UT-2.17.2 Test of swap method #2, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSwap2() throws Exception {
        System.out.println("swap #2");
        RPNStack instance = new RPNStack();
        instance.push("-7.25j");
        assertThrows(InsufficientArgumentsException.class, () -> {
            instance.swap();
        });
    }

    /**
     * UT-2.18.1 Test of over method #1, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testOver1() throws Exception {
        System.out.println("over #1");
        RPNStack instance = new RPNStack();
        instance.push("-9.81j");
        instance.push("2.33");
        instance.over();
        assertEquals(instance.top(), "-9.81j");
    }

    /**
     * UT-2.18.2 Test of over method #2, of class RPNStack.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testOver2() throws Exception {
        System.out.println("over #2");
        RPNStack instance = new RPNStack();
        instance.push("-1.36");
        assertThrows(InsufficientArgumentsException.class, () -> {
            instance.over();
        });
    }

}
