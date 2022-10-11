module com.wovi10.valutaconverter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.wovi10.valutaconverter to javafx.fxml;
    exports com.wovi10.valutaconverter;
    exports com.example;
    opens com.example to javafx.fxml;
    exports com.wovi10.valutaconverter.utils;
    opens com.wovi10.valutaconverter.utils to javafx.fxml;
}