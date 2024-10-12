package com.example.rksp3.task1;



public class Task1 {
    public static void main(String[] args) {

        TemperatureSensor temperatureSensor = new TemperatureSensor();
        CO2Sensor co2Sensor = new CO2Sensor();
        AlarmSystem alarmSystem = new AlarmSystem();


        alarmSystem.monitor(temperatureSensor.getTemperatureStream(), co2Sensor.getCO2Stream());


        try {//чтоб не дропалось
            Thread.sleep(100000);  // Работает 10 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
