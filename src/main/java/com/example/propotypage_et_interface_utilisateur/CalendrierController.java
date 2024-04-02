package com.example.propotypage_et_interface_utilisateur;

import com.example.Icalendar.ApiCalendar;
import com.example.Icalendar.Event;
import com.example.Icalendar.ParserIcalendar;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;

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
    public Text textModeSelectionne;

    @FXML
    Button filtreButton;
    public TextField typeCoursTextField;
    public TextField salleTextField;
    public TextField groupeTextField;
    public TextField matiereTextField;
    public Text definirFiltreText;
    public Text matiereText;
    public Text groupeText;
    public Text salleText;
    public Text typeText;

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

    @FXML
    Button aujourduiButton;

    public void setMode(String mode) {
        this.mode = mode;
        this.textModeSelectionne.setText("Mode sélectionnez : "+mode);
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

    String pathFileRessource;

    @FXML
    Text mouthText;

    public static void setCouleur(String couleur) {
        CalendrierController.couleur.set(couleur);
    }

    static StringProperty couleur = new SimpleStringProperty("white");



    ApiCalendar apiCalendar;

    LocalDateTime localDateTime;

    HashMap<String,String> filtres = new HashMap<String,String>();
    String[] mois = {"Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Aout","Sepctembre","Octobre","Novembre","Decembre"};

    private void setColor(Color color){
        this.mouthText.setFill(color);
        this.urlText.setFill(color);
        this.nameEdtText.setFill(color);
        this.selectionEdtText.setFill(color);
        this.definirFiltreText.setFill(color);
        this.matiereText.setFill(color);
        this.groupeText.setFill(color);
        this.salleText.setFill(color);
        this.typeText.setFill(color);
        this.textModeSelectionne.setFill(color);
    }
    public void couleur(){
        if (Objects.equals(CalendrierController.couleur.get(), "black")) {
            this.setColor(Color.WHITE);
        } else {
            this.setColor(Color.BLACK);
        }
        anchorPane.setStyle("-fx-background-color:" + CalendrierController.couleur.get() + ";");
    }

    private void affichageJour(){
        URL test ;
        FXMLLoader fxmlLoader;
        AnchorPane anchorPane;
        test = CasDuJourController.class.getResource("CasDuJour.fxml");
        ArrayList<ArrayList<ArrayList<Event>>> output = apiCalendar.getEventDay(localDateTime,filtres);
        fxmlLoader =new FXMLLoader(test);
        try {
            anchorPane = fxmlLoader.load();
            CasDuJourController controller = fxmlLoader.getController();
            controller.setList(output);
            controller.setDay(this.localDateTime);
            this.mouthText.setText(this.mois[this.localDateTime.getMonth().getValue()-1]+" "+this.localDateTime.getYear());
            if (!Objects.equals(pathFileRessource, "vide") && (Objects.equals(mode, "salle") || Objects.equals(mode, "favoris"))) {
                controller.affichage(this.mode, this.modeConnexion, this.apiCalendar);
            }
            else{
                controller.affichage(this.mode, this.modeConnexion, null);
            }
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
        ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> output = apiCalendar.getEventWeek(localDateTime,filtres);
        fxmlLoader =new FXMLLoader(test);
        try {
            LocalDateTime localDateTime1 = localDateTime.minusDays(localDateTime.getDayOfWeek().getValue()-1);
            anchorPane = fxmlLoader.load();
            CasDuSemaineController controller = fxmlLoader.getController();
            controller.setArrayList(output);
            controller.setJourLundi(localDateTime1);
            controller.setJourMardi(localDateTime1.plusDays(1));
            controller.setJourMercredi(localDateTime1.plusDays(2));
            controller.setJourJeudi(localDateTime1.plusDays(3));
            controller.setJourVendredi(localDateTime1.plusDays(4));
            if (!Objects.equals(pathFileRessource, "vide") && (Objects.equals(mode, "salle") || Objects.equals(mode, "favoris"))) {
                controller.affichage(this.mode, this.modeConnexion, this.apiCalendar);
            }
            else{
                controller.affichage(this.mode, this.modeConnexion, null);
            }
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
        ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> output = apiCalendar.getEventMounth(localDateTime,filtres);
        fxmlLoader =new FXMLLoader(test);
        try {
            anchorPane = fxmlLoader.load();
            CasDuMoisController controller = fxmlLoader.getController();
            controller.setArrayList(output);
            controller.setDateTime(apiCalendar.getDateMounth(localDateTime));
            if (!Objects.equals(pathFileRessource, "vide") && (Objects.equals(mode, "salle") || Objects.equals(mode, "favoris"))) {
                controller.affichage(this.mode, this.modeConnexion, this.apiCalendar);
            }
            else{
                controller.affichage(this.mode, this.modeConnexion, null);
            }
            calendrier.getChildren().clear();
            calendrier.getChildren().add(anchorPane);
            this.mouthText.setText(this.mois[this.localDateTime.getMonth().getValue()-1]+" "+this.localDateTime.getYear());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changementEdt(int valeur){
        try {
            if (valeur>=0) {
                pathFileRessource = "src/main/resources/com/example/connexion/" + list.get(idListe).get("mailAdresse") + "/" + mode + "/" + selectionEdtComboBox.getItems().get(valeur).toString() + ".ics";
                apiCalendar = new ParserIcalendar().parse("src/main/resources/com/example/connexion/" + list.get(idListe).get("mailAdresse") + "/" + mode + "/" + selectionEdtComboBox.getItems().get(valeur).toString() + ".ics");
            }
            else {
                pathFileRessource = "vide";
                apiCalendar = new ParserIcalendar().parse("src/main/resources/com/example/Icalendar/vide.ics");
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
    public void creationListEdt(){
        this.selectionEdtComboBox.getItems().clear();
        File dossier = new File("src/main/resources/com/example/connexion/"+this.list.get(this.idListe).get("mailAdresse")+"/"+this.mode+"/");
        for (int i = 0; i < Objects.requireNonNull(dossier.listFiles()).length; i++){
            this.selectionEdtComboBox.getItems().add(Objects.requireNonNull(dossier.listFiles())[i].getName().split("\\.")[0]);
        }
        this.selectionEdtComboBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                changementEdt(number2.intValue());
            }
        });
    }

    private void setOnHiddenListenerOnStage2(){
        CalendrierApplication.stage2.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                try {
                    apiCalendar = new ParserIcalendar().parse(apiCalendar.getFilepath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int identifiant = choiceBox.getSelectionModel().getSelectedIndex();
                if (identifiant == 0) {
                    affichageJour();
                } else if (identifiant == 1) {
                    affichageSemaine();
                } else {
                    affichageMois();
                }
            }
        });
    }

    private void setOnChangedListenerOnChoiceBox(){
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
    }

    private void setWidthChangeListenerOnStage(){
        final int[] change = {0};
        CalendrierApplication.stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.doubleValue() > 530) {
                    anchorPane.setPrefWidth(newValue.doubleValue()-20);
                    if (newValue.doubleValue() < 791) {
                        calendrier.setLayoutX(10);
                        if (change[0] ==0) {
                            anchorPane.setPrefHeight(anchorPane.getPrefHeight()+300);
                            change[0] =1;
                        }
                        selectionEdtText.setLayoutY(116);
                        selectionEdtText.setLayoutX(newValue.doubleValue() - 250);
                        selectionEdtComboBox.setLayoutY(127);
                        selectionEdtComboBox.setLayoutX(newValue.doubleValue() - 200);
                        definirFiltreText.setLayoutY(185);
                        definirFiltreText.setLayoutX(newValue.doubleValue() - 200);
                        matiereText.setLayoutY(220);
                        matiereText.setLayoutX(newValue.doubleValue() - 260);
                        matiereTextField.setLayoutY(205);
                        matiereTextField.setLayoutX(newValue.doubleValue() - 180);
                        groupeText.setLayoutY(255);
                        groupeText.setLayoutX(newValue.doubleValue() - 260);
                        groupeTextField.setLayoutY(240);
                        groupeTextField.setLayoutX(newValue.doubleValue() - 180);
                        salleText.setLayoutY(293);
                        salleText.setLayoutX(newValue.doubleValue() - 260);
                        salleTextField.setLayoutY(278);
                        salleTextField.setLayoutX(newValue.doubleValue() - 180);
                        typeText.setLayoutY(326);
                        typeText.setLayoutX(newValue.doubleValue() - 260);
                        typeCoursTextField.setLayoutY(311);
                        typeCoursTextField.setLayoutX(newValue.doubleValue() - 180);
                        filtreButton.setLayoutY(346);
                        filtreButton.setLayoutX(newValue.doubleValue() - 150);

                        buttonBefore.setLayoutX(20);
                        buttonBefore.setLayoutY(380);

                        aujourduiButton.setLayoutX((newValue.doubleValue() / 3) - 50);
                        aujourduiButton.setLayoutY(380);

                        buttonTheme.setLayoutY(225);

                        mouthText.setLayoutX(newValue.doubleValue() / 2);
                        mouthText.setLayoutY(400);
                        buttonAfter.setLayoutY(380);
                    } else {
                        calendrier.setLayoutX(240);
                        if (change[0] ==1) {
                            anchorPane.setPrefHeight(anchorPane.getPrefHeight()-300);
                            change[0] =0;
                        }
                        selectionEdtText.setLayoutY(252);
                        selectionEdtText.setLayoutX(13);
                        selectionEdtComboBox.setLayoutY(263);
                        selectionEdtComboBox.setLayoutX(66);

                        definirFiltreText.setLayoutY(321);
                        definirFiltreText.setLayoutX(59);
                        matiereText.setLayoutY(356);
                        matiereText.setLayoutX(38);
                        matiereTextField.setLayoutY(339);
                        matiereTextField.setLayoutX(86);
                        groupeText.setLayoutY(391);
                        groupeText.setLayoutX(39);
                        groupeTextField.setLayoutY(374);
                        groupeTextField.setLayoutX(87);
                        salleText.setLayoutY(429);
                        salleText.setLayoutX(54);
                        salleTextField.setLayoutY(412);
                        salleTextField.setLayoutX(88);
                        typeText.setLayoutY(462);
                        typeText.setLayoutX(7);
                        typeCoursTextField.setLayoutY(445);
                        typeCoursTextField.setLayoutX(89);
                        filtreButton.setLayoutY(482);
                        filtreButton.setLayoutX(89);

                        buttonBefore.setLayoutX(240);
                        buttonBefore.setLayoutY(85);

                        aujourduiButton.setLayoutX(362);
                        aujourduiButton.setLayoutY(85);

                        buttonTheme.setLayoutY(531);

                        mouthText.setLayoutX(600);
                        mouthText.setLayoutY(100);
                        buttonAfter.setLayoutY(85);
                    }
                    buttonAfter.setLayoutX(newValue.doubleValue() - 100);
                    calendrier.setPrefWidth(newValue.doubleValue());
                    deconnexionButton.setLayoutX(newValue.doubleValue() - 150);
                    choiceBox.setLayoutX(newValue.doubleValue() - 200);
                }
                anchorPane.setStyle("-fx-background-color:" + CalendrierController.couleur.get() + ";");
            }
        });

    }

    private void setHeightChangeListenerOnStage(){
        CalendrierApplication.stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (anchorPane.getPrefWidth()<791) {
                    anchorPane.setPrefHeight(newValue.doubleValue() + 300-42);
                }
                else{
                    anchorPane.setPrefHeight(newValue.doubleValue()-42);
                }
                anchorPane.setStyle("-fx-background-color:" + CalendrierController.couleur.get() + ";");
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        localDateTime = LocalDateTime.now();
        this.setOnChangedListenerOnChoiceBox();
        this.setOnHiddenListenerOnStage2();
        this.choiceBox.getItems().add("Jour");
        this.choiceBox.getItems().add("Semaine");
        this.choiceBox.getItems().add("Mois");
        try {
            pathFileRessource = "vide";
            this.apiCalendar = new ParserIcalendar().parse("src/main/resources/com/example/Icalendar/vide.ics");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.choiceBox.getSelectionModel().select(0);
        this.setWidthChangeListenerOnStage();
        this.setHeightChangeListenerOnStage();
    }

    @FXML
    public void buttonAfterOnMouseClicked(){
        if (!CalendrierApplication.stage2.isShowing()) {
            if (this.choiceBox.getSelectionModel().isSelected(0)) {
                if (localDateTime.getDayOfWeek().getValue() >= 5) {
                    while (localDateTime.getDayOfWeek().getValue() >= 5) {
                        localDateTime = localDateTime.plusDays(1);
                    }
                } else {
                    localDateTime = localDateTime.plusDays(1);
                }
                affichageJour();
            } else if (this.choiceBox.getSelectionModel().isSelected(1)) {
                localDateTime = localDateTime.plusDays(7);
                affichageSemaine();
            } else {
                localDateTime = localDateTime.plusMonths(1);
                affichageMois();
            }
        }
    }

    @FXML
    public void buttonBeforeOnMouseClicked(){
        if (!CalendrierApplication.stage2.isShowing()) {
            if (this.choiceBox.getSelectionModel().isSelected(0)) {
                if (localDateTime.getDayOfWeek().getValue() == 1) {
                    localDateTime = localDateTime.minusDays(3);
                } else {
                    localDateTime = localDateTime.minusDays(1);
                }
                affichageJour();
            } else if (this.choiceBox.getSelectionModel().isSelected(1)) {
                localDateTime = localDateTime.minusDays(7);
                affichageSemaine();
            } else {
                localDateTime = localDateTime.minusMonths(1);
                affichageMois();
            }
        }
    }

    @FXML
    public void buttonThemeOnMouseClicked() throws IOException {
        if (!CalendrierApplication.stage2.isShowing()) {
            if (Objects.equals(CalendrierController.couleur.get(), "black")) {
                CalendrierController.couleur.set("white");
                this.setColor(Color.BLACK);
            } else {
                CalendrierController.couleur.set("black");
                this.setColor(Color.WHITE);
            }
            anchorPane.setStyle("-fx-background-color:" + CalendrierController.couleur.get() + ";");
            this.list.get(this.idListe).put("color",CalendrierController.couleur.get());
            this.updateDB();
        }
    }

    private void updateDB() throws IOException {
        File file = new File("src/main/resources/com/example/connexion/db.txt");
        FileWriter fileWriter = new FileWriter(file,false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i=0 ; i<list.size();i++){
            bufferedWriter.write("###############\n");
            bufferedWriter.write("name:"+list.get(i).get("name")+"\n");
            bufferedWriter.write("firstName:"+list.get(i).get("firstName")+"\n");
            bufferedWriter.write("color:"+list.get(i).get("color")+"\n");
            bufferedWriter.write("type:"+list.get(i).get("type")+"\n");
            bufferedWriter.write("mailAdresse:"+list.get(i).get("mailAdresse")+"\n");
            bufferedWriter.write("password:"+list.get(i).get("password")+"\n");
        }
        bufferedWriter.close();
        fileWriter.close();
    }
    @FXML
    public void addEdtOnMouseClicked() throws IOException {
        if (!CalendrierApplication.stage2.isShowing()) {

            if (!Objects.equals(nameEdtTextField.getText(), "") && !Objects.equals(urlTextField.getText(), "")) {
                InputStream in = null;
                try {
                    in = new URL(urlTextField.getText()).openStream();
                    Files.copy(in, Paths.get("src/main/resources/com/example/connexion/" + this.list.get(this.idListe).get("mailAdresse") + "/" + mode + "/" + nameEdtTextField.getText() + ".ics"), StandardCopyOption.REPLACE_EXISTING);
                    this.selectionEdtComboBox.getItems().add(nameEdtTextField.getText());
                } catch (IOException e) {
                    System.out.println("Erreur");
                }
            }
        }
    }

    @FXML
    public void deconnexionOnMouseClicked() throws IOException {
        if (!CalendrierApplication.stage2.isShowing()) {
            File file = new File("src/main/resources/com/example/connexion/connected.txt");
            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.close();
            Stage stage = (Stage) deconnexionButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(ConnexionController.class.getResource("pageConnexion.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Page de Connexion");
            stage.setScene(scene);
        }
    }

    @FXML
    public void favorisOnMouseClicked() {
        if (!CalendrierApplication.stage2.isShowing()) {
            if (!Objects.equals(this.mode, "favoris")) {
                setMode("favoris");
                this.selectionEdtComboBox.getItems().clear();
                File dossier = new File("src/main/resources/com/example/connexion/" + this.list.get(this.idListe).get("mailAdresse") + "/" + this.mode + "/");
                for (int i = 0; i < Objects.requireNonNull(dossier.listFiles()).length; i++) {
                    this.selectionEdtComboBox.getItems().add(Objects.requireNonNull(dossier.listFiles())[i].getName().split("\\.")[0]);
                }
            }
        }
    }

    @FXML
    public void formationOnMouseClicked() {
        if (!CalendrierApplication.stage2.isShowing()) {
            if (!Objects.equals(this.mode, "formation")) {
                setMode("formation");
                this.selectionEdtComboBox.getItems().clear();
                File dossier = new File("src/main/resources/com/example/connexion/" + this.list.get(this.idListe).get("mailAdresse") + "/" + this.mode + "/");
                for (int i = 0; i < Objects.requireNonNull(dossier.listFiles()).length; i++) {
                    this.selectionEdtComboBox.getItems().add(Objects.requireNonNull(dossier.listFiles())[i].getName().split("\\.")[0]);
                }
            }
        }
    }

    @FXML
    public void personnelOnMouseClicked() {
        if (!CalendrierApplication.stage2.isShowing()) {
            if (!Objects.equals(this.mode, "personnel")) {
                setMode("personnel");
                this.selectionEdtComboBox.getItems().clear();
                File dossier = new File("src/main/resources/com/example/connexion/" + this.list.get(this.idListe).get("mailAdresse") + "/" + this.mode + "/");
                for (int i = 0; i < Objects.requireNonNull(dossier.listFiles()).length; i++) {
                    this.selectionEdtComboBox.getItems().add(Objects.requireNonNull(dossier.listFiles())[i].getName().split("\\.")[0]);
                }
            }
        }
    }

    @FXML
    public void salleOnMouseClicked() {
        if (!CalendrierApplication.stage2.isShowing()) {
            if (!Objects.equals(this.mode, "salle")) {
                setMode("salle");
                this.selectionEdtComboBox.getItems().clear();
                File dossier = new File("src/main/resources/com/example/connexion/" + this.list.get(this.idListe).get("mailAdresse") + "/" + this.mode + "/");
                for (int i = 0; i < Objects.requireNonNull(dossier.listFiles()).length; i++) {
                    this.selectionEdtComboBox.getItems().add(Objects.requireNonNull(dossier.listFiles())[i].getName().split("\\.")[0]);
                }
            }
        }
    }

    @FXML
    public void aujourdhuiOnMouseClicked() {
        if (!CalendrierApplication.stage2.isShowing()) {
            this.localDateTime = LocalDateTime.now();
            int identifiant = choiceBox.getSelectionModel().getSelectedIndex();
            if (identifiant == 0) {
                affichageJour();
            } else if (identifiant == 1) {
                affichageSemaine();
            } else {
                affichageMois();
            }
        }
    }

    @FXML
    public void filtreOnMouseClicked() {
        if (!CalendrierApplication.stage2.isShowing()) {
            this.filtres.clear();
            if (!Objects.equals(this.matiereTextField.getText(), "")) {
                this.filtres.put("Matière ", this.matiereTextField.getText());
            }
            if (!Objects.equals(this.groupeTextField.getText(), "")) {
                this.filtres.put("TD ", this.groupeTextField.getText());
            }
            if (!Objects.equals(this.salleTextField.getText(), "")) {
                this.filtres.put("Salle ", this.salleTextField.getText());
            }
            if (!Objects.equals(this.typeCoursTextField.getText(), "")) {
                this.filtres.put("Type ", this.typeCoursTextField.getText());
            }
            int identifiant = choiceBox.getSelectionModel().getSelectedIndex();
            if (identifiant == 0) {
                affichageJour();
            } else if (identifiant == 1) {
                affichageSemaine();
            } else {
                affichageMois();
            }
        }
    }
}
