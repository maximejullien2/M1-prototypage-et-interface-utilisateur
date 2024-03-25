package com.example.propotypage_et_interface_utilisateur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CasDuSemaineController implements Initializable{
    @FXML
    VBox vBoxLundi;

    @FXML
    VBox vBoxMardi;

    @FXML
    VBox vBoxMercredi;

    @FXML
    VBox vBoxJeudi;

    @FXML
    VBox vBoxVendredi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        URL test = CaseDeLaSemaineController.class.getResource("CaseDeLaSemaine.fxml");
        try {
            HBox hBox = new HBox();
            FXMLLoader fxmlLoader2 = new FXMLLoader(test);
            AnchorPane anchorPane2 = fxmlLoader2.load();
            CaseDeLaSemaineController caseDeLaSemaineController = fxmlLoader2.getController();
            caseDeLaSemaineController.setOpacity(0.0);
            caseDeLaSemaineController.setHeigth(3);
            vBoxLundi.getChildren().add(anchorPane2);
            for (int i=0 ; i<1 ; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(test);
                AnchorPane anchorPane = fxmlLoader.load();
                caseDeLaSemaineController = fxmlLoader.getController();
                caseDeLaSemaineController.setNombreDeCase(2);
                caseDeLaSemaineController.setInformation("C130","08h00-09h30\\TP",
                        "Cours:UCE 3 Interface et prototypage","Prof:MDHAFFAR Salima",
                        "TD:M1-IA-IL-CLA/M1-IA-ALT");
                hBox.getChildren().add(anchorPane);
            }
            VBox vbox = new VBox();
            fxmlLoader2 = new FXMLLoader(test);
            anchorPane2 = fxmlLoader2.load();
            caseDeLaSemaineController = fxmlLoader2.getController();
            caseDeLaSemaineController.setOpacity(0.0);
            vbox.getChildren().add(anchorPane2);
            FXMLLoader fxmlLoader = new FXMLLoader(test);
            AnchorPane anchorPane = fxmlLoader.load();
            caseDeLaSemaineController = fxmlLoader.getController();
            caseDeLaSemaineController.setNombreDeCase(2);
            caseDeLaSemaineController.setInformation("C130","08h00-09h30\\TP",
                    "Cours:UCE 3 Interface et prototypage","Prof:MDHAFFAR Salima",
                    "TD:M1-IA-IL-CLA/M1-IA-ALT");
            vbox.getChildren().add(anchorPane);
            hBox.getChildren().add(vbox);
            vBoxLundi.getChildren().add(hBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
