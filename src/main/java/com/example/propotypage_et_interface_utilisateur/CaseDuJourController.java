package com.example.propotypage_et_interface_utilisateur;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;

public class CaseDuJourController implements Initializable {
    @FXML
    AnchorPane anchorPane;
    @FXML
    Text salle;
    @FXML
    VBox vBox;

    Integer nombreDeCase = 1;
    public void setNombreDeCase(Integer nombreDeCase){
        this.nombreDeCase = nombreDeCase;this.anchorPane.setPrefWidth(638/this.nombreDeCase);
    }
    public void setInformation(HashMap<String,String> hashMap, String horraire){
        this.anchorPane.setPrefWidth(638/nombreDeCase);
        final String horraireDebut = horraire;
        List<Text> list = new ArrayList<Text>();
        double height = this.anchorPane.getPrefHeight()-10;
        double heightVbox = this.vBox.getLayoutY();
        if (hashMap.get("Type ")!= null)
            horraire = horraire + hashMap.get("Type ");
        else{
            if (hashMap.isEmpty()){
                horraire = horraire + "Fériée";
            }
        }
        Text horaireAffichage = new Text(horraire);
        vBox.getChildren().add(horaireAffichage);
        while (horaireAffichage.getLayoutBounds().getWidth()>this.anchorPane.getPrefWidth()-10){
            horraire = horraire.substring(0,horraire.length()-1);
            horaireAffichage.setText(horraire);
        }
        heightVbox = heightVbox+horaireAffichage.getLayoutBounds().getHeight();
        if (heightVbox>=height){
            vBox.getChildren().remove(horaireAffichage);
        }
        else{
            list.add(horaireAffichage);
        }
        if (heightVbox<height && hashMap.get("Matière ")!= null) {
            String resultatMatiere = "Matière :"+hashMap.get("Matière ");
            Text matiere = new Text(resultatMatiere);
            vBox.getChildren().add(matiere);
            while (matiere.getLayoutBounds().getWidth()>this.anchorPane.getPrefWidth()-10){
                resultatMatiere = resultatMatiere.substring(0,resultatMatiere.length()-1);
                matiere.setText(resultatMatiere);
            }
            heightVbox = heightVbox+matiere.getLayoutBounds().getHeight();
            if (heightVbox>=height){
                vBox.getChildren().remove(matiere);
            }
            else {
                list.add(matiere);
            }
        }
        if (heightVbox<height &&hashMap.get("Enseignant ")!= null) {
            String resultatEnseignant = "Enseignant :" +hashMap.get("Enseignant ");
            Text enseignant = new Text(resultatEnseignant);
            vBox.getChildren().add(enseignant);
            while (enseignant.getLayoutBounds().getWidth()>this.anchorPane.getPrefWidth()-10){
                resultatEnseignant = resultatEnseignant.substring(0,resultatEnseignant.length()-1);
                enseignant.setText(resultatEnseignant);
            }
            heightVbox = heightVbox+enseignant.getLayoutBounds().getHeight();
            if (heightVbox>=height){
                vBox.getChildren().remove(enseignant);
            }
            else {
                list.add(enseignant);
            }
        }
        if (heightVbox<height &&hashMap.get("Enseignants ")!= null) {
            String resultatEnseignant = "Enseignants :" +hashMap.get("Enseignants ");
            Text enseignant = new Text(resultatEnseignant);
            vBox.getChildren().add(enseignant);
            while (enseignant.getLayoutBounds().getWidth()>this.anchorPane.getPrefWidth()-10){
                resultatEnseignant = resultatEnseignant.substring(0,resultatEnseignant.length()-1);
                enseignant.setText(resultatEnseignant);
            }
            heightVbox = heightVbox+enseignant.getLayoutBounds().getHeight();
            if (heightVbox>=height){
                vBox.getChildren().remove(enseignant);
            }
            else {
                list.add(enseignant);
            }
        }
        if (heightVbox<height &&hashMap.get("Promotion ")!= null) {
            String resultatPromotion = "Promotion :" +hashMap.get("Promotion ");
            Text promotion = new Text(resultatPromotion);
            vBox.getChildren().add(promotion);
            while (promotion.getLayoutBounds().getWidth()>this.anchorPane.getPrefWidth()-10){
                resultatPromotion = resultatPromotion.substring(0,resultatPromotion.length()-1);
                promotion.setText(resultatPromotion);
            }
            heightVbox = heightVbox+promotion.getLayoutBounds().getHeight();
            if (heightVbox>=height){
                vBox.getChildren().remove(promotion);
            }
            else {
                list.add(promotion);
            }
        }
        if (heightVbox<height &&hashMap.get("TD ")!= null) {
            String resultatTD = "TD :" + hashMap.get("TD ");
            Text td = new Text(resultatTD);
            vBox.getChildren().add(td);
            while (td.getLayoutBounds().getWidth() > this.anchorPane.getPrefWidth() - 10) {
                resultatTD = resultatTD.substring(0, resultatTD.length() - 1);
                td.setText(resultatTD);
            }
            heightVbox = heightVbox + td.getLayoutBounds().getHeight();
            if (heightVbox >= height) {
                vBox.getChildren().remove(td);
            }
            else {
                list.add(td);
            }
        }
        if (hashMap.get("Salle ")!= null){
            String resultatSalle = hashMap.get("Salle ");
            this.salle.setText(resultatSalle);
            while (this.salle.getLayoutBounds().getWidth()>this.anchorPane.getPrefWidth()-10){
                resultatSalle = resultatSalle.substring(0,resultatSalle.length()-1);
                this.salle.setText(resultatSalle);
            }
        }
        if (hashMap.get("Salles ")!= null){
            String resultatSalle = hashMap.get("Salles ");
            this.salle.setText(resultatSalle);
            while (this.salle.getLayoutBounds().getWidth()>this.anchorPane.getPrefWidth()-10){
                resultatSalle = resultatSalle.substring(0,resultatSalle.length()-1);
                this.salle.setText(resultatSalle);
            }
        }
        if (hashMap.get("Lieu ")!= null){
            String resultatSalle = hashMap.get("Lieu ");
            this.salle.setText(resultatSalle);
            while (this.salle.getLayoutBounds().getWidth()>this.anchorPane.getPrefWidth()-10){
                resultatSalle = resultatSalle.substring(0,resultatSalle.length()-1);
                this.salle.setText(resultatSalle);
            }
        }
        if (heightVbox<height && hashMap.get("Type ")!= null) {
            String resultatType = "Type :" +hashMap.get("Type ");
            Text type = new Text(resultatType);
            vBox.getChildren().add(type);
            while (type.getLayoutBounds().getWidth() > this.anchorPane.getPrefWidth() - 10) {
                resultatType = resultatType.substring(0, resultatType.length() - 1);
                type.setText(resultatType);
            }
            heightVbox = heightVbox+type.getLayoutBounds().getHeight();
            if (heightVbox>=height){
                vBox.getChildren().remove(type);
            }
            else {
                list.add(type);
            }

        }
        if (heightVbox<height && hashMap.get("Personnes ")!= null) {
            String resultatMemo = "Personnes :" +hashMap.get("Personnes ");
            Text memo = new Text(resultatMemo);
            vBox.getChildren().add(memo);
            while (memo.getLayoutBounds().getWidth() > this.anchorPane.getPrefWidth() - 10) {
                resultatMemo = resultatMemo.substring(0, resultatMemo.length() - 1);
                memo.setText(resultatMemo);
            }
            heightVbox = heightVbox+memo.getLayoutBounds().getHeight();
            if (heightVbox>=height){
                vBox.getChildren().remove(memo);
            }
            else {
                list.add(memo);
            }
        }
        if (heightVbox<height && hashMap.get("Groupe ")!= null) {
            String resultatMemo = "Groupe :" +hashMap.get("Groupe ");
            Text memo = new Text(resultatMemo);
            vBox.getChildren().add(memo);
            while (memo.getLayoutBounds().getWidth() > this.anchorPane.getPrefWidth() - 10) {
                resultatMemo = resultatMemo.substring(0, resultatMemo.length() - 1);
                memo.setText(resultatMemo);
            }
            heightVbox = heightVbox+memo.getLayoutBounds().getHeight();
            if (heightVbox>=height){
                vBox.getChildren().remove(memo);
            }
            else {
                list.add(memo);
            }
        }
        if (heightVbox<height && hashMap.get("Mémo ")!= null) {
            String resultatMemo = "Mémo :" +hashMap.get("Mémo ");
            Text memo = new Text(resultatMemo);
            vBox.getChildren().add(memo);
            while (memo.getLayoutBounds().getWidth() > this.anchorPane.getPrefWidth() - 10) {
                resultatMemo = resultatMemo.substring(0, resultatMemo.length() - 1);
                memo.setText(resultatMemo);
            }
            heightVbox = heightVbox+memo.getLayoutBounds().getHeight();
            if (heightVbox>=height){
                vBox.getChildren().remove(memo);
            }
            else {
                list.add(memo);
            }
        }
        Color color;
        if (Objects.equals(CalendrierController.couleur.get(), "white")){
            String backgroundColor = "cyan";
            if (Objects.equals(hashMap.get("Type "), " Evaluation")){
                backgroundColor = "red";
            }
            if (hashMap.get("Matière ")!=null && hashMap.get("Matière ").contains("UEO")){
                backgroundColor = "green";
            }
            if (hashMap.get("Couleur ")!=null){
                backgroundColor = hashMap.get("Couleur ").replace("0x","#").replaceAll(" ","");
            }
            color = Color.BLACK;
            anchorPane.setStyle("-fx-background-color:"+backgroundColor+";-fx-border-color: black;");
        }
        else{
            String backgroundColor = "blue";
            if (Objects.equals(hashMap.get("Type "), " Evaluation")){
                Color color2 = (Color) Paint.valueOf("red");
                backgroundColor = color2.darker().toString().replace("0x","#");
            }
            if (hashMap.get("Matière ") !=null && hashMap.get("Matière ").contains("UEO")){
                Color color2 = (Color) Paint.valueOf("green");
                backgroundColor = color2.darker().toString().replace("0x","#");
            }
            if (hashMap.get("Couleur ")!=null){
                backgroundColor = hashMap.get("Couleur ").replace("0x","#").replaceAll(" ","");
                Color color2 = (Color) Paint.valueOf(backgroundColor);
                backgroundColor = color2.darker().toString().replace("0x","#");
            }
            color = Color.WHITE;
            anchorPane.setStyle("-fx-background-color:"+backgroundColor+";-fx-border-color: white;");
        }
        salle.setFill(color);
        for (int i = 0; i< list.size(); i++){
            list.get(i).setFill(color);
        }
        disableText();
        StringProperty stringProperty = CalendrierController.couleur;
        List<Text> finalList = list;
        stringProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                Color color;
                if (Objects.equals(s, "black")){
                    String backgroundColor = "cyan";
                    if (Objects.equals(hashMap.get("Type "), " Evaluation")){
                        backgroundColor = "red";
                    }
                    if (hashMap.get("Matière ")!=null && hashMap.get("Matière ").contains("UEO")){
                        backgroundColor = "green";
                    }
                    if (hashMap.get("Couleur ")!=null){
                        backgroundColor = hashMap.get("Couleur ").replace("0x","#").replaceAll(" ","");
                    }
                    color = Color.BLACK;
                    anchorPane.setStyle("-fx-background-color:"+backgroundColor+";-fx-border-color: black;");
                }
                else{
                    String backgroundColor = "blue";
                    if (Objects.equals(hashMap.get("Type "), " Evaluation")){
                        Color color2 = (Color) Paint.valueOf("red");
                        backgroundColor = color2.darker().toString().replace("0x","#");
                    }
                    if (hashMap.get("Matière ") !=null && hashMap.get("Matière ").contains("UEO")){
                        Color color2 = (Color) Paint.valueOf("green");
                        backgroundColor = color2.darker().toString().replace("0x","#");
                    }
                    if (hashMap.get("Couleur ")!=null){
                        backgroundColor = hashMap.get("Couleur ").replace("0x","#").replaceAll(" ","");
                        Color color2 = (Color) Paint.valueOf(backgroundColor);
                        backgroundColor = color2.darker().toString().replace("0x","#");
                    }
                    color = Color.WHITE;
                    anchorPane.setStyle("-fx-background-color:"+backgroundColor+";-fx-border-color: white;");
                }
                salle.setFill(color);
                for (int i = 0; i< list.size(); i++){
                    list.get(i).setFill(color);
                }
            }
        });
        CalendrierApplication.stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setWidth(newValue.doubleValue());
                vBox.getChildren().clear();
                String horraire = horraireDebut;
                List<Text> list = new ArrayList<Text>();
                double height = anchorPane.getPrefHeight()-10;
                double heightVbox = vBox.getLayoutY();
                if (hashMap.get("Type ")!= null)
                    horraire = horraire + hashMap.get("Type ");
                else{
                    if (hashMap.isEmpty()){
                        horraire = horraire + "Fériée";
                    }
                }
                Text horaireAffichage = new Text(horraire);
                vBox.getChildren().add(horaireAffichage);
                while (horaireAffichage.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                    horraire = horraire.substring(0,horraire.length()-1);
                    horaireAffichage.setText(horraire);
                }
                heightVbox = heightVbox+horaireAffichage.getLayoutBounds().getHeight();
                if (heightVbox>=height){
                    vBox.getChildren().remove(horaireAffichage);
                }
                else{
                    list.add(horaireAffichage);
                }
                if (heightVbox<height && hashMap.get("Matière ")!= null) {
                    String resultatMatiere = "Matière :"+hashMap.get("Matière ");
                    Text matiere = new Text(resultatMatiere);
                    vBox.getChildren().add(matiere);
                    while (matiere.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                        resultatMatiere = resultatMatiere.substring(0,resultatMatiere.length()-1);
                        matiere.setText(resultatMatiere);
                    }
                    heightVbox = heightVbox+matiere.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(matiere);
                    }
                    else {
                        list.add(matiere);
                    }
                }
                if (heightVbox<height &&hashMap.get("Enseignant ")!= null) {
                    String resultatEnseignant = "Enseignant :" +hashMap.get("Enseignant ");
                    Text enseignant = new Text(resultatEnseignant);
                    vBox.getChildren().add(enseignant);
                    while (enseignant.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                        resultatEnseignant = resultatEnseignant.substring(0,resultatEnseignant.length()-1);
                        enseignant.setText(resultatEnseignant);
                    }
                    heightVbox = heightVbox+enseignant.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(enseignant);
                    }
                    else {
                        list.add(enseignant);
                    }
                }
                if (heightVbox<height &&hashMap.get("Enseignants ")!= null) {
                    String resultatEnseignant = "Enseignants :" +hashMap.get("Enseignants ");
                    Text enseignant = new Text(resultatEnseignant);
                    vBox.getChildren().add(enseignant);
                    while (enseignant.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                        resultatEnseignant = resultatEnseignant.substring(0,resultatEnseignant.length()-1);
                        enseignant.setText(resultatEnseignant);
                    }
                    heightVbox = heightVbox+enseignant.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(enseignant);
                    }
                    else {
                        list.add(enseignant);
                    }
                }
                if (heightVbox<height &&hashMap.get("Promotion ")!= null) {
                    String resultatPromotion = "Promotion :" +hashMap.get("Promotion ");
                    Text promotion = new Text(resultatPromotion);
                    vBox.getChildren().add(promotion);
                    while (promotion.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                        resultatPromotion = resultatPromotion.substring(0,resultatPromotion.length()-1);
                        promotion.setText(resultatPromotion);
                    }
                    heightVbox = heightVbox+promotion.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(promotion);
                    }
                    else {
                        list.add(promotion);
                    }
                }
                if (heightVbox<height &&hashMap.get("TD ")!= null) {
                    String resultatTD = "TD :" + hashMap.get("TD ");
                    Text td = new Text(resultatTD);
                    vBox.getChildren().add(td);
                    while (td.getLayoutBounds().getWidth() > anchorPane.getPrefWidth() - 10) {
                        resultatTD = resultatTD.substring(0, resultatTD.length() - 1);
                        td.setText(resultatTD);
                    }
                    heightVbox = heightVbox + td.getLayoutBounds().getHeight();
                    if (heightVbox >= height) {
                        vBox.getChildren().remove(td);
                    }
                    else {
                        list.add(td);
                    }
                }
                if (hashMap.get("Salle ")!= null){
                    String resultatSalle = hashMap.get("Salle ");
                    salle.setText(resultatSalle);
                    while (salle.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                        resultatSalle = resultatSalle.substring(0,resultatSalle.length()-1);
                        salle.setText(resultatSalle);
                    }
                }
                if (hashMap.get("Salles ")!= null){
                    String resultatSalle = hashMap.get("Salles ");
                    salle.setText(resultatSalle);
                    while (salle.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                        resultatSalle = resultatSalle.substring(0,resultatSalle.length()-1);
                        salle.setText(resultatSalle);
                    }
                }
                if (hashMap.get("Lieu ")!= null){
                    String resultatSalle = hashMap.get("Lieu ");
                    salle.setText(resultatSalle);
                    while (salle.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                        resultatSalle = resultatSalle.substring(0,resultatSalle.length()-1);
                        salle.setText(resultatSalle);
                    }
                }
                if (heightVbox<height && hashMap.get("Type ")!= null) {
                    String resultatType = "Type :" +hashMap.get("Type ");
                    Text type = new Text(resultatType);
                    vBox.getChildren().add(type);
                    while (type.getLayoutBounds().getWidth() > anchorPane.getPrefWidth() - 10) {
                        resultatType = resultatType.substring(0, resultatType.length() - 1);
                        type.setText(resultatType);
                    }
                    heightVbox = heightVbox+type.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(type);
                    }
                    else {
                        list.add(type);
                    }

                }
                if (heightVbox<height && hashMap.get("Personnes ")!= null) {
                    String resultatMemo = "Personnes :" +hashMap.get("Personnes ");
                    Text memo = new Text(resultatMemo);
                    vBox.getChildren().add(memo);
                    while (memo.getLayoutBounds().getWidth() > anchorPane.getPrefWidth() - 10) {
                        resultatMemo = resultatMemo.substring(0, resultatMemo.length() - 1);
                        memo.setText(resultatMemo);
                    }
                    heightVbox = heightVbox+memo.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(memo);
                    }
                    else {
                        list.add(memo);
                    }
                }
                if (heightVbox<height && hashMap.get("Groupe ")!= null) {
                    String resultatMemo = "Groupe :" +hashMap.get("Groupe ");
                    Text memo = new Text(resultatMemo);
                    vBox.getChildren().add(memo);
                    while (memo.getLayoutBounds().getWidth() > anchorPane.getPrefWidth() - 10) {
                        resultatMemo = resultatMemo.substring(0, resultatMemo.length() - 1);
                        memo.setText(resultatMemo);
                    }
                    heightVbox = heightVbox+memo.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(memo);
                    }
                    else {
                        list.add(memo);
                    }
                }
                if (heightVbox<height && hashMap.get("Mémo ")!= null) {
                    String resultatMemo = "Mémo :" +hashMap.get("Mémo ");
                    Text memo = new Text(resultatMemo);
                    vBox.getChildren().add(memo);
                    while (memo.getLayoutBounds().getWidth() > anchorPane.getPrefWidth() - 10) {
                        resultatMemo = resultatMemo.substring(0, resultatMemo.length() - 1);
                        memo.setText(resultatMemo);
                    }
                    heightVbox = heightVbox+memo.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(memo);
                    }
                    else {
                        list.add(memo);
                    }
                }
                Color color;
                if (Objects.equals(CalendrierController.couleur.get(), "white")){
                    String backgroundColor = "cyan";
                    if (Objects.equals(hashMap.get("Type "), " Evaluation")){
                        backgroundColor = "red";
                    }
                    if (hashMap.get("Matière ")!=null && hashMap.get("Matière ").contains("UEO")){
                        backgroundColor = "green";
                    }
                    if (hashMap.get("Couleur ")!=null){
                        backgroundColor = hashMap.get("Couleur ").replace("0x","#").replaceAll(" ","");
                    }
                    color = Color.BLACK;
                    anchorPane.setStyle("-fx-background-color:"+backgroundColor+";-fx-border-color: black;");
                }
                else{
                    String backgroundColor = "blue";
                    if (Objects.equals(hashMap.get("Type "), " Evaluation")){
                        Color color2 = (Color) Paint.valueOf("red");
                        backgroundColor = color2.darker().toString().replace("0x","#");
                    }
                    if (hashMap.get("Matière ") !=null && hashMap.get("Matière ").contains("UEO")){
                        Color color2 = (Color) Paint.valueOf("green");
                        backgroundColor = color2.darker().toString().replace("0x","#");
                    }
                    if (hashMap.get("Couleur ")!=null){
                        backgroundColor = hashMap.get("Couleur ").replace("0x","#").replaceAll(" ","");
                        Color color2 = (Color) Paint.valueOf(backgroundColor);
                        backgroundColor = color2.darker().toString().replace("0x","#");
                    }
                    color = Color.WHITE;
                    anchorPane.setStyle("-fx-background-color:"+backgroundColor+";-fx-border-color: white;");
                }
                salle.setFill(color);
                for (int i = 0; i< list.size(); i++){
                    list.get(i).setFill(color);
                }
            }
        });
        this.setWidth(CalendrierApplication.stage.getWidth());
        CalendrierApplication.stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                anchorPane.setPrefHeight((anchorPane.getPrefHeight()*(newValue.doubleValue()-116))/(oldValue.doubleValue()-116));
                vBox.getChildren().clear();
                String horraire = horraireDebut;
                List<Text> list = new ArrayList<Text>();
                double height = anchorPane.getPrefHeight()-10;
                double heightVbox = vBox.getLayoutY();
                if (hashMap.get("Type ")!= null)
                    horraire = horraire + hashMap.get("Type ");
                else{
                    if (hashMap.isEmpty()){
                        horraire = horraire + "Fériée";
                    }
                }
                Text horaireAffichage = new Text(horraire);
                vBox.getChildren().add(horaireAffichage);
                while (horaireAffichage.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                    horraire = horraire.substring(0,horraire.length()-1);
                    horaireAffichage.setText(horraire);
                }
                heightVbox = heightVbox+horaireAffichage.getLayoutBounds().getHeight();
                if (heightVbox>=height){
                    vBox.getChildren().remove(horaireAffichage);
                }
                else{
                    list.add(horaireAffichage);
                }
                if (heightVbox<height && hashMap.get("Matière ")!= null) {
                    String resultatMatiere = "Matière :"+hashMap.get("Matière ");
                    Text matiere = new Text(resultatMatiere);
                    vBox.getChildren().add(matiere);
                    while (matiere.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                        resultatMatiere = resultatMatiere.substring(0,resultatMatiere.length()-1);
                        matiere.setText(resultatMatiere);
                    }
                    heightVbox = heightVbox+matiere.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(matiere);
                    }
                    else {
                        list.add(matiere);
                    }
                }
                if (heightVbox<height &&hashMap.get("Enseignant ")!= null) {
                    String resultatEnseignant = "Enseignant :" +hashMap.get("Enseignant ");
                    Text enseignant = new Text(resultatEnseignant);
                    vBox.getChildren().add(enseignant);
                    while (enseignant.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                        resultatEnseignant = resultatEnseignant.substring(0,resultatEnseignant.length()-1);
                        enseignant.setText(resultatEnseignant);
                    }
                    heightVbox = heightVbox+enseignant.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(enseignant);
                    }
                    else {
                        list.add(enseignant);
                    }
                }
                if (heightVbox<height &&hashMap.get("Enseignants ")!= null) {
                    String resultatEnseignant = "Enseignants :" +hashMap.get("Enseignants ");
                    Text enseignant = new Text(resultatEnseignant);
                    vBox.getChildren().add(enseignant);
                    while (enseignant.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                        resultatEnseignant = resultatEnseignant.substring(0,resultatEnseignant.length()-1);
                        enseignant.setText(resultatEnseignant);
                    }
                    heightVbox = heightVbox+enseignant.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(enseignant);
                    }
                    else {
                        list.add(enseignant);
                    }
                }
                if (heightVbox<height &&hashMap.get("Promotion ")!= null) {
                    String resultatPromotion = "Promotion :" +hashMap.get("Promotion ");
                    Text promotion = new Text(resultatPromotion);
                    vBox.getChildren().add(promotion);
                    while (promotion.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                        resultatPromotion = resultatPromotion.substring(0,resultatPromotion.length()-1);
                        promotion.setText(resultatPromotion);
                    }
                    heightVbox = heightVbox+promotion.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(promotion);
                    }
                    else {
                        list.add(promotion);
                    }
                }
                if (heightVbox<height &&hashMap.get("TD ")!= null) {
                    String resultatTD = "TD :" + hashMap.get("TD ");
                    Text td = new Text(resultatTD);
                    vBox.getChildren().add(td);
                    while (td.getLayoutBounds().getWidth() > anchorPane.getPrefWidth() - 10) {
                        resultatTD = resultatTD.substring(0, resultatTD.length() - 1);
                        td.setText(resultatTD);
                    }
                    heightVbox = heightVbox + td.getLayoutBounds().getHeight();
                    if (heightVbox >= height) {
                        vBox.getChildren().remove(td);
                    }
                    else {
                        list.add(td);
                    }
                }
                if (hashMap.get("Salle ")!= null){
                    String resultatSalle = hashMap.get("Salle ");
                    salle.setText(resultatSalle);
                    while (salle.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                        resultatSalle = resultatSalle.substring(0,resultatSalle.length()-1);
                        salle.setText(resultatSalle);
                    }
                }
                if (hashMap.get("Salles ")!= null){
                    String resultatSalle = hashMap.get("Salles ");
                    salle.setText(resultatSalle);
                    while (salle.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                        resultatSalle = resultatSalle.substring(0,resultatSalle.length()-1);
                        salle.setText(resultatSalle);
                    }
                }
                if (hashMap.get("Lieu ")!= null){
                    String resultatSalle = hashMap.get("Lieu ");
                    salle.setText(resultatSalle);
                    while (salle.getLayoutBounds().getWidth()>anchorPane.getPrefWidth()-10){
                        resultatSalle = resultatSalle.substring(0,resultatSalle.length()-1);
                        salle.setText(resultatSalle);
                    }
                }
                if (heightVbox<height && hashMap.get("Type ")!= null) {
                    String resultatType = "Type :" +hashMap.get("Type ");
                    Text type = new Text(resultatType);
                    vBox.getChildren().add(type);
                    while (type.getLayoutBounds().getWidth() > anchorPane.getPrefWidth() - 10) {
                        resultatType = resultatType.substring(0, resultatType.length() - 1);
                        type.setText(resultatType);
                    }
                    heightVbox = heightVbox+type.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(type);
                    }
                    else {
                        list.add(type);
                    }

                }
                if (heightVbox<height && hashMap.get("Personnes ")!= null) {
                    String resultatMemo = "Personnes :" +hashMap.get("Personnes ");
                    Text memo = new Text(resultatMemo);
                    vBox.getChildren().add(memo);
                    while (memo.getLayoutBounds().getWidth() > anchorPane.getPrefWidth() - 10) {
                        resultatMemo = resultatMemo.substring(0, resultatMemo.length() - 1);
                        memo.setText(resultatMemo);
                    }
                    heightVbox = heightVbox+memo.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(memo);
                    }
                    else {
                        list.add(memo);
                    }
                }
                if (heightVbox<height && hashMap.get("Groupe ")!= null) {
                    String resultatMemo = "Groupe :" +hashMap.get("Groupe ");
                    Text memo = new Text(resultatMemo);
                    vBox.getChildren().add(memo);
                    while (memo.getLayoutBounds().getWidth() > anchorPane.getPrefWidth() - 10) {
                        resultatMemo = resultatMemo.substring(0, resultatMemo.length() - 1);
                        memo.setText(resultatMemo);
                    }
                    heightVbox = heightVbox+memo.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(memo);
                    }
                    else {
                        list.add(memo);
                    }
                }
                if (heightVbox<height && hashMap.get("Mémo ")!= null) {
                    String resultatMemo = "Mémo :" +hashMap.get("Mémo ");
                    Text memo = new Text(resultatMemo);
                    vBox.getChildren().add(memo);
                    while (memo.getLayoutBounds().getWidth() > anchorPane.getPrefWidth() - 10) {
                        resultatMemo = resultatMemo.substring(0, resultatMemo.length() - 1);
                        memo.setText(resultatMemo);
                    }
                    heightVbox = heightVbox+memo.getLayoutBounds().getHeight();
                    if (heightVbox>=height){
                        vBox.getChildren().remove(memo);
                    }
                    else {
                        list.add(memo);
                    }
                }
                Color color;
                if (Objects.equals(CalendrierController.couleur.get(), "white")){
                    String backgroundColor = "cyan";
                    if (Objects.equals(hashMap.get("Type "), " Evaluation")){
                        backgroundColor = "red";
                    }
                    if (hashMap.get("Matière ")!=null && hashMap.get("Matière ").contains("UEO")){
                        backgroundColor = "green";
                    }
                    if (hashMap.get("Couleur ")!=null){
                        backgroundColor = hashMap.get("Couleur ").replace("0x","#").replaceAll(" ","");
                    }
                    color = Color.BLACK;
                    anchorPane.setStyle("-fx-background-color:"+backgroundColor+";-fx-border-color: black;");
                }
                else{
                    String backgroundColor = "blue";
                    if (Objects.equals(hashMap.get("Type "), " Evaluation")){
                        Color color2 = (Color) Paint.valueOf("red");
                        backgroundColor = color2.darker().toString().replace("0x","#");
                    }
                    if (hashMap.get("Matière ") !=null && hashMap.get("Matière ").contains("UEO")){
                        Color color2 = (Color) Paint.valueOf("green");
                        backgroundColor = color2.darker().toString().replace("0x","#");
                    }
                    if (hashMap.get("Couleur ")!=null){
                        backgroundColor = hashMap.get("Couleur ").replace("0x","#").replaceAll(" ","");
                        Color color2 = (Color) Paint.valueOf(backgroundColor);
                        backgroundColor = color2.darker().toString().replace("0x","#");
                    }
                    color = Color.WHITE;
                    anchorPane.setStyle("-fx-background-color:"+backgroundColor+";-fx-border-color: white;");
                }
                salle.setFill(color);
                for (int i = 0; i< list.size(); i++){
                    list.get(i).setFill(color);
                }
            }
        });
        anchorPane.setPrefHeight((anchorPane.getPrefHeight()*(CalendrierApplication.stage.getHeight()-116))/(727-116));
    }
    private void setWidth(double newValue){
        int suppression=240;
        if (CalendrierApplication.stage.getWidth()<791){
            suppression=10;
        }
        anchorPane.setPrefWidth((newValue-suppression-57-50)/nombreDeCase);
        vBox.setPrefWidth((newValue-suppression-57-50)/nombreDeCase);
    }

    public void setOpacity(double value){
        this.anchorPane.setOpacity(value);
        if (value==0){
            CalendrierApplication.stage.heightProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    anchorPane.setPrefHeight((anchorPane.getPrefHeight() * (newValue.doubleValue() - 116)) / (oldValue.doubleValue() - 116));
                }
            });
            anchorPane.setPrefHeight((anchorPane.getPrefHeight()*(CalendrierApplication.stage.getHeight()-116))/(727-116));
            CalendrierApplication.stage.widthProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    setWidth(newValue.doubleValue());
                }
            });
            this.setWidth(CalendrierApplication.stage.getWidth());
        }

    }
    public void setHeigth(double valueADiviser){
        this.anchorPane.setPrefHeight((double)this.anchorPane.getPrefHeight()/valueADiviser);
    }

    private void disableText(){
        double height = this.anchorPane.getPrefHeight();
        if (this.salle.getLayoutY()>height){
            this.salle.setVisible(false);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
