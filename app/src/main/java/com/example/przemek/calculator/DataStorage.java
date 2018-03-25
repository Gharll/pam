package com.example.przemek.calculator;

import android.os.Bundle;

public class DataStorage {

    private final int MAX_STORED_NUMBER_SIZE = 2;
    private double storedNumbers[] = new double[MAX_STORED_NUMBER_SIZE];

    /*StoredNumbersPointer is defined to specify where store next number */
    private int storedNumbersPointer = 0;
    private String storedOperation;
    private double result;

    public DataStorage(){
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

    public void storeNumber(double value){
        storedNumbers[storedNumbersPointer] = value;
    }

    public void pointAtNextNumber(){
        if(storedNumbersPointer < MAX_STORED_NUMBER_SIZE - 1){
            storedNumbersPointer++;
        }
    }

    public void storeOperation(String operation){
        this.storedOperation = operation;
    }


    public Double getResult(){
        return result;
    }

    public void save(Bundle savedInstanceState){
        savedInstanceState.putDoubleArray("storedNumber", storedNumbers);
        savedInstanceState.putString("storedOperation", storedOperation);
    }

    public void restore(Bundle outState){
       storedNumbers = outState.getDoubleArray("storedNumber");
       storedOperation = outState.getString("storedOperation");
    }

    public int getMAX_STORED_NUMBER_SIZE() {
        return MAX_STORED_NUMBER_SIZE;
    }

    public double[] getStoredNumbers() {
        return storedNumbers;
    }

    public double getPointedStoredNumber(){
        return storedNumbers[storedNumbersPointer];
    }

    public void setStoredNumbers(double[] storedNumbers) {
        this.storedNumbers = storedNumbers;
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

    public void setResult(double result) {
        this.result = result;
    }
}