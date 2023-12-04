package it.unisa.diem.ids2023.rpnpsc.exceptions;

/**
 * L'eccezione {@code EmptyStackException} viene sollevata quando lo stack è
 * vuoto e l'operazione richiesta non può essere eseguita o completata.
 *
 * @see RPNException
 */
public class EmptyStackException extends RPNException {

    /**
     * Costruttore della classe {@code EmptyStackException}.
     *
     * @param headerText Intestazione del messaggio di errore o di warning.
     * @param contentText Testo del messaggio di errore o di warning.
     */
    public EmptyStackException(String headerText, String contentText) {
        super(headerText, contentText);
    }

}
