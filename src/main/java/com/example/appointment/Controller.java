package com.example.appointment;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private DatePicker date;

    @FXML
    private ComboBox<String> doctorName;

    private final String[] doctorNameList = {"Д.Гаравсүрэн"};

    @FXML
    private TableView<Appointment> timetable;

    @FXML
    private TableColumn<Appointment, LocalDate> DateColumn;
    @FXML
    private TableColumn<Appointment, LocalTime> startTimeColumn;

    @FXML
    void accept() {
        ObservableList<Appointment> saveAppointment = FXCollections.observableArrayList();
        saveAppointment = timetable.getSelectionModel().getSelectedItems();
        timetable.getItems().removeAll(saveAppointment);
        System.out.println(saveAppointment);
    }

    @FXML
    void cancel() {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doctorName.getItems().addAll(doctorNameList);
        showTime();

    }

    @FXML
    void ShowTimeList(){
        String query = "SELECT * FROM appointment WHERE 'date' = "+date.getValue()+" ";
        DatabaseConnection.executeQuery(query);
        showTime();
    }

    public void showTime() {
        ObservableList<Appointment> list = DatabaseConnection.getTimeList();

        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        timetable.setItems(list);
    }
}
