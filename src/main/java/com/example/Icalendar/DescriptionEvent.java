package com.example.Icalendar;

import java.util.HashMap;

public class DescriptionEvent {
    HashMap<String,String> listDescription;

    DescriptionEvent(HashMap<String,String> listDescription){
        this.listDescription = listDescription;
    }
    DescriptionEvent(){
        listDescription = new HashMap<String,String>();
    }

    public void addDescription(String key , String value){
        this.listDescription.put(key,value);
    }
}
