package it.unisa.diem.ids2023.rpnpsc;

import it.unisa.diem.ids2023.rpnpsc.exceptions.RPNException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * La classe {@code RPNViewController} rappresenta il controller MVC
 * dell'applicazione JavaFX. Fornisce i metodi handler per la gestione degli
 * eventi UI. Inoltre, incapsula le istanze di {@code RPNStack} e {@code Memory}
 * utilizzate durante l'esecuzione del programma.
 */
public class RPNViewController implements Initializable {

    /**
     * Rappresenta il campo di inserimento all'interno della UI.
     */
    @FXML
    private TextField txtElemento;

    /**
     * Rappresenta la visualizzazione dello stack all'interno della UI.
     */
    @FXML
    private ListView<String> lstStack;

    /**
     * Rappresenta il pulsante con menu per la selezione delle variabili.
     */
    @FXML
    private SplitMenuButton smbVariables;

    /**
     * Rappresenta il testo iniziale visualizzato sul pulsante per l'inserimento
     * delle variabili.
     */
    private String smbInitialText;

    /**
     * Costituisce l'istanza di {@code RPNStack} utilizzata durante la gestione
     * degli eventi UI.
     */
    private final RPNStack stack;

    /**
     * Costituisce l'istanza di {@code Memory} utilizzata durante la gestione
     * degli eventi UI.
     */
    private final Memory memory;

    /**
     * Rappresenta l'istanza di {@code Alert} utilizzata per i messaggi di
     * errore.
     */
    private final Alert errorAlert;

    /**
     * Rappresenta l'istanza di {@code Alert} utilizzata per i messaggi di
     * warning.
     */
    private final Alert warningAlert;

    /**
     * Costruttore della classe {@code RPNViewController}
     */
    public RPNViewController() {
        this.stack = new RPNStack();
        this.memory = Memory.getInstance();
        this.errorAlert = new Alert(Alert.AlertType.ERROR);
        this.errorAlert.setTitle(RPNCalculator.APP_NAME);
        Image alertIcon = new Image(RPNCalculator.class.getResourceAsStream("images/icon.png"));
        Stage errorStage = (Stage) this.errorAlert.getDialogPane().getScene().getWindow();
        errorStage.getIcons().add(alertIcon);
        this.warningAlert = new Alert(Alert.AlertType.WARNING);
        this.warningAlert.setTitle(RPNCalculator.APP_NAME);
        Stage warningStage = (Stage) this.warningAlert.getDialogPane().getScene().getWindow();
        warningStage.getIcons().add(alertIcon);
    }

