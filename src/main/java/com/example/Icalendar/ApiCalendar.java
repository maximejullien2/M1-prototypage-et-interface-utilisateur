package com.example.Icalendar;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Represent Object we will use to stock data for the calendar
 */
public class ApiCalendar {
    /**
     * Variable who will stock all Event to display .
     */
    private ArrayList<Event> listApiCalendar;

    public String getFilepath() {
        return filepath;
    }

    private String filepath;

    ApiCalendar(String filepath){
        this.listApiCalendar = new ArrayList<Event>();
        this.filepath = filepath;
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

    public ArrayList<ArrayList<ArrayList<Event>>> getEventDay(LocalDateTime dateTime, HashMap<String,String> filtresList){
        ArrayList<Event> output = new ArrayList<Event>();
        for(int i = 0 ; i< this.listApiCalendar.size(); i++){
            if (this.listApiCalendar.get(i).getDateEvent().getStartDate().getDayOfYear() == dateTime.getDayOfYear() && this.listApiCalendar.get(i).getDateEvent().getStartDate().getYear() == dateTime.getYear()){
                output.add(this.listApiCalendar.get(i));
            }
        }
        if (filtresList!=null) {
            for (int i = 0; i < filtresList.size(); i++) {
                ArrayList<Event> output2 = new ArrayList<Event>();
                for (int j = 0; j < output.size(); j++) {
                    String filtreValue = (String) filtresList.keySet().toArray()[i];
                    if (output.get(j).getDescriptionEvent().getDescription(filtreValue) != null) {
                        for (int q = 0 ; q<output.get(j).getDescriptionEvent().getDescription(filtreValue).split(Pattern.quote("\\,")).length;q++) {
                            if (output.get(j).getDescriptionEvent().getDescription(filtreValue).split(Pattern.quote("\\,"))[q].replaceAll(" ", "")
                                    .toLowerCase().contains(filtresList.get(filtreValue).replaceAll(" ", "").toLowerCase())) {
                                output2.add(output.get(j));
                                break;
                            }
                        }
                    }
                }
                output = output2;
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
                if (j != tester && (output.get(j).getDateEvent().getEndDate().isAfter(output.get(tester).getDateEvent().getEndDate()) ||
                        (output.get(j).getDateEvent().getEndDate().isEqual(output.get(tester).getDateEvent().getEndDate()) && nombreColonne == nbColonneinit)  ||
                        (output.get(j).getDateEvent().getStartDate().isEqual(output.get(tester).getDateEvent().getStartDate()) ))){
                    if (output.get(j).getDateEvent().getEndDate().isAfter(output.get(tester).getDateEvent().getEndDate())) {
                        tester = j;
                    }
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
                            position = position + nb+1;
                            j = j - 1;
                        }
                        j = j + 1;
                    }
                    if (!colonne.isEmpty()) {
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

    public ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> getEventWeek(LocalDateTime dateTime, HashMap<String,String> filtresList){
        ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> arrayList = new ArrayList<ArrayList<ArrayList<ArrayList<Event>>>>();
        while (dateTime.getDayOfWeek().getValue()>1){
            dateTime = dateTime.minusDays(1);
        }
        for (int i = 0 ; i < 5 ; i++){
            arrayList.add(getEventDay(dateTime,filtresList));
            dateTime = dateTime.plusDays(1);
        }
        return arrayList;
    }

    public ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> getEventMounth(LocalDateTime dateTime, HashMap<String,String> filtresList){
        ArrayList<ArrayList<ArrayList<ArrayList<Event>>>> arrayList = new ArrayList<ArrayList<ArrayList<ArrayList<Event>>>>();
        int month = dateTime.getMonth().getValue();
        if (dateTime.getDayOfMonth()>1){
            dateTime = dateTime.minusDays(dateTime.getDayOfMonth()-1);
        }
        if (dateTime.getDayOfWeek().getValue()==6 || dateTime.getDayOfWeek().getValue()==7){
            dateTime = dateTime.plusDays(8-dateTime.getDayOfWeek().getValue());
        }
        if (dateTime.getDayOfWeek().getValue()>1){
            dateTime = dateTime.minusDays(dateTime.getDayOfWeek().getValue()-1);
        }
        boolean conditionWhile ;
        if (month==12){
            conditionWhile = dateTime.getMonth().getValue()!=1;
        }
        else{
            if (month!=1)
                conditionWhile = dateTime.getMonth().getValue()-month<=0;
            else
                conditionWhile = dateTime.getMonth().getValue() != 2;
        }
        while (conditionWhile) {
            for (int i = 0; i < 5; i++) {
                arrayList.add(getEventDay(dateTime,filtresList));
                dateTime = dateTime.plusDays(1);
            }
            dateTime = dateTime.plusDays(2);
            if (month==12){
                conditionWhile = dateTime.getMonth().getValue()!=1;
            }
            else{
                if (month!=1)
                    conditionWhile = dateTime.getMonth().getValue()-month<=0;
                else
                    conditionWhile = dateTime.getMonth().getValue() != 2;
            }
        }
        return arrayList;
    }

    public LocalDateTime getDateMounth(LocalDateTime dateTime){
        if (dateTime.getDayOfMonth()>1){
            dateTime = dateTime.minusDays(dateTime.getDayOfMonth()-1);
        }
        if (dateTime.getDayOfWeek().getValue()==6 || dateTime.getDayOfWeek().getValue()==7){
            dateTime = dateTime.plusDays(8-dateTime.getDayOfWeek().getValue());
        }
        if (dateTime.getDayOfWeek().getValue()>1){
            dateTime = dateTime.minusDays(dateTime.getDayOfWeek().getValue()-1);
        }
        return dateTime;
    }

    public String getSalle(){
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i =0 ; i< listApiCalendar.size();i++){
            if (listApiCalendar.get(i).getDescriptionEvent().getDescription("Salle ")!=null){
                boolean ajouter = true;
                for (int j = 0 ; j<arrayList.size();j++){
                    if (Objects.equals(arrayList.get(j), listApiCalendar.get(i).getDescriptionEvent().getDescription("Salle "))){
                        ajouter = !ajouter;
                        break;
                    }
                }
                if (ajouter)
                    arrayList.add(listApiCalendar.get(i).getDescriptionEvent().getDescription("Salle "));
            }
        }
        System.out.println(arrayList);
        return arrayList.get(0);
    }

    public boolean getIfPossibleToAddEvent(LocalDateTime dateTimeDebut,LocalDateTime dateTimeFin){
        ArrayList<LocalDateTime> arrayList=new ArrayList<LocalDateTime>();
        arrayList.add(dateTimeDebut);
        while (dateTimeFin.isAfter(dateTimeDebut)){
            dateTimeDebut = dateTimeDebut.plusMinutes(30);
            arrayList.add(dateTimeDebut);
        }
        for (int i=0 ; i< this.listApiCalendar.size();i++){
            if (listApiCalendar.get(i).getDateEvent().getStartDate().getDayOfYear() == dateTimeDebut.getDayOfYear() &&
                    listApiCalendar.get(i).getDateEvent().getStartDate().getYear() == dateTimeDebut.getYear()){
                System.out.println(listApiCalendar.get(i).getDateEvent().getStartDate().toString());
                System.out.println(listApiCalendar.get(i).getDateEvent().getEndDate().toString());
                System.out.println("____________________________");
                for (int j = 0 ; j< arrayList.size();j++){
                    System.out.println(arrayList.get(j).toString());
                    if (listApiCalendar.get(i).getDateEvent().getStartDate().isEqual(arrayList.get(j)) ||
                            listApiCalendar.get(i).getDateEvent().getEndDate().isEqual(arrayList.get(j)) ||
                            (listApiCalendar.get(i).getDateEvent().getStartDate().isBefore(arrayList.get(j)) &&
                                    (listApiCalendar.get(i).getDateEvent().getEndDate().isEqual(arrayList.get(j)) ||
                                            listApiCalendar.get(i).getDateEvent().getEndDate().isAfter(arrayList.get(j))))){
                        return false;
                    }
                }
                System.out.println("(((((((((((((((((((((((((((((((((");
            }
        }
        return true;
    }
}
