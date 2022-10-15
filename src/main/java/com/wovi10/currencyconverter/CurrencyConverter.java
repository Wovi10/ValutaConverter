package com.wovi10.currencyconverter;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;

import static com.wovi10.currencyconverter.utils.ProgramConstants.*;

public class CurrencyConverter extends Application {
    public Double input_double;
    public Double output_double;
    public TextField input_TF;
    public TextField output_TF;
    ComboBox<Object> valutaFrom_CB;
    ComboBox<Object> valutaTo_CB;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, PROGRAM_WIDTH, PROGRAM_HEIGHT);
        setupForm(root);
        primaryStage.setTitle(PROGRAM_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupForm(Group root) {
        Button convert_Btn = createConvertButton();
        valutaFrom_CB = createFirstComboBox();
        valutaTo_CB = createSecondComboBox();
        input_TF = createInputField();
        output_TF = createOutputField();
        addToForm(root, convert_Btn);
    }

    //region Buttons
    private Button createConvertButton() {
        Button convert_Btn = new Button();
        placeButtonOnPane(convert_Btn);
        convert_Btn.setOnAction(actionEvent ->
                convertInput()
        );
        return convert_Btn;
    }

    private void placeButtonOnPane(Button button) {
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
        input_double = Double.parseDouble(input_TF.getText());
    }

    private void calculateConversion() {
        Currency input_Currency = DEFAULT_CURRENCY;
        for (Currency currency : CURRENCIES) {
            if (currency.getName().equals(valutaFrom_CB.getValue())) {
                input_Currency = currency;
            }
        }
        Currency output_Currency = DEFAULT_CURRENCY;
        for (Currency currency : CURRENCIES) {
            if (currency.getName().equals(valutaTo_CB.getValue())) {
                output_Currency = currency;
            }
        }
        String input_CurrAbbr = input_Currency.getAbbreviation();
        String output_CurrAbbr = output_Currency.getAbbreviation();
        output_double = convert(input_CurrAbbr, output_CurrAbbr, input_double);
    }

    private Double convert(String input_CurrAbbr, String output_CurrAbbr, Double amount_Double) {
        Double exchangeValue = getExchangeValue(input_CurrAbbr, output_CurrAbbr);
        return Currency.convert(amount_Double,exchangeValue);
    }

    private Double getExchangeValue(String input_CurrAbbr, String output_CurrAbbr) {
        return Currency.getExchangeValue(input_CurrAbbr, output_CurrAbbr);
    }

    private void setOutput() {
        output_TF.setText(Currency.formatAmount(output_double));
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
        ComboBox<Object> comboBox = new ComboBox<>();
        placeComboBoxOnPane(comboBox, layoutY);
        return comboBox;
    }

    private void placeComboBoxOnPane(ComboBox<Object> comboBox, int layoutY) {
        comboBox.setLayoutX(STANDARD_INDENT + TEXTFIELD_WIDTH);
        comboBox.setLayoutY(layoutY);
        comboBox.setValue(CHOOSE_A_VALUTA);
    }

    private ComboBox<Object> fillComboBox(ComboBox<Object> comboBox) {
        for (Currency currency : CURRENCIES) {
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
        TextField textField = createDefaultTextField(STANDARD_HEIGHT + 30);
        textField.setEditable(false);
        return textField;
    }

    private TextField createDefaultTextField(int layoutY) {
        TextField defaultTextField = new TextField();
        placeTextFieldOnPane(layoutY, defaultTextField);
        return defaultTextField;
    }

    private static void placeTextFieldOnPane(int layoutY, TextField defaultTextField) {
        defaultTextField.setLayoutX(STANDARD_INDENT);
        defaultTextField.setLayoutY(layoutY);
        defaultTextField.setPrefWidth(TEXTFIELD_WIDTH);
    }
    //endregion

    private void addToForm(Group root, Button convertButton) {
        root.getChildren().add(convertButton);
        root.getChildren().add(input_TF);
        root.getChildren().add(valutaFrom_CB);
        root.getChildren().add(output_TF);
        root.getChildren().add(valutaTo_CB);
    }
}
