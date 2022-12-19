package com.example.appointment;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;

public class DoctorViewController implements Initializable {

    @FXML
    private DatePicker doctorDate;

    @FXML
    private ComboBox doctorName;

    @FXML
    private TextField doctorTime;

    @FXML
    private TableView<Appointment> tableview;

    @FXML
    private TableColumn<Appointment, String> DoctorName;
    @FXML
    private TableColumn<Appointment, Date> DoctorDateColumn;

    @FXML
    private TableColumn<Appointment, String> DoctorTimeColumn;

    @FXML
    void cancel() {
        Platform.exit();
    }

    @FXML
    void delete() {
        tableview.getItems().removeAll(tableview.getSelectionModel().getSelectedItem());

    }

    @FXML
    void insert() {
        String query = "insert into appointment1 values('"+doctorDate.getValue()+"','"+doctorTime.getText()+"')";
        DatabaseConnection.executeQuery(query);
        showTimeTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTimeTable();

//        DoctorTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        DoctorTimeColumn.setOnEditCommit(Event -> {
//            TablePosition<Appointment, String> pos = Event.getTablePosition();
//            String newStartTime = Event.getNewValue();
//            int row = pos.getRow();
//            Appointment appointment = Event.getTableView().getItems().get(row);
//            appointment.setStartTime(Time.valueOf(newStartTime));
//        });
    }

    private void showTimeTable() {
        ObservableList<Appointment> list = DatabaseConnection.getTimeList();

        DoctorName.setCellValueFactory(new PropertyValueFactory<>("doctor   "));
        DoctorDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        DoctorTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        tableview.setItems(list);
    }
}
