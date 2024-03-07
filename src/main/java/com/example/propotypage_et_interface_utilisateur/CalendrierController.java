package com.example.propotypage_et_interface_utilisateur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CalendrierController implements Initializable {
    @FXML
    ChoiceBox choiceBox;

    @FXML
    Pane calendrier;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.choiceBox.getItems().add("Jour");
        this.choiceBox.getItems().add("Semaine");
        this.choiceBox.getItems().add("Mois");
        this.choiceBox.getSelectionModel().select(0);
    }
}
