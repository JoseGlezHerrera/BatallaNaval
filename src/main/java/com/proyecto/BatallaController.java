package com.proyecto;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class BatallaController {

    //Elementos de la interfaz
    @FXML private Label tituloLabel;
    @FXML private ImageView imagenEspana;
    @FXML private ImageView imagenPirata;
    @FXML private ProgressBar vidasEspanaBar;
    @FXML private ProgressBar vidasPirataBar;
    @FXML private Label fuerzaEspanaLabel, agilidadEspanaLabel, suerteEspanaLabel;
    @FXML private Label fuerzaPirataLabel, agilidadPirataLabel, suertePirataLabel;
    @FXML private Button dispararButton;
    @FXML private Button reiniciarButton; //Esto está así para que deje de dar error al compilar

    // Estadísticas y lógica del juego
    private final int VIDAS_MAXIMAS = 100;
    private double vidasEspana = VIDAS_MAXIMAS;
    private double vidasPirata = VIDAS_MAXIMAS;

    // Estadísticas de España (más fuerza)
    private final int fuerzaEspana = 80;
    private final int agilidadEspana = 40;
    private final int suerteEspana = 50;

    // Estadísticas de Piratas (más agilidad)
    private final int fuerzaPirata = 40;
    private final int agilidadPirata = 80;
    private final int suertePirata = 50;

    private boolean juegoActivo = true;
    private final Random random = new Random();

    @FXML
    public void initialize() {
        configurarInterfaz();
    }

    private void configurarInterfaz() {
        // Cargar imágenes controlando errores
        try {
            // Uso ruta absoluta desde la raíz de resources para evitar problemas
            Image imgEspana = new Image("/espana.png");
            Image imgPirata = new Image("/pirata.png");

            // Verifico la carga
            if (imgEspana.isError()) {
                throw new RuntimeException("Error al cargar espana.png: " + imgEspana.getException());
            }
            if (imgPirata.isError()) {
                throw new RuntimeException("Error al cargar pirata.png: " + imgPirata.getException());
            }

            imagenEspana.setImage(imgEspana);
            imagenPirata.setImage(imgPirata);

        } catch (Exception e) {
            System.err.println("ERROR: No se pudieron cargar las imágenes.");
            System.err.println("Causa: " + e.getMessage());
            System.err.println("No se han encontrado las imágenes en la carpeta 'src/main/resources/'.");
        }

        fuerzaEspanaLabel.setText("Fuerza: " + fuerzaEspana);
        agilidadEspanaLabel.setText("Agilidad: " + agilidadEspana);
        suerteEspanaLabel.setText("Suerte: " + suerteEspana);

        fuerzaPirataLabel.setText("Fuerza: " + fuerzaPirata);
        agilidadPirataLabel.setText("Agilidad: " + agilidadPirata);
        suertePirataLabel.setText("Suerte: " + suertePirata);

        reiniciarJuego();
    }

    private void reiniciarJuego() {
        vidasEspana = VIDAS_MAXIMAS;
        vidasPirata = VIDAS_MAXIMAS;
        juegoActivo = true;
        actualizarBarrasDeVida();
        tituloLabel.setText("¡Pelea por el control del mar!");
        dispararButton.setDisable(false);
    }

    @FXML
    private void handleDisparar() {
        if (!juegoActivo) return;

        dispararButton.setDisable(true); // Evitar clics múltiples

        // Lógica de combate
        // Tirada de España (ataque)
        int tiradaEspana = random.nextInt(100) + 1; // 1 a 100
        int resultadoEspana = tiradaEspana + fuerzaEspana + (suerteEspana / 10);

        // Tirada de Piratas (defensa)
        int tiradaPirata = random.nextInt(100) + 1;
        int resultadoPirata = tiradaPirata + agilidadPirata + (suertePirata / 10);

        // Comparar resultados
        if (resultadoEspana > resultadoPirata) {

            double daño = (resultadoEspana - resultadoPirata) / 5.0; // El daño es proporcional a la diferencia
            vidasPirata -= daño;
            tituloLabel.setText("El impacto del Imperio Español causa " + String.format("%.1f", daño) + " de daño.");
        } else {
            tituloLabel.setText("¡El pirata Inglés esquiva el ataque!");
        }

        actualizarBarrasDeVida();

        if (comprobarFinJuego()) {
            return;
        }

        // Turno del Pirata
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            if (!juegoActivo) return;

            // Tirada de Piratas
            int tiradaPirAtaque = random.nextInt(100) + 1;
            int resultadoPirAtaque = tiradaPirAtaque + fuerzaPirata + (suertePirata / 10);

            // Tirada de España (defensa)
            int tiradaEspDef = random.nextInt(100) + 1;
            int resultadoEspDef = tiradaEspDef + agilidadEspana + (suerteEspana / 10);

            // Comparar
            if (resultadoPirAtaque > resultadoEspDef) {
                // Pirata acierta
                double daño = (resultadoPirAtaque - resultadoEspDef) / 5.0;
                vidasEspana -= daño;
                tituloLabel.setText("El cañonazo del Pirata Inglés causa " + String.format("%.1f", daño) + " de daño.");
            } else {
                tituloLabel.setText("¡El Imperio Español esquiva el ataque!");
            }

            actualizarBarrasDeVida();
            comprobarFinJuego();

            // Reactiva el botón para el siguiente turno del jugador
            dispararButton.setDisable(false);
        });
        pause.play();
    }

    private void actualizarBarrasDeVida() {
        vidasEspanaBar.setProgress(Math.max(0, vidasEspana / VIDAS_MAXIMAS));
        vidasPirataBar.setProgress(Math.max(0, vidasPirata / VIDAS_MAXIMAS));
    }

    private boolean comprobarFinJuego() {
        if (vidasPirata <= 0) {
            juegoActivo = false;
            tituloLabel.setText("¡VICTORIA DEL IMPERIO ESPAÑOL!");
            dispararButton.setDisable(true);
            return true;
        }
        if (vidasEspana <= 0) {
            juegoActivo = false;
            tituloLabel.setText("¡VICTORIA DE LOS PIRATAS INGLESES!");
            dispararButton.setDisable(true);
            return true;
        }
        return false;
    }

    @FXML
    private void handleReiniciar() {
        reiniciarJuego();
    }


    @FXML
    private void handleSalir() {
        Platform.exit();
    }

    @FXML
    private void handlePantallaCompleta() {
        // oBTENGO la ventana (Stage) a partir de cualquier componente de la escena
        Stage stage = (Stage) tituloLabel.getScene().getWindow();
        stage.setFullScreen(true);
    }
}