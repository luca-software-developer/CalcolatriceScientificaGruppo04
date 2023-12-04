package it.unisa.diem.ids2023.rpnpsc.exceptions;

/**
 * L'eccezione {@code InsufficientArgumentsException} viene sollevata quando lo
 * stack o il sotto-stack non contiene argomenti a sufficienza per completare
 * l'operazione richiesta.
 *
 * @see RPNException
 */
public class InsufficientArgumentsException extends RPNException {

    /**
     * Costruttore della classe {@code InsufficientArgumentsException}.
     *
     * @param headerText Intestazione del messaggio di errore o di warning.
     * @param contentText Testo del messaggio di errore o di warning.
     */
    public InsufficientArgumentsException(String headerText, String contentText) {
        super(headerText, contentText);
    }

}
