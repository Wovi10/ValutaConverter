package com.wovi10.currencyconverter;

import com.wovi10.currencyconverter.utils.CurrencyConstants;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class CurrencyConverter extends Application {
    public static final int STANDARD_INDENT = 50;
    public static final int STANDARD_HEIGHT = 50;
    public Double input_int;
    public Double output_int;
    public TextField inputValuta;
    public TextField outputValuta;
    ComboBox<Object> valutaFrom_CB;
    ComboBox<Object> valutaTo_CB;
    ArrayList<Currency> Currencies = Currency.initiateCurrencies();
    Currency defaultCurrency = new Currency("default", "default");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(CurrencyConstants.PROGRAM_TITLE);
        Group root = new Group();
        Scene scene = new Scene(root, CurrencyConstants.PROGRAM_WIDTH, CurrencyConstants.PROGRAM_HEIGHT);
        setupForm(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupForm(Group root) {
        Button convertButton = createConvertButton();
        valutaFrom_CB = createFirstComboBox();
        valutaTo_CB = createSecondComboBox();
        inputValuta = createInputField();
        outputValuta = createOutputField();
        fillComboBox(valutaFrom_CB);
        fillComboBox(valutaTo_CB);
        root.getChildren().add(convertButton);
        root.getChildren().add(inputValuta);
        root.getChildren().add(valutaFrom_CB);
        root.getChildren().add(outputValuta);
        root.getChildren().add(valutaTo_CB);
    }

    private TextField createInputField() {
        return createDefaultTextField(STANDARD_HEIGHT);
    }

    private TextField createOutputField() {
        TextField outputTextField = createDefaultTextField(STANDARD_HEIGHT + 30);
        outputTextField.setEditable(false);
        return outputTextField;
    }

    private TextField createDefaultTextField(int layoutY) {
        TextField defaultTextField = new TextField();
        defaultTextField.setLayoutX(STANDARD_INDENT);
        defaultTextField.setLayoutY(layoutY);
        defaultTextField.setPrefWidth(50);
        return defaultTextField;
    }

    private void fillComboBox(ComboBox<Object> comboBox){
        for (Currency currency : Currencies) {
            comboBox.getItems().add(currency.getName());
        }
    }

    private ComboBox<Object> createFirstComboBox() {
        return createComboBox(STANDARD_HEIGHT);
    }

    private ComboBox<Object> createSecondComboBox() {
        return createComboBox(STANDARD_HEIGHT + 30);
    }

    private ComboBox<Object> createComboBox(int layoutY) {
        ComboBox<Object> valutaComboBox = new ComboBox<>();
        placeComboBoxOnPane(valutaComboBox, layoutY, "Choose a valuta");
        return valutaComboBox;
    }

    private Button createConvertButton() {
        Button convertButton = new Button();
        createDefaultButton(convertButton, STANDARD_HEIGHT + 60,"Convert");
        convertButton.setOnAction( actionEvent ->
                convertInput()
        );
        return convertButton;
    }

    private void convertInput() {
        setInput();
        calculateConversion();
        setOutput();
    }

    private void setOutput() {
        String formatted_output = CurrencyConstants.FORMAT.format(output_int);
        outputValuta.setText(formatted_output);
    }

    private void calculateConversion() {
        Currency currencyInput = defaultCurrency;
        for (Currency currency : Currencies) {
            if (currency.getName().equals(valutaFrom_CB.getValue())){
                currencyInput = currency;
            }
        }
        Currency currencyOutput = defaultCurrency;
        for (Currency currency : Currencies) {
            if (currency.getName().equals(valutaTo_CB.getValue())){
                currencyOutput = currency;
            }
        }
        String nameCurrencyInput = currencyInput.getAbbreviation();
        String nameCurrencyOutput = currencyOutput.getAbbreviation();
        output_int = convert(nameCurrencyInput, nameCurrencyOutput, input_int);
    }

    private Double convert(String nameCurrencyInput, String nameCurrencyOutput, Double amount) {
        Double output;
        Double exchangeValue = getExchangeValue(nameCurrencyInput, nameCurrencyOutput);
        output = amount * exchangeValue;
        return output;
    }

    private Double getExchangeValue(String abbreviationInput, String abbreviationOutput){
        Double output = CurrencyConstants.DEFAULT_EXCHANGEVALUE;
        for (Currency currency : Currencies) {
            if (abbreviationInput.equals(currency.getAbbreviation())){
                HashMap<String, Double> exchangeValues = currency.getExchangeValues();
                System.out.println(exchangeValues);
                System.out.println(abbreviationOutput);
                output = exchangeValues.get(abbreviationOutput);
                System.out.println(output);
            }
        }
        return output;
    }

    private void setInput() {
        input_int = Double.parseDouble(inputValuta.getText());
    }

    private void placeComboBoxOnPane(ComboBox<Object> comboBox , int layoutY, String displayText){
        comboBox.setLayoutX(CurrencyConverter.STANDARD_INDENT + 50);
        comboBox.setLayoutY(layoutY);
        comboBox.setValue(displayText);
    }

    private void createDefaultButton(Button button , int layoutY, String displayText){
        button.setLayoutX(CurrencyConverter.STANDARD_INDENT + 50);
        button.setLayoutY(layoutY);
        button.setText(displayText);
    }
}