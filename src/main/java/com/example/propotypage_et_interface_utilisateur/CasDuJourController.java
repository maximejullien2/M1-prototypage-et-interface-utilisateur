package com.example.propotypage_et_interface_utilisateur;

import com.example.Icalendar.Event;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CasDuJourController implements Initializable {
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

    String day;

    public void affichage(){
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
                paneCours.setStyle("-fx-border-color:"+s+";");
                paneHeure.setStyle("-fx-border-color:"+s+";");
            }
        });
        jour.setText(this.day);
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
                                casePourLeJourController.setOpacity(0.0);
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
                    casePourLeJourController.setOpacity(0);
                    casePourLeJourController.setHeigth(3.065);
                    vBox.getChildren().add(anchorPane);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setList(ArrayList<ArrayList<ArrayList<Event>>> list) {
        this.list = list;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
