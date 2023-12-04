package it.unisa.diem.ids2023.rpnpsc.exceptions;

/**
 * La classe {@code RPNException} rappresenta la classe base da cui ereditano
 * tutte le custom exceptions legate al funzionamento della calcolatrice.
 * Fornisce un costruttore che prevede un'intestazione e un testo da utilizzare
 * per i messaggi di errore e di warning.
 *
 * @see EmptyStackException
 * @see InsufficientArgumentsException
 * @see InvalidFormatException
 */
public class RPNException extends Exception {

    /**
     * Intestazione del messaggio di errore o di warning.
     */
    private final String headerText;

    /**
     * Testo del messaggio di errore o di warning.
     */
    private final String contentText;

    /**
     * Costruttore della classe {@code RPNException}.
     *
     * @param headerText Intestazione del messaggio di errore o di warning.
     * @param contentText Testo del messaggio di errore o di warning.
     */
    public RPNException(String headerText, String contentText) {
        super(String.format("%s %s", headerText, contentText));
        this.headerText = headerText;
        this.contentText = contentText;
    }

    /**
     * Restituisce l'intestazione del messaggio di errore o di warning.
     *
     * @return Restituisce l'intestazione del messaggio di errore o di warning.
     */
    public String getHeaderText() {
        return headerText;
    }

    /**
     * Restituisce il testo del messaggio di errore o di warning.
     *
     * @return Restituisce il testo del messaggio di errore o di warning.
     */
    public String getContentText() {
        return contentText;
    }

}
