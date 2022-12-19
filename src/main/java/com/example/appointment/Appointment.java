package com.example.appointment;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class Appointment {
    private Doctor doctor;
    private Patient patient ;
    private LocalDate date;
    private Time startTime;

    public Appointment(Date date, Time time, String s) {
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "doctor=" + doctor +
                ", patient=" + patient +
                ", date=" + date +
                ", startTime=" + startTime +
                '}';
    }

    public Appointment(Date date, Time startTime) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date.toLocalDate();
        this.startTime = startTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
}
