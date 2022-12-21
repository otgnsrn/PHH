package com.example.appointment;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableColumn<Appointment, LocalDate> DateColumn;

    @FXML
    private TableColumn<Appointment, LocalDate> DoctorDateColumn;

    @FXML
    private TableColumn<Appointment, String> DoctorName;

    @FXML
    private TableColumn<Appointment, LocalTime> DoctorTimeColumn;

    @FXML
    private DatePicker date;

    @FXML
    private DatePicker doctorDate;

    @FXML
    private ComboBox<String> doctorName;

    private final String[] doctorNameList = {"Д.Гаравсүрэн"};

    @FXML
    private TextField doctorTime;

    @FXML
    private TableColumn<Appointment, LocalTime> startTimeColumn;

    @FXML
    private TableView<Appointment> tableview;

    @FXML
    private TableView<Appointment> timetable;

    @FXML
    void ShowTimeList(ActionEvent event) {
        String query = "SELECT * FROM appointment WHERE 'date' = "+date.getValue()+" ";
        DatabaseConnection.executeQuery(query);
        showTime();
    }

    private void showTime() {
        ObservableList<Appointment> list = DatabaseConnection.getTimeList();

        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        timetable.setItems(list);
    }

    @FXML
    void accept(ActionEvent event) {
        ObservableList<Appointment> saveAppointment = FXCollections.observableArrayList();
        saveAppointment = timetable.getSelectionModel().getSelectedItems();
        timetable.getItems().removeAll(saveAppointment);
        System.out.println(saveAppointment);
    }

    @FXML
    void cancel(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void delete(ActionEvent event) {
        tableview.getItems().removeAll(tableview.getSelectionModel().getSelectedItem());
    }

    @FXML
    void insert(ActionEvent event) {
        String query = "insert into appointment values('"+doctorDate.getValue()+"','"+doctorTime.getText()+"')";
        DatabaseConnection.executeQuery(query);
        showTimeTable();
    }

    private void showTimeTable() {
        ObservableList<Appointment> list = DatabaseConnection.getTimeList();

//        DoctorName.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        DoctorDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        DoctorTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        tableview.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doctorName.getItems().addAll(doctorNameList);
        showTime();
        showTimeTable();
    }
}
