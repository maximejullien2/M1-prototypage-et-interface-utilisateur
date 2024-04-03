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
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

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

    @FXML
    AnchorPane anchorPaneInGridPane;

    ArrayList<ArrayList<ArrayList<Event>>> list;

    LocalDateTime day;
    String[] jours = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};

    ArrayList<String> listProffesseur = new ArrayList<String>();

    private void setColor(Color couleur){
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
    }

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
        this.setColor(couleur);
        jour.setText(this.jours[this.day.getDayOfWeek().getValue()-1]+" "+this.day.getDayOfMonth());
        URL test = CasePourLeJourController.class.getResource("CasePourLeJour.fxml");
        try {
            for (int i=0 ; i< list.size() ; i++) {
                if (list.get(i)!=null) {
                    HBox hBox = new HBox();
                    for (int j = 0 ; j < list.get(i).size() ; j++){
                        VBox vBox1 = new VBox();
                        for(int q=0 ; q < list.get(i).get(j).size();q++){
                            if (list.get(i).get(j).get(q)==null){
                                this.setCaseVide(test,list.get(i).size(),vBox1);
                            }
                            else{
                                this.setCase(test,vBox1,i,j ,q);
                            }
                        }
                            hBox.getChildren().add(vBox1);
                    }
                    vBox.getChildren().add(hBox);
                }
                else {
                    this.setCaseVide(test,1,vBox);
                }
            }
            this.setModeSalle(mode,userPriviledge,apiCalendar);
            this.setModePersonnel(mode,apiCalendar);
            this.setModeFormation(mode);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            this.setHeigth(CalendrierApplication.stage.getHeight(),727);
        }
    }

    private void setModeSalle(String mode,String userPriviledge,ApiCalendar apiCalendar) {
        if (Objects.equals(mode, "salle") && Objects.equals(userPriviledge, "enseignant")) {
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
        }
    }

    private void setListenerOnHeigthStage(){
        CalendrierApplication.stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (!Objects.equals(oldValue.toString(), "NaN")) {
                    setHeigth(newValue.doubleValue(),oldValue.doubleValue());
                }
            }
        });
    }

    private void setHeigth(double newValue,double oldValue){
        paneHeure.setPrefHeight(newValue - 116);
        anchorPane.setPrefHeight(newValue - 116-42);
        gridPane.setPrefHeight((heure8.getLayoutY()*(newValue-116)/(oldValue-116))-5);
        paneCours.setLayoutY((heure8.getLayoutY()*(newValue-116)/(oldValue-116))-5);
        paneCours.setPrefHeight(newValue  - 116 - 100+57-paneCours.getLayoutY());
        heure8.setLayoutY(heure8.getLayoutY()*(newValue-116)/(oldValue-116));
        heure9.setLayoutY(heure9.getLayoutY()*(newValue-116)/(oldValue-116));
        heure10.setLayoutY(heure10.getLayoutY()*(newValue-116)/(oldValue-116));
        heure11.setLayoutY(heure11.getLayoutY()*(newValue-116)/(oldValue-116));
        heure12.setLayoutY(heure12.getLayoutY()*(newValue-116)/(oldValue-116));
        heure13.setLayoutY(heure13.getLayoutY()*(newValue-116)/(oldValue-116));
        heure14.setLayoutY(heure14.getLayoutY()*(newValue-116)/(oldValue-116));
        heure15.setLayoutY(heure15.getLayoutY()*(newValue-116)/(oldValue-116));
        heure16.setLayoutY(heure16.getLayoutY()*(newValue-116)/(oldValue-116));
        heure17.setLayoutY(heure17.getLayoutY()*(newValue-116)/(oldValue-116));
        heure18.setLayoutY(heure18.getLayoutY()*(newValue-116)/(oldValue-116));
        heure19.setLayoutY(heure19.getLayoutY()*(newValue-116)/(oldValue-116));
        line9.setLayoutY(line9.getLayoutY()*(newValue-116)/(oldValue-116));
        line10.setLayoutY(line10.getLayoutY()*(newValue-116)/(oldValue-116));
        line11.setLayoutY(line11.getLayoutY()*(newValue-116)/(oldValue-116));
        line12.setLayoutY(line12.getLayoutY()*(newValue-116)/(oldValue-116));
        line13.setLayoutY(line13.getLayoutY()*(newValue-116)/(oldValue-116));
        line14.setLayoutY(line14.getLayoutY()*(newValue-116)/(oldValue-116));
        line15.setLayoutY(line15.getLayoutY()*(newValue-116)/(oldValue-116));
        line16.setLayoutY(line16.getLayoutY()*(newValue-116)/(oldValue-116));
        line17.setLayoutY(line17.getLayoutY()*(newValue-116)/(oldValue-116));
        line18.setLayoutY(line18.getLayoutY()*(newValue-116)/(oldValue-116));
        line19.setLayoutY(line19.getLayoutY()*(newValue-116)/(oldValue-116));
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
                setWidth(newValue.doubleValue(),suppression);
            }
        });
    }

    private void setWidth(double valeurWidth,int suppression){
        anchorPane.setPrefWidth(valeurWidth-suppression-57-50);
        paneCours.setPrefWidth(valeurWidth-suppression-57-50);
        gridPane.setPrefWidth(valeurWidth-suppression-57-50);
        if (!Double.toString(valeurWidth).equals("NaN")) {
            anchorPaneInGridPane.setPrefWidth(valeurWidth - suppression - 57 - 50);
            jour.setX(anchorPaneInGridPane.getPrefWidth() / 2.25);
        }
        line9.setEndX(valeurWidth-suppression-57-67);
        line10.setEndX(valeurWidth-suppression-57-67);
        line11.setEndX(valeurWidth-suppression-57-67);
        line12.setEndX(valeurWidth-suppression-57-67);
        line13.setEndX(valeurWidth-suppression-57-67);
        line14.setEndX(valeurWidth-suppression-57-67);
        line15.setEndX(valeurWidth-suppression-57-67);
        line16.setEndX(valeurWidth-suppression-57-67);
        line17.setEndX(valeurWidth-suppression-57-67);
        line18.setEndX(valeurWidth-suppression-57-67);
        line19.setEndX(valeurWidth-suppression-57-67);
    }
    private void setModePersonnel(String mode,ApiCalendar apiCalendar){
        if (Objects.equals(mode, "favoris")) {
            vBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    FXMLLoader fxmlLoader = new FXMLLoader(CreationEvenementController.class.getResource("CreationDEvenement.fxml"));
                    Scene scene2 = null;
                    try {
                        scene2 = new Scene(fxmlLoader.load());
                        CreationEvenementController controller = fxmlLoader.getController();
                        controller.setCalendar(apiCalendar);
                        controller.setDateTime(day);
                        controller.setMode(mode);
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
    }
    private void setModeFormation(String mode){
        if (Objects.equals(mode, "formation")) {
            int id=0;
            for (int i = 0; i < vBox.getChildren().size(); i++) {
                if (vBox.getChildren().get(i).getOpacity() != 0.0) {
                    if (listProffesseur.get(id)!=null) {
                        int finalId = id;
                        vBox.getChildren().get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                String[] listNomProffeseur = listProffesseur.get(finalId).split(Pattern.quote("\\,"));
                                String nom = listNomProffeseur[0].split(" ")[1];
                                String firstname = listNomProffeseur[0].split(" ")[2];
                                String mail = "mailto:"+firstname.toLowerCase()+"."+nom.toLowerCase()+"@univ-avignon.fr";
                                for (int i=1;i<listNomProffeseur.length;i++){
                                    nom = listNomProffeseur[i].split(" ")[1];
                                    firstname = listNomProffeseur[i].split(" ")[2];
                                    mail = mail+","+firstname.toLowerCase()+"."+nom.toLowerCase()+"@univ-avignon.fr";
                                }
                                Desktop desktop = getDesktop();
                                try {
                                    desktop.mail(new URI(mail));
                                } catch (IOException | URISyntaxException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                    }
                    id= id+1;
                }
            }
        }
    }
    private void setCaseVide(URL test,int size,VBox vBox) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(test);
        CasePourLeJourController casePourLeJourController ;
        AnchorPane anchorPane = fxmlLoader.load();
        casePourLeJourController = fxmlLoader.getController();
        casePourLeJourController.setHeigth(3.065);
        casePourLeJourController.setNombreDeCase(size);
        casePourLeJourController.setOpacity(0.0);
        vBox.getChildren().add(anchorPane);
    }

    private void setCase(URL test,VBox vBox,int i,int j , int q) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(test);
        AnchorPane anchorPane = fxmlLoader.load();
        CasePourLeJourController casePourLeJourController ;
        casePourLeJourController = fxmlLoader.getController();
        casePourLeJourController.setNombreDeCase(list.get(i).size());
        casePourLeJourController.setHeigth((double) 90 / ChronoUnit.MINUTES.between(list.get(i).get(j).get(q).getDateEvent().getStartDate(), list.get(i).get(j).get(q).getDateEvent().getEndDate()));
        Event event = list.get(i).get(j).get(q);
        if (event.getDescriptionEvent().getDescription("Enseignant ")!=null)
            this.listProffesseur.add(event.getDescriptionEvent().getDescription("Enseignant "));
        else if (event.getDescriptionEvent().getDescription("Enseignants ")!=null)
            this.listProffesseur.add(event.getDescriptionEvent().getDescription("Enseignants "));
        else
            this.listProffesseur.add(null);
        casePourLeJourController.setInformation(event.getDescriptionEvent().getListDescription(),Integer.toString(event.getDateEvent().getStartDate().getHour()) + "h" + Integer.toString(event.getDateEvent().getStartDate().getMinute()) + "-" +
                Integer.toString(event.getDateEvent().getEndDate().getHour()) + "h" + Integer.toString(event.getDateEvent().getEndDate().getMinute())
                + "\\" );
        vBox.getChildren().add(anchorPane);
    }
    public void setList(ArrayList<ArrayList<ArrayList<Event>>> list) {
        this.list = list;
    }

    public void setDay(LocalDateTime day) {
        this.day = day;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StringProperty listenerOnColor = CalendrierController.couleur;
        listenerOnColor.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                Color couleur;
                if (Objects.equals(s, "black")){
                    couleur = Color.BLACK;
                }
                else {
                    couleur = Color.WHITE;
                }
                setColor(couleur);
                paneCours.setStyle("-fx-border-color:"+s+";");
                paneHeure.setStyle("-fx-border-color:"+s+";");
                gridPane.setStyle("-fx-border-color:" + s + ";");
            }
        });
    }
}
