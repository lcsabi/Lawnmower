package demo;

import component.Garden;

// document with JavaDoc
// add stopwatch -> Timer, TimerTask
// simulate time: different for moving and cutting (based on grass length)
// add logging to console as well as to a text file -> SLF4J API -> src/resources/logback.xml
// make into JavaFX

public class Demo {

    public static void main(String[] args) {
        Garden garden = new Garden(5, 5);
        garden.run();
    }
}
