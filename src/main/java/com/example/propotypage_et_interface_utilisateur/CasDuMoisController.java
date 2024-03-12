package com.example.propotypage_et_interface_utilisateur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
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
            for (int j=0 ; j<5 ; j++) {
                for (int i = 1+7*j; i <6+7*j; i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader(test);
                    VBox vBox = fxmlLoader.load();
                    CaseDuMoisController caseDuMoisController = fxmlLoader.getController();
                    String nombreSeance = Integer.toString(i) + " séances";
                    caseDuMoisController.setNombreSeanceId(nombreSeance);
                    String jour = Integer.toString(i);
                    int paddingEnMoins = 0;
                    if (jour.length() == 2 || nombreSeance.split(" ")[0].length() == 2) {
                        if (jour.length() == 2) {
                            paddingEnMoins = paddingEnMoins + 6;
                        }
                        if (nombreSeance.split(" ")[0].length() == 2) {
                            paddingEnMoins = paddingEnMoins + 7;
                        }
                    }
                    caseDuMoisController.setJourId(jour, paddingEnMoins);
                    for (int nb=0 ; nb<i;nb++){
                        caseDuMoisController.addCircle("Test \n TestTestTestTestTestTestTest");
                    }
                   jourMois.getChildren().add(vBox);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
