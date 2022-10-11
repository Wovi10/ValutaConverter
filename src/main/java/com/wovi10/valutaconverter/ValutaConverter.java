package com.wovi10.valutaconverter;

import com.wovi10.valutaconverter.utils.ValutaConstants;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ValutaConverter extends Application {
    ArrayList<Valuta> Currencies = initiateCurrencies();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(ValutaConstants.PROGRAM_TITLE);
        Group root = new Group();
        Scene scene = new Scene(root, ValutaConstants.PROGRAM_WIDTH, ValutaConstants.PROGRAM_HEIGHT);
        setupForm(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupForm(Group root) {
        Button convertButton = createConvertButton();
        ComboBox<Object> valutaFrom_CB = createFirstComboBox();
        ComboBox<Object> valutaTo_CB = createSecondComboBox();
        fillComboBox(valutaFrom_CB);
        fillComboBox(valutaTo_CB);
        root.getChildren().add(convertButton);
        root.getChildren().add(valutaFrom_CB);
        root.getChildren().add(valutaTo_CB);
    }

    private void fillComboBox(ComboBox<Object> comboBox){
        for (Valuta valuta: Currencies) {
            comboBox.getItems().add(valuta.getName());
        }

    }
    private ComboBox<Object> createSecondComboBox() {
        ComboBox<Object> valutaComboBox = new ComboBox<>();
        valutaComboBox.setLayoutX(50);
        valutaComboBox.setLayoutY(80);
        valutaComboBox.setValue("Choose a valuta");
        return valutaComboBox;
    }

    private ComboBox<Object> createFirstComboBox() {
        ComboBox<Object> valutaComboBox = new ComboBox<>();
        valutaComboBox.setLayoutX(50);
        valutaComboBox.setLayoutY(50);
        valutaComboBox.setValue("Choose a valuta");
        return valutaComboBox;
    }

    private Button createConvertButton() {
        Button convertButton = new Button();
        convertButton.setLayoutX(50);
        convertButton.setLayoutY(110);
        convertButton.setText("Convert");
        convertButton.setOnAction( actionEvent ->
                System.out.println("Converted"));
        return convertButton;
    }

    private ArrayList<Valuta> initiateCurrencies() {
        ArrayList<Valuta> currencies = new ArrayList<>();
        currencies.add(new Valuta("Euro", "EUR"));
        currencies.add(new Valuta("Australian Dollar", "AUD"));
        currencies.add(new Valuta("US Dollar", "USD"));
        for (Valuta valuta: currencies) {
            valuta.setDefaults();
        }
        return currencies;
    }

    private void placeObjectOnPane(Object objectToPlace ,int layoutX, int layoutY, String textToShow){

    }
}
