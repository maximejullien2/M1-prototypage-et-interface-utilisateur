package com.example.propotypage_et_interface_utilisateur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CaseDeLaSemaineController implements Initializable {
    @FXML
    AnchorPane anchorPane;
    @FXML
    Text salle;
    @FXML
    Text heureEtType;
    @FXML
    Text cours;
    @FXML
    Text professeur;
    @FXML
    Text groupeOuPromotion;

    Integer nombreDeCase = 1;
    public void setNombreDeCase(Integer nombreDeCase){
        this.nombreDeCase = nombreDeCase;
    }

    private void disableText(){
        double height = this.anchorPane.getPrefHeight();
        if (this.salle.getLayoutY()>height){
            this.salle.setVisible(false);
        }
        if (this.heureEtType.getLayoutY()>height){
            this.heureEtType.setVisible(false);
        }
        if (this.cours.getLayoutY()>height){
            this.cours.setVisible(false);
        }
        if (this.professeur.getLayoutY()>height){
            this.professeur.setVisible(false);
        }
        if (this.groupeOuPromotion.getLayoutY()>height){
            this.groupeOuPromotion.setVisible(false);
        }
    }
    public void setInformation(String salle,String heureEtType,String cours,String professeur,String groupeOuPromotion){

        if (salle!=null && salle.length()>21/nombreDeCase){
            salle = salle.substring(0,20/nombreDeCase);
        }
        this.salle.setText(salle);
        if(heureEtType!=null &&heureEtType.length()>23/nombreDeCase){
            heureEtType = heureEtType.substring(0,22/nombreDeCase);
        }
        this.heureEtType.setText(heureEtType);
        if(cours!=null &&cours.length()>23/nombreDeCase){
            cours = cours.substring(0,22/nombreDeCase);
        }
        this.cours.setText(cours);
        if(professeur!=null &&professeur.length()>24/nombreDeCase){
            professeur = professeur.substring(0,23/nombreDeCase);
        }
        this.professeur.setText(professeur);
        if(groupeOuPromotion!=null &&groupeOuPromotion.length()>25/nombreDeCase){
            groupeOuPromotion = groupeOuPromotion.substring(0,24/nombreDeCase);
        }
        this.groupeOuPromotion.setText(groupeOuPromotion);
        this.anchorPane.setPrefWidth(128/nombreDeCase);
        disableText();
    }
    public void setOpacity(double value){
        this.anchorPane.setOpacity(value);
    }
    public void setHeigth(double valueADiviser){
        this.anchorPane.setPrefHeight(this.anchorPane.getPrefHeight()/valueADiviser);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            }
}
