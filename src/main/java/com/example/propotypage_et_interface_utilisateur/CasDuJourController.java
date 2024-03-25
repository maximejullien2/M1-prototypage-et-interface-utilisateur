package com.example.propotypage_et_interface_utilisateur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CasDuJourController implements Initializable {
    @FXML
    VBox vBox;

    @FXML
    Text jour;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jour.setText("Mardi");
        URL test = CasePourLeJourController.class.getResource("CasePourLeJour.fxml");
        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(test);
            AnchorPane anchorPane2 = fxmlLoader2.load();
            CasePourLeJourController casePourLeJourController = fxmlLoader2.getController();
            casePourLeJourController.setOpacity(0.0);
            casePourLeJourController.setHeigth(3);
            vBox.getChildren().add(anchorPane2);
            for (int i=0 ; i<1 ; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(test);
                AnchorPane anchorPane = fxmlLoader.load();
                casePourLeJourController = fxmlLoader.getController();
                casePourLeJourController.setNombreDeCase(9);
                casePourLeJourController.setInformation("C1300000000000000000000000000000000000000","08h00-09h30\\TP",
                        "Cours:UCE 3 Interface et prototypage","Prof:MDHAFFAR Salima",
                        "TD:M1-IA-IL-CLA/M1-IA-ALT");
                vBox.getChildren().add(anchorPane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
