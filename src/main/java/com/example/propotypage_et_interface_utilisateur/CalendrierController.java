package com.example.propotypage_et_interface_utilisateur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CalendrierController implements Initializable {
    @FXML
    ChoiceBox choiceBox;

    @FXML
    Pane calendrier;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                URL test ;
                if (number2.intValue()==0)
                    test = CasDuJourController.class.getResource("CasDuJour.fxml");
                else if(number2.intValue()==1)
                    test = CasDuSemaineController.class.getResource("CasDuSemaine.fxml");
                else
                    test = CasDuMoisController.class.getResource("CasDuMois.fxml");
                try {
                    calendrier.getChildren().clear();
                    calendrier.getChildren().add(new FXMLLoader().load(test));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        this.choiceBox.getItems().add("Jour");
        this.choiceBox.getItems().add("Semaine");
        this.choiceBox.getItems().add("Mois");
        this.choiceBox.getSelectionModel().select(0);
    }
}
