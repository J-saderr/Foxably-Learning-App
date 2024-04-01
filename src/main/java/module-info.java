module com.example.foxably {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.foxably to javafx.fxml;
    exports com.example.foxably;
    exports com.example.foxably.Login;
    opens com.example.foxably.Login to javafx.fxml;
    exports com.example.foxably.Controller;
    opens com.example.foxably.Controller to javafx.fxml;
}