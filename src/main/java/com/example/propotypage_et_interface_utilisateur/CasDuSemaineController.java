package com.example.propotypage_et_interface_utilisateur;

import com.example.Icalendar.Event;
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
import java.util.Objects;
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

    @FXML
    Pane paneHeure;

    @FXML
    GridPane gridPane;
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
    Pane paneLundi;

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
    Text jourLundi;

    @FXML
    Text jourMardi;

    @FXML
    Text jourMercredi;

    @FXML
    Text jourJeudi;

    @FXML
    Text jourVendredi;
    public void setArrayList(ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> arrayList) {
        this.arrayList = arrayList;
    }

    private void setColorText(Color couleur){
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
        lundi.setFill(couleur);
        mardi.setFill(couleur);
        mercredi.setFill(couleur);
        jeudi.setFill(couleur);
        vendredi.setFill(couleur);
        jourLundi.setFill(couleur);
        jourMardi.setFill(couleur);
        jourMercredi.setFill(couleur);
        jourJeudi.setFill(couleur);
        jourVendredi.setFill(couleur);
    }

    private void setColorPaneVBox(String couleur){
        paneHeure.setStyle("-fx-border-color:"+couleur+";");
        vBoxLundi.setStyle("-fx-border-color:"+couleur+";");
        vBoxMardi.setStyle("-fx-border-color:"+couleur+";");
        vBoxMercredi.setStyle("-fx-border-color:"+couleur+";");
        vBoxJeudi.setStyle("-fx-border-color:"+couleur+";");
        vBoxVendredi.setStyle("-fx-border-color:"+couleur+";");
        paneLundi.setStyle("-fx-border-color:"+couleur+";");
        paneMardi.setStyle("-fx-border-color:"+couleur+";");
        paneMercredi.setStyle("-fx-border-color:"+couleur+";");
        paneJeudi.setStyle("-fx-border-color:"+couleur+";");
        paneVendredi.setStyle("-fx-border-color:"+couleur+";");
    }
    private void setColor(String color){
        gridPane.setStyle("-fx-border-color:" + color + ";");
        Color couleur;
        if (color == "black"){
            couleur = Color.BLACK;
        }
        else {
            couleur = Color.WHITE;
        }
        setColorText(couleur);
        setColorPaneVBox(color);
    }
    ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> arrayList;
    public void affichage(){
        URL test = CaseDeLaSemaineController.class.getResource("CaseDeLaSemaine.fxml");
        StringProperty essai = CalendrierController.couleur;
        essai.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                setColor(s);
            }
        });
        try {
            CaseDeLaSemaineController casePourLeJourController ;
            VBox[] vBox = {vBoxLundi,vBoxMardi,vBoxMercredi,vBoxJeudi,vBoxVendredi};
            Text[] texts = {jourLundi,jourMardi,jourMercredi,jourJeudi,jourVendredi};
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
                                    texts[pointeur].setText(Integer.toString(list.get(i).get(j).get(q).getDateEvent().getStartDate().getDayOfMonth()));
                                    FXMLLoader fxmlLoader = new FXMLLoader(test);
                                    AnchorPane anchorPane = fxmlLoader.load();
                                    casePourLeJourController = fxmlLoader.getController();
                                    casePourLeJourController.setNombreDeCase(list.get(i).size());
                                    casePourLeJourController.setHeigth((double) 90 / ChronoUnit.MINUTES.between(list.get(i).get(j).get(q).getDateEvent().getStartDate(), list.get(i).get(j).get(q).getDateEvent().getEndDate()));
                                    Event event = list.get(i).get(j).get(q);
                                    casePourLeJourController.setInformation(event.getDescriptionEvent().getListDescription(), Integer.toString(event.getDateEvent().getStartDate().getHour()) + "h" + Integer.toString(event.getDateEvent().getStartDate().getMinute()) + "-" +
                                            Integer.toString(event.getDateEvent().getEndDate().getHour()) + "h" + Integer.toString(event.getDateEvent().getEndDate().getMinute())
                                            + "\\");
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
            if (Objects.equals(essai.get(), "white"))
                setColor("black");
            else
                setColor("white");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
