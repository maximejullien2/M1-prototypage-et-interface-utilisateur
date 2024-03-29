package com.example.propotypage_et_interface_utilisateur;

import com.example.Icalendar.ApiCalendar;
import com.example.Icalendar.Event;
import com.example.Icalendar.ParserIcalendar;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class CalendrierController implements Initializable {
    public TextField urlTextField;
    public Text urlText;
    public TextField nameEdtTextField;
    public Text nameEdtText;
    public Button addEdtButton;
    @FXML
    ChoiceBox choiceBox;

    @FXML
    AnchorPane anchorPane;

    @FXML
    Pane calendrier;

    @FXML
    Button buttonAfter;

    @FXML
    Button buttonBefore;

    @FXML
    Button buttonTheme;

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setModeConnexion(String modeConnexion) {
        this.modeConnexion = modeConnexion;
    }

    /**
     * favoris , personnel , formation,salle
     */
    String mode;

    /**
     * Enseignant ou Eleve
     */
    String modeConnexion;

    public void setList(ArrayList<HashMap<String, String>> list) {
        this.list = list;
    }

    ArrayList<HashMap<String,String>> list;

    public void setIdListe(int idListe) {
        this.idListe = idListe;
    }

    int idListe;


    public static void setCouleur(String couleur) {
        CalendrierController.couleur.set(couleur);
    }

    static StringProperty couleur = new SimpleStringProperty("white");



    ApiCalendar apiCalendar;

    LocalDateTime localDateTime;

    String[] jour = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
    private void affichageJour(){
        URL test ;
        FXMLLoader fxmlLoader;
        AnchorPane anchorPane;
        test = CasDuJourController.class.getResource("CasDuJour.fxml");
        ArrayList<ArrayList<ArrayList<Event>>> output = apiCalendar.getEventDay(localDateTime);
        fxmlLoader =new FXMLLoader(test);
        try {
            anchorPane = fxmlLoader.load();
            CasDuJourController controller = fxmlLoader.getController();
            controller.setList(output);
            controller.setDay(this.jour[this.localDateTime.getDayOfWeek().getValue()-1]+" "+this.localDateTime.getDayOfMonth());
            controller.affichage();
            calendrier.getChildren().clear();
            calendrier.getChildren().add(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void affichageSemaine(){
        URL test ;
        FXMLLoader fxmlLoader;
        AnchorPane anchorPane;
        test = CasDuSemaineController.class.getResource("CasDuSemaine.fxml");
        ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> output = apiCalendar.getEventWeek(localDateTime);
        fxmlLoader =new FXMLLoader(test);
        try {
            anchorPane = fxmlLoader.load();
            CasDuSemaineController controller = fxmlLoader.getController();
            controller.setArrayList(output);
            controller.affichage();
            calendrier.getChildren().clear();
            calendrier.getChildren().add(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void affichageMois(){
        URL test ;
        FXMLLoader fxmlLoader;
        AnchorPane anchorPane;
        test = CasDuMoisController.class.getResource("CasDuMois.fxml");
        ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> output = apiCalendar.getEventMounth(localDateTime);
        fxmlLoader =new FXMLLoader(test);
        try {
            anchorPane = fxmlLoader.load();
            CasDuMoisController controller = fxmlLoader.getController();
            controller.setArrayList(output);
            controller.setDateTime(apiCalendar.getDateMounth(localDateTime));
            controller.affichage();
            calendrier.getChildren().clear();
            calendrier.getChildren().add(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        localDateTime = LocalDateTime.now();
        this.choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if (number2.intValue()==0) {
                    affichageJour();
                }
                else if(number2.intValue()==1) {
                    affichageSemaine();
                }
                else {
                    affichageMois();
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

    @FXML
    public void buttonAfterOnMouseClicked(){
        if (this.choiceBox.getSelectionModel().isSelected(0)){
            if (localDateTime.getDayOfWeek().getValue()>=5){
                while (localDateTime.getDayOfWeek().getValue()>=5){
                    localDateTime = localDateTime.plusDays(1);
                }
            }
            else{
                localDateTime = localDateTime.plusDays(1);
            }
            affichageJour();
        } else if (this.choiceBox.getSelectionModel().isSelected(1)) {
            localDateTime = localDateTime.plusDays(7);
            affichageSemaine();
        }
        else {
            localDateTime = localDateTime.plusMonths(1);
            affichageMois();
        }
    }

    @FXML
    public void buttonBeforeOnMouseClicked(){
        if (this.choiceBox.getSelectionModel().isSelected(0)){
            if (localDateTime.getDayOfWeek().getValue()==1){
                localDateTime=localDateTime.minusDays(3);
            }
            else{
                localDateTime = localDateTime.minusDays(1);
            }
            affichageJour();
        } else if (this.choiceBox.getSelectionModel().isSelected(1)) {
            localDateTime = localDateTime.minusDays(7);
            affichageSemaine();
        }
        else {
            localDateTime = localDateTime.minusMonths(1);
            affichageMois();
        }
    }

    @FXML
    public void buttonThemeOnMouseClicked(){
        if (Objects.equals(CalendrierController.couleur.get(), "black")){
            CalendrierController.couleur.set("white");
        }
        else{
            CalendrierController.couleur.set("black");
        }
        anchorPane.setStyle("-fx-background-color:"+CalendrierController.couleur.get()+";");
    }

    @FXML
    public void addEdtOnMouseClicked() throws IOException {
        if (!Objects.equals(nameEdtTextField.getText(), "") && !Objects.equals(urlTextField.getText(), "")){
            InputStream in = null;
            try {
                in = new URL(urlTextField.getText()).openStream();
                Files.copy(in, Paths.get("src/main/resources/com/example/connexion/"+this.list.get(this.idListe).get("mailAdresse")+"/"+mode+"/"+nameEdtTextField.getText()+".ics"), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("Erreur");
            }
        }
    }
}
