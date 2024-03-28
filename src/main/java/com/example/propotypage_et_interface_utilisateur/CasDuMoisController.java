package com.example.propotypage_et_interface_utilisateur;

import com.example.Icalendar.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CasDuMoisController {

    @FXML
    FlowPane jourMois;

    ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> arrayList;

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    LocalDateTime dateTime;

    public void affichage(){
        URL test = CasDuMoisController.class.getResource("CaseDuMois.fxml");
        try {
            CaseDuMoisController casePourLeJourController ;
            for (int pointeur = 0 ; pointeur<arrayList.size(); pointeur++) {
                ArrayList<ArrayList<ArrayList<Event>>> list = arrayList.get(pointeur);
                FXMLLoader fxmlLoader = new FXMLLoader(test);
                VBox vBox = fxmlLoader.load();
                CaseDuMoisController caseDuMoisController = fxmlLoader.getController();
                int nombreSeance = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != null) {
                        for (int j = 0; j < list.get(i).size(); j++) {
                            for (int q = 0; q < list.get(i).get(j).size(); q++) {
                                if (list.get(i).get(j).get(q) != null) {
                                    nombreSeance = nombreSeance+1;
                                    Event event = list.get(i).get(j).get(q);
                                    String tootlipText = "                        "+Integer.toString(event.getDateEvent().getStartDate().getHour()) + "h" + Integer.toString(event.getDateEvent().getStartDate().getMinute()) + "-" +
                                            Integer.toString(event.getDateEvent().getEndDate().getHour()) + "h" + Integer.toString(event.getDateEvent().getEndDate().getMinute()) + "\n";
                                    if (event.getDescriptionEvent().getDescription("Matière ")!= null)
                                        tootlipText = tootlipText +"Matière :" +event.getDescriptionEvent().getDescription("Matière ") + "\n";
                                    if (event.getDescriptionEvent().getDescription("Enseignant ")!= null)
                                        tootlipText = tootlipText +"Enseignant :" +event.getDescriptionEvent().getDescription("Enseignant ") + "\n";
                                    if (event.getDescriptionEvent().getDescription("Promotion ")!= null)
                                        tootlipText = tootlipText +"Promotion :" +event.getDescriptionEvent().getDescription("Promotion ") + "\n";
                                    if (event.getDescriptionEvent().getDescription("TD ")!= null)
                                        tootlipText = tootlipText +"TD :" +event.getDescriptionEvent().getDescription("TD ") + "\n";
                                    if (event.getDescriptionEvent().getDescription("Salle ")!= null)
                                        tootlipText = tootlipText +"Salle :" +event.getDescriptionEvent().getDescription("Salle ") + "\n";
                                    if (event.getDescriptionEvent().getDescription("Type ")!= null)
                                        tootlipText = tootlipText +"Type :" +event.getDescriptionEvent().getDescription("Type ") + "\n";
                                    if (event.getDescriptionEvent().getDescription("Mémo ")!= null)
                                        tootlipText = tootlipText +"Mémo :" +event.getDescriptionEvent().getDescription("Mémo ") + "\n";
                                    caseDuMoisController.addCircle(tootlipText);
                                }
                            }
                        }
                    }
                }
                String seance = Integer.toString(nombreSeance) + " séances";
                caseDuMoisController.setNombreSeanceId(seance);
                String jour = Integer.toString(dateTime.getDayOfMonth());
                int paddingEnMoins = 0;
                if (jour.length() == 2 || seance.split(" ")[0].length() == 2) {
                    if (jour.length() == 2) {
                        paddingEnMoins = paddingEnMoins + 6;
                    }
                    if (seance.split(" ")[0].length() == 2) {
                        paddingEnMoins = paddingEnMoins + 7;
                    }
                }
                caseDuMoisController.setJourId(jour, paddingEnMoins);
                jourMois.getChildren().add(vBox);
                if (dateTime.getDayOfWeek().getValue()<5)
                    dateTime = dateTime.plusDays(1);
                else
                    dateTime = dateTime.plusDays(3);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*@Override
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
    }*/

    public void setArrayList(ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> arrayList) {
        this.arrayList = arrayList;
    }

}
