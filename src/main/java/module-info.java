module com.example.convertor {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.convertor to javafx.fxml;
    exports com.example.convertor;
}