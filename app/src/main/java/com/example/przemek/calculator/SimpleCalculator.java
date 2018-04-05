package com.example.przemek.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimpleCalculator {

    protected DataStorage dataStorage = new DataStorage();
    protected Displayer displayer;
    protected boolean isCalculated;


    public SimpleCalculator(Displayer displayer){
        this.displayer = displayer;
    }

    public SimpleCalculator(Displayer displayer, DataStorage dataStorage){
        this.displayer = displayer;
        this.dataStorage = dataStorage;
    }

    public void handleError(){
        if(displayer.getFlags().isError()){
            displayer.getFlags().setError(false);
            handleClear();
        }
    }

    public void handleClear(){
        displayer.clear();
        dataStorage.clear();
        displayer.setInitialData();
    }

    public void handleNumber(int number){
        String sNumber = String.valueOf(number);
        if(displayer.isInitalData() || displayer.getFlags().isToOverride()){
            displayer.set(sNumber);
            displayer.getFlags().setToOverride(false);
            displayer.getFlags().setDotAllowed(true);
        } else {
            displayer.append(String.valueOf(sNumber));
        }

    }

    public void handleZeroNumber(){
        if(!displayer.isEmpty()){
            handleNumber(0);
        }
    }

    public void handleOperation(char operationSymbol){
        if (!displayer.isEmpty() && !displayer.isLastCharacterSymbol()) {
            try {
                dataStorage.storeNumber(new BigDecimal(displayer.getData()));
                dataStorage.pointAtNextNumber();
                dataStorage.storeOperation(String.valueOf(operationSymbol));
                displayer.getFlags().setToOverride(true);
            }catch (NumberFormatException e) {
                showError();
            }

        }
    }

    public void handleBackspace(){
        if(displayer.getData().equals("-0.")){
            displayer.negate();
        }
        if(displayer.getData().length() == 2 && !displayer.isPositive()){
            displayer.setInitialData();
        } else {
            displayer.deleteLastCharacter();
            displayer.getFlags().setToOverride(false);
        }

    }

    public void handleDot(){
        if (!displayer.isEmpty()
                && !displayer.isLastCharacterSymbol()
                && displayer.getFlags().isDotAllowed()) {
            displayer.getFlags().setDotAllowed(false);
            displayer.getFlags().setToOverride(false);
            displayer.append(".");
        }
    }

    public void handleNegate(){
        if(!displayer.isInitalData() && !displayer.getData().equals("0.")){
            displayer.negate();
        }
    }

    public void handleEqual(){
        if(!displayer.isLastCharacterSymbol()){
                try{
                    dataStorage.storeNumber(displayer.getData());
                   // dataStorage.setStoredNumber(1, dataStorage.getResult());
                } catch (NumberFormatException e) {
                    showError("Bad input");
                }
                if(!displayer.getFlags().isError()){
                    try{
                        handleCalculate();
                        displayer.getFlags().setToOverride(true);
                    } catch (NumberFormatException e){
                        showError("Overflow");
                    }
                }
        }
    }

    public void handleCalculate(){
        dataStorage.resetStoredNumberPointer();
        try {
            isCalculated = calculate();
        } catch(ArithmeticException e){
            displayer.set("Division by zero!");
            displayer.getFlags().setError(true);
        }
        if(!displayer.getFlags().isError()){
            try{
                String result = dataStorage.getFormattedResult();
                if(isCalculated){
                    displayer.set(result);
                    dataStorage.storeNumber(result);
                    if (displayer.getData().contains(".")) {
                        displayer.getFlags().setDotAllowed(false);
                    } else {
                        displayer.getFlags().setDotAllowed(true);
                    }
                }
            } catch(NumberFormatException e) {
                showError();
            }
        }
    }

    public boolean calculate(){
        if(dataStorage.getStoredOperation() != null){
            BigDecimal firstNumber = dataStorage.getStoredNumbers()[0];
            BigDecimal secondNumber = dataStorage.getStoredNumbers()[1];
            switch(dataStorage.getStoredOperation()){
                case "+":
                    dataStorage.setResult(firstNumber.add(secondNumber));
                    break;
                case "-":
                    dataStorage.setResult(firstNumber.subtract(secondNumber));
                    break;
                case "*":
                    dataStorage.setResult(firstNumber.multiply(secondNumber));
                    break;
                case "/":
                    if(firstNumber.equals(BigDecimal.ZERO)){
                        dataStorage.storeNumber(BigDecimal.ZERO);
                    } else {
                        dataStorage.setResult(firstNumber.divide(secondNumber,
                                dataStorage.DIV_PRECISION,
                                RoundingMode.HALF_UP));
                    }
                    break;

                default:
                    return false;
            }
        }
        return true;
    }


    protected void showError(String message){
        displayer.set(message);
        displayer.getFlags().setError(true);
        dataStorage.clear();
    }

    protected void showError(){
        showError("Error");
    }

    protected void showResult(double result){
        try{
            dataStorage.storeNumber(result);
            dataStorage.pointAtNextNumber();
            dataStorage.setResult(result);
            displayer.set(dataStorage.getFormattedResult());
            displayer.getFlags().setToOverride(true);
            dataStorage.clear();
        } catch (NumberFormatException e){
            if(result == Double.NEGATIVE_INFINITY){
                showError("Bad input");
            } else if(result == Double.POSITIVE_INFINITY){
                showError("Overflow");
            } else {
                throw e;
            }

        }
    }

}
