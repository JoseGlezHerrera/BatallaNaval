package com.proyecto;

import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 Simple controller, friendly names for 2º DAM Canarias
*/
public class ControladorJuego {

    private int vidasEsp = 3;
    private int vidasPir = 3;
    private final Random rnd = new Random();

    @FXML private Label txtInfo, txtVidasEsp, txtVidasPir;
    @FXML private Button btnDisparar, btnReiniciar;
    @FXML private ImageView esp1, esp2, esp3, pir1, pir2, pir3;

    private Image imgBarco;

    @FXML
    public void initialize() {
        imgBarco = new Image(getClass().getResourceAsStream("/barco.png"));
        reiniciar();
    }

    @FXML
    public void disparar() {
        boolean golpe = rnd.nextBoolean();
        if (golpe) {
            vidasPir--;
            txtInfo.setText("¡Diana! Hundimos un barco pirata.");
        } else {
            vidasEsp--;
            txtInfo.setText("¡Nos han dado! España pierde un barco.");
        }
        actualizarUi();
        if (vidasEsp == 0 || vidasPir == 0) {
            btnDisparar.setDisable(true);
            btnReiniciar.setDisable(false);
            txtInfo.setText(vidasEsp > 0 ? "Victoria Española 🇪🇸" : "Nos hundieron... ☠️");
        }
    }

    @FXML
    public void reiniciar() {
        vidasEsp = 3;
        vidasPir = 3;
        txtInfo.setText("Preparados para la misión.");
        btnDisparar.setDisable(false);
        btnReiniciar.setDisable(true);
        actualizarUi();
    }

    private void actualizarUi() {
        txtVidasEsp.setText("Vidas España: " + vidasEsp);
        txtVidasPir.setText("Vidas Piratas: " + vidasPir);
        ImageView[] esp = {esp1, esp2, esp3};
        ImageView[] pir = {pir1, pir2, pir3};
        for (int i = 0; i < 3; i++) {
            esp[i].setImage(imgBarco); esp[i].setOpacity(i < vidasEsp ? 1.0 : 0.2);
            pir[i].setImage(imgBarco); pir[i].setOpacity(i < vidasPir ? 1.0 : 0.2);
        }
    }
}
