package com.Fritz;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class Controller {

    @FXML
    public GridPane gridPane;

    @FXML
    public Button exitButton, minimizeButton;

    @FXML
    private Label resultLabel;

    @FXML
    private static Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;

    @FXML
    private Button buttonPlus, buttonMinus, buttonMultiply, buttonDivide;

    @FXML
    private Button buttonAC;

    @FXML
    private Button buttonSign, buttonPercent;

    @FXML
    private Button buttonPoint;

    @FXML
    private Button buttonEqual;

    private double number1;
    private double number2;
    private double result;

    private boolean isFirstNumber;
    private boolean isSecondNumber;
    private String operationSymbol;
    private int operationSymbolPressed = 0;
    private int aux = 0;

    public void handleExitButton() {
            Platform.exit();
    }

    public void handleMinimizeButton(ActionEvent event) {
            ((Stage)((Button)event.getSource()).getScene().getWindow()).setIconified(true);
    }

    public void handleACButton() {
            isFirstNumber = false;
            isSecondNumber = false;
            number1 = 0;
            number2 = 0;
            result = 0;
            operationSymbol = null;
            operationSymbolPressed = 0;
            resultLabel.setText("0");
    }

    public void handleSignButton() {
        if (!isSecondNumber && getLabel() != number1)
            if (!Objects.equals(resultLabel.getText(), "0")) {
                result = getLabel();
                result *= -1;
                showResult();
            } else {
                resultLabel.setText("-0");
            }
        System.out.println(number1 + " " + operationSymbol + " " + number2 + " = " + resultLabel.getText());
    }

    public void handlePercentButton() {
            if (!Objects.equals(resultLabel.getText(), "0")) {
                result = getLabel();
                result /= 100;
                showResult();
            }
        System.out.println(number1 + " " + operationSymbol + " " + number2 + " = " + resultLabel.getText());
    }

    public void handleNumberButton(ActionEvent event) {

        String digit = ((Button) event.getSource()).getText();

        if (!isFirstNumber) {
            if (resultLabel.getText().equals("0")) {
                resultLabel.setText(String.valueOf(digit));
            } else if (resultLabel.getText().equals("-0")) {
                resultLabel.setText("-" + digit);
            } else {
                resultLabel.setText(resultLabel.getText() + digit);
            }
        } else {
            if (getLabel() == number1 || operationSymbolPressed != 0) {
                if (aux == 0) {
                    resultLabel.setText("");
                }
            }
            if (resultLabel.getText().equals("0")) {
                resultLabel.setText(String.valueOf(digit));
            } else if (resultLabel.getText().equals("-0")) {
                resultLabel.setText("-" + digit);
            } else {
                resultLabel.setText(resultLabel.getText() + digit);
            }
        }
        System.out.println("Number    -->    " + number1 + " " + operationSymbol + " " + number2 + " = " + resultLabel.getText());
    }

    public void handleOperationsButton(ActionEvent event) {

        operationSymbol = ((Button) event.getSource()).getText();

        if (!isFirstNumber && operationSymbolPressed == 0) {
            number1 = getLabel();
            isFirstNumber = true;
        }
        if (operationSymbolPressed == 1){
            number2 = getLabel();
            calculate(operationSymbol);
            showResult();
            number1 = result;

        } else {
            number2 = getLabel();
            calculate(operationSymbol);
        }

        calculate(operationSymbol);
        showResult();
        operationSymbolPressed++;
        System.out.println("Operation   -->    " + number1 + " " + operationSymbol + " " + number2 + " = " + resultLabel.getText());
    }

    public void handleEqualsButton() {


        System.out.println(number1 + " " + operationSymbol + " " + number2 + " = " + resultLabel.getText());
    }

    public void handlePointButton() {
        String lastCharacter = String.valueOf(resultLabel.getText()).substring(resultLabel.getText().length() - 1);
        if (getLabel() % 1 == 0 && !lastCharacter.equals(".")) {
            resultLabel.setText(resultLabel.getText() + ".");
        }
        System.out.println(number1 + " " + operationSymbol + " " + number2 + " = " + resultLabel.getText());
    }

    public double getLabel() {
        return Double.parseDouble(resultLabel.getText());
    }

    public void calculate(String operationSymbol) {
        switch (operationSymbol) {
            case "+" -> result = number1 + number2;
            case "-" -> result = number1 - number2;
            case "*" -> result = number1 * number2;
            case "/" -> result = number1 / number2;
        }
    }

    public void showResult() {
        if (result % 1 == 0) {
            resultLabel.setText(String.valueOf((int) result));
        } else {
            resultLabel.setText(String.valueOf(result));
        }
    }
}