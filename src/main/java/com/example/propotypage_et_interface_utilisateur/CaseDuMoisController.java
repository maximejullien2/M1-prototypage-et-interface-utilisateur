package com.example.propotypage_et_interface_utilisateur;

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

import static java.awt.Desktop.getDesktop;

public class CaseDuMoisController implements Initializable {

    @FXML
    VBox vBox;

    @FXML
    HBox hBox;

    @FXML
    Text nombreSeanceId;

    @FXML
    Text jourID;

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
            setColor("white");    }

    void addCircle(String tooltipMessage,String mode,String userPriviledge,String couleur){
        if (couleur!=null){
            colorCode.add(couleur);
        }
        else{
            colorCode.add("cyan");
        }
        Circle circle = new Circle(7, Paint.valueOf("CYAN"));
        circle.setStroke(Paint.valueOf("BLACK"));
        if (Objects.equals(mode, "formation")) {
            circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
