module com.example.foxably {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.foxably to javafx.fxml;
    exports com.example.foxably;
}