package com.example.propotypage_et_interface_utilisateur;

import com.example.Icalendar.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CasDuJourController implements Initializable {
    @FXML
    VBox vBox;

    @FXML
    Text jour;

    List<Event> list;

    String day;

    public void affichage(){
        jour.setText(this.day);
        URL test = CasePourLeJourController.class.getResource("CasePourLeJour.fxml");
        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(test);
            AnchorPane anchorPane2 = fxmlLoader2.load();
            CasePourLeJourController casePourLeJourController = fxmlLoader2.getController();
            casePourLeJourController.setOpacity(0.0);
            casePourLeJourController.setHeigth(3);
            vBox.getChildren().add(anchorPane2);
            for (int i=0 ; i< list.size() ; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(test);
                AnchorPane anchorPane = fxmlLoader.load();
                casePourLeJourController = fxmlLoader.getController();
                casePourLeJourController.setNombreDeCase(1);
                for (int j =0 ; j<list.get(i).getDescriptionEvent().getListDescription().keySet().toArray().length;j++){
                    System.out.println(list.get(i).getDescriptionEvent().getListDescription().keySet().toArray()[j]);
                }
                casePourLeJourController.setInformation(list.get(i).getDescriptionEvent().getDescription("Salle "),Integer.toString(list.get(i).getDateEvent().getStartDate().getHour())+"h"+Integer.toString(list.get(i).getDateEvent().getStartDate().getMinute())+"-"+
                                Integer.toString(list.get(i).getDateEvent().getEndDate().getHour())+"h"+Integer.toString(list.get(i).getDateEvent().getEndDate().getMinute())
                                +"\\"+list.get(i).getDescriptionEvent().getDescription("Type "),
                        list.get(i).getDescriptionEvent().getDescription("Matière "),list.get(i).getDescriptionEvent().getDescription("Enseignant "),
                        list.get(i).getDescriptionEvent().getDescription("TD "));
                vBox.getChildren().add(anchorPane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setList(List<Event> list) {
        this.list = list;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
