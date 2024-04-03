package com.example.propotypage_et_interface_utilisateur;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class ConnexionController implements Initializable {
    @FXML
    Text connexionText;

    @FXML
    Text mailAdresseText;

    @FXML
    Text passwordText;

    @FXML
    TextField mailAdresse;

    @FXML
    Button connexionButton;

    @FXML
    Text inscriptionText;

    @FXML
    Text errorText;

    @FXML
    PasswordField password;

    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();

    EventHandler<KeyEvent> touches = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode()== KeyCode.ENTER){
                try {
                    connexionOnMouseClicked();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (event.getCode()== KeyCode.I){
                try {
                    inscriptionOnMouseClicked();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };

    @FXML
    public void inscriptionOnMouseClicked() throws IOException {
        CalendrierApplication.stage.removeEventFilter(KeyEvent.KEY_PRESSED,touches);
        Stage stage = (Stage) connexionButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(InscriptionController.class.getResource("InscriptionPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inscription");
        stage.setHeight(500);
        stage.setWidth(600);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    @FXML
    public void connexionOnMouseClicked() throws IOException {
        errorText.setText("");
        for (int i=0 ; i<list.size();i++){
            if (Objects.equals(list.get(i).get("mailAdresse"), mailAdresse.getText()) &&
                    Objects.equals(list.get(i).get("password"), password.getText())){
                    File file = new File("src/main/resources/com/example/connexion/connected.txt");
                    FileWriter fileWriter = new FileWriter(file,false);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(Integer.toString(i));
                    bufferedWriter.close();
                    Stage stage = (Stage) connexionButton.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(CalendrierController.class.getResource("Calendrier.fxml"));
                    ScrollPane scrollPane = fxmlLoader.load();

                    CalendrierApplication.stage.removeEventFilter(KeyEvent.KEY_PRESSED,touches);
                    CalendrierController controller = fxmlLoader.getController();
                    CalendrierController.setCouleur(list.get(i).get("color"));
                    controller.setMode("favoris");
                    controller.couleur();
                    controller.setModeConnexion(list.get(i).get("type"));
                    controller.setList(list);
                    controller.setIdListe(i);
                    controller.creationListEdt();

                stage.setWidth(1075);
                stage.setHeight(727);
                stage.setMinWidth(530);
                stage.setMinHeight(720);
                stage.setResizable(true);
                    stage.setTitle("Calendrier");
                    Scene scene = new Scene(scrollPane);
                    stage.setScene(scene);
                return;
            }
        }
        errorText.setText("Le mot de passe ou l'adresse mail est mal rensigné ou n'existe pas");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        CalendrierApplication.stage.addEventFilter(KeyEvent.KEY_PRESSED,touches);
    }
}
