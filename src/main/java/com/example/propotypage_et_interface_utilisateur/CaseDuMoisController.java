package com.example.propotypage_et_interface_utilisateur;

import com.example.Icalendar.DescriptionEvent;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.PopupWindow;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static java.awt.Desktop.getDesktop;

public class CaseDuMoisController implements Initializable {

    @FXML
    VBox vBox;

    @FXML
    AnchorPane anchorPane;

    @FXML
    Text nombreSeanceId;

    @FXML
    Text jourID;

    public FlowPane getFlowPane() {
        return flowPane;
    }

    @FXML
    FlowPane flowPane;

    ArrayList<Circle> list = new ArrayList<>();
    ArrayList<String> listString = new ArrayList<>();

    ArrayList<String> colorCode=new ArrayList<>();
    void setNombreSeanceId(String text){
        this.nombreSeanceId.setText(text);
        if (Objects.equals(CalendrierController.couleur.get(), "white"))
            setColor("black");
        else
            setColor("white");
    }

    void setJourId(String text,int paddingEnMoins){
        this.jourID.setText(text);
        HBox.setMargin(jourID,new Insets(0,0,0,49-paddingEnMoins));
        if (Objects.equals(CalendrierController.couleur.get(), "white"))
            setColor("black");
        else
            setColor("white");
    }

    void addCircle(String tooltipMessage, String mode, DescriptionEvent descriptionEvent, String couleur){
        if (couleur!=null){
            colorCode.add(couleur);
        }
        else{
            colorCode.add("cyan");
        }
        Circle circle = new Circle(7, Paint.valueOf("CYAN"));
        circle.setStroke(Paint.valueOf("BLACK"));
        if (Objects.equals(mode, "formation")) {
            String enseignants;
            if (descriptionEvent.getDescription("Enseignant ")!=null)
                enseignants = descriptionEvent.getDescription("Enseignant ");
            else if (descriptionEvent.getDescription("Enseignants ")!=null)
                enseignants = descriptionEvent.getDescription("Enseignants ");
            else
                enseignants =null;
            if (enseignants != null) {
                circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String[] listNomProffeseur = enseignants.split(Pattern.quote("\\,"));
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
        }
        if(flowPane.getChildren().size()%8!=0){
            FlowPane.setMargin(circle,new Insets(0,0,0,10));
        }
        Tooltip tooltip = new Tooltip(tooltipMessage);
        tooltip.setPrefWidth(200);
        tooltip.setWrapText(true);
        tooltip.setStyle("-fx-text-fill: red;");
        tooltip.setShowDelay(Duration.seconds(0.1));
        tooltip.setHideDelay(Duration.seconds(0.1));
        tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
        Tooltip.install(circle,tooltip);
        list.add(circle);
        listString.add(tooltipMessage);
        flowPane.getChildren().add(circle);
        if (Objects.equals(CalendrierController.couleur.get(), "white"))
            setColor("black");
        else
            setColor("white");
    }

    private void setColor(String couleur){
        Color couleurText;
        String couleurCercle;
        String couleurBordureCercle;
        if (Objects.equals(couleur, "white")){
            couleurText = Color.WHITE;
            couleurBordureCercle = "white";
        }
        else{
            couleurText = Color.BLACK;
            couleurBordureCercle = "black";
        }
        nombreSeanceId.setFill(couleurText);
        jourID.setFill(couleurText);
        vBox.setStyle("-fx-border-color:"+couleur+";-fx-border-width: 0.2");
        for (int i=0 ; i<list.size();i++){
            couleurCercle = colorCode.get(i).replace("0x","#").replaceAll(" ","");
            Color color = (Color) Paint.valueOf(couleurCercle);
            if (Objects.equals(couleur, "white")){
                couleurCercle = color.darker().toString().replace("0x","#");
            }
            list.get(i).setStroke(Paint.valueOf(couleurBordureCercle));
            list.get(i).setFill(Paint.valueOf(couleurCercle));
            Tooltip tooltip = new Tooltip(listString.get(i));
            tooltip.setPrefWidth(200);
            tooltip.setWrapText(true);
            tooltip.setStyle("-fx-text-fill:"+couleur+";");
            tooltip.setShowDelay(Duration.seconds(0.1));
            tooltip.setHideDelay(Duration.seconds(0.1));
            tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
            Tooltip.install( list.get(i),tooltip);
        }
    }

    private void setHeight(double newValue){
        int suppression=240;
        if (CalendrierApplication.stage.getWidth()<791){
            suppression=10;
        }
        vBox.setPrefWidth((newValue-suppression-57-20-4)/5);
        anchorPane.setPrefWidth((newValue-suppression-57-20-4)/5);
        flowPane.setPrefWidth((newValue-suppression-57-20-4)/5);
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

        CalendrierApplication.stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setHeight(newValue.doubleValue());
            }
        });
        this.setHeight(CalendrierApplication.stage.getWidth());
        CalendrierApplication.stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                vBox.setPrefHeight((vBox.getPrefHeight()*(newValue.doubleValue()-116))/(oldValue.doubleValue()-116));
                flowPane.setLayoutY(vBox.getLayoutY());
            }
        });
        vBox.setPrefHeight((vBox.getPrefHeight()*(CalendrierApplication.stage.getHeight()-116))/(727-116));
    }
}
