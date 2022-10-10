package com.wovi10.valutaconverter;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ValutaConverter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Valuta Converter");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
        setButtons(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setButtons(Group root) {
        Button convertButton = createConvertButton();
        root.getChildren().add(convertButton);
        ComboBox valutaFrom_CB = createFirstComboBox();
    }

    private ComboBox createFirstComboBox() {
        return null;
    }

    private Button createConvertButton() {
        Button convertButton = new Button();
        convertButton.setLayoutX(100);
        convertButton.setLayoutY(80);
        convertButton.setText("Convert");
        convertButton.setOnAction( actionEvent ->
                System.out.println("Converted"));
        return convertButton;
    }
}
