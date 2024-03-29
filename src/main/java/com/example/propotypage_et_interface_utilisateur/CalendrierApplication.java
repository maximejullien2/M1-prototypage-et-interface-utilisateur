package com.example.propotypage_et_interface_utilisateur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class CalendrierApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        File file = new File("src/main/resources/com/example/connexion/connected.txt");
        try {
            if (!file.exists())
                file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(ConnexionController.class.getResource("pageConnexion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Page de Connexion");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}