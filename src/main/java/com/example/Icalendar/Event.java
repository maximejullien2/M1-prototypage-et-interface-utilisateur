package com.example.Icalendar;

public class Event {
    DateEvent dateEvent;

    String summary;

    String location;

    DescriptionEvent descriptionEvent;

    Event(DateEvent dateEvent,String summary,String location,DescriptionEvent descriptionEvent){
        this.dateEvent = dateEvent;
        this.summary = summary;
        this.location = location;
        this.descriptionEvent = descriptionEvent;
    }

}
