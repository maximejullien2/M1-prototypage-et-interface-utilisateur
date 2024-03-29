package com.example.Icalendar;

import java.util.Comparator;


public class DateEventComparator implements Comparator<Event> {

    @Override
    public int compare(Event o1, Event o2) {
        return o1.getDateEvent().getStartDate().compareTo(o2.getDateEvent().getStartDate());
    }
}
