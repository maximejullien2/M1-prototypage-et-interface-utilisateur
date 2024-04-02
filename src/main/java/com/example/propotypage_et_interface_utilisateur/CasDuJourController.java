package com.example.propotypage_et_interface_utilisateur;

import com.example.Icalendar.ApiCalendar;
import com.example.Icalendar.Event;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.awt.Desktop.getDesktop;

public class CasDuJourController implements Initializable {
    public AnchorPane anchorPane;
    @FXML
    Line line9;

    @FXML
    Line line10;

    @FXML
    Line line11;

    @FXML
    Line line12;

    @FXML
    Line line13;

    @FXML
    Line line14;

    @FXML
    Line line15;

    @FXML
    Line line16;

    @FXML
    Line line17;

    @FXML
    Line line18;

    @FXML
    Line line19;

    @FXML
    VBox vBox;

    @FXML
    Text jour;

    @FXML
    GridPane gridPane;

    @FXML
    Pane paneHeure;

    @FXML
    Pane paneCours;

    @FXML
    Text heure8;

    @FXML
    Text heure9;

    @FXML
    Text heure10;

    @FXML
    Text heure11;

    @FXML
    Text heure12;

    @FXML
    Text heure13;

    @FXML
    Text heure14;

    @FXML
    Text heure15;

    @FXML
    Text heure16;

    @FXML
    Text heure17;

    @FXML
    Text heure18;

    @FXML
    Text heure19;

    ArrayList<ArrayList<ArrayList<Event>>> list;

