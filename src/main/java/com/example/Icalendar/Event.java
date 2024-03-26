package com.example.Icalendar;

/**
 * Object who represent an Event for a calendar.
 */
public class Event {
    /**
     * This Object represent time information for this Event.
     */
    private DateEvent dateEvent;
    /**
     * This represent a summary for this Event.
     */
    private String summary;
    /**
     * This represent where the Event will be .
     */
    private String location;
    /**
     * This Object represent list of despriction for this Event.
     */
    private DescriptionEvent descriptionEvent;

    Event(DateEvent dateEvent,String summary,String location,DescriptionEvent descriptionEvent){
        this.dateEvent = dateEvent;
        this.summary = summary;
        this.location = location;
        this.descriptionEvent = descriptionEvent;
    }

    public DateEvent getDateEvent() {
        return dateEvent;
    }

    public String getSummary() {
        return summary;
    }

    public String getLocation() {
        return location;
    }

    public DescriptionEvent getDescriptionEvent() {
        return descriptionEvent;
    }
}
