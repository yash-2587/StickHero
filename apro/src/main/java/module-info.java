module com.example.apro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.apro to javafx.fxml;
    exports com.example.apro;
}