module com.wovi10.valutaconverter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.wovi10.currencyconverter to javafx.fxml;
    exports com.wovi10.currencyconverter;
    exports com.example;
    opens com.example to javafx.fxml;
    exports com.wovi10.currencyconverter.utils;
    opens com.wovi10.currencyconverter.utils to javafx.fxml;
}