    /**
     * Metodo di inizializzazione. Imposta i gestori degli eventi per le singole
     * variabili selezionabili nell'apposito menu a tendina.
     *
     * @param location La posizione usata per risolvere i percorsi relativi
     * dell'oggetto radice, o null se la posizione non è nota.
     * @param resources Le risorse usate per localizzare l'oggetto radice, o
     * null se l'oggetto radice non è stato localizzato.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lstStack.setItems(stack.getObservableList());
        smbInitialText = smbVariables.getText();
        Iterator<MenuItem> iterator = smbVariables.getItems().iterator();
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            menuItem.setOnAction((ActionEvent event) -> {
                txtElemento.setText(txtElemento.getText() + menuItem.getText());
                txtElemento.positionCaret(txtElemento.getLength());
                smbVariables.setText(menuItem.getText());
            });
        }
    }

    /**
     * Gestisce l'evento di inserimento di un elemento nello stack. Effettua il
     * riconoscimento e la classificazione dell'elemento inserito, chiamando gli
     * opportuni metodi per applicare le operazioni di manipolazione dello stack
     * e sulle variabili complesse.
     *
     * @param event Evento JavaFX.
     * @see RPNStack
     * @see Memory
     */
    @FXML
    private void insertElement(Event event) {
        if (event instanceof KeyEvent) {
            KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (keyEvent.isControlDown()) {
                    applySolve(event);
                    return;
                }
            } else {
                return;
            }
        }
        String element = txtElemento.getText().trim().toLowerCase();
        txtElemento.clear();
        if (element.isEmpty()) {
            return;
        }
        if (RPNStack.isOperand(element) || RPNStack.isOperator(element)) {
            stack.push(element);
            return;
        }
        if (element.equals("=")) {
            applySolve(event);
            return;
        }
        if (RPNStack.isStackManipulation(element)) {
            switch (element) {
                case "clear": {
                    applyClear(event);
                    break;
                }
                case "drop": {
                    applyDrop(event);
                    break;
                }
                case "dup": {
                    applyDup(event);
                    break;
                }
                case "swap": {
                    applySwap(event);
                    break;
                }
                case "over": {
                    applyOver(event);
                    break;
                }
                default: {
                    break;
                }
            }
            return;
        }
        if (RPNStack.isVariableOperation(element)) {
            element = element.replaceAll("\\s", "");
            Pattern variablePattern = Pattern.compile("^(?<operation>[><+-])(?<variable>[a-z])$");
            Matcher variableMatcher = variablePattern.matcher(element);
            if (variableMatcher.find()) {
                char operation = variableMatcher.group("operation").charAt(0);
                char variable = variableMatcher.group("variable").charAt(0);
                switch (operation) {
                    case '>': {
                        try {
                            memory.setVariable(variable, Complex.parse(stack.pop()));
                        } catch (RPNException ex) {
                            errorAlert.setHeaderText(ex.getHeaderText());
                            errorAlert.setContentText(ex.getContentText());
                            errorAlert.show();
                            return;
                        }
                        break;
                    }
                    case '<': {
                        stack.push(memory.getVariable(variable).toString());
                        break;
                    }
                    case '+': {
                        try {
                            memory.setVariable(variable, memory.getVariable(variable).add(Complex.parse(stack.pop())));
                        } catch (RPNException ex) {
                            errorAlert.setHeaderText(ex.getHeaderText());
                            errorAlert.setContentText(ex.getContentText());
                            errorAlert.show();
                            return;
                        }
                        break;
                    }
                    case '-': {
                        try {
                            memory.setVariable(variable, memory.getVariable(variable).subtract(Complex.parse(stack.pop())));
                        } catch (RPNException ex) {
                            errorAlert.setHeaderText(ex.getHeaderText());
                            errorAlert.setContentText(ex.getContentText());
                            errorAlert.show();
                            return;
                        }
                        break;
                    }
                    default: {
                        break;
                    }
                }
                return;
            }
        }
        errorAlert.setHeaderText("Formato non valido!");
        errorAlert.setContentText("Formato dell'elemento non valido.");
        errorAlert.show();
    }

    /**
     * Gestisce la pressione del pulsante virtuale CE.
     *
     * @param actionEvent Evento di pressione del pulsante.
     */
    @FXML
    private void clearEntry(ActionEvent actionEvent) {
        txtElemento.clear();
    }

    /**
     * Gestisce la pressione del pulsante virtuale C.
     *
     * @param actionEvent Evento di pressione del pulsante.
     */
    @FXML
    private void clearComputation(ActionEvent actionEvent) {
        clearEntry(actionEvent);
        stack.clear();
    }

    /**
     * Gestisce la pressione del pulsante virtuale "backspace".
     *
     * @param actionEvent Evento di pressione del pulsante.
     */
    @FXML
    private void backspace(ActionEvent actionEvent) {
        if (txtElemento.getText().isEmpty()) {
            return;
        }
        txtElemento.setText(txtElemento.getText(0, txtElemento.getLength() - 1));
        txtElemento.positionCaret(txtElemento.getLength());
    }

    /**
     * Gestisce la pressione di tutti i pulsanti di inserimento di numeri e
     * operazioni.
     *
     * @param actionEvent Evento di pressione del pulsante.
     */
    @FXML
    private void appendSymbol(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        txtElemento.setText(txtElemento.getText() + button.getText());
        txtElemento.positionCaret(txtElemento.getLength());
    }

    /**
     * Applica ricorsivamente la funzione di risoluzione dello stack definita in
     * {@code RPNStack} per semplificare il più possibile la pila di operazioni
     * inserite. Gestisce, inoltre, alcune situazioni di errore che possono
     * verificarsi.
     *
     * @param event Evento JavaFX.
     * @see RPNStack
     */
    @FXML
    private void applySolve(Event event) {
        if (stack.isEmpty()) {
            warningAlert.setHeaderText("Stack vuoto!");
            warningAlert.setContentText("Nessuna operazione da eseguire.");
            warningAlert.show();
            return;
        }
        try {
            stack.push(stack.solve().toString());
            if (stack.size() > 1 && RPNStack.isOperand(stack.top())) {
                String item = stack.pop();
                applySolve(event);
                stack.push(item);
            }
        } catch (ArithmeticException ex) {
            errorAlert.setHeaderText("Divisione per zero!");
            errorAlert.setContentText("Non è possibile dividere per zero.");
            errorAlert.show();
            applySolve(event);
        } catch (RPNException ex) {
            errorAlert.setHeaderText(ex.getHeaderText());
            errorAlert.setContentText(ex.getContentText());
            errorAlert.show();
        }
    }

    /**
     * Gestisce il comando di visualizzazione del menu a tendina delle variabili
     * complesse.
     *
     * @param actionEvent Evento di pressione del pulsante.
     */
    @FXML
    private void openVariablesMenu(ActionEvent actionEvent) {
        if (smbVariables.getText().equals(smbInitialText)) {
            smbVariables.show();
        } else {
            txtElemento.setText(txtElemento.getText() + smbVariables.getText());
            txtElemento.positionCaret(txtElemento.getLength());
        }
    }

    /**
     * Applica l'operazione di "clear" allo stack corrente.
     *
     * @param event Evento JavaFX.
     * @see RPNStack
     */
    @FXML
    private void applyClear(Event event) {
        stack.clear();
    }

    /**
     * Applica l'operazione di "drop" allo stack corrente.
     *
     * @param event Evento JavaFX.
     * @see RPNStack
     */
    @FXML
    private void applyDrop(Event event) {
        try {
            stack.drop();
        } catch (RPNException ex) {
            errorAlert.setHeaderText(ex.getHeaderText());
            errorAlert.setContentText(ex.getContentText());
            errorAlert.show();
        }
    }

    /**
     * Applica l'operazione di "dup" allo stack corrente.
     *
     * @param event Evento JavaFX.
     * @see RPNStack
     */
    @FXML
    private void applyDup(Event event) {
        try {
            stack.dup();
        } catch (RPNException ex) {
            errorAlert.setHeaderText(ex.getHeaderText());
            errorAlert.setContentText(ex.getContentText());
            errorAlert.show();
        }
    }

    /**
     * Applica l'operazione di "swap" allo stack corrente.
     *
     * @param event Evento JavaFX.
     * @see RPNStack
     */
    @FXML
    private void applySwap(Event event) {
        try {
            stack.swap();
        } catch (RPNException ex) {
            errorAlert.setHeaderText(ex.getHeaderText());
            errorAlert.setContentText(ex.getContentText());
            errorAlert.show();
        }
    }

    /**
     * Applica l'operazione di "over" allo stack corrente.
     *
     * @param event Evento JavaFX.
     * @see RPNStack
     */
    @FXML
    private void applyOver(Event event) {
        try {
            stack.over();
        } catch (RPNException ex) {
            errorAlert.setHeaderText(ex.getHeaderText());
            errorAlert.setContentText(ex.getContentText());
            errorAlert.show();
        }
    }

}
