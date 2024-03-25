package com.example.Icalendar;

import java.time.LocalDateTime;

public class DateEvent {

    private LocalDateTime startDate;
    private LocalDateTime lastModified;
    private LocalDateTime endDate;

    DateEvent(String startDate , String endDate,String lastModified){
        if(startDate.length()>8) {
            startDate = startDate.substring(0, 4) + "-" + startDate.substring(4, 6) + "-" + startDate.substring(6, 11) + ":"
                    + startDate.substring(11, 13) + ":" + startDate.substring(13, 15);
        }
        else{
            startDate = startDate.substring(0, 4) + "-" + startDate.substring(4, 6) + "-" + startDate.substring(6, 8)+"T00:00:00";
        }
        this.startDate= LocalDateTime.parse(startDate);

        if(lastModified!=null) {
            lastModified = lastModified.substring(0, 4) + "-" + lastModified.substring(4, 6) + "-" +
                    lastModified.substring(6, 11) + ":"+ lastModified.substring(11, 13) + ":" + lastModified.substring(13, 15);
            this.lastModified = LocalDateTime.parse(lastModified);
        }
        else {
            this.lastModified=LocalDateTime.now();
        }

        if(endDate.length()>8){
            endDate = endDate.substring(0,4)+"-"+endDate.substring(4,6)+"-"+endDate.substring(6,11)+":"
                    +endDate.substring(11,13)+":"+endDate.substring(13,15);

        }
        else{
            endDate = endDate.substring(0, 4) + "-" + endDate.substring(4, 6) + "-" + endDate.substring(6, 8)+"T00:00:00";
        }
        this.endDate = LocalDateTime.parse(endDate);
    }
}
