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
        LocalDateTime date = dateTime.withHour(8).withMinute(0).withSecond(0).withNano(0);
        DateEventComparator dateEventComparator = new DateEventComparator();
        Collections.sort(output,dateEventComparator);
        ArrayList<ArrayList<ArrayList<Event>>> trueOutput = new ArrayList<ArrayList<ArrayList<Event>>>();
        ArrayList<Event> listATrier = new ArrayList<Event>();
        for (int i =0 ; i<output.size();i++){
            if (output.get(i).getDateEvent().getStartDate().isBefore(date)){
                output.get(i).getDateEvent().setStartDate(date);
            }
            if (output.get(i).getDateEvent().getEndDate().isAfter(dateTime.withHour(19).withMinute(0).withSecond(0).withNano(0))){
                output.get(i).getDateEvent().setEndDate(dateTime.withHour(19).withMinute(0).withSecond(0).withNano(0));
            }
            while (output.get(i).getDateEvent().getStartDate().isAfter(date)){
                trueOutput.add(null);
                date=date.plusMinutes(30);
            }
            int j = i;
            int tester = i;
            int augmenter = 0;
            int nombreColonne = 1;
            int nbColonneinit = 1;
            while (j < output.size()){
                if (output.get(j).getDateEvent().getStartDate().isAfter(output.get(tester).getDateEvent().getEndDate()) || output.get(j).getDateEvent().getStartDate().isEqual(output.get(tester).getDateEvent().getEndDate())){
                    break;
                }
                if (j != tester && (output.get(j).getDateEvent().getEndDate().isAfter(output.get(tester).getDateEvent().getEndDate()) || (output.get(j).getDateEvent().getEndDate().isEqual(output.get(tester).getDateEvent().getEndDate()) && nombreColonne == nbColonneinit)||output.get(j).getDateEvent().getStartDate().isEqual(output.get(tester).getDateEvent().getStartDate()))){
                    tester = j;
                    nombreColonne = nombreColonne + 1;
                    augmenter = 0;
                    nbColonneinit = nombreColonne;
                } else if (j != tester && augmenter == 0 && output.get(j).getDateEvent().getEndDate().isBefore(output.get(tester).getDateEvent().getEndDate()) && output.get(j).getDateEvent().getStartDate().isAfter(output.get(tester).getDateEvent().getStartDate())) {
                    nombreColonne = nombreColonne + 1;
                    augmenter = 1;
                }
                listATrier.add(output.get(j));
                j=j+1;
            }
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
            for (int i =0 ; i< nombreCase ; i++) {
                if (!arrayList.isEmpty()) {
                    ArrayList<Event> colonne = new ArrayList<>();
                    colonne.add(arrayList.get(0));
                    arrayList.remove(0);
                    int position = 0;
                    int j = 0;
                    while (j < arrayList.size()) {
                        if (!colonne.get(position).getDateEvent().getEndDate().isAfter(arrayList.get(j).getDateEvent().getStartDate()) || colonne.get(position).getDateEvent().getEndDate().isEqual(arrayList.get(j).getDateEvent().getStartDate())) {
                            int nb = 0;
                            while (nb < Duration.between(colonne.get(position).getDateEvent().getEndDate(), arrayList.get(j).getDateEvent().getStartDate()).toMinutes() / 30) {
                                colonne.add(null);
                                nb = nb + 1;
                            }
                            colonne.add(arrayList.get(j));
                            arrayList.remove(j);
                            position = position + 1;
                            j = j - 1;
                        }
                        j = j + 1;
                    }
                    if (colonne.size() != 0) {
                        int valeur = 0;
                        while (dateStar.isBefore(colonne.get(valeur).getDateEvent().getStartDate())) {
                            dateStar = dateStar.plusMinutes(30);
                            colonne.add(0, null);
                            valeur = valeur + 1;
                        }
                    }
                    listArrayList.add(colonne);
                }
            }
        }
        return listArrayList;
    }

    public ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> getEventWeek(LocalDateTime dateTime){
        ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> arrayList = new ArrayList<ArrayList<ArrayList<ArrayList<Event>>>>();
        while (dateTime.getDayOfWeek().getValue()>1){
            dateTime = dateTime.minusDays(1);
        }
        for (int i = 0 ; i < 5 ; i++){
            arrayList.add(getEventDay(dateTime));
            dateTime = dateTime.plusDays(1);
        }
        return arrayList;
    }

    public ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> getEventMounth(LocalDateTime dateTime){
        ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> arrayList = new ArrayList<ArrayList<ArrayList<ArrayList<Event>>>>();
        int month = dateTime.getMonth().getValue();
        if (dateTime.getDayOfMonth()>1){
            dateTime = dateTime.minusDays(dateTime.getDayOfMonth()-1);
        }
        if (dateTime.getDayOfWeek().getValue()>1){
            dateTime = dateTime.minusDays(dateTime.getDayOfWeek().getValue()-1);
        }
        while (month>=dateTime.getMonth().getValue()) {
            for (int i = 0; i < 5; i++) {
                arrayList.add(getEventDay(dateTime));
                dateTime = dateTime.plusDays(1);
            }
            dateTime = dateTime.plusDays(2);
        }
        return arrayList;
    }

    public LocalDateTime getDateMounth(LocalDateTime dateTime){
        if (dateTime.getDayOfMonth()>1){
            dateTime = dateTime.minusDays(dateTime.getDayOfMonth()-1);
        }
        if (dateTime.getDayOfWeek().getValue()>1){
            dateTime = dateTime.minusDays(dateTime.getDayOfWeek().getValue()-1);
        }
        return dateTime;
    }
}
