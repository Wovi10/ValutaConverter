module com.example.valutaconverter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.valutaconverter to javafx.fxml;
    exports com.example.valutaconverter;
}