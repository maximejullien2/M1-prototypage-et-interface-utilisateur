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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
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
    public Button deconnexionButton;
    public ComboBox selectionEdtComboBox;
    public Text selectionEdtText;
    public Button favorisButton;
    public Button formationButton;
    public Button personnelOnMouseClicked;
    public Button salleButton;
    @FXML
    ComboBox choiceBox;

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

    @FXML
    Text mouthText;

    public static void setCouleur(String couleur) {
        CalendrierController.couleur.set(couleur);
    }

    static StringProperty couleur = new SimpleStringProperty("white");



    ApiCalendar apiCalendar;

    LocalDateTime localDateTime;

    String[] jour = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
    String[] mois = {"Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Aout","Sepctembre","Octobre","Novembre","Decembre"};
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
            this.mouthText.setText(this.mois[this.localDateTime.getMonth().getValue()-1]+" "+this.localDateTime.getYear());
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
            this.mouthText.setText(this.mois[this.localDateTime.getMonth().getValue()-1]+" "+this.localDateTime.getYear());
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
            this.mouthText.setText(this.mois[this.localDateTime.getMonth().getValue()-1]+" "+this.localDateTime.getYear());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creationListEdt(){
        this.selectionEdtComboBox.getItems().clear();
        File dossier = new File("src/main/resources/com/example/connexion/"+this.list.get(this.idListe).get("mailAdresse")+"/"+this.mode+"/");
        for (int i = 0; i < Objects.requireNonNull(dossier.listFiles()).length; i++){
            this.selectionEdtComboBox.getItems().add(Objects.requireNonNull(dossier.listFiles())[i].getName().split("\\.")[0]);
        }
        this.selectionEdtComboBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                try {
                    if (number2.intValue()>=0) {
                        apiCalendar = new ParserIcalendar().parse("src/main/resources/com/example/connexion/" + list.get(idListe).get("mailAdresse") + "/" + mode + "/" + selectionEdtComboBox.getItems().get(number2.intValue()).toString() + ".ics");
                    }
                    else {
                        apiCalendar = new ParserIcalendar().parse("src/main/resources/com/example/Icalendar/test.ics");
                    }
                        int identifiant = choiceBox.getSelectionModel().getSelectedIndex();
                        if (identifiant == 0){
                            affichageJour();
                        } else if (identifiant == 1) {
                            affichageSemaine();
                        }
                        else{
                            affichageMois();
                        }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
        this.choiceBox.setVisibleRowCount(3);
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
            this.mouthText.setFill(Color.BLACK);
            this.urlText.setFill(Color.BLACK);
            this.nameEdtText.setFill(Color.BLACK);
            this.selectionEdtText.setFill(Color.BLACK);
        }
        else{
            CalendrierController.couleur.set("black");
            this.mouthText.setFill(Color.WHITE);
            this.urlText.setFill(Color.WHITE);
            this.nameEdtText.setFill(Color.WHITE);
            this.selectionEdtText.setFill(Color.WHITE);
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
                this.selectionEdtComboBox.getItems().add(nameEdtTextField.getText());
            } catch (IOException e) {
                System.out.println("Erreur");
            }
        }
    }

    @FXML
    public void deconnexionOnMouseClicked() throws IOException {
        File file = new File("src/main/resources/com/example/connexion/connected.txt");
        FileWriter fileWriter = new FileWriter(file,false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.close();
        Stage stage = (Stage) deconnexionButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ConnexionController.class.getResource("pageConnexion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Page de Connexion");
        stage.setScene(scene);
    }

    @FXML
    public void favorisOnMouseClicked() {
        if (!Objects.equals(this.mode, "favoris")){
            this.mode = "favoris";
            this.selectionEdtComboBox.getItems().clear();
            File dossier = new File("src/main/resources/com/example/connexion/"+this.list.get(this.idListe).get("mailAdresse")+"/"+this.mode+"/");
            for (int i = 0; i < Objects.requireNonNull(dossier.listFiles()).length; i++){
                this.selectionEdtComboBox.getItems().add(Objects.requireNonNull(dossier.listFiles())[i].getName().split("\\.")[0]);
            }
        }
    }

    @FXML
    public void formationOnMouseClicked() {
        if (!Objects.equals(this.mode, "formation")){
            this.mode = "formation";
            this.selectionEdtComboBox.getItems().clear();
            File dossier = new File("src/main/resources/com/example/connexion/"+this.list.get(this.idListe).get("mailAdresse")+"/"+this.mode+"/");
            for (int i = 0; i < Objects.requireNonNull(dossier.listFiles()).length; i++){
                this.selectionEdtComboBox.getItems().add(Objects.requireNonNull(dossier.listFiles())[i].getName().split("\\.")[0]);
            }
        }
    }

    @FXML
    public void personnelOnMouseClicked() {
        if (!Objects.equals(this.mode, "personnel")){
            this.mode = "personnel";
            this.selectionEdtComboBox.getItems().clear();
            File dossier = new File("src/main/resources/com/example/connexion/"+this.list.get(this.idListe).get("mailAdresse")+"/"+this.mode+"/");
            for (int i = 0; i < Objects.requireNonNull(dossier.listFiles()).length; i++){
                this.selectionEdtComboBox.getItems().add(Objects.requireNonNull(dossier.listFiles())[i].getName().split("\\.")[0]);
            }
        }
    }

    @FXML
    public void salleOnMouseClicked() {
        if (!Objects.equals(this.mode, "salle")){
            this.mode = "salle";
            this.selectionEdtComboBox.getItems().clear();
            File dossier = new File("src/main/resources/com/example/connexion/"+this.list.get(this.idListe).get("mailAdresse")+"/"+this.mode+"/");
            for (int i = 0; i < Objects.requireNonNull(dossier.listFiles()).length; i++){
                this.selectionEdtComboBox.getItems().add(Objects.requireNonNull(dossier.listFiles())[i].getName().split("\\.")[0]);
            }
        }
    }
}
