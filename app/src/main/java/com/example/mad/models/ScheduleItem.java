package com.example.mad.models;

public class ScheduleItem {
    private int id;
    private String scheduleId;
    private String startCity;
    private String endCity;
    private String time;
    private String trainName;

    public ScheduleItem(int id, String scheduleId, String startCity, String endCity, String time, String trainName) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.startCity = startCity;
        this.endCity = endCity;
        this.time = time;
        this.trainName = trainName;
    }

    // Add getters and setters here


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
}
