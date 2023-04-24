package demo;

import component.Garden;

public class Demo {

    public static void main(String[] args) {
        Garden garden = new Garden(5, 5);
        garden.run();
        //garden.printWeatherInfo();
    }
}
