package com.example.przemek.calculator;

import android.os.Bundle;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class DataStorage {

    private final int MAX_STORED_NUMBER_SIZE = 2;
    private BigDecimal storedNumbers[] = new BigDecimal[MAX_STORED_NUMBER_SIZE];

    /*StoredNumbersPointer is defined to specify where store next number */
    private int storedNumbersPointer = 0;
    private String storedOperation;
    private BigDecimal result;
    protected final int PRECISON = 10;

    public DataStorage(){
        clear();
    }

    public void clear(){
        resetStoredNumbers();
        resetStoredNumberPointer();
    }

    public void resetStoredNumbers(){
        storedNumbers[0] = BigDecimal.ZERO;
        storedNumbers[1] = BigDecimal.ZERO;
    }

    public void resetStoredNumberPointer(){
        storedNumbersPointer = 0;
    }

    public void storeNumber(String value) {
        storedNumbers[storedNumbersPointer] = new BigDecimal(value);
    }

    public void storeNumber(double value){
        storedNumbers[storedNumbersPointer] = BigDecimal.valueOf(value);
    }

    public void pointAtNextNumber(){
        if(storedNumbersPointer < MAX_STORED_NUMBER_SIZE - 1){
            storedNumbersPointer++;
        }
    }

    public void storeOperation(String operation){
        this.storedOperation = operation;
    }


    /*public BigDecimal getResult(){
        return result;
    }*/

    public String getFormattedResult(){
        return result.round(new MathContext(PRECISON, RoundingMode.UP)).toEngineeringString();
    }

    public void save(Bundle savedInstanceState){
        //savedInstanceState.putDoubleArray("storedNumber", storedNumbers);
        savedInstanceState.putString("storedOperation", storedOperation);
    }

    public void restore(Bundle outState){
       //storedNumbers = outState.getDoubleArray("storedNumber");
       storedOperation = outState.getString("storedOperation");
    }

    public int getMAX_STORED_NUMBER_SIZE() {
        return MAX_STORED_NUMBER_SIZE;
    }

    public BigDecimal[] getStoredNumbers() {
        return storedNumbers;
    }

    public BigDecimal getPointedStoredNumber(){
        return storedNumbers[storedNumbersPointer];
    }


    public int getStoredNumbersPointer() {
        return storedNumbersPointer;
    }

    public void setStoredNumbersPointer(int storedNumbersPointer) {
        this.storedNumbersPointer = storedNumbersPointer;
    }

    public String getStoredOperation() {
        return storedOperation;
    }

    public void setStoredOperation(String storedOperation) {
        this.storedOperation = storedOperation;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public int getPrecision(){
        return PRECISON;
    }
}