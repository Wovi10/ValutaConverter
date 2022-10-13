package com.wovi10.valutaconverter;

import com.wovi10.valutaconverter.utils.ValutaConstants;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ValutaConverter extends Application {
    public static final int STANDARD_INDENT = 50;
    public static final int STANDARD_HEIGHT = 50;
    public int input_int;
    public int output_int;
    public TextField inputValuta;
    public TextField outputValuta;
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
        for (Valuta valuta: Currencies) {
            comboBox.getItems().add(valuta.getName());
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
        setVariables();
        calculateConversion();
    }

    private void calculateConversion() {
        
    }

    private void setVariables() {
        input_int = Integer.parseInt(inputValuta.getAccessibleText());
        output_int = Integer.parseInt(outputValuta.getAccessibleText());
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

    private void placeComboBoxOnPane(ComboBox<Object> comboBox , int layoutY, String displayText){
        comboBox.setLayoutX(ValutaConverter.STANDARD_INDENT + 50);
        comboBox.setLayoutY(layoutY);
        comboBox.setValue(displayText);
    }

    private void createDefaultButton(Button button , int layoutY, String displayText){
        button.setLayoutX(ValutaConverter.STANDARD_INDENT + 50);
        button.setLayoutY(layoutY);
        button.setText(displayText);
    }
}
