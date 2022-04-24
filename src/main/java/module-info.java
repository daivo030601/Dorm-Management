module com.mycompany.dormmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.dormmanagement to javafx.fxml;
    exports com.mycompany.dormmanagement;
}
