package com.example.Icalendar;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Represent Object we will use to stock data for the calendar
 */
public class ApiCalendar {
    /**
     * Variable who will stock all Event to display .
     */
    private ArrayList<Event> listApiCalendar;

    ApiCalendar(){
        this.listApiCalendar = new ArrayList<Event>();
    }

    /**
     * Methods to add an event into the calendar
     * @param event Specific event to add
     */
    public void addEvent(Event event){
        for (int i =0 ; i<this.listApiCalendar.size();i++){
            if (this.listApiCalendar.get(i).getDateEvent().getStartDate().isEqual(event.getDateEvent().getStartDate()) &&
                    this.listApiCalendar.get(i).getDateEvent().getEndDate().isEqual(event.getDateEvent().getEndDate()) &&
                    Objects.equals(this.listApiCalendar.get(i).getLocation(), event.getLocation()) &&
                    Objects.equals(this.listApiCalendar.get(i).getSummary(), event.getSummary())){
                return;
            }
        }
        this.listApiCalendar.add(event);
    }

    public ArrayList<ArrayList<ArrayList<Event>>> getEventDay(LocalDateTime dateTime){
        ArrayList<Event> output = new ArrayList<Event>();
        for(int i = 0 ; i< this.listApiCalendar.size(); i++){
            if (this.listApiCalendar.get(i).getDateEvent().getStartDate().getDayOfYear() == dateTime.getDayOfYear()){
                output.add(this.listApiCalendar.get(i));
            }
        }
        LocalDateTime date = dateTime.withHour(8).withMinute(30).withSecond(0).withNano(0);
        DateEventComparator dateEventComparator = new DateEventComparator();
        Collections.sort(output,dateEventComparator);
        ArrayList<ArrayList<ArrayList<Event>>> trueOutput = new ArrayList<ArrayList<ArrayList<Event>>>();
        ArrayList<Event> listATrier = new ArrayList<Event>();
        for (int i =0 ; i<output.size();i++){
            while (output.get(i).getDateEvent().getStartDate().isAfter(date)){
                trueOutput.add(null);
                date=date.plusMinutes(30);
            }
            int j = i;
            int tester = i;
            int nombreColonne = 1;
            while (j < output.size()){
                System.out.println(output.get(tester).getDateEvent().getStartDate());
                System.out.println(output.get(tester).getDateEvent().getEndDate());
                System.out.println(output.get(j).getDateEvent().getStartDate());
                System.out.println(output.get(j).getDateEvent().getEndDate());
                System.out.println("{{{{}}}}}}}}");
                if (output.get(j).getDateEvent().getStartDate().isAfter(output.get(tester).getDateEvent().getEndDate()) || output.get(j).getDateEvent().getStartDate().isEqual(output.get(tester).getDateEvent().getEndDate())){
                    System.out.println("-----------------------------------------------");
                    break;
                }
                if (j != tester && (output.get(j).getDateEvent().getEndDate().isAfter(output.get(tester).getDateEvent().getEndDate()) ||  output.get(j).getDateEvent().getStartDate().isEqual(output.get(tester).getDateEvent().getStartDate()))){
                    tester = j;
                    nombreColonne = nombreColonne + 1;
                }
                listATrier.add(output.get(j));
                j=j+1;
            }
            System.out.println(nombreColonne);
            System.out.println("66666666666666666666666666666666666666");
            trueOutput.add(getManyEvent(listATrier,nombreColonne));
            i=j-1;
            date = output.get(tester).getDateEvent().getEndDate();
        }
        return trueOutput;
    }

    private ArrayList<ArrayList<Event>> getManyEvent(ArrayList<Event> arrayList ,int nombreCase){
        ArrayList<ArrayList<Event>> listArrayList = new ArrayList<ArrayList<Event>>();
        if (arrayList != null){
            LocalDateTime dateStar = arrayList.get(0).getDateEvent().getStartDate();
            for (int i =0 ; i< nombreCase ; i++){
                ArrayList<Event> colonne = new ArrayList<>();
                colonne.add(arrayList.get(0));
                arrayList.remove(0);
                int position = 0;
                int j = 0;
                while(j<arrayList.size()){
                    if (!colonne.get(position).getDateEvent().getEndDate().isAfter(arrayList.get(j).getDateEvent().getStartDate()) || colonne.get(position).getDateEvent().getEndDate().isEqual(arrayList.get(j).getDateEvent().getStartDate())){
                        int nb = 0;
                        while (nb < Duration.between(colonne.get(position).getDateEvent().getEndDate(),arrayList.get(j).getDateEvent().getStartDate()).toMinutes()/30){
                            colonne.add(null);
                            nb=nb+1;
                        }
                        colonne.add(arrayList.get(j));
                        arrayList.remove(j);
                        if (j != 0) {
                            position = position + 1;
                            j = j - 1;}
                    }
                    j=j+1;
                }
                if (colonne.size() != 0){
                int valeur = 0 ;
                while (dateStar.isBefore(colonne.get(valeur).getDateEvent().getStartDate())){
                    dateStar = dateStar.plusMinutes(30);
                    colonne.add(0,null);
                    valeur = valeur + 1 ;
                }
                }
                listArrayList.add(colonne);
            }
        }
        return listArrayList;
    }
}
