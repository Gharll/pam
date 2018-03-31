package com.example.przemek.calculator;


import java.math.BigDecimal;

public class AdvancedCalculator extends SimpleCalculator {

    public AdvancedCalculator(Displayer displayer, DataStorage dataStorage){
        super(displayer, dataStorage);

    }

    private void showResult(double result){
        dataStorage.clear();
        dataStorage.storeNumber(result);
        dataStorage.pointAtNextNumber();
        displayer.set(String.valueOf(result));
    }

    public void handleSinEvent(){
        double result = Math.sin(displayer.getParsedData());
        showResult(result);
    }



    public void handleCosEvent(){
        double result = Math.cos(displayer.getParsedData());
        showResult(result);
    }

    public void handleTanEvent(){
        double result = Math.tan(displayer.getParsedData());
        showResult(result);
    }

    public void handleLogEvent(){
        double result = Math.log(displayer.getParsedData());
        showResult(result);
    }

    public void handleLnEvent(){
        double result = Math.log10(displayer.getParsedData());
        showResult(result);
    }

    public void handleSqrtEvent(){
        double result = Math.sqrt(displayer.getParsedData());
        showResult(result);
    }

    public void handlePowerSquareEvent(){
        double result = Math.pow(displayer.getParsedData(), 2);
        showResult(result);
    }

    public void handlePowerEvent(){
        if(super.dataStorage.getStoredNumbersPointer() == 0){

        }
    }

    public void handlePercentEvent(){
        int pointer = dataStorage.getStoredNumbersPointer();
        if(pointer == 0){
            super.handleClear();
        } else if(pointer == 1) {
            BigDecimal first = dataStorage.getStoredNumbers()[0];
            BigDecimal second = new BigDecimal(displayer.getData());

            // that means: first*second/100
            dataStorage.setStoredNumber(1, super.divideFormatted(first.multiply(second),
                    new BigDecimal(100)));
            super.handleCalculate();
        }
    }

}
