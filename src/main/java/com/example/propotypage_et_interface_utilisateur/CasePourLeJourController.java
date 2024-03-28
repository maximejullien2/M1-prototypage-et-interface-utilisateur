package com.example.propotypage_et_interface_utilisateur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CasePourLeJourController implements Initializable {
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
    public void setInformation(String salle,String heureEtType,String cours,String professeur,String groupeOuPromotion){
        this.anchorPane.setPrefWidth(638/nombreDeCase);
        this.salle.setText(salle);
        while (this.salle.getLayoutBounds().getWidth()>this.anchorPane.getPrefWidth()-10){
            salle = salle.substring(0,salle.length()-1);
            this.salle.setText(salle);
        }
        this.heureEtType.setText(heureEtType);
        while (this.heureEtType.getLayoutBounds().getWidth()>this.anchorPane.getPrefWidth()-10 && heureEtType.length()>0){
            heureEtType = heureEtType.substring(0,heureEtType.length()-1);
            this.heureEtType.setText(heureEtType);
        }
        this.cours.setText(cours);
        while (this.cours.getLayoutBounds().getWidth()>this.anchorPane.getPrefWidth()-10){
            cours = cours.substring(0,cours.length()-1);
            this.cours.setText(cours);
        }
        this.professeur.setText(professeur);
        while (this.professeur.getLayoutBounds().getWidth()>this.anchorPane.getPrefWidth()-10){
            professeur = professeur.substring(0,professeur.length()-1);
            this.professeur.setText(professeur);
        }
        this.groupeOuPromotion.setText(groupeOuPromotion);
        while (this.groupeOuPromotion.getLayoutBounds().getWidth()>this.anchorPane.getPrefWidth()-10){
            groupeOuPromotion = groupeOuPromotion.substring(0,groupeOuPromotion.length()-1);
            this.groupeOuPromotion.setText(groupeOuPromotion);
        }
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