    LocalDateTime day;
    String[] jours = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};

    public void affichage(String mode, String userPriviledge, ApiCalendar apiCalendar){
        Color couleur;
        if (Objects.equals(CalendrierController.couleur.get(), "white")){
            couleur = Color.BLACK;
            paneCours.setStyle("-fx-border-color:black;");
            paneHeure.setStyle("-fx-border-color:black;");
            gridPane.setStyle("-fx-border-color:black;");
        }
        else {
            couleur = Color.WHITE;
            paneCours.setStyle("-fx-border-color:white;");
            paneHeure.setStyle("-fx-border-color:white;");
            gridPane.setStyle("-fx-border-color:white;");
        }
        jour.setFill(couleur);
        heure8.setFill(couleur);
        heure9.setFill(couleur);
        heure10.setFill(couleur);
        heure11.setFill(couleur);
        heure12.setFill(couleur);
        heure13.setFill(couleur);
        heure14.setFill(couleur);
        heure15.setFill(couleur);
        heure16.setFill(couleur);
        heure17.setFill(couleur);
        heure18.setFill(couleur);
        heure19.setFill(couleur);
        line9.setStroke(couleur);
        line10.setStroke(couleur);
        line11.setStroke(couleur);
        line12.setStroke(couleur);
        line13.setStroke(couleur);
        line14.setStroke(couleur);
        line15.setStroke(couleur);
        line16.setStroke(couleur);
        line17.setStroke(couleur);
        line18.setStroke(couleur);
        line19.setStroke(couleur);
        StringProperty essai = CalendrierController.couleur;
        essai.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                gridPane.setStyle("-fx-border-color:" + s + ";");
                Color couleur;
                if (s == "black"){
                    couleur = Color.BLACK;
                }
                else {
                    couleur = Color.WHITE;
                }
                jour.setFill(couleur);
                    heure8.setFill(couleur);
                    heure9.setFill(couleur);
                    heure10.setFill(couleur);
                    heure11.setFill(couleur);
                    heure12.setFill(couleur);
                    heure13.setFill(couleur);
                    heure14.setFill(couleur);
                    heure15.setFill(couleur);
                    heure16.setFill(couleur);
                    heure17.setFill(couleur);
                    heure18.setFill(couleur);
                    heure19.setFill(couleur);
                    line9.setStroke(couleur);
                    line10.setStroke(couleur);
                    line11.setStroke(couleur);
                    line12.setStroke(couleur);
                    line13.setStroke(couleur);
                    line14.setStroke(couleur);
                    line15.setStroke(couleur);
                    line16.setStroke(couleur);
                    line17.setStroke(couleur);
                    line18.setStroke(couleur);
                    line19.setStroke(couleur);
                paneCours.setStyle("-fx-border-color:"+s+";");
                paneHeure.setStyle("-fx-border-color:"+s+";");
            }
        });
        jour.setText(this.jours[this.day.getDayOfWeek().getValue()-1]+" "+this.day.getDayOfMonth());
        URL test = CasePourLeJourController.class.getResource("CasePourLeJour.fxml");
        try {
            CasePourLeJourController casePourLeJourController ;
            for (int i=0 ; i< list.size() ; i++) {
                if (list.get(i)!=null) {
                    HBox hBox = new HBox();
                    for (int j = 0 ; j < list.get(i).size() ; j++){
                        VBox vBox1 = new VBox();
                        for(int q=0 ; q < list.get(i).get(j).size();q++){
                            if (list.get(i).get(j).get(q)==null){
                                FXMLLoader fxmlLoader = new FXMLLoader(test);
                                AnchorPane anchorPane = fxmlLoader.load();
                                casePourLeJourController = fxmlLoader.getController();
                                casePourLeJourController.setHeigth(3.065);
                                casePourLeJourController.setNombreDeCase(list.get(i).size());
                                casePourLeJourController.setOpacity(1.0);
                                vBox1.getChildren().add(anchorPane);
                            }
                            else{
                                FXMLLoader fxmlLoader = new FXMLLoader(test);
                                AnchorPane anchorPane = fxmlLoader.load();
                                casePourLeJourController = fxmlLoader.getController();
                                casePourLeJourController.setNombreDeCase(list.get(i).size());
                                casePourLeJourController.setHeigth((double) 90 / ChronoUnit.MINUTES.between(list.get(i).get(j).get(q).getDateEvent().getStartDate(), list.get(i).get(j).get(q).getDateEvent().getEndDate()));
                                Event event = list.get(i).get(j).get(q);
                                casePourLeJourController.setInformation(event.getDescriptionEvent().getListDescription(),Integer.toString(event.getDateEvent().getStartDate().getHour()) + "h" + Integer.toString(event.getDateEvent().getStartDate().getMinute()) + "-" +
                                        Integer.toString(event.getDateEvent().getEndDate().getHour()) + "h" + Integer.toString(event.getDateEvent().getEndDate().getMinute())
                                        + "\\" );
                                vBox1.getChildren().add(anchorPane);
                            }
                        }
                            hBox.getChildren().add(vBox1);
                    }
                    vBox.getChildren().add(hBox);
                }
                else {
                    FXMLLoader fxmlLoader = new FXMLLoader(test);
                    AnchorPane anchorPane = fxmlLoader.load();
                    casePourLeJourController = fxmlLoader.getController();
                    casePourLeJourController.setOpacity(1.0);
                    casePourLeJourController.setHeigth(3.065);
                    vBox.getChildren().add(anchorPane);
                }
            }
            if (Objects.equals(mode, "salle") && Objects.equals(userPriviledge, "enseignant")){
                vBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        FXMLLoader fxmlLoader = new FXMLLoader(CalendrierController.class.getResource("ReservationDeSalle.fxml"));
                        Scene scene2 = null;
                        try {
                            scene2 = new Scene(fxmlLoader.load());
                            CreationController controller = fxmlLoader.getController();
                            controller.setDateTime(day);
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
            } else if (Objects.equals(mode, "favoris")) {
                vBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        FXMLLoader fxmlLoader = new FXMLLoader(CreationEvenementController.class.getResource("CreationDEvenement.fxml"));
                        Scene scene2 = null;
                        try {
                            scene2 = new Scene(fxmlLoader.load());
                            CreationEvenementController controller = fxmlLoader.getController();
                            System.out.println(apiCalendar);
                            controller.setCalendar(apiCalendar);
                            controller.setDateTime(day);
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
            for (int i =0 ; i<vBox.getChildren().size();i++){
                if (vBox.getChildren().get(i).getOpacity()!=0.0 && Objects.equals(mode, "formation")){
                vBox.getChildren().get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Desktop desktop = getDesktop();
                        try {
                            desktop.mail(new URI("mailto:maxime.jullien2@alumni.univ-avignon.fr"));
                        } catch (IOException | URISyntaxException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                anchorPane.setPrefWidth(newValue.doubleValue()-suppression-57-50);
                paneCours.setPrefWidth(newValue.doubleValue()-suppression-57-50);
                gridPane.setPrefWidth(newValue.doubleValue()-suppression-57-50);
                line9.setEndX(newValue.doubleValue()-suppression-57-67);
                line10.setEndX(newValue.doubleValue()-suppression-57-67);
                line11.setEndX(newValue.doubleValue()-suppression-57-67);
                line12.setEndX(newValue.doubleValue()-suppression-57-67);
                line13.setEndX(newValue.doubleValue()-suppression-57-67);
                line14.setEndX(newValue.doubleValue()-suppression-57-67);
                line15.setEndX(newValue.doubleValue()-suppression-57-67);
                line16.setEndX(newValue.doubleValue()-suppression-57-67);
                line17.setEndX(newValue.doubleValue()-suppression-57-67);
                line18.setEndX(newValue.doubleValue()-suppression-57-67);
                line19.setEndX(newValue.doubleValue()-suppression-57-67);
            }
        });
        CalendrierApplication.stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //y =116 max720 107 max 601
                if (!Objects.equals(oldValue.toString(), "NaN")) {
                    paneHeure.setPrefHeight(newValue.doubleValue() - 116);
                    anchorPane.setPrefHeight(newValue.doubleValue() - 116-42);
                    gridPane.setPrefHeight((heure8.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116))-5);
                    paneCours.setLayoutY((heure8.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116))-5);
                    paneCours.setPrefHeight(newValue.doubleValue()  - 116 - 100+57-paneCours.getLayoutY());
                    heure8.setLayoutY(heure8.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    heure9.setLayoutY(heure9.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    heure10.setLayoutY(heure10.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    heure11.setLayoutY(heure11.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    heure12.setLayoutY(heure12.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    heure13.setLayoutY(heure13.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    heure14.setLayoutY(heure14.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    heure15.setLayoutY(heure15.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    heure16.setLayoutY(heure16.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    heure17.setLayoutY(heure17.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    heure18.setLayoutY(heure18.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    heure19.setLayoutY(heure19.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    line9.setLayoutY(line9.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    line10.setLayoutY(line10.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    line11.setLayoutY(line11.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    line12.setLayoutY(line12.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    line13.setLayoutY(line13.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    line14.setLayoutY(line14.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    line15.setLayoutY(line15.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    line16.setLayoutY(line16.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    line17.setLayoutY(line17.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    line18.setLayoutY(line18.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                    line19.setLayoutY(line19.getLayoutY()*(newValue.doubleValue()-116)/(oldValue.doubleValue()-116));
                }
            }
        });
        System.out.println(CalendrierApplication.stage.getWidth());
        int suppression=240;
        if (CalendrierApplication.stage.getWidth()<791){
            suppression=10;
            if (CalendrierApplication.stage.getWidth()<791) {
                anchorPane.setLayoutY(anchorPane.getLayoutY() + 300);
            }
        }
        anchorPane.setPrefWidth(CalendrierApplication.stage.getWidth()-suppression-57-50);
        paneCours.setPrefWidth(CalendrierApplication.stage.getWidth()-suppression-57-50);
        gridPane.setPrefWidth(CalendrierApplication.stage.getWidth()-suppression-57-50);
        line9.setEndX(CalendrierApplication.stage.getWidth()-suppression-57-67);
        line10.setEndX(CalendrierApplication.stage.getWidth()-suppression-57-67);
        line11.setEndX(CalendrierApplication.stage.getWidth()-suppression-57-67);
        line12.setEndX(CalendrierApplication.stage.getWidth()-suppression-57-67);
        line13.setEndX(CalendrierApplication.stage.getWidth()-suppression-57-67);
        line14.setEndX(CalendrierApplication.stage.getWidth()-suppression-57-67);
        line15.setEndX(CalendrierApplication.stage.getWidth()-suppression-57-67);
        line16.setEndX(CalendrierApplication.stage.getWidth()-suppression-57-67);
        line17.setEndX(CalendrierApplication.stage.getWidth()-suppression-57-67);
        line18.setEndX(CalendrierApplication.stage.getWidth()-suppression-57-67);
        line19.setEndX(CalendrierApplication.stage.getWidth()-suppression-57-67);

        if (!Objects.equals(Double.toString(CalendrierApplication.stage.getHeight()), "NaN")) {
            paneHeure.setPrefHeight(CalendrierApplication.stage.getHeight() - 116);
            anchorPane.setPrefHeight(CalendrierApplication.stage.getHeight() - 116 - 42);
            gridPane.setPrefHeight((heure8.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116))-5);
            paneCours.setLayoutY((heure8.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116)) - 5);
            paneCours.setPrefHeight(CalendrierApplication.stage.getHeight() - 116 - 102.5+57-paneCours.getLayoutY());
            heure8.setLayoutY(heure8.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            heure9.setLayoutY(heure9.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            heure10.setLayoutY(heure10.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            heure11.setLayoutY(heure11.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            heure12.setLayoutY(heure12.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            heure13.setLayoutY(heure13.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            heure14.setLayoutY(heure14.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            heure15.setLayoutY(heure15.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            heure16.setLayoutY(heure16.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            heure17.setLayoutY(heure17.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            heure18.setLayoutY(heure18.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            heure19.setLayoutY(heure19.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            line9.setLayoutY(line9.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            line10.setLayoutY(line10.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            line11.setLayoutY(line11.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            line12.setLayoutY(line12.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            line13.setLayoutY(line13.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            line14.setLayoutY(line14.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            line15.setLayoutY(line15.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            line16.setLayoutY(line16.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            line17.setLayoutY(line17.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            line18.setLayoutY(line18.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
            line19.setLayoutY(line19.getLayoutY() * (CalendrierApplication.stage.getHeight() - 116) / (727 - 116));
        }
    }

    public void setList(ArrayList<ArrayList<ArrayList<Event>>> list) {
        this.list = list;
    }

    public void setDay(LocalDateTime day) {
        this.day = day;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
