// ReservationItem.java
package com.example.mad.models;

public class ReservationItem {
    private int id;
    private String reservationId;
    private String startCity;
    private String endCity;
    private String time;
    private String trainName;

    public ReservationItem(int id, String reservationId, String startCity, String endCity, String time, String trainName) {
        this.id = id;
        this.reservationId = reservationId;
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

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
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
