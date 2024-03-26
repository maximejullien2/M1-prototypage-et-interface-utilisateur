package com.example.Icalendar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

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
        this.listApiCalendar.add(event);
    }

    public ArrayList<Event> getEventDay(LocalDateTime dateTime){
        ArrayList<Event> output = new ArrayList<Event>();
        for(int i = 0 ; i< this.listApiCalendar.size(); i++){
            if (this.listApiCalendar.get(i).getDateEvent().getStartDate().getDayOfYear() == dateTime.getDayOfYear()){
                output.add(this.listApiCalendar.get(i));
            }
        }
        DateEventComparator dateEventComparator = new DateEventComparator();
        Collections.sort(output,dateEventComparator);
        return output;
    }
}
