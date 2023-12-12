package it.unisa.diem.ids2023.rpnpsc;

import it.unisa.diem.ids2023.rpnpsc.exceptions.RPNException;
import it.unisa.diem.ids2023.rpnpsc.exceptions.InsufficientArgumentsException;
import it.unisa.diem.ids2023.rpnpsc.exceptions.InvalidFormatException;
import it.unisa.diem.ids2023.rpnpsc.exceptions.EmptyStackException;
import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * La classe {@code RPNStack} rappresenta uno stack di operandi e operatori.
 * Fornisce metodi statici per il riconoscimento del tipo di elemento contenuto
 * nello stack e per la verifica del relativo formato. Definisce tutte le
 * operazioni di manipolazione dello stack indicate nella Specifica dei
 * Requisiti. Fornisce, inoltre, il metodo di risoluzione dello stack di
 * operazioni.
 */
public class RPNStack {

    /**
     * Costituisce lo stack di elementi utilizzato per memorizzare operandi e
     * operazioni.
     */
    private final Stack<String> stack;

    /**
     * Lista osservabile utilizzata per consentire la visualizzazione dello
     * stack aggiornato all'interno della visualizzazione principale.
     */
    private final ObservableList<String> observableList;

    /**
     * Costruttore della classe {@code RPNStack}.
     */
    public RPNStack() {
        this.stack = new Stack<>();
        this.observableList = FXCollections.observableList(stack);
    }

    /**
     * Restituisce la lista osservabile.
     *
     * @return Restituisce la lista osservabile.
     */
    public ObservableList<String> getObservableList() {
        return observableList;
    }

    /**
     * Restituisce true se l'elemento è un operando reale o complesso.
     *
     * @param item Elemento dello stack.
     * @return Restituisce true se l'elemento è un operando reale o complesso.
     * @see Complex
     */
    public static boolean isOperand(String item) {
        try {
            Complex.parse(item);
            return true;
        } catch (InvalidFormatException ex) {
            return false;
        }
    }

    /**
     * Restituisce true se l'elemento è un operatore binario.
     *
     * @param item Elemento dello stack.
     * @return Restituisce true se l'elemento è un operatore binario.
     */
    public static boolean isBinaryOperator(String item) {
        return Arrays.asList("+", "-", "*", "/").contains(item);
    }

    /**
     * Restituisce true se l'elemento è un operatore unario.
     *
     * @param item Elemento dello stack.
     * @return Restituisce true se l'elemento è un operatore unario.
     */
    public static boolean isUnaryOperator(String item) {
        return Arrays.asList("+-", "sqrt").contains(item);
    }

    /**
     * Restituisce true se l'elemento è un operatore unario o binario.
     *
     * @param item Elemento dello stack.
     * @return Restituisce true se l'elemento è un operatore unario o binario.
     */
    public static boolean isOperator(String item) {
        return isBinaryOperator(item) || isUnaryOperator(item);
    }

    /**
     * Restituisce true se l'elemento indica un'operazione di manipolazione
     * dello stack.
     *
     * @param item Elemento dello stack.
     * @return Restituisce true se l'elemento indica un'operazione di
     * manipolazione dello stack.
     */
    public static boolean isStackManipulation(String item) {
        return Arrays.asList("clear", "drop", "dup", "swap", "over").contains(item);
    }

    /**
     * Restituisce true se l'elemento specifica un'operazione su variabile.
     *
     * @param item Elemento dello stack.
     * @return Restituisce true se l'elemento specifica un'operazione su
     * variabile.
     */
    public static boolean isVariableOperation(String item) {
        String element = item.replaceAll("\\s", "");
        Pattern variablePattern = Pattern.compile("[><+-][a-z]");
        return variablePattern.matcher(element).find();
    }

