package com.example.propotypage_et_interface_utilisateur;

import com.example.Icalendar.ApiCalendar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CreationEvenementController implements Initializable {
    public Spinner heureDateDebutSpinner;
    public Spinner minDateDebutSpinner;
    public Spinner heureDateFinSpinner;
    public Spinner minDateFinSpinner;
    public ColorPicker colorPicker;
    public TextField lieuTextField;
    public TextArea groupeTextArea;
    public TextArea personnesTextArea;
    public TextField typeTextField;
    public Button enregistreButton;
    public TextArea memoTextArea;
    public Text errorText;
    public Text memoText;
    public Text lieuText;
    public Text couleurText;
    public Text groupeText;
    public Text personnesText;
    public Text debutText;
    public Text finText;
    public Text debutHText;
    public Text finHText;
    public Text finMinText;
    public Text debutMinText;
    public Text typeText;

    @FXML
    AnchorPane anchorPane;

    public void setMode(String mode) {
        this.mode = mode;
    }

    private String mode;

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    public void setCalendar(ApiCalendar calendar) {
        this.calendar = calendar;
    }

    private ApiCalendar calendar;

    private LocalDateTime dateTime;

    private void setColor(Color color){
        typeText.setFill(color);
        debutText.setFill(color);
        debutMinText.setFill(color);
        finText.setFill(color);
        finMinText.setFill(color);
        couleurText.setFill(color);
        debutHText.setFill(color);
        finHText.setFill(color);
        memoText.setFill(color);
        lieuText.setFill(color);
        personnesText.setFill(color);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Integer> hours = FXCollections.observableArrayList(8,9,10,11,12,13,14,15,16,17,18);
        ObservableList<Integer> minute = FXCollections.observableArrayList(0,30);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(hours);
        valueFactory.setValue(8);
        heureDateDebutSpinner.setValueFactory(valueFactory);

        SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(minute);
        valueFactory1.setValue(0);
        minDateDebutSpinner.setValueFactory(valueFactory1);

        SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(minute);
        valueFactory2.setValue(0);
        minDateFinSpinner.setValueFactory(valueFactory2);

        ObservableList<Integer> hoursFin = FXCollections.observableArrayList(9,10,11,12,13,14,15,16,17,18,19);
        SpinnerValueFactory<Integer> valueFactory3 = new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(hoursFin);
        valueFactory3.setValue(9);
        heureDateFinSpinner.setValueFactory(valueFactory3);

        CalendrierController.couleur.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (Objects.equals(CalendrierController.couleur.get(), "black")) {
                    setColor(Color.BLACK);
                } else {
                    setColor(Color.WHITE);
                }
                anchorPane.setStyle("-fx-background-color:" + CalendrierController.couleur.get() + ";");
            }
        });
        if (Objects.equals(CalendrierController.couleur.get(), "black")) {
            setColor(Color.WHITE);
        } else {
            setColor(Color.BLACK);
        }
        anchorPane.setStyle("-fx-background-color:" + CalendrierController.couleur.get() + ";");
    }

    private void initializeDTSTAMP(BufferedWriter bufferedWriter) throws IOException {
        String mois = "" + LocalDateTime.now().getMonth().getValue();
        if (mois.length() < 2) {
            mois = "0" + mois;
        }
        String jour = "" + LocalDateTime.now().getDayOfMonth();
        if (jour.length() < 2) {
            jour = "0" + jour;
        }
        String heure = "" + LocalDateTime.now().getHour();
        if (heure.length() < 2) {
            heure = "0" + heure;
        }
        String minutes = "" + LocalDateTime.now().getMinute();
        if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }
        bufferedWriter.write("DTSTAMP:" +
                LocalDateTime.now().getYear() + mois + jour + "T" +
                heure + minutes + "00Z\n");
    }

    private void initializeOtherDT(BufferedWriter bufferedWriter) throws IOException {
        String mois;
        String jour;
        mois = "" + dateTime.getMonth().getValue();
        if (mois.length() < 2) {
            mois = "0" + mois;
        }
        jour = "" + dateTime.getDayOfMonth();
        if (jour.length() < 2) {
            jour = "0" + jour;
        }
        String heureDebut;
        String heureFin;
        if (this.dateTime.isBefore(LocalDateTime.of(2023,10,11,0,0)) || this.dateTime.isAfter(LocalDateTime.of(2024,03,31,0,0))){
            heureDebut = "" + dateTime.withHour((Integer) heureDateDebutSpinner.getValue()-2).getHour();
            heureFin = "" + dateTime.withHour((Integer) heureDateFinSpinner.getValue()-2).getHour();
        }
        else{
            heureDebut = "" + dateTime.withHour((Integer) heureDateDebutSpinner.getValue()-1).getHour();
            heureFin = "" + dateTime.withHour((Integer) heureDateFinSpinner.getValue()-1).getHour();
        }
        String minDebut = "" + dateTime.withMinute((Integer) minDateDebutSpinner.getValue()).getMinute();
        String minFin = "" + dateTime.withMinute((Integer) minDateFinSpinner.getValue()).getMinute();
        if (heureDebut.length() < 2) {
            heureDebut = "0" + heureDebut;
        }
        if (heureFin.length() < 2) {
            heureFin = "0" + heureFin;
        }
        if (minDebut.length() < 2) {
            minDebut = "0" + minDebut;
        }
        if (minFin.length() < 2) {
            minFin = "0" + minFin;
        }
        bufferedWriter.write("DTSTART;VALUE=DATE:" +
                dateTime.getYear() + mois + jour + "T" +
                heureDebut + minDebut + "00Z\n");
        bufferedWriter.write("DTEND;VALUE=DATE:" +
                dateTime.getYear() + mois + jour + "T" +
                heureFin + minFin + "00Z\n");
    }

    private void writeSummary(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write("SUMMARY;LANGUAGE=fr:" +this.colorPicker.getValue().toString()+ " - "+this.lieuTextField.getText() +
                " - " + this.typeTextField.getText());
        if (!Objects.equals(this.personnesTextArea.getText(), "")){
            bufferedWriter.write( " - "+this.personnesTextArea.getText());
        }
        if (!Objects.equals(this.groupeTextArea.getText(), "")){
            bufferedWriter.write( " - "+this.groupeTextArea.getText());
        }
        if (!Objects.equals(this.memoTextArea.getText(), "")){
            bufferedWriter.write( " - "+this.memoTextArea.getText());
        }
        bufferedWriter.newLine();
    }

    private void writeDescription(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write("DESCRIPTION;LANGUAGE=fr:Couleur : " + this.colorPicker.getValue() + "\\nType :" + this.typeTextField.getText()
                + "\\nLieu :" + this.lieuTextField.getText());
        if (!Objects.equals(this.personnesTextArea.getText(), "")){
            bufferedWriter.write( "\\nPersonnes :" + this.personnesTextArea.getText());
        }
        if (!Objects.equals(this.groupeTextArea.getText(), "")){
            bufferedWriter.write( "\\nGroupe :" + this.groupeTextArea.getText());
        }
        if (!Objects.equals(this.memoTextArea.getText(), "")){
            bufferedWriter.write( "\\nMémo :" + this.memoTextArea.getText());
        }
        bufferedWriter.newLine();
    }
    @FXML
    public void enregistreOnMouseClicked() throws URISyntaxException, IOException {
        if (!dateTime.withHour((Integer) heureDateDebutSpinner.getValue()).withMinute((Integer) minDateDebutSpinner.getValue()).withSecond(0).withNano(0).isAfter(
                dateTime.withHour((Integer) heureDateFinSpinner.getValue()).withMinute((Integer) minDateFinSpinner.getValue()).withSecond(0).withNano(0))) {
            if (!Objects.equals(lieuTextField.getText(), "") &&
                    !Objects.equals(typeTextField.getText(), "")) {
                    Files.copy(Paths.get(this.calendar.getFilepath()), Paths.get("src/main/resources/com/example/Icalendar/copie.ics"), REPLACE_EXISTING);
                    File file = new File(this.calendar.getFilepath());
                    FileWriter fileWriter = new FileWriter(file, false);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/com/example/Icalendar/copie.ics"));
                    String line = bufferedReader.readLine();
                    while (!line.equals("END:VCALENDAR")) {
                        bufferedWriter.write(line);
                        bufferedWriter.newLine();
                        line = bufferedReader.readLine();
                    }
                    bufferedWriter.write("BEGIN:VEVENT\n" +
                            "CATEGORIES:HYPERPLANNING\n");
                    this.initializeDTSTAMP(bufferedWriter);
                    bufferedWriter.write("UID:Enregistrement\n");
                    this.initializeDTSTAMP(bufferedWriter);
                    this.writeSummary(bufferedWriter);
                    bufferedWriter.write("LOCATION;LANGUAGE=fr:" + this.lieuTextField.getText() + "\n");
                    this.writeDescription(bufferedWriter);
                    bufferedWriter.write("X-ALT-DESC;\n");
                    bufferedWriter.write("END:VEVENT\n");
                    bufferedWriter.write("END:VCALENDAR");
                    bufferedWriter.close();
                    bufferedReader.close();
                    new File("src/main/resources/com/example/Icalendar/copie.ics").delete();
                    Stage stage = (Stage) enregistreButton.getScene().getWindow();
                    stage.close();
            }
            else{
                errorText.setText("Il manque des arguments à mettre.");
                if (Objects.equals(lieuTextField.getText(), "")){
                    lieuTextField.setStyle("-fx-border-color: red;");
                }
                if (Objects.equals(typeTextField.getText(), "")){
                    typeTextField.setStyle("-fx-border-color: red;");
                }
            }
        }
        else{
            errorText.setText("L'heure de début est après l'heure de fin.");
            heureDateDebutSpinner.setStyle("-fx-border-color: red;");
            heureDateFinSpinner.setStyle("-fx-border-color: red;");
            minDateDebutSpinner.setStyle("-fx-border-color: red;");
            minDateFinSpinner.setStyle("-fx-border-color: red;");
        }
    }
}
