package com.example.przemek.calculator;


import java.math.BigDecimal;

public class AdvancedCalculator extends SimpleCalculator {

    public AdvancedCalculator(Displayer displayer){
        super(displayer);

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
        int pointer = super.dataStorage.getStoredNumbersPointer();
        if(pointer == 0){
            showResult(0.0);
        } else if(pointer == 1) {
            BigDecimal first = dataStorage.getStoredNumbers()[0];
            BigDecimal second = new BigDecimal(displayer.getData());
            // that means: result = first*second/100
            dataStorage.getStoredNumbers()[1] =  super.divideFormatted(first, second);
            super.handleEqual();
        }
    }

    @Override
    public void calculate(){
        super.calculate();
        if(super.dataStorage.getStoredOperation() != null){
            switch(super.dataStorage.getStoredOperation()){
                /*case "+":
                    dataStorage.setResult(dataStorage.getStoredNumbers()[0]
                            + dataStorage.getStoredNumbers()[1]);
                    break;*/
            }
        }
    }
}
