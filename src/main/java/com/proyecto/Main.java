package com.proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Carga el FXML desde el classpath
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/batalla.fxml")));

        // Crea la escena y aplica la hoja de estilos
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

        // Configura y muestra la ventana principal
        stage.setTitle("Batalla Naval");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}