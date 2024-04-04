package com.example.propotypage_et_interface_utilisateur;

import com.example.Icalendar.ApiCalendar;
import com.example.Icalendar.Event;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.awt.Desktop.getDesktop;

public class CasDeLaSemaineController {

    public AnchorPane anchorPane;
    public AnchorPane anchorPaneLundi;
    public AnchorPane anchorPaneMardi;
    public AnchorPane anchorPaneMercredi;
    public AnchorPane anchorPaneJeudi;
    public AnchorPane anchorPaneVendredi;
    @FXML
    Line line9;

    @FXML
    Line line10;

    @FXML
    Line line11;

    @FXML
    Line line12;

    @FXML
    Line line13;

    @FXML
    Line line14;

    @FXML
    Line line15;

    @FXML
    Line line16;

    @FXML
    Line line17;

    @FXML
    Line line18;

    @FXML
    Line line19;

    @FXML
    VBox vBoxLundi;

    @FXML
    VBox vBoxMardi;

    @FXML
    VBox vBoxMercredi;

    @FXML
    VBox vBoxJeudi;

    @FXML
    VBox vBoxVendredi;

    @FXML
    Pane paneHeure;


    @FXML
    GridPane gridPane;
    @FXML
    Text heure8;

    @FXML
    Text heure9;

    @FXML
    Text heure10;

    @FXML
    Text heure11;

    @FXML
    Text heure12;

    @FXML
    Text heure13;

    @FXML
    Text heure14;

    @FXML
    Text heure15;

    @FXML
    Text heure16;

    @FXML
    Text heure17;

    @FXML
    Text heure18;

    @FXML
    Text heure19;

    @FXML
    Pane paneLundi;

    @FXML
    Pane paneMardi;

    @FXML
    Pane paneMercredi;

    @FXML
    Pane paneJeudi;

    @FXML
    Pane paneVendredi;

    @FXML
    Text lundi;

    @FXML
    Text mardi;

    @FXML
    Text mercredi;

    @FXML
    Text jeudi;

    @FXML
    Text vendredi;
    LocalDateTime[] localDateTimesList={null,null,null,null,null};

    ArrayList<ArrayList<String>> listProffesseur = new ArrayList<ArrayList<String>>();

    ArrayList<ArrayList<String>> listToolTip = new ArrayList<>();

    public void setJourLundi(LocalDateTime localDateTime) {
        this.jourLundi.setText(String.valueOf(localDateTime.getDayOfMonth()));
        localDateTimesList[0] = localDateTime;
    }

    public void setJourMardi(LocalDateTime localDateTime) {
        this.jourMardi.setText(String.valueOf(localDateTime.getDayOfMonth()));
        localDateTimesList[1] = localDateTime;
    }

    public void setJourMercredi(LocalDateTime localDateTime) {
        this.jourMercredi.setText(String.valueOf(localDateTime.getDayOfMonth()));
        localDateTimesList[2] = localDateTime;
    }

    public void setJourJeudi(LocalDateTime localDateTime) {
        this.jourJeudi.setText(String.valueOf(localDateTime.getDayOfMonth()));
        localDateTimesList[3] = localDateTime;
    }

    public void setJourVendredi(LocalDateTime localDateTime) {
        this.jourVendredi.setText(String.valueOf(localDateTime.getDayOfMonth()));
        localDateTimesList[4] = localDateTime;
    }

    @FXML
    Text jourLundi;

    @FXML
    Text jourMardi;

    @FXML
    Text jourMercredi;

    @FXML
    Text jourJeudi;

    @FXML
    Text jourVendredi;
    public void setArrayList(ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> arrayList) {
        this.arrayList = arrayList;
    }

    private void setColorText(Color couleur){
        heure8.setFill(couleur);
        heure9.setFill(couleur);
        heure10.setFill(couleur);
        heure11.setFill(couleur);
        heure12.setFill(couleur);
        heure13.setFill(couleur);
        heure14.setFill(couleur);
        heure15.setFill(couleur);
        heure16.setFill(couleur);
        heure17.setFill(couleur);
        heure18.setFill(couleur);
        heure19.setFill(couleur);
        line9.setStroke(couleur);
        line10.setStroke(couleur);
        line11.setStroke(couleur);
        line12.setStroke(couleur);
        line13.setStroke(couleur);
        line14.setStroke(couleur);
        line15.setStroke(couleur);
        line16.setStroke(couleur);
        line17.setStroke(couleur);
        line18.setStroke(couleur);
        line19.setStroke(couleur);
        lundi.setFill(couleur);
        mardi.setFill(couleur);
        mercredi.setFill(couleur);
        jeudi.setFill(couleur);
        vendredi.setFill(couleur);
        jourLundi.setFill(couleur);
        jourMardi.setFill(couleur);
        jourMercredi.setFill(couleur);
        jourJeudi.setFill(couleur);
        jourVendredi.setFill(couleur);
    }

