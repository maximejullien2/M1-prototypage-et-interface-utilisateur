package com.example.propotypage_et_interface_utilisateur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalendrierApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalendrierController.class.getResource("Calendrier.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Calendrier!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}