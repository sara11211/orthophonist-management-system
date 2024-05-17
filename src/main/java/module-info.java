module com {
    requires javafx.controls;
    requires javafx.fxml;

    opens com to javafx.fxml;
    opens com.models to javafx.base;
    exports com;
}
