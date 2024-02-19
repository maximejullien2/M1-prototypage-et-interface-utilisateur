package com.example.Icalendar;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


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
            String lastModified = bufferedReader.readLine().split(":")[1];
            bufferedReader.readLine();
            String dateStart = bufferedReader.readLine().split(":")[1];
            String dateEnd = bufferedReader.readLine().split(":")[1];

            DateEvent dateEvent = new DateEvent(dateStart,dateEnd,lastModified);
            DescriptionEvent descriptionEvent = new DescriptionEvent();
            String summary = bufferedReader.readLine().split(";")[1].split(":")[1] + bufferedReader.readLine();
            String location = bufferedReader.readLine().split(";")[1].split(":")[1];

            String ligne = bufferedReader.readLine();
            String description="";
            String content="";
            boolean ajouter = false;
            while(ligne.split(";")[0]!="X-ALT-DESC"){
                String[] lines = ligne.split(";");
                if(lines.length>1){
                    for (int i =0 ; i< lines[1].split("\\n").length;i++){
                        int j = 1 ;
                        if(i != 0) {
                            j = j - 1;
                        }
                        description = lines[1].split("\\n")[i].split(":")[j];
                        if(lines[1].split("\\n")[i].split(":").length>1) {
                            content = lines[1].split("\\n")[i].split(":")[j + 1];
                        }
                        ajouter=false;
                        if(lines[1].split("\\n").length>1 && i < lines[1].split("\\n").length -1){
                            descriptionEvent.addDescription(description,content);
                            ajouter=true;
                        }
                    }
                }
                else{
                    for (int i=0 ; i<lines[0].split("\n").length ; i++){
                        if(ajouter==false) {
                            description = description + lines[0].split("\\n")[i].split(":")[0];
                            content = content + lines[0].split("\\n")[i].split(":")[1];
                            ajouter=true;
                        }
                        else{
                            description = lines[0].split("\\n")[i].split(":")[0];
                            if(lines[0].split("\\n")[i].split(":").length>1) {
                                content = lines[0].split("\\n")[i].split(":")[1];
                            }
                            else{
                                ajouter = false;
                            }
                        }
                        if(ajouter){
                            descriptionEvent.addDescription(description,content);
                        }
                    }
                }
                ligne = bufferedReader.readLine();
            }
            Event event = new Event(dateEvent,summary,location,descriptionEvent);
            apiCalendar.addEvent(event);
            while(!bufferedReader.readLine().equals("BEGIN:VEVENT")){}
        }

        return apiCalendar;
    }
}
