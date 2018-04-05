package com.example.przemek.calculator;

import android.os.Bundle;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class DataStorage {

    public final int MAX_STORED_NUMBER_SIZE = 2;
    private BigDecimal storedNumbers[] = new BigDecimal[MAX_STORED_NUMBER_SIZE];
    /*StoredNumbersPointer is defined to specify where store next number */
    private int storedNumbersPointer = 0;
    private String storedOperation;
    private BigDecimal result = BigDecimal.ZERO;
    public final int FORMAT_PRECISON = 16;
    public  final int DIV_PRECISION = 9997;


    public void save(Bundle savedInstanceState){
        savedInstanceState.putSerializable("storedNumber", storedNumbers);
        savedInstanceState.putSerializable("result", result);
        savedInstanceState.putString("storedOperation", storedOperation);
        savedInstanceState.putInt("storedNumbersPointer", storedNumbersPointer);
    }

    public void restore(Bundle outState){
        storedNumbers = (BigDecimal[]) outState.getSerializable("storedNumber");
        result = (BigDecimal) outState.getSerializable("result");
        storedOperation = outState.getString("storedOperation");
        storedNumbersPointer = outState.getInt("storedNumbersPointer");
    }

    public DataStorage(){
        clear();
    }


    public void clear(){
        clearStoredNumbers();
        clearOperation();
        resetStoredNumberPointer();
    }

    public void clearStoredNumbers(){
        storedNumbers[0] = null;
        storedNumbers[1] = null;
        result = BigDecimal.ZERO;
    }

    public void clearOperation(){
        storedOperation = "";
    }

    public void resetStoredNumberPointer(){
        storedNumbersPointer = 0;
    }

    public void storeNumber(BigDecimal value){
        storedNumbers[storedNumbersPointer] = value;
    }

    public void storeNumber(Double value){
        storedNumbers[storedNumbersPointer] = new BigDecimal(value);
    }

    public void storeNumber(String value){
        storedNumbers[storedNumbersPointer] = new BigDecimal(value);
    }

    public void pointAtNextNumber(){
        if(storedNumbersPointer < MAX_STORED_NUMBER_SIZE - 1){
            storedNumbersPointer++;
        }
    }

    public void storeOperation(String operation){
        this.storedOperation = operation;
    }

    public String getFormattedResult(){
        if(result == BigDecimal.ZERO){
            return "0";
        } else {
            return result.round(new MathContext(FORMAT_PRECISON, RoundingMode.HALF_UP))
                    .stripTrailingZeros()
                    .toEngineeringString();
        }

    }

    public BigDecimal getResult(){
        return result;
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

    public String getStoredOperation() {
        return storedOperation;
    }


    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public void setResult(Double result){
        this.result = new BigDecimal(result);
    }

    public void setStoredNumber(int index, BigDecimal value){
        storedNumbers[index] = value;
    }
}