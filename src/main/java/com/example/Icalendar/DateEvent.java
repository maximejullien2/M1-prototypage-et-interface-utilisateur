package com.example.Icalendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateEvent {

    private LocalDateTime startDate;
    private LocalDateTime lastModified;
    private LocalDateTime endDate;

    DateEvent(String startDate , String endDate,String lastModified){
        startDate = startDate.substring(0,4)+"-"+startDate.substring(4,6)+"-"+startDate.substring(6,11)+":"
                +startDate.substring(11,13)+":"+startDate.substring(13,15);
        this.startDate= LocalDateTime.parse(startDate);

        lastModified = lastModified.substring(0,4)+"-"+lastModified.substring(4,6)+"-"+lastModified.substring(6,11)+":"
                +lastModified.substring(11,13)+":"+lastModified.substring(13,15);
        this.lastModified = LocalDateTime.parse(lastModified);

        endDate = endDate.substring(0,4)+"-"+endDate.substring(4,6)+"-"+endDate.substring(6,11)+":"
                +endDate.substring(11,13)+":"+endDate.substring(13,15);
        this.endDate = LocalDateTime.parse(endDate);
    }
}
