package com.example.Icalendar;

import java.util.HashMap;

/**
 * Object who represent description for an Event
 */
public class DescriptionEvent {
    /**
     * List of description for an Event
     */
    private HashMap<String,String> listDescription;

    DescriptionEvent(HashMap<String,String> listDescription){
        this.listDescription = listDescription;
    }

    DescriptionEvent(){
        listDescription = new HashMap<String,String>();
    }

    /**
     * Add a descritpion into the listDescription
     * @param key What type of description it is.
     * @param value Value of this description.
     */
    public void addDescription(String key , String value){
        this.listDescription.put(key,value);
    }
}