    private void setColorPaneVBox(String couleur){
        paneHeure.setStyle("-fx-border-color:"+couleur+";");
        vBoxLundi.setStyle("-fx-border-color:"+couleur+";");
        vBoxMardi.setStyle("-fx-border-color:"+couleur+";");
        vBoxMercredi.setStyle("-fx-border-color:"+couleur+";");
        vBoxJeudi.setStyle("-fx-border-color:"+couleur+";");
        vBoxVendredi.setStyle("-fx-border-color:"+couleur+";");
        paneLundi.setStyle("-fx-border-color:"+couleur+";");
        paneMardi.setStyle("-fx-border-color:"+couleur+";");
        paneMercredi.setStyle("-fx-border-color:"+couleur+";");
        paneJeudi.setStyle("-fx-border-color:"+couleur+";");
        paneVendredi.setStyle("-fx-border-color:"+couleur+";");
        anchorPaneLundi.setStyle("-fx-border-color:"+couleur+";");
        anchorPaneMardi.setStyle("-fx-border-color:"+couleur+";");
        anchorPaneMercredi.setStyle("-fx-border-color:"+couleur+";");
        anchorPaneJeudi.setStyle("-fx-border-color:"+couleur+";");
        anchorPaneVendredi.setStyle("-fx-border-color:"+couleur+";");
    }
    private void setColor(String color){
        gridPane.setStyle("-fx-border-color:" + color + ";");
        Color couleur;
        if (Objects.equals(color, "black")){
            couleur = Color.BLACK;
        }
        else {
            couleur = Color.WHITE;
        }
        setColorText(couleur);
        setColorPaneVBox(color);
    }
    ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> arrayList;
    public void affichage(String mode,String userPriviledge, ApiCalendar apiCalendar){
        URL test = CaseDeLaSemaineController.class.getResource("CaseDeLaSemaine.fxml");
        StringProperty essai = CalendrierController.couleur;
        essai.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                setColor(s);
            }
        });
        try {
            VBox[] vBox = {vBoxLundi,vBoxMardi,vBoxMercredi,vBoxJeudi,vBoxVendredi};
            for (int pointeur = 0 ; pointeur<arrayList.size(); pointeur++) {
                listProffesseur.add(new ArrayList<String>());
                listToolTip.add(new ArrayList<String>());
                ArrayList<ArrayList<ArrayList<Event>>> list = arrayList.get(pointeur);
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != null) {
                        HBox hBox = new HBox();
                        for (int j = 0; j < list.get(i).size(); j++) {
                            VBox vBox1 = new VBox();
                            for (int q = 0; q < list.get(i).get(j).size(); q++) {
                                if (list.get(i).get(j).get(q) == null) {
                                    this.setCaseVide(test,list.get(i).size(),vBox1);
                                } else {
                                    this.setCase(test,vBox1,list,i,j ,q);
                                }
                            }
                            hBox.getChildren().add(vBox1);
                        }
                        vBox[pointeur].getChildren().add(hBox);
                    } else {
                        this.setCaseVide(test,1,vBox[pointeur]);
                    }
                }
            }
            setModeFormation(mode,vBox);
            setModeSalle(mode,userPriviledge,apiCalendar,vBox);
            setModePersonnel(mode,apiCalendar,vBox);
            if (Objects.equals(essai.get(), "white"))
                setColor("black");
            else
                setColor("white");

            setListenerOnWidthStage();
            setListenerOnHeigthStage();
            int suppression=240;
            if (CalendrierApplication.stage.getWidth()<791){
                suppression=10;
                if (CalendrierApplication.stage.getWidth()<791) {
                    anchorPane.setLayoutY(anchorPane.getLayoutY() + 300);
                }
            }
            this.setWidth(CalendrierApplication.stage.getWidth(),suppression);
            if (!Objects.equals(Double.toString(CalendrierApplication.stage.getHeight()), "NaN")) {
                this.setHeigth(CalendrierApplication.stage.getHeight(),727);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setModeSalle(String mode,String userPriviledge,ApiCalendar apiCalendar,VBox[] vBox) {
        if (Objects.equals(mode, "salle") && Objects.equals(userPriviledge, "enseignant")) {
            for (int i=0 ; i<5;i++) {
                int finalI = i;
                vBox[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        FXMLLoader fxmlLoader = new FXMLLoader(CalendrierController.class.getResource("ReservationDeSalle.fxml"));
                        Scene scene2 = null;
                        try {
                            scene2 = new Scene(fxmlLoader.load());
                            ReservationDeSalleController controller = fxmlLoader.getController();
                            controller.setDateTime(localDateTimesList[finalI]);
                            controller.setMode(mode);
                            controller.setCalendar(apiCalendar);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        CalendrierApplication.stage2.setResizable(false);
                        CalendrierApplication.stage2.setTitle("Réservation de Salle");
                        CalendrierApplication.stage2.setScene(scene2);
                        CalendrierApplication.stage2.show();
                    }
                });
            }
        }
    }
    private void setModePersonnel(String mode,ApiCalendar apiCalendar,VBox[] vBox){
        if (Objects.equals(mode, "favoris")) {
            for (int i=0 ; i<5;i++) {
                int finalI = i;
                vBox[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        FXMLLoader fxmlLoader = new FXMLLoader(CreationEvenementController.class.getResource("CreationDEvenement.fxml"));
                        Scene scene2 = null;
                        try {
                            scene2 = new Scene(fxmlLoader.load());
                            CreationEvenementController controller = fxmlLoader.getController();
                            controller.setDateTime(localDateTimesList[finalI]);
                            controller.setMode(mode);
                            controller.setCalendar(apiCalendar);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        CalendrierApplication.stage2.setResizable(false);
                        CalendrierApplication.stage2.setTitle("Réservation d'Evenement");
                        CalendrierApplication.stage2.setScene(scene2);
                        CalendrierApplication.stage2.show();
                    }
                });
            }
        }
    }
    private void setModeFormation(String mode,VBox[] vBox){
        if(Objects.equals(mode, "formation")){
            for (int j=0 ; j<5;j++) {
                int id=0;
                for (int i = 0; i < vBox[j].getChildren().size(); i++) {
                    if (vBox[j].getChildren().get(i).getOpacity() != 0.0) {
                        if (listProffesseur.get(j).get(id)!=null) {
                            int finalId = id;
                            int finalJ = j;
                            vBox[j].getChildren().get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    String[] listNomProffeseur = listProffesseur.get(finalJ).get(finalId).split(Pattern.quote("\\,"));
                                    String nom = listNomProffeseur[0].split(" ")[1];
                                    String firstname = listNomProffeseur[0].split(" ")[2];
                                    String mail = "mailto:" + firstname.toLowerCase() + "." + nom.toLowerCase() + "@univ-avignon.fr";
                                    for (int i = 1; i < listNomProffeseur.length; i++) {
                                        nom = listNomProffeseur[i].split(" ")[1];
                                        firstname = listNomProffeseur[i].split(" ")[2];
                                        mail = mail + "," + firstname.toLowerCase() + "." + nom.toLowerCase() + "@univ-avignon.fr";
                                    }
                                    Desktop desktop = getDesktop();
                                    try {
                                        desktop.mail(new URI(mail));
                                    } catch (IOException | URISyntaxException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                        }
                        id=id+1;
                    }
                }
            }
        }
    }
    private void setCaseVide(URL test,int size,VBox vBox) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(test);
        CaseDeLaSemaineController casePourLeJourController ;
        AnchorPane anchorPane = fxmlLoader.load();
        casePourLeJourController = fxmlLoader.getController();
        casePourLeJourController.setHeigth(3.065);
        casePourLeJourController.setNombreDeCase(size);
        casePourLeJourController.setOpacity(0.0);
        vBox.getChildren().add(anchorPane);
    }

    private void setCase(URL test,VBox vBox,ArrayList<ArrayList<ArrayList<Event>>> list,int i,int j , int q) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(test);
        AnchorPane anchorPane = fxmlLoader.load();
        CaseDeLaSemaineController casePourLeJourController ;
        casePourLeJourController = fxmlLoader.getController();
        casePourLeJourController.setNombreDeCase(list.get(i).size());
        casePourLeJourController.setHeigth((double) 90 / ChronoUnit.MINUTES.between(list.get(i).get(j).get(q).getDateEvent().getStartDate(), list.get(i).get(j).get(q).getDateEvent().getEndDate()));
        Event event = list.get(i).get(j).get(q);
        if (event.getDescriptionEvent().getDescription("Enseignant ")!=null)
            this.listProffesseur.get(listProffesseur.size()-1).add(event.getDescriptionEvent().getDescription("Enseignant "));
        else if (event.getDescriptionEvent().getDescription("Enseignants ")!=null)
            this.listProffesseur.get(listProffesseur.size()-1).add(event.getDescriptionEvent().getDescription("Enseignants "));
        else
            this.listProffesseur.get(listProffesseur.size()-1).add(null);
        casePourLeJourController.setInformation(event.getDescriptionEvent().getListDescription(),Integer.toString(event.getDateEvent().getStartDate().getHour()) + "h" + Integer.toString(event.getDateEvent().getStartDate().getMinute()) + "-" +
                Integer.toString(event.getDateEvent().getEndDate().getHour()) + "h" + Integer.toString(event.getDateEvent().getEndDate().getMinute())
                + "\\" );
        vBox.getChildren().add(anchorPane);
    }

     private void setListenerOnHeigthStage(){
        CalendrierApplication.stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (!Objects.equals(oldValue.toString(), "NaN")) {
                    setHeigth(newValue.doubleValue(),oldValue.doubleValue());
                }
            }
        });
    }

    private void setHeigth(double newValue,double oldValue){
        paneHeure.setPrefHeight(newValue - 116);
        anchorPane.setPrefHeight(newValue - 116-42);


        heure8.setLayoutY(heure8.getLayoutY()*(newValue-116)/(oldValue-116));
        gridPane.setPrefHeight((heure8.getLayoutY()*(newValue-116)/(oldValue-116))-5);
        paneLundi.setLayoutY((heure8.getLayoutY() * (newValue - 116) / (oldValue - 116)) - 5);
        paneLundi.setPrefHeight(newValue - 116 - 102.5+57-paneLundi.getLayoutY());

        vBoxLundi.setPrefHeight(newValue  - 116 - 102.5);

        paneMardi.setLayoutY((heure8.getLayoutY() * (newValue - 116) / (oldValue - 116)) - 5);
        paneMardi.setPrefHeight(newValue  - 116 - 100+57-paneMardi.getLayoutY());
        vBoxMardi.setPrefHeight(newValue  - 116 - 100+57-vBoxMardi.getLayoutY());

        paneMercredi.setLayoutY((heure8.getLayoutY() * (newValue - 116) / (oldValue - 116)) - 5);
        paneMercredi.setPrefHeight(newValue  - 116 - 100+57-paneMercredi.getLayoutY());
        vBoxMercredi.setPrefHeight(newValue  - 116 - 100+57-vBoxMercredi.getLayoutY());

        paneJeudi.setLayoutY((heure8.getLayoutY() * (newValue - 116) / (oldValue - 116)) - 5);
        paneJeudi.setPrefHeight(newValue  - 116 - 100+57-paneJeudi.getLayoutY());
        vBoxJeudi.setPrefHeight(newValue - 116 - 100+57-vBoxJeudi.getLayoutY());

        paneVendredi.setLayoutY((heure8.getLayoutY() * (newValue - 116) / (oldValue - 116)) - 5);
        paneVendredi.setPrefHeight(newValue  - 116 - 100+57-paneVendredi.getLayoutY());
        vBoxVendredi.setPrefHeight(newValue  - 116 - 100+57-vBoxVendredi.getLayoutY());
        heure9.setLayoutY(heure9.getLayoutY()*(newValue-116)/(oldValue-116));
        heure10.setLayoutY(heure10.getLayoutY()*(newValue-116)/(oldValue-116));
        heure11.setLayoutY(heure11.getLayoutY()*(newValue-116)/(oldValue-116));
        heure12.setLayoutY(heure12.getLayoutY()*(newValue-116)/(oldValue-116));
        heure13.setLayoutY(heure13.getLayoutY()*(newValue-116)/(oldValue-116));
        heure14.setLayoutY(heure14.getLayoutY()*(newValue-116)/(oldValue-116));
        heure15.setLayoutY(heure15.getLayoutY()*(newValue-116)/(oldValue-116));
        heure16.setLayoutY(heure16.getLayoutY()*(newValue-116)/(oldValue-116));
        heure17.setLayoutY(heure17.getLayoutY()*(newValue-116)/(oldValue-116));
        heure18.setLayoutY(heure18.getLayoutY()*(newValue-116)/(oldValue-116));
        heure19.setLayoutY(heure19.getLayoutY()*(newValue-116)/(oldValue-116));
        line9.setLayoutY(line9.getLayoutY()*(newValue-116)/(oldValue-116));
        line10.setLayoutY(line10.getLayoutY()*(newValue-116)/(oldValue-116));
        line11.setLayoutY(line11.getLayoutY()*(newValue-116)/(oldValue-116));
        line12.setLayoutY(line12.getLayoutY()*(newValue-116)/(oldValue-116));
        line13.setLayoutY(line13.getLayoutY()*(newValue-116)/(oldValue-116));
        line14.setLayoutY(line14.getLayoutY()*(newValue-116)/(oldValue-116));
        line15.setLayoutY(line15.getLayoutY()*(newValue-116)/(oldValue-116));
        line16.setLayoutY(line16.getLayoutY()*(newValue-116)/(oldValue-116));
        line17.setLayoutY(line17.getLayoutY()*(newValue-116)/(oldValue-116));
        line18.setLayoutY(line18.getLayoutY()*(newValue-116)/(oldValue-116));
        line19.setLayoutY(line19.getLayoutY()*(newValue-116)/(oldValue-116));
    }

    private void setListenerOnWidthStage(){
        CalendrierApplication.stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int suppression=240;
                if (newValue.doubleValue()<791){
                    suppression = 10;
                    if (oldValue.doubleValue()>=791 && newValue.doubleValue()<791) {
                        anchorPane.setLayoutY(anchorPane.getLayoutY() + 300);
                    }
                }
                if (oldValue.doubleValue()<=791 && newValue.doubleValue()>791){
                    anchorPane.setLayoutY(anchorPane.getLayoutY() - 300);
                }
                setWidth(newValue.doubleValue(),suppression);
            }
        });
    }

    private void setWidth(double valeurWidth,int suppression){
        anchorPane.setPrefWidth(valeurWidth-suppression-57-20);
        gridPane.setPrefWidth(valeurWidth-suppression-57-20);

        paneLundi.setPrefWidth(gridPane.getPrefWidth()/5);
        vBoxLundi.setPrefWidth(gridPane.getPrefWidth()/5);

        paneMardi.setLayoutX((gridPane.getPrefWidth()/5)+57);
        paneMardi.setPrefWidth(gridPane.getPrefWidth()/5);
        vBoxMardi.setPrefWidth(gridPane.getPrefWidth()/5);

        paneMercredi.setLayoutX((gridPane.getPrefWidth()/5)*2+57);
        paneMercredi.setPrefWidth(gridPane.getPrefWidth()/5);
        vBoxMercredi.setPrefWidth(gridPane.getPrefWidth()/5);

        paneJeudi.setLayoutX((gridPane.getPrefWidth()/5)*3+57);
        paneJeudi.setPrefWidth(gridPane.getPrefWidth()/5);
        vBoxJeudi.setPrefWidth(gridPane.getPrefWidth()/5);

        paneVendredi.setLayoutX((gridPane.getPrefWidth()/5)*4+57);
        paneVendredi.setPrefWidth(gridPane.getPrefWidth()/5);
        vBoxVendredi.setPrefWidth(gridPane.getPrefWidth()/5);

        anchorPaneLundi.setPrefWidth(gridPane.getPrefWidth()/15);
        lundi.setX((anchorPaneLundi.getPrefWidth()/7.5)-8);
        jourLundi.setX((anchorPaneLundi.getPrefWidth()/7.5));

        anchorPaneMardi.setPrefWidth(gridPane.getPrefWidth()/15);
        mardi.setX((anchorPaneMardi.getPrefWidth()/7.5)-11);
        jourMardi.setX((anchorPaneMardi.getPrefWidth()/7.5));

        anchorPaneMercredi.setPrefWidth(gridPane.getPrefWidth()/100);
        mercredi.setX((anchorPaneMercredi.getPrefWidth()/7.5)-7);
        jourMercredi.setX((anchorPaneMercredi.getPrefWidth()/7.5));

        anchorPaneJeudi.setPrefWidth(gridPane.getPrefWidth()/15);
        jeudi.setX((anchorPaneJeudi.getPrefWidth()/7.5)-9);
        jourJeudi.setX((anchorPaneJeudi.getPrefWidth()/7.5));

        anchorPaneVendredi.setPrefWidth(gridPane.getPrefWidth()/100);
        vendredi.setX((anchorPaneVendredi.getPrefWidth()/7.5)-10);
        jourVendredi.setX((anchorPaneVendredi.getPrefWidth()/7.5));

        line9.setEndX(valeurWidth-suppression-57-37);
        line10.setEndX(valeurWidth-suppression-57-37);
        line11.setEndX(valeurWidth-suppression-57-37);
        line12.setEndX(valeurWidth-suppression-57-37);
        line13.setEndX(valeurWidth-suppression-57-37);
        line14.setEndX(valeurWidth-suppression-57-37);
        line15.setEndX(valeurWidth-suppression-57-37);
        line16.setEndX(valeurWidth-suppression-57-37);
        line17.setEndX(valeurWidth-suppression-57-37);
        line18.setEndX(valeurWidth-suppression-57-37);
        line19.setEndX(valeurWidth-suppression-57-37);
    }
}
