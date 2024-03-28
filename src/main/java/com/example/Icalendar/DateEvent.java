package com.example.Icalendar;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateEvent {

    private LocalDateTime startDate;
    private LocalDateTime lastModified;
    private LocalDateTime endDate;

    DateEvent(String startDate , String endDate,String lastModified){
        if(startDate.length()>8) {
            int valeur = Integer.parseInt(startDate.substring(9, 11))+1;
            String heure="";
            if (valeur < 10) {
                heure = "0";
            }
            startDate = startDate.substring(0, 4) + "-" + startDate.substring(4, 6) + "-" + startDate.substring(6, 9)+heure+Integer.toString(valeur) + ":"
                    + startDate.substring(11, 13) + ":" + startDate.substring(13, 15);
        }
        else{
            startDate = startDate.substring(0, 4) + "-" + startDate.substring(4, 6) + "-" + startDate.substring(6, 8)+"T00:00:00";
        }
        this.startDate= LocalDateTime.parse(startDate);
        if (this.startDate.isBefore(LocalDateTime.of(2023,10,11,0,0)) || this.startDate.isAfter(LocalDateTime.of(2024,03,31,0,0))){
            this.startDate = this.startDate.plusHours(1);
        }
        if(lastModified!=null) {
            lastModified = lastModified.substring(0, 4) + "-" + lastModified.substring(4, 6) + "-" +
                    lastModified.substring(6, 11) + ":"+ lastModified.substring(11, 13) + ":" + lastModified.substring(13, 15);
            this.lastModified = LocalDateTime.parse(lastModified);
        }
        else {
            this.lastModified=LocalDateTime.now();
        }

        if(endDate.length()>8){
            int valeur = Integer.parseInt(endDate.substring(9, 11))+1;
            String heure="";
            if (valeur < 10) {
                heure = "0";
            }
            endDate = endDate.substring(0,4)+"-"+endDate.substring(4,6)+"-"+endDate.substring(6, 9)+heure+Integer.toString(valeur) +":"
                    +endDate.substring(11,13)+":"+endDate.substring(13,15);
        }
        else{
            endDate = endDate.substring(0, 4) + "-" + endDate.substring(4, 6) + "-" + endDate.substring(6, 8)+"T00:00:00";
        }
        this.endDate = LocalDateTime.parse(endDate);
        if (this.endDate.isBefore(LocalDateTime.of(2023,10,11,0,0)) || this.endDate.isAfter(LocalDateTime.of(2024,03,31,0,0))){
            this.endDate = this.endDate.plusHours(1);
        }
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
