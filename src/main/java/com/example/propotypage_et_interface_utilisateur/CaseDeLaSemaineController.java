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
    public void setInformation(String salle,String heureEtType,String cours,String professeur,String groupeOuPromotion){
        if (salle.length()>21/nombreDeCase){
            salle = salle.substring(0,20/nombreDeCase);
        }
        this.salle.setText(salle);
        if(heureEtType.length()>23/nombreDeCase){
            heureEtType = heureEtType.substring(0,22/nombreDeCase);
        }
        this.heureEtType.setText(heureEtType);
        if(cours.length()>23/nombreDeCase){
            cours = cours.substring(0,22/nombreDeCase);
        }
        this.cours.setText(cours);
        if(professeur.length()>24/nombreDeCase){
            professeur = professeur.substring(0,23/nombreDeCase);
        }
        this.professeur.setText(professeur);
        if(groupeOuPromotion.length()>25/nombreDeCase){
            groupeOuPromotion = groupeOuPromotion.substring(0,24/nombreDeCase);
        }
        this.groupeOuPromotion.setText(groupeOuPromotion);
        this.anchorPane.setPrefWidth(128/nombreDeCase);

    }
    public void setOpacity(double value){
        this.anchorPane.setOpacity(value);
    }
    public void setHeigth(int valueADiviser){
        this.anchorPane.setPrefHeight(this.anchorPane.getPrefHeight()/valueADiviser);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            }
}
