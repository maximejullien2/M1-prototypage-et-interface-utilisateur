package com.example.Icalendar;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * This object will parse an Icalendar file into an object ApiCalendar.
 */
public class ParserIcalendar {

    /**
     * This method will create an Object ApiCalendar for a specific Icalendar file .
     * @param bufferedReader Object who read data from a specific file given before
     * @return Return an Object ApiCalendar
     * @throws IOException
     */
    public ApiCalendar createCalendar(BufferedReader bufferedReader) throws IOException {
        ApiCalendar apiCalendar = new ApiCalendar();
        while(!bufferedReader.readLine().equals("BEGIN:VEVENT")){}
        while(bufferedReader.readLine()!=null){
            DateEvent dateEvent = this.createDateEvent(bufferedReader);
            apiCalendar.addEvent(creatEvent(bufferedReader,dateEvent));
        }
        return apiCalendar;
    }

    /**
     * Parse a specific Icalendar file into an Object ApiCalendar
     * @param filename What file we want to parse
     * @return Return an Object ApiCalendar
     * @throws IOException
     */
    public ApiCalendar parse(String filename) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        return createCalendar(bufferedReader);
    }

    /**
     * Create for a specific Event an Object DateEvent
     * @param bufferedReader Where we need to read to get information
     * @return Return date informtion into an Object DateEvent
     * @throws IOException
     */
    private DateEvent createDateEvent(BufferedReader bufferedReader) throws IOException {
        bufferedReader.readLine();
        String line = bufferedReader.readLine();
        String dateLastModified ;
        if(line.split(":")[0].equals("LAST-MODIFIED")){
            dateLastModified = line.split(":")[1];
            bufferedReader.readLine();
        }
        else{
            dateLastModified =null;
        }
        String dateStart = bufferedReader.readLine().split(":")[1];
        String dateEnd = bufferedReader.readLine().split(":")[1];
        return new DateEvent(dateStart,dateEnd,dateLastModified);
    }

    /**
     * Create an Event between 2 cases : if it a holiday Event or a Module Event.
     * @param bufferedReader Where we need to read to get information
     * @param dateEvent Information of date for a specific Event
     * @return Return an Event with all information required
     * @throws IOException
     */
    private Event creatEvent(BufferedReader bufferedReader,DateEvent dateEvent) throws IOException {
        String allContent="";
        String line = bufferedReader.readLine();
        while (!line.split(";")[0].equals("LOCATION") && !line.split(";")[0].equals("DESCRIPTION") && !line.equals("END:VEVENT")){
            allContent=allContent+line;
            line = bufferedReader.readLine();
        }
        if(line.equals("END:VEVENT")){
            return createHolidayEvent(bufferedReader,dateEvent,line);
        }
        else{
            return createModuleEvent(bufferedReader,dateEvent,allContent,line);
        }
    }

    /**
     * Create a Holiday Event
     * @param bufferedReader Where we need to read to get information
     * @param dateEvent Information of date for a specific Event
     * @param line Last line we read in the Icalendar file.
     * @return Return a Holiday Event
     * @throws IOException
     */
    private Event createHolidayEvent(BufferedReader bufferedReader,DateEvent dateEvent,String line)throws IOException {
        DescriptionEvent descriptionEvent = new DescriptionEvent();
        descriptionEvent.addDescription("TYPE",line.split(":")[1]);
        bufferedReader.readLine();
        return new Event(dateEvent, "","", descriptionEvent);
    }

    /**
     * Create a Module Event
     * @param bufferedReader Where we need to read to get information.
     * @param dateEvent Information of date for a specific Event.
     * @param allContent Information for the Summary , we will use to get all information for Location and Description.
     * @param line Last line we read in the Icalendar file.
     * @return Return a Module Event.
     * @throws IOException
     */
    private Event createModuleEvent(BufferedReader bufferedReader,DateEvent dateEvent,String allContent,String line) throws IOException {
        DescriptionEvent descriptionEvent = new DescriptionEvent();
        String summary = allContent.split(";")[1].split(":")[1];
        while (!line.split(";")[0].equals("DESCRIPTION")) {
            allContent = allContent + line;
            line = bufferedReader.readLine();
        }
        String location = allContent.split(";")[1].split(":")[1];
        allContent = "";
        while (!line.split(";")[0].equals("X-ALT-DESC")) {
            if (line.substring(0,1).equals(" "))
                line = line.substring(1);
            allContent = allContent + line;
            line = bufferedReader.readLine();
        }
        descriptionEvent.addDescription(allContent.split(";")[1].split(Pattern.quote("\\n"))[0].split(":")[1], allContent.split(";")[1].split(Pattern.quote("\\n"))[0].split(":")[2]);
        for (int i = 1; i < allContent.split(";")[1].split(Pattern.quote("\\n")).length; i++) {
            String key = allContent.split(";")[1].split(Pattern.quote("\\n"))[i].split(":")[0];
            String value = allContent.split(";")[1].split(Pattern.quote("\\n"))[i].split(":")[1];
            if(value.indexOf("=")!=-1){
                if(value.charAt(value.indexOf("=")-1)!=' '){
                    value = value.substring(0,value.indexOf("="))+" "+value.substring(value.indexOf("="));
                }
                if(value.charAt(value.indexOf("=")+1)!=' '){
                    value = value.substring(0,value.indexOf("=")+1)+" "+value.substring(value.indexOf("=")+1);
                }
            }
            descriptionEvent.addDescription(key, value);
        }
        while (!bufferedReader.readLine().equals("BEGIN:VEVENT")) {}
        return new Event(dateEvent, summary, location, descriptionEvent);
    }
}
