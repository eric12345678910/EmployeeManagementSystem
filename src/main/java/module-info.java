module com.example.assignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    //requires org.controlsfx.controls;

    exports application;
    opens application to javafx.fxml;
}