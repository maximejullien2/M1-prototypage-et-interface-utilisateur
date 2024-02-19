package com.example.Icalendar;

import java.util.ArrayList;

public class ApiCalendar {
    private ArrayList<Event> listApiCalendar;

    ApiCalendar(){
        this.listApiCalendar = new ArrayList<Event>();
    }

    public void addEvent(Event event){
        listApiCalendar.add(event);
    }
}
