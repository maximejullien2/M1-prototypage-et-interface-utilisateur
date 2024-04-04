package com.example.propotypage_et_interface_utilisateur;

import com.example.Icalendar.ApiCalendar;
import com.example.Icalendar.Event;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CasDuMoisController implements Initializable{

    @FXML
    FlowPane jourMois;

    @FXML
    AnchorPane paneLundi;

    @FXML
    Pane paneMardi;

    @FXML
    Pane paneMercredi;

    @FXML
    Pane paneJeudi;

    @FXML
    Pane paneVendredi;

    @FXML
    Text lundi;

    @FXML
    Text mardi;

    @FXML
    Text mercredi;

    @FXML
    Text jeudi;

    @FXML
    Text vendredi;

    @FXML
    GridPane gridPane;

    @FXML
    AnchorPane anchorPane;
    private void setColor(String couleur){
        Color color;
        if (couleur=="black"){
            color = Color.BLACK;
        }
        else{
            color = Color.WHITE;
        }
        lundi.setFill(color);
        mardi.setFill(color);
        mercredi.setFill(color);
        jeudi.setFill(color);
        vendredi.setFill(color);
        paneLundi.setStyle("-fx-border-color:"+couleur+";");
        paneMardi.setStyle("-fx-border-color:"+couleur+";");
        paneMercredi.setStyle("-fx-border-color:"+couleur+";");
        paneJeudi.setStyle("-fx-border-color:"+couleur+";");
        paneVendredi.setStyle("-fx-border-color:"+couleur+";");
    }
    ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> arrayList;

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    LocalDateTime dateTime;

    public void affichage(String mode, String userPriviledge, ApiCalendar apiCalendar){
        URL test = CasDuMoisController.class.getResource("CaseDuMois.fxml");
        try {
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
                                    nombreSeance = nombreSeance + 1;
                                    Event event = list.get(i).get(j).get(q);
                                    caseDuMoisController = this.creationEvent(event, caseDuMoisController, userPriviledge, mode);
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
                this.setModeSalle(mode, userPriviledge, apiCalendar, vBox);
                this.setModePersonnel(mode,apiCalendar,vBox);
            }
            if (Objects.equals(CalendrierController.couleur.get(), "black"))
                setColor("white");
            else
                setColor("black");

            this.setListenerOnWidthStage();
            this.setListenerOnHeigthStage();
            int suppression=240;
            if (CalendrierApplication.stage.getWidth()<791){
                suppression=10;
                if (CalendrierApplication.stage.getWidth()<791) {
                    anchorPane.setLayoutY(anchorPane.getLayoutY() + 300);
                }
            }
            this.setWidth(CalendrierApplication.stage.getWidth(),suppression);
            if (!Objects.equals(Double.toString(CalendrierApplication.stage.getHeight()), "NaN")) {
                this.setHeigth(CalendrierApplication.stage.getHeight());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setModeSalle(String mode,String userPriviledge,ApiCalendar apiCalendar,VBox vBox) {
        if (Objects.equals(mode, "salle") && Objects.equals(userPriviledge, "enseignant")){
            LocalDateTime finalDateTime = dateTime;
            vBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    FXMLLoader fxmlLoader = new FXMLLoader(CalendrierController.class.getResource("ReservationDeSalle.fxml"));
                    Scene scene2 = null;
                    try {
                        scene2 = new Scene(fxmlLoader.load());
                        ReservationDeSalleController controller = fxmlLoader.getController();
                        controller.setDateTime(finalDateTime);
                        controller.setMode(mode);
                        controller.setCalendar(apiCalendar);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    CalendrierApplication.stage2.setResizable(false);
                    CalendrierApplication.stage2.setTitle("Réservation de Salle");
                    CalendrierApplication.stage2.setScene(scene2);
                    CalendrierApplication.stage2.show();
                }
            });
        }
    }

    private void setListenerOnHeigthStage(){
        CalendrierApplication.stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //y =116 max720 107 max 601
                if (!Objects.equals(oldValue.toString(), "NaN")) {
                    setHeigth(newValue.doubleValue());
                }
            }
        });
    }

    private void setHeigth(double newValue){
        anchorPane.setPrefHeight(newValue - 116-42);
    }

    private void setListenerOnWidthStage(){
        CalendrierApplication.stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int suppression=240;
                if (newValue.doubleValue()<791){
                    suppression = 10;
                    if (oldValue.doubleValue()>=791 && newValue.doubleValue()<791) {
                        anchorPane.setLayoutY(anchorPane.getLayoutY() + 300);
                    }
                }
                if (oldValue.doubleValue()<=791 && newValue.doubleValue()>791){
                    anchorPane.setLayoutY(anchorPane.getLayoutY() - 300);
                }
                setWidth(newValue.doubleValue(), suppression);
            }
        });
    }

    private void setWidth(double valeurWidth,int suppression){
        anchorPane.setPrefWidth(valeurWidth-suppression-57-20);
        gridPane.setPrefWidth(valeurWidth-suppression-57-20);
        jourMois.setPrefWidth(valeurWidth-suppression-57-20);
        paneLundi.setPrefWidth((CalendrierApplication.stage.getWidth()-suppression-57-20)/5);
        lundi.setLayoutX(paneLundi.getPrefWidth()/2.6);
        paneMardi.setPrefWidth((CalendrierApplication.stage.getWidth()-suppression-57-20)/5);
        mardi.setLayoutX(paneMardi.getPrefWidth()/2.75);
        paneMercredi.setPrefWidth((CalendrierApplication.stage.getWidth()-suppression-57-20)/5);
        mercredi.setLayoutX(paneMercredi.getPrefWidth()/2.85);
        paneJeudi.setPrefWidth((CalendrierApplication.stage.getWidth()-suppression-57-20)/5);
        jeudi.setLayoutX(paneJeudi.getPrefWidth()/2.6);
        paneVendredi.setPrefWidth((CalendrierApplication.stage.getWidth()-suppression-57-20)/5);
        vendredi.setLayoutX(paneVendredi.getPrefWidth()/3);
    }
    private void setModePersonnel(String mode,ApiCalendar apiCalendar,VBox vBox){
        if (Objects.equals(mode, "favoris")) {
            LocalDateTime finalDateTime = dateTime;
            vBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    FXMLLoader fxmlLoader = new FXMLLoader(CreationEvenementController.class.getResource("CreationDEvenement.fxml"));
                    Scene scene2 = null;
                    try {
                        scene2 = new Scene(fxmlLoader.load());
                        CreationEvenementController controller = fxmlLoader.getController();
                        controller.setDateTime(finalDateTime);
                        controller.setMode(mode);
                        controller.setCalendar(apiCalendar);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    CalendrierApplication.stage2.setResizable(false);
                    CalendrierApplication.stage2.setTitle("Réservation d'Evenement");
                    CalendrierApplication.stage2.setScene(scene2);
                    CalendrierApplication.stage2.show();
                }
            });
        }
        jourMois.getChildren().add(vBox);
        if (dateTime.getDayOfWeek().getValue()<5)
            dateTime = dateTime.plusDays(1);
        else
            dateTime = dateTime.plusDays(3);
    }

    private CaseDuMoisController creationEvent(Event event ,CaseDuMoisController caseDuMoisController,String userPriviledge,String mode){
        String tootlipText = "                        "+Integer.toString(event.getDateEvent().getStartDate().getHour()) + "h" + Integer.toString(event.getDateEvent().getStartDate().getMinute()) + "-" +
                Integer.toString(event.getDateEvent().getEndDate().getHour()) + "h" + Integer.toString(event.getDateEvent().getEndDate().getMinute()) + "\n";
        if (!event.getDescriptionEvent().getListDescription().isEmpty()) {
            if (event.getDescriptionEvent().getDescription("Matière ") != null)
                tootlipText = tootlipText + "Matière :" + event.getDescriptionEvent().getDescription("Matière ") + "\n";
            if (event.getDescriptionEvent().getDescription("Enseignant ") != null)
                tootlipText = tootlipText + "Enseignant :" + event.getDescriptionEvent().getDescription("Enseignant ") + "\n";
            if (event.getDescriptionEvent().getDescription("Enseignants ") != null)
                tootlipText = tootlipText + "Enseignants :" + event.getDescriptionEvent().getDescription("Enseignants ") + "\n";
            if (event.getDescriptionEvent().getDescription("Promotion ") != null)
                tootlipText = tootlipText + "Promotion :" + event.getDescriptionEvent().getDescription("Promotion ") + "\n";
            if (event.getDescriptionEvent().getDescription("TD ") != null)
                tootlipText = tootlipText + "TD :" + event.getDescriptionEvent().getDescription("TD ") + "\n";
            if (event.getDescriptionEvent().getDescription("Salle ") != null)
                tootlipText = tootlipText + "Salle :" + event.getDescriptionEvent().getDescription("Salle ") + "\n";
            if (event.getDescriptionEvent().getDescription("Salles ") != null)
                tootlipText = tootlipText + "Salles :" + event.getDescriptionEvent().getDescription("Salles ") + "\n";
            if (event.getDescriptionEvent().getDescription("Lieu ") != null)
                tootlipText = tootlipText + "Lieu :" + event.getDescriptionEvent().getDescription("Lieu ") + "\n";
            if (event.getDescriptionEvent().getDescription("Type ") != null)
                tootlipText = tootlipText + "Type :" + event.getDescriptionEvent().getDescription("Type ") + "\n";
            if (event.getDescriptionEvent().getDescription("Personnes ") != null)
                tootlipText = tootlipText + "Personnes :" + event.getDescriptionEvent().getDescription("Personnes ") + "\n";
            if (event.getDescriptionEvent().getDescription("Groupe ") != null)
                tootlipText = tootlipText + "Groupe :" + event.getDescriptionEvent().getDescription("Groupe ") + "\n";
            if (event.getDescriptionEvent().getDescription("Mémo ") != null)
                tootlipText = tootlipText + "Mémo :" + event.getDescriptionEvent().getDescription("Mémo ") + "\n";
        }
        else{
            tootlipText = tootlipText+"Férié";
        }
        caseDuMoisController.addCircle(tootlipText,mode,event.getDescriptionEvent(),event.getDescriptionEvent().getDescription("Couleur "));
        return caseDuMoisController;
    }

    public void setArrayList(ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> arrayList) {
        this.arrayList = arrayList;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StringProperty stringProperty = CalendrierController.couleur;
        stringProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                setColor(s);
            }
        });
    }
}
