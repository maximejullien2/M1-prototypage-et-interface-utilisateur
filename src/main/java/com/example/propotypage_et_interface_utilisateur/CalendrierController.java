package com.example.propotypage_et_interface_utilisateur;

import com.example.Icalendar.ApiCalendar;
import com.example.Icalendar.Event;
import com.example.Icalendar.ParserIcalendar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CalendrierController implements Initializable {
    @FXML
    ChoiceBox choiceBox;

    @FXML
    Pane calendrier;

    ApiCalendar apiCalendar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                URL test ;
                FXMLLoader fxmlLoader;
                AnchorPane anchorPane;
                if (number2.intValue()==0) {
                    test = CasDuJourController.class.getResource("CasDuJour.fxml");
                    ArrayList<Event> output = apiCalendar.getEventDay(LocalDateTime.now().minusDays(1));
                    fxmlLoader =new FXMLLoader(test);
                    try {
                        anchorPane = fxmlLoader.load();
                        CasDuJourController controller = fxmlLoader.getController();
                        controller.setList(output);
                        controller.setDay(LocalDateTime.now().getDayOfWeek().name());
                        controller.affichage();
                        calendrier.getChildren().clear();
                        calendrier.getChildren().add(anchorPane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(number2.intValue()==1) {
                    test = CasDuSemaineController.class.getResource("CasDuSemaine.fxml");
                    fxmlLoader = new FXMLLoader(test);
                    try {
                        anchorPane = fxmlLoader.load();
                        calendrier.getChildren().clear();
                        calendrier.getChildren().add(anchorPane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    test = CasDuMoisController.class.getResource("CasDuMois.fxml");
                        fxmlLoader = new FXMLLoader(test);
                    try {
                        anchorPane = fxmlLoader.load();
                        calendrier.getChildren().clear();
                        calendrier.getChildren().add(anchorPane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.choiceBox.getItems().add("Jour");
        this.choiceBox.getItems().add("Semaine");
        this.choiceBox.getItems().add("Mois");
        try {
            this.apiCalendar = new ParserIcalendar().parse("src/main/resources/com/example/Icalendar/test.ics");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.choiceBox.getSelectionModel().select(0);
    }
}
