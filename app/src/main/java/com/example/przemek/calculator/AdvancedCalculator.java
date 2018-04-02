package com.example.przemek.calculator;


import java.math.BigDecimal;

public class AdvancedCalculator extends SimpleCalculator {

    public AdvancedCalculator(Displayer displayer, DataStorage dataStorage){
        super(displayer, dataStorage);
    }

    public AdvancedCalculator(Displayer displayer){
        super(displayer);
    }

    public void handleSinEvent(){
        try{
            double result = Math.sin(displayer.getParsedData());
            showResult(result);
        } catch (NumberFormatException e){
            showError("Bad input");
        }
    }



    public void handleCosEvent(){
        try{
            double result = Math.cos(displayer.getParsedData());
            showResult(result);
        } catch (NumberFormatException e){
            showError("Bad input");
        }


    }

    public void handleTanEvent(){
        try{
            double result = Math.tan(displayer.getParsedData());
            showResult(result);
        } catch (NumberFormatException e){
            showError("Bad input");
        }
    }

    public void handleLogEvent(){
        try{
            double result = Math.log(displayer.getParsedData());
            showResult(result);
        } catch (NumberFormatException e){
            showError("Bad input");
        }


    }

    public void handleLnEvent(){
        try{
            double result = Math.log10(displayer.getParsedData());
            showResult(result);
        } catch (NumberFormatException e){
            showError("Bad input");
        }


    }

    public void handleSqrtEvent(){
        try{
            double result = Math.sqrt(displayer.getParsedData());
            showResult(result);
        } catch (NumberFormatException e){
            showError("Bad input");
        }


    }


    public void handlePowerSquareEvent(){
        try{
            dataStorage.setStoredNumber(1, new BigDecimal(displayer.getParsedData()));
            double result = Math.pow(displayer.getParsedData(), 2);
            showResult(result);
        } catch (NumberFormatException e){
            showError("Bad input");
        }


    }

    public void handlePowerEvent(){
        dataStorage.storeNumber(displayer.getData());
        dataStorage.pointAtNextNumber();
        dataStorage.storeOperation("^");
        displayer.clear();
        displayer.setInitialData();
    }

    public void calculatePower(){
        if (dataStorage.getStoredNumbers()[1] == null){
            dataStorage.getStoredNumbers()[1] = new BigDecimal(0.0);
        }
        BigDecimal base = dataStorage.getStoredNumbers()[0];
        BigDecimal power = dataStorage.getStoredNumbers()[1];
        double result = Math.pow(base.doubleValue(), power.doubleValue());
        dataStorage.setResult(result);
    }

    public void handlePercentEvent(){
        int pointer = dataStorage.getStoredNumbersPointer();
        if(pointer == 0){
            super.handleClear();
        } else if(pointer == 1) {
            BigDecimal first = dataStorage.getStoredNumbers()[0];
            BigDecimal second = new BigDecimal(displayer.getData());

            // that means: first*second/100
            dataStorage.setStoredNumber(1, first.multiply(second).divide(new BigDecimal(100)));
            super.handleCalculate();
        }
    }

    public void calculate(){
        super.calculate();
        if(dataStorage.getStoredOperation() != null){
            switch(dataStorage.getStoredOperation()){
                case "^":
                    this.calculatePower();
                    break;
            }
        }
    }

}
