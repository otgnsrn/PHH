module com.example.appointment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.appointment to javafx.fxml;
    exports com.example.appointment;
}