package it.unisa.diem.ids2023.rpnpsc;

import java.util.HashMap;
import java.util.Map;

/**
 * La classe {@code Memory} rappresenta la memoria della calcolatrice e realizza
 * la persistenza delle variabili. Essa è realizzata secondo il design pattern
 * Singleton per le motivazioni discusse in fase di Design.
 */
public class Memory {

    /**
     * Rappresenta l'istanza della classe {@code Memory}.
     */
    private static Memory instance;

    /**
     * La mappa memory contiene le variabili complesse, identificate da un
     * carattere.
     */
    private final Map<Character, Complex> memory;

    /**
     * Costruttore privato della classe {@code Memory}.
     */
    private Memory() {
        memory = new HashMap<>();
    }

    /**
     * Restituisce l'istanza della classe, istanzia {@code Memory} se l'istanza
     * della classe risulta essere uguale a {@code null}.
     *
     * @return Restituisce l'istanza della classe.
     */
    public static Memory getInstance() {
        if (instance == null) {
            instance = new Memory();
        }
        return instance;
    }

    /**
     * Restituisce il valore della variabile passata come parametro.
     *
     * @param variable Nome della variabile.
     * @return Restituisce il valore della variabile passata come parametro.
     */
    public Complex getVariable(char variable) {
        if (!memory.containsKey(variable)) {
            memory.put(variable, new Complex(0));
        }
        return memory.get(variable);
    }

    /**
     * Imposta il valore della variabile passata come parametro.
     *
     * @param variable Nome della variabile.
     * @param complex Valore della variabile.
     */
    public void setVariable(char variable, Complex complex) {
        memory.put(variable, complex);
    }

}