    /**
     * Metodo di risoluzione dello stack di operandi e operazioni. L'obiettivo
     * del metodo {@code solve} è di fornire il risultato complesso della
     * valutazione del sotto-stack su cui viene chiamato. Utilizza, quindi, un
     * approccio ricorsivo.
     *
     * @return Restituisce il risultato della valutazione dello stack di
     * operandi e operazioni.
     * @throws RPNException Se vengono riscontrati errori durante la valutazione
     * dello stack di operandi e operazioni.
     * @see Complex
     */
    public Complex solve() throws RPNException {
        if (isEmpty()) {
            throw new InsufficientArgumentsException("Argomenti insufficienti!", "Gli argomenti forniti sono insufficienti.");
        }
        String item = pop();
        if (isOperand(item)) {
            return Complex.parse(item);
        } else if (isBinaryOperator(item)) {
            Complex firstOperand = solve();
            Complex secondOperand = solve();
            switch (item) {
                case "+": {
                    return secondOperand.add(firstOperand);
                }
                case "-": {
                    return secondOperand.subtract(firstOperand);
                }
                case "*": {
                    return secondOperand.multiply(firstOperand);
                }
                case "/":
                default: {
                    return secondOperand.divide(firstOperand);
                }
            }
        } else {
            Complex operand = solve();
            switch (item) {
                case "+-": {
                    return operand.getOpposite();
                }
                case "sqrt":
                default: {
                    return operand.getSquareRoot();
                }
            }
        }
    }

    /**
     * Restituisce true se lo stack è vuoto.
     *
     * @return Restituisce true se lo stack è vuoto.
     */
    public boolean isEmpty() {
        return observableList.isEmpty();
    }

    /**
     * Aggiunge un elemento allo stack.
     *
     * @param item Elemento da aggiungere.
     */
    public void push(String item) {
        observableList.add(item);
    }

    /**
     * Rimuove l'elemento in posizione top e lo restituisce.
     *
     * @return Restituisce l'elemento in posizione top.
     * @throws EmptyStackException Se lo stack è vuoto.
     */
    public String pop() throws EmptyStackException {
        if (observableList.isEmpty()) {
            throw new EmptyStackException("Stack vuoto!", "Non è possibile prelevare un elemento da uno stack vuoto.");
        }
        return observableList.remove(observableList.size() - 1);
    }

    /**
     * Restituisce l'elemento in posizione top.
     *
     * @return Restituisce l'elemento in posizione top.
     * @throws EmptyStackException Se lo stack è vuoto.
     */
    public String top() throws EmptyStackException {
        if (observableList.isEmpty()) {
            throw new EmptyStackException("Stack vuoto!", "Non è possibile prelevare un elemento da uno stack vuoto.");
        }
        return observableList.get(observableList.size() - 1);
    }

    /**
     * Restituisce il numero di elementi contenuti nello stack.
     *
     * @return Restituisce il numero di elementi contenuti nello stack.
     */
    public int size() {
        return observableList.size();
    }

    /**
     * Svuota lo stack.
     */
    public void clear() {
        observableList.clear();
    }

    /**
     * Rimuove l'elemento in posizione top e lo restituisce.
     *
     * @return Restituisce l'elemento in posizione top.
     * @throws EmptyStackException Se lo stack è vuoto.
     */
    public String drop() throws EmptyStackException {
        return pop();
    }

    /**
     * Duplica l'elemento in posizione top.
     *
     * @throws EmptyStackException Se lo stack è vuoto.
     */
    public void dup() throws EmptyStackException {
        push(top());
    }

    /**
     * Scambia gli ultimi due elementi nello stack.
     *
     * @throws InsufficientArgumentsException Se lo stack è vuoto o gli elementi
     * contenuti nello stack non sono sufficienti a completare l'operazione.
     */
    public void swap() throws InsufficientArgumentsException {
        if (size() < 2) {
            throw new InsufficientArgumentsException("Argomenti insufficienti!", "Non è possibile completare l'operazione di swap.");
        }
        try {
            String first = pop();
            String second = pop();
            push(first);
            push(second);
        } catch (EmptyStackException ex) {
            //  Condizione già gestita.
        }
    }

    /**
     * Inserisce una copia del penultimo elemento nello stack.
     *
     * @throws InsufficientArgumentsException Se lo stack è vuoto o gli elementi
     * contenuti nello stack non sono sufficienti a completare l'operazione.
     */
    public void over() throws InsufficientArgumentsException {
        if (size() < 2) {
            throw new InsufficientArgumentsException("Argomenti insufficienti!", "Non è possibile completare l'operazione di over.");
        }
        try {
            String last = pop();
            String secondLast = top();
            push(last);
            push(secondLast);
        } catch (EmptyStackException ex) {
            //  Condizione già gestita.
        }
    }

}
