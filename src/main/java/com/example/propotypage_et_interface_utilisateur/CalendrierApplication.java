package com.example.propotypage_et_interface_utilisateur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class CalendrierApplication extends Application {
    static Stage stage2;
    static Stage stage;

    @Override
    public void start(Stage stage3) throws IOException {
        CalendrierApplication.stage2 = new Stage();
        CalendrierApplication.stage = new Stage();
        int creationFile=creationFichier();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/com/example/connexion/connected.txt"));
        String id = bufferedReader.readLine();
        if (creationFile==1 || Objects.equals(id, null)) {
            FXMLLoader fxmlLoader = new FXMLLoader(ConnexionController.class.getResource("ConnexionPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Page de Connexion");
            stage.setScene(scene);
            stage.show();
        }
        else{
            acceAuCalendrier(id);
        }
    }

    private int creationFichier(){
        File file = new File("src/main/resources/com/example/connexion/db.txt");
        File file2 = new File("src/main/resources/com/example/connexion/connected.txt");
        File dossier = new File("src/main/resources/com/example/connexion");
        int creationFile=0;
        try {
            dossier.mkdir();
            if (!file.exists()) {
                file.createNewFile();
                if (file2.exists()) {
                    file2.delete();
                }
                creationFile=1;
            }
            if (!file2.exists()) {
                file2.createNewFile();
                creationFile=1;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return creationFile;
    }
    private void acceAuCalendrier(String id) throws IOException {
        ArrayList<HashMap<String,String>> list = getDB("src/main/resources/com/example/connexion/db.txt");
        FXMLLoader fxmlLoader = new FXMLLoader(CalendrierController.class.getResource("Calendrier.fxml"));
        ScrollPane anchorPane = fxmlLoader.load();
        CalendrierController controller = fxmlLoader.getController();
        CalendrierController.setCouleur(list.get(Integer.parseInt(id)).get("color"));
        controller.setMode("favoris");
        controller.couleur();
        controller.setModeConnexion(list.get(Integer.parseInt(id)).get("type"));
        controller.setList(list);
        controller.setIdListe(Integer.parseInt(id));
        controller.creationListEdt();
        stage.setTitle("Calendrier");
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setWidth(1075);
        stage.setHeight(727);
        stage.setMinWidth(530);
        stage.setMinHeight(720);
        stage.setResizable(true);
        stage.show();
    }

    private ArrayList<HashMap<String,String>> getDB(String pathfile){
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        File file = new File(pathfile);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathfile));
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
        return list;
    }

    public static void main(String[] args) {
        launch();
    }
}