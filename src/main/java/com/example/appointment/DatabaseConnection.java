package com.example.appointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseConnection {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/appointment", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ObservableList<Appointment> getTimeList(){
        ObservableList<Appointment> TimeList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM appointment ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Appointment appointments;
            while(rs.next()) {
                appointments = new Appointment(rs.getDate(1), rs.getTime(2));
                TimeList.add(appointments);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TimeList;
    }

    public static void executeQuery(String query) {
        Connection con = getConnection();
        Statement st;
        try {
            assert con != null;
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


