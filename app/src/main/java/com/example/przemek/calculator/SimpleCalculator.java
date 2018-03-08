package com.example.przemek.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Przemek on 06.03.2018.
 */

public class SimpleCalculator {

    private ArrayList<Double> storedNumbers = new ArrayList<>(2);
    private final int maxStoredNumbers = 2;
    private String storedOperation;
    private double result;

    public SimpleCalculator(){
    }


    //threw new exception?
    public void storeNextNumber(String number){
        if(storedNumbers.size() < maxStoredNumbers){
            Double convertedNumber = Double.parseDouble(number);
            storedNumbers.add(convertedNumber);
        }

    }

    public void storeNextNumber(Double number){
        if(storedNumbers.size() < maxStoredNumbers){
            storedNumbers.add(number);
        }
    }

    public void storeOperation(String operation){
        this.storedOperation = operation;
    }

    public void clear(){
        storedNumbers.clear();
        storedOperation = "";
    }

    public void calculate(){
        if (isReadyToCalculate()){
            switch(storedOperation){
                case "+":
                    result = storedNumbers.get(0) + storedNumbers.get(1);
                    break;
                case "-":
                    result = storedNumbers.get(0) - storedNumbers.get(1);
                    break;
                case "*":
                    result = storedNumbers.get(0) * storedNumbers.get(1);
                    break;
                case "/":
                    result = storedNumbers.get(0) / storedNumbers.get(1);
                    break;
            }
        }

    }

    public Double getResult(){
        return result;
    }

    public boolean isReadyToCalculate(){
        return storedNumbers.size() == 2 && storedOperation != null && storedOperation.length() > 0;
    }
}