package it.unisa.diem.ids2023.rpnpsc;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * La classe {@code RPNCalculator} rappresenta l'applicazione JavaFX.
 */
public class RPNCalculator extends Application {

    /**
     * Definisce il nome dell'applicazione visualizzato nella barra del titolo e
     * nelle finestre di dialogo.
     */
    public static final String APP_NAME = "RPN Programmable Scientific Calculator";

    /**
     * Rappresenta la versione attuale dell'applicazione, visualizzata nella
     * barra del titolo.
     */
    public static final String APP_VERSION = "1.0.0.0";

    /**
     * Carica la view dal file RPNView.fxml e costruisce la scena. Imposta la
     * visualizzazione della finestra.
     *
     * @param stage Container JavaFX di livello top.
     * @throws IOException Se si verifica un errore di I/O durante caricamento
     * della view.
     */
    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(RPNCalculator.class.getResource("views/RPNView.fxml"))));
        stage.getIcons().add(new Image(RPNCalculator.class.getResourceAsStream("images/icon.png")));
        stage.setTitle(String.format("%s ver. %s", APP_NAME, APP_VERSION));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Punto di ingresso dell'applicazione.
     *
     * @param args Parametri da riga di comando.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
