package it.unisa.diem.ids2023.rpnpsc.exceptions;

/**
 * L'eccezione {@code InvalidFormatException} viene sollevata quando il formato
 * specificato per un operando o un operatore non è valido.
 *
 * @see RPNException
 */
public class InvalidFormatException extends RPNException {

    /**
     * Costruttore della classe {@code InvalidFormatException}.
     *
     * @param headerText Intestazione del messaggio di errore o di warning.
     * @param contentText Testo del messaggio di errore o di warning.
     */
    public InvalidFormatException(String headerText, String contentText) {
        super(headerText, contentText);
    }

}
