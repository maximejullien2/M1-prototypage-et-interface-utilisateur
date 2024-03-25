package com.example.Icalendar;

import java.util.ArrayList;

/**
 * Represent Object we will use to stock data for the calendar
 */
public class ApiCalendar {
    /**
     * Variable who will stock all Event to display .
     */
    private ArrayList<Event> listApiCalendar;

    ApiCalendar(){
        this.listApiCalendar = new ArrayList<Event>();
    }

    /**
     * Methods to add an event into the calendar
     * @param event Specific event to add
     */
    public void addEvent(Event event){
        listApiCalendar.add(event);
    }
}
