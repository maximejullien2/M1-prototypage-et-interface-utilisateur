package com.example.propotypage_et_interface_utilisateur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CasDuMoisController implements Initializable {

    @FXML
    FlowPane jourMois;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        URL test = CasDuMoisController.class.getResource("CaseDuMois.fxml");
        try {
            jourMois.getChildren().add(new FXMLLoader().load(test));
            jourMois.getChildren().add(new FXMLLoader().load(test));
            jourMois.getChildren().add(new FXMLLoader().load(test));
            jourMois.getChildren().add(new FXMLLoader().load(test));
            jourMois.getChildren().add(new Circle(50));
            jourMois.getChildren().add(new Circle());
            jourMois.getChildren().add(new Circle());
            jourMois.getChildren().add(new Circle());
            jourMois.getChildren().add(new Circle());
            jourMois.getChildren().add(new Circle());
            jourMois.getChildren().add(new Circle());
            jourMois.getChildren().add(new Circle());
            jourMois.getChildren().add(new Circle());
            jourMois.getChildren().add(new FXMLLoader().load(test));
            jourMois.getChildren().add(new FXMLLoader().load(test));
            jourMois.getChildren().add(new FXMLLoader().load(test));
            jourMois.getChildren().add(new FXMLLoader().load(test));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
