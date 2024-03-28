package com.example.propotypage_et_interface_utilisateur;

import com.example.Icalendar.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CasDuSemaineController {
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

    public void setArrayList(ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> arrayList) {
        this.arrayList = arrayList;
    }

    ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> arrayList;
    public void affichage(){
        URL test = CaseDeLaSemaineController.class.getResource("CaseDeLaSemaine.fxml");
        try {
            CaseDeLaSemaineController casePourLeJourController ;
            VBox[] vBox = {vBoxLundi,vBoxMardi,vBoxMercredi,vBoxJeudi,vBoxVendredi};
            for (int pointeur = 0 ; pointeur<arrayList.size(); pointeur++) {
                ArrayList<ArrayList<ArrayList<Event>>> list = arrayList.get(pointeur);
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != null) {
                        HBox hBox = new HBox();
                        for (int j = 0; j < list.get(i).size(); j++) {
                            VBox vBox1 = new VBox();
                            for (int q = 0; q < list.get(i).get(j).size(); q++) {
                                if (list.get(i).get(j).get(q) == null) {
                                    FXMLLoader fxmlLoader = new FXMLLoader(test);
                                    AnchorPane anchorPane = fxmlLoader.load();
                                    casePourLeJourController = fxmlLoader.getController();
                                    casePourLeJourController.setNombreDeCase(list.get(i).size());
                                    casePourLeJourController.setOpacity(0);
                                    casePourLeJourController.setHeigth(3);
                                    vBox1.getChildren().add(anchorPane);
                                } else {
                                    FXMLLoader fxmlLoader = new FXMLLoader(test);
                                    AnchorPane anchorPane = fxmlLoader.load();
                                    casePourLeJourController = fxmlLoader.getController();
                                    casePourLeJourController.setNombreDeCase(list.get(i).size());
                                    casePourLeJourController.setHeigth((double) 90 / ChronoUnit.MINUTES.between(list.get(i).get(j).get(q).getDateEvent().getStartDate(), list.get(i).get(j).get(q).getDateEvent().getEndDate()));
                                    Event event = list.get(i).get(j).get(q);
                                    casePourLeJourController.setInformation(event.getDescriptionEvent().getDescription("Salle "), Integer.toString(event.getDateEvent().getStartDate().getHour()) + "h" + Integer.toString(event.getDateEvent().getStartDate().getMinute()) + "-" +
                                                    Integer.toString(event.getDateEvent().getEndDate().getHour()) + "h" + Integer.toString(event.getDateEvent().getEndDate().getMinute())
                                                    + "\\" + event.getDescriptionEvent().getDescription("Type "),
                                            event.getDescriptionEvent().getDescription("Matière "), event.getDescriptionEvent().getDescription("Enseignant "),
                                            event.getDescriptionEvent().getDescription("TD "));
                                    vBox1.getChildren().add(anchorPane);
                                }
                            }
                            hBox.getChildren().add(vBox1);
                        }
                        vBox[pointeur].getChildren().add(hBox);
                    } else {
                        FXMLLoader fxmlLoader = new FXMLLoader(test);
                        AnchorPane anchorPane = fxmlLoader.load();
                        casePourLeJourController = fxmlLoader.getController();
                        casePourLeJourController.setOpacity(0);
                        casePourLeJourController.setHeigth(3);
                        vBox[pointeur].getChildren().add(anchorPane);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
