package com.example.propotypage_et_interface_utilisateur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.PopupWindow;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

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
    void setNombreSeanceId(String text){
        this.nombreSeanceId.setText(text);
    }

    void setJourId(String text,int paddingEnMoins){
        this.jourID.setText(text);
        HBox.setMargin(jourID,new Insets(0,0,0,49-paddingEnMoins));
    }

    void addCircle(String tooltipMessage){
        Circle circle = new Circle(7, Paint.valueOf("DODGERBLUE"));
        circle.setStroke(Paint.valueOf("BLACK"));
        if(flowPane.getChildren().size()%8!=0){
            FlowPane.setMargin(circle,new Insets(0,0,0,10));
        }
        Tooltip tooltip = new Tooltip(tooltipMessage);
        tooltip.setPrefWidth(100);
        tooltip.setWrapText(true);
        tooltip.setShowDelay(Duration.seconds(0.1));
        tooltip.setHideDelay(Duration.seconds(0.1));
        tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
        Tooltip.install(circle,tooltip);
        flowPane.getChildren().add(circle);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
