package com.example.Icalendar;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        ParserIcalendar parserIcalendar = new ParserIcalendar();
        parserIcalendar.parse("src/main/resources/com/example/Icalendar/vide.ics");
    }
}
