package com.example.propotypage_et_interface_utilisateur;

import com.example.Icalendar.ApiCalendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CreationController implements Initializable {
    public Spinner heureDateDebutSpinner;
    public Spinner minDateDebutSpinner;
    public Spinner heureDateFinSpinner;
    public Spinner minDateFinSpinner;
    public TextField enseignantTextField;
    public TextField coursTextField;
    public TextArea tdTextArea;
    public TextArea promotionTextArea;
    public TextField typeTextField;
    public Button enregistreButton;
    public TextArea memoTextArea;
    public Text errorText;

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Integer> hours = FXCollections.observableArrayList(8,9,10,11,12,13,14,15,16,17,18);
        ObservableList<Integer> minute = FXCollections.observableArrayList(0,30);


        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(hours);

        // Default value
        valueFactory.setValue(8);

        heureDateDebutSpinner.setValueFactory(valueFactory);

        SpinnerValueFactory<Integer> valueFactory1 = //
                new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(minute);

        // Default value
        valueFactory1.setValue(0);

        minDateDebutSpinner.setValueFactory(valueFactory1);

        SpinnerValueFactory<Integer> valueFactory2 = //
                new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(minute);

        // Default value
        valueFactory2.setValue(0);

        minDateFinSpinner.setValueFactory(valueFactory2);

        ObservableList<Integer> hoursFin = FXCollections.observableArrayList(9,10,11,12,13,14,15,16,17,18,19);
        SpinnerValueFactory<Integer> valueFactory3 = //
                new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(hoursFin);

        // Default value
        valueFactory3.setValue(9);

        heureDateFinSpinner.setValueFactory(valueFactory3);

    }

    @FXML
    public void enregistreOnMouseClicked() throws URISyntaxException, IOException {
        if (!dateTime.withHour((Integer) heureDateDebutSpinner.getValue()).withMinute((Integer) minDateDebutSpinner.getValue()).withSecond(0).withNano(0).isAfter(
                dateTime.withHour((Integer) heureDateFinSpinner.getValue()).withMinute((Integer) minDateFinSpinner.getValue()).withSecond(0).withNano(0))) {
            if (!Objects.equals(coursTextField.getText(), "") &&
                    !Objects.equals(enseignantTextField.getText(), "") &&
                    !Objects.equals(typeTextField.getText(), "")) {
                if (calendar.getIfPossibleToAddEvent(dateTime.withHour((Integer) heureDateDebutSpinner.getValue()).withMinute((Integer) minDateDebutSpinner.getValue()).withSecond(0).withNano(0),
                        dateTime.withHour((Integer) heureDateFinSpinner.getValue()).withMinute((Integer) minDateFinSpinner.getValue()).withSecond(0).withNano(0))) {
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
                    bufferedWriter.write("UID:Enregistrement\n");
                    mois = "" + dateTime.getMonth().getValue();
                    if (mois.length() < 2) {
                        mois = "0" + mois;
                    }
                    jour = "" + dateTime.getDayOfMonth();
                    if (jour.length() < 2) {
                        jour = "0" + jour;
                    }
                    String heureDebut = "" + dateTime.withHour((Integer) heureDateDebutSpinner.getValue() - 1).getHour();
                    String heureFin = "" + dateTime.withHour((Integer) heureDateFinSpinner.getValue() - 1).getHour();
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
                    bufferedWriter.write("SUMMARY;LANGUAGE=fr:" + this.coursTextField.getText() + " - " + this.enseignantTextField.getText() +
                            " - " + this.typeTextField.getText());
                    if (!Objects.equals(this.tdTextArea.getText(), "")){
                        bufferedWriter.write( " - "+this.tdTextArea.getText());
                    }
                    if (!Objects.equals(this.promotionTextArea.getText(), "")){
                        bufferedWriter.write( " - "+this.promotionTextArea.getText());
                    }
                    if (!Objects.equals(this.memoTextArea.getText(), "")){
                        bufferedWriter.write( " - "+this.memoTextArea.getText());
                    }
                    bufferedWriter.newLine();
                    bufferedWriter.write("LOCATION;LANGUAGE=fr:" + this.calendar.getSalle() + "\n");
                    bufferedWriter.write("DESCRIPTION;LANGUAGE=fr:Matière : " + this.coursTextField.getText() + "\\nEnseignant :" + this.enseignantTextField.getText()+"\\nType :" + this.typeTextField.getText()
                            + "\\nSalle :" + this.calendar.getSalle());
                    if (!Objects.equals(this.tdTextArea.getText(), "")){
                        bufferedWriter.write( "\\nTD :" + this.tdTextArea.getText());
                    }
                    if (!Objects.equals(this.promotionTextArea.getText(), "")){
                        bufferedWriter.write( "\\nPromotions :" + this.promotionTextArea.getText());
                    }
                    if (!Objects.equals(this.memoTextArea.getText(), "")){
                        bufferedWriter.write( "\\nMémo :" + this.memoTextArea.getText());
                    }
                    bufferedWriter.newLine();
                    bufferedWriter.write("X-ALT-DESC;\n");
                    bufferedWriter.write("END:VEVENT\n");
                    bufferedWriter.write("END:VCALENDAR");
                    bufferedWriter.close();
                    bufferedReader.close();
                    new File("src/main/resources/com/example/Icalendar/copie.ics").delete();

                    Stage stage = (Stage) enregistreButton.getScene().getWindow();
                    stage.close();

                }
                else {
                    errorText.setText("La salle est pris sur ce laps de temps.");
                }
            }
            else{
                errorText.setText("Il manque des arguments à mettre.");
                if (Objects.equals(coursTextField.getText(), "")){
                    coursTextField.setStyle("-fx-border-color: red;");
                }
                if (Objects.equals(enseignantTextField.getText(), "")){
                    enseignantTextField.setStyle("-fx-border-color: red;");
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
