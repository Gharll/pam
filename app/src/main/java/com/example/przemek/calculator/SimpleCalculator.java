package com.example.przemek.calculator;

public class SimpleCalculator {

    private final int MAX_STORED_NUMBER_SIZE = 2;
    private Double storedNumbers[] = new Double[MAX_STORED_NUMBER_SIZE];

    /*StoredNumbersPointer is defined to specify where store next number */
    private int storedNumbersPointer = 0;
    private String storedOperation;
    private double result;

    public SimpleCalculator(){
        clear();
    }

    public void clear(){
        resetStoredNumbers();
        resetStoredNumberPointer();
    }

    public void resetStoredNumbers(){
        storedNumbers[0] = 0.0;
        storedNumbers[1] = 0.0;
    }

    public void resetStoredNumberPointer(){
        storedNumbersPointer = 0;
    }

    public void storeNumber(String value) {
        storedNumbers[storedNumbersPointer] = Double.parseDouble(value);
    }

    public void pointAtNextNumber(){
        if(storedNumbersPointer < MAX_STORED_NUMBER_SIZE - 1){
            storedNumbersPointer++;
        }
    }

    public void storeOperation(String operation){
        this.storedOperation = operation;
    }

    public void calculate(){
        if(storedOperation != null){
            switch(storedOperation){
                case "+":
                    result = storedNumbers[0] + storedNumbers[1];
                    break;
                case "-":
                    result = storedNumbers[0] - storedNumbers[1];
                    break;
                case "*":
                    result = storedNumbers[0] * storedNumbers[1];
                    break;
                case "/":
                    result = storedNumbers[0] / storedNumbers[1];
                    break;
            }
        }
    }

    public Double getResult(){
        return result;
    }

}