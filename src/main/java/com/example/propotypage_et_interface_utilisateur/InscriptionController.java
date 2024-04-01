package com.example.propotypage_et_interface_utilisateur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class InscriptionController implements Initializable {


    public Text inscriptionText;
    public PasswordField password;
    public Text passwordText;
    public Text mailAdresseText;
    public TextField mailAdresse;
    public Text nameText;
    public Text firstNameText;

    public TextField name;
    public TextField firstName;
    public Button inscriptionButton;
    public Text errorText;
    public CheckBox ensignantCheckbox;
    public CheckBox eleveCheckbox;

    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();

    @FXML
    public void inscriptionOnMouseClicked() throws IOException {
        this.name.setStyle("");
        this.firstName.setStyle("");
        this.password.setStyle("");
        this.mailAdresse.setStyle("");
        this.eleveCheckbox.setStyle("");
        this.ensignantCheckbox.setStyle("");
        if (Objects.equals(this.name.getText(), "")){
            this.name.setStyle("-fx-border-color: red;");
        }
        if (Objects.equals(this.firstName.getText(), "")){
            this.firstName.setStyle("-fx-border-color: red;");
        }
        if (Objects.equals(this.password.getText(), "")){
            this.password.setStyle("-fx-border-color: red;");
        }
        if (Objects.equals(this.mailAdresse.getText(), "")){
            this.mailAdresse.setStyle("-fx-border-color: red;");
        }
        if (!this.eleveCheckbox.isSelected() && !this.ensignantCheckbox.isSelected()){
            this.eleveCheckbox.setStyle("-fx-border-color: red;");
            this.ensignantCheckbox.setStyle("-fx-border-color: red;");
        }
        if (Objects.equals(this.name.getText(), "") || Objects.equals(this.firstName.getText(), "")||
                Objects.equals(this.password.getText(), "") || Objects.equals(this.mailAdresse.getText(), "") ||
                (!this.eleveCheckbox.isSelected() && !this.ensignantCheckbox.isSelected())){
            this.errorText.setText("Il manque des informations à renseigné");
        }
        else{
            for (int i=0 ; i< list.size();i++){
                if (Objects.equals(list.get(i).get("mailAdresse"), this.mailAdresse.getText())){
                    this.mailAdresse.setStyle("-fx-border-color: red;");
                    this.errorText.setText("Renseigné une autre adresse mail");
                    return;
                }
            }
            BufferedWriter bufferedWriter = getBufferedWriter();
            bufferedWriter.close();
            Stage stage = (Stage) inscriptionButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(ConnexionController.class.getResource("pageConnexion.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Page de Connexion");
            stage.setScene(scene);
        }
    }

    private BufferedWriter getBufferedWriter() throws IOException {
        File file = new File("src/main/resources/com/example/connexion/db.txt");
        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("###############");
        bufferedWriter.newLine();
        bufferedWriter.write("name:"+this.name.getText());
        bufferedWriter.newLine();
        bufferedWriter.write("firstName:"+this.firstName.getText());
        bufferedWriter.newLine();
        bufferedWriter.write("color:white");
        bufferedWriter.newLine();
        if (ensignantCheckbox.isSelected())
            bufferedWriter.write("type:enseignant");
        else
            bufferedWriter.write("type:eleve");
        bufferedWriter.newLine();
        bufferedWriter.write("mailAdresse:"+this.mailAdresse.getText());
        bufferedWriter.newLine();
        bufferedWriter.write("password:"+this.password.getText());
        bufferedWriter.newLine();
        File file1 = new File("src/main/resources/com/example/connexion/"+this.mailAdresse.getText());
        file1.mkdir();
        file1 = new File("src/main/resources/com/example/connexion/"+this.mailAdresse.getText()+"/favoris");
        file1.mkdir();
        file1 = new File("src/main/resources/com/example/connexion/"+this.mailAdresse.getText()+"/salle");
        file1.mkdir();
        file1 = new File("src/main/resources/com/example/connexion/"+this.mailAdresse.getText()+"/formation");
        file1.mkdir();
        file1 = new File("src/main/resources/com/example/connexion/"+this.mailAdresse.getText()+"/personnel");
        file1.mkdir();
        return bufferedWriter;
    }

    @FXML
    public void eleveOnMouseClicked() {
        ensignantCheckbox.setDisable(eleveCheckbox.isSelected());
    }

    @FXML
    public void enseignantOnMouseClicked() {
        eleveCheckbox.setDisable(ensignantCheckbox.isSelected());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/main/resources/com/example/connexion/db.txt");
        try {
            if (!file.exists())
                file.createNewFile();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/com/example/connexion/db.txt"));
            while (bufferedReader.readLine()!=null){
                HashMap<String,String> test = new HashMap<String,String>();
                String line = bufferedReader.readLine();
                while (!Objects.equals(line.split(":")[0], "password")){
                    test.put(line.split(":")[0],line.split(":")[1]);
                    line = bufferedReader.readLine();
                }
                test.put(line.split(":")[0],line.split(":")[1]);
                list.add(test);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
