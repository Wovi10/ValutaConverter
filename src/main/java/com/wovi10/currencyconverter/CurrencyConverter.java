package com.wovi10.currencyconverter;

import com.wovi10.currencyconverter.utils.CurrencyConstants;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;

import static com.wovi10.currencyconverter.utils.CurrencyConstants.*;

public class CurrencyConverter extends Application {
    public Double input_int;
    public Double output_int;
    public TextField inputValuta;
    public TextField outputValuta;
    ComboBox<Object> valutaFrom_CB;
    ComboBox<Object> valutaTo_CB;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(PROGRAM_TITLE);
        Group root = new Group();
        Scene scene = new Scene(root, PROGRAM_WIDTH, PROGRAM_HEIGHT);
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
        addToForm(root, convertButton);
    }

    //region Buttons
    private Button createConvertButton() {
        Button convertButton = new Button();
        createDefaultButton(convertButton);
        convertButton.setOnAction( actionEvent ->
                convertInput()
        );
        return convertButton;
    }
    private void createDefaultButton(Button button){
        button.setLayoutX(STANDARD_INDENT + TEXTFIELD_WIDTH);
        button.setLayoutY(STANDARD_HEIGHT + 60);
        button.setText(CONVERT_TEXT);
    }
    private void convertInput() {
        setInput();
        calculateConversion();
        setOutput();
    }
    private void setInput() {
        input_int = Double.parseDouble(inputValuta.getText());
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
        double output;
        Double exchangeValue = getExchangeValue(nameCurrencyInput, nameCurrencyOutput);
        output = amount * exchangeValue;
        return output;
    }
    private Double getExchangeValue(String abbreviationInput, String abbreviationOutput){
        Double output = DEFAULT_EXCHANGEVALUE;
        for (Currency currency : Currencies) {
            if (abbreviationInput.equals(currency.getAbbreviation())){
                HashMap<String, Double> exchangeValues = currency.getExchangeValues();
                output = exchangeValues.get(abbreviationOutput);
            }
        }
        return output;
    }
    private void setOutput() {
        String formatted_output = FORMAT.format(output_int);
        outputValuta.setText(formatted_output);
    }
    //endregion

    //region ComboBoxes
    private ComboBox<Object> createFirstComboBox() {
        ComboBox<Object> comboBox = createComboBox(STANDARD_HEIGHT);
        return fillComboBox(comboBox);
    }
    private ComboBox<Object> createSecondComboBox() {
        ComboBox<Object> comboBox = createComboBox(STANDARD_HEIGHT + 30);
        return fillComboBox(comboBox);
    }
    private ComboBox<Object> createComboBox(int layoutY) {
        ComboBox<Object> valutaComboBox = new ComboBox<>();
        placeComboBoxOnPane(valutaComboBox, layoutY);
        return valutaComboBox;
    }
    private void placeComboBoxOnPane(ComboBox<Object> comboBox , int layoutY){
        comboBox.setLayoutX(STANDARD_INDENT + TEXTFIELD_WIDTH);
        comboBox.setLayoutY(layoutY);
        comboBox.setValue(CHOOSE_A_VALUTA);
    }
    private ComboBox<Object> fillComboBox(ComboBox<Object> comboBox){
        for (Currency currency : Currencies) {
            comboBox.getItems().add(currency.getName());
        }
        return comboBox;
    }
    //endregion

    //region TextFields
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
        defaultTextField.setPrefWidth(TEXTFIELD_WIDTH);
        return defaultTextField;
    }
    //endregion

    private void addToForm(Group root, Button convertButton) {
        root.getChildren().add(convertButton);
        root.getChildren().add(inputValuta);
        root.getChildren().add(valutaFrom_CB);
        root.getChildren().add(outputValuta);
        root.getChildren().add(valutaTo_CB);
    }
}
