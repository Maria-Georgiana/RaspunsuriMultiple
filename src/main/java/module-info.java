module com.example.raspunsurimultiple {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.xerial.sqlitejdbc;

    opens com.example.raspunsurimultiple.Domain;
    opens com.example.raspunsurimultiple to javafx.fxml;
    exports com.example.raspunsurimultiple;
}