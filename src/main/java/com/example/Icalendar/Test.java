package com.example.Icalendar;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        ParserIcalendar parserIcalendar = new ParserIcalendar("src/main/resources/com/example/Icalendar/test.ics");
        parserIcalendar.parseToJson();
    }
}
