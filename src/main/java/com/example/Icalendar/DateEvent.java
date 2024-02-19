package com.example.Icalendar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateEvent {

    private LocalDate startDate;
    private LocalDate lastModified;
    private LocalDate endDate;

    DateEvent(String startDate , String lastModified,String endDate){
        System.out.println(startDate);

        this.startDate = LocalDate.parse("20230922T123000");
        System.out.println(startDate);
        this.lastModified = LocalDate.parse(lastModified,DateTimeFormatter.ISO_INSTANT);
        System.out.println(startDate);
        this.endDate = LocalDate.parse(endDate,DateTimeFormatter.ISO_INSTANT);
        System.out.println(startDate);

    }
}
