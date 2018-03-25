package com.example.przemek.calculator;

import java.math.BigDecimal;

/**
 * Created by Przemek on 22.03.2018.
 */

public class SimpleCalculator {

    protected DataStorage dataStorage = new DataStorage();
    protected Symbols symbols = new Symbols();
    protected Displayer displayer;


    public SimpleCalculator(Displayer displayer){
        this.displayer = displayer;
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
        //handleError();
        displayer.append(String.valueOf(number));
    }

    public void handleZeroNumber(){
        //handleError();
        if(!displayer.isEmpty() && !displayer.getFlags().isInitialValue()){
            handleNumber(0);
        }
    }

    public void handleOperation(char operationSymbol){
        //handleError();
        if (!displayer.isEmpty() && !displayer.isLastCharacterSymbol()) {
            try {
                dataStorage.storeNumber(displayer.getData());
                dataStorage.pointAtNextNumber();
                dataStorage.storeOperation(String.valueOf(operationSymbol));
                displayer.clear();
                displayer.setInitialData();
            }catch (NumberFormatException e) {
                handleBadInput();
            }

        }
    }

    public void handleBackspace(){
        //handleError();
        displayer.deleteLastCharacter();
    }

    public void handleDot(){
        //handleError();
        if (!displayer.isEmpty()
                && !displayer.isLastCharacterSymbol()
                && displayer.getFlags().isDotAllowed()
                && !displayer.getFlags().isInitialValue()) {
            displayer.getFlags().setDotAllowed(false);
            displayer.append(".");
        }
    }

    public void handleNegate(){
        //handleError();
        displayer.negate();
    }

    public void handleEqual(){
        //handleError();
        if(!displayer.isLastCharacterSymbol()){
            try{
                dataStorage.storeNumber(displayer.getData());
                dataStorage.resetStoredNumberPointer();
                displayer.getFlags().setEqualAllowed(false);
                this.calculate();
                String result = dataStorage.getFormattedResult();
                displayer.set(result);
                dataStorage.storeNumber(result);
                if (displayer.getData().contains(".")) {
                    displayer.getFlags().setDotAllowed(false);
                } else {
                    displayer.getFlags().setDotAllowed(true);
                }
            } catch(NumberFormatException e){
                handleBadInput();
            }

        }
    }

    public void calculate(){
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
                    dataStorage.setResult(divideFormatted(firstNumber, secondNumber));
                    break;
            }
        }
    }

    public BigDecimal divideFormatted(BigDecimal firstNumber, BigDecimal secondNumber){
        return firstNumber.divide(secondNumber, dataStorage.getPrecision(), BigDecimal.ROUND_FLOOR);
    }

    private void handleBadInput(){
        displayer.set("Bad input");
        displayer.getFlags().setError(true);
        dataStorage.clear();
    }

}
