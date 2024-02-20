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
        ArrayList<HashMap<String,String>> outputJson = new ArrayList<HashMap<String,String>>();
        ApiCalendar apiCalendar = new ApiCalendar();

        while(!bufferedReader.readLine().equals("BEGIN:VEVENT")){}
        while(!bufferedReader.readLine().equals("END:VCALENDAR")){
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
            while (!line.split(";")[0].equals("LOCATION")){
                allContent=allContent+line;
                line = bufferedReader.readLine();
            }

            String summary = allContent.split(";")[1].split(":")[1];

            allContent="";
            while (!line.split(";")[0].equals("DESCRIPTION")){
                allContent=allContent+line;
                line = bufferedReader.readLine();
            }

            String location = allContent.split(";")[1].split(":")[1];

            allContent="";
            while (!line.split(";")[0].equals("X-ALT-DESC")){
                allContent=allContent+line;
                line = bufferedReader.readLine();
            }
            descriptionEvent.addDescription(allContent.split(";")[1].split(Pattern.quote("\\n"))[0].split(":")[1],allContent.split(";")[1].split(Pattern.quote("\\n"))[0].split(":")[2]);
            //System.out.println("###########");
            for (int i = 1 ; i<allContent.split(";")[1].split(Pattern.quote("\\n")).length;i++){
                String key = allContent.split(";")[1].split(Pattern.quote("\\n"))[i].split(":")[0];
                String value = allContent.split(";")[1].split(Pattern.quote("\\n"))[i].split(":")[1];
                /*System.out.println("key = "+key);
                System.out.println("value = "+value);
                System.out.println("###########");*/
                descriptionEvent.addDescription(key,value);
            }
            Event event = new Event(dateEvent,summary,location,descriptionEvent);
            apiCalendar.addEvent(event);
            while(!bufferedReader.readLine().equals("BEGIN:VEVENT")){}
        }
        return apiCalendar;
    }

}
