package com.example.Icalendar;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;


public class ParserIcalendar {

    private String filename;

    ParserIcalendar(String filename){
        this.filename = filename ;
    }

    public ApiCalendar parseToJson() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filename)));
        ApiCalendar apiCalendar = new ApiCalendar();

        while(!bufferedReader.readLine().equals("BEGIN:VEVENT")){}
        while(bufferedReader.readLine()!=null){
            bufferedReader.readLine();
            String text = bufferedReader.readLine();
            String lastModified ;
            if(text.split(":")[0].equals("LAST-MODIFIED")){
                lastModified = text.split(":")[1];
                bufferedReader.readLine();
            }
            else{
                lastModified =null;
            }
            String dateStart = bufferedReader.readLine().split(":")[1];
            String dateEnd = bufferedReader.readLine().split(":")[1];
            DateEvent dateEvent = new DateEvent(dateStart,dateEnd,lastModified);
            DescriptionEvent descriptionEvent = new DescriptionEvent();
            String allContent="";
            String line = bufferedReader.readLine();
            while (!line.split(";")[0].equals("LOCATION") && !line.equals("END:VEVENT")){
                allContent=allContent+line;
                line = bufferedReader.readLine();
            }
            if(line.equals("END:VEVENT")){
                descriptionEvent.addDescription("TYPE",line.split(":")[1]);
                Event event = new Event(dateEvent, "","", descriptionEvent);
                apiCalendar.addEvent(event);
                bufferedReader.readLine();
            }
            else {
                String summary = allContent.split(";")[1].split(":")[1];
                allContent = "";
                while (!line.split(";")[0].equals("DESCRIPTION")) {
                    allContent = allContent + line;
                    line = bufferedReader.readLine();
                }

                String location = allContent.split(";")[1].split(":")[1];
                allContent = "";
                while (!line.split(";")[0].equals("X-ALT-DESC")) {
                    allContent = allContent + line;
                    line = bufferedReader.readLine();
                }
                descriptionEvent.addDescription(allContent.split(";")[1].split(Pattern.quote("\\n"))[0].split(":")[1], allContent.split(";")[1].split(Pattern.quote("\\n"))[0].split(":")[2]);
                for (int i = 1; i < allContent.split(";")[1].split(Pattern.quote("\\n")).length; i++) {
                    String key = allContent.split(";")[1].split(Pattern.quote("\\n"))[i].split(":")[0];
                    String value = allContent.split(";")[1].split(Pattern.quote("\\n"))[i].split(":")[1];
                    System.out.println("Key = "+key);
                    System.out.println("Value = "+value);
                    descriptionEvent.addDescription(key, value);
                }
                Event event = new Event(dateEvent, summary, location, descriptionEvent);
                apiCalendar.addEvent(event);
                while (!bufferedReader.readLine().equals("BEGIN:VEVENT")) {
                }
            }
        }
        return apiCalendar;
    }

}
