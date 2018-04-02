package com.example.przemek.calculator;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

public class Displayer {

    private TextView tv;
    private String data;
    private final String INITIAL_DATA = "0";
    private final int MAX_NUMBER_LENGTH = 16;
    private Symbols symbols = new Symbols();
    private Flags flags = new Flags();

    public void save(Bundle savedInstanceState) {
        savedInstanceState.putString("data", getData());
        savedInstanceState.putParcelable("flags", getFlags());
    }

    public void restore(Bundle outState) {
        data = outState.getString("data");
        flags = outState.getParcelable("flags");
        refresh();
    }

    public Displayer(TextView tv) {
        this.tv = tv;
        setInitialData();
    }

    public Flags getFlags() {
        return flags;
    }

    private boolean isNegative(){
        return data.charAt(0) == '-';
    }

    public void append(String s) {
        int max;
        if (isNegative()) {
            max = MAX_NUMBER_LENGTH + 1;
        } else {
            max = MAX_NUMBER_LENGTH;
        }
        if(data.length() < max){
            String appended = this.data + s;
            set(appended);
        }

    }

    public void setInitialData() {
        clear();
        flags.setDotAllowed(true);
        set(INITIAL_DATA);
    }

    public void set(String s) {
        this.data = s;
        tv.setText(data);
    }

    public String getData() {
        return data;
    }

    public Double getParsedData(){
        return Double.parseDouble(data);
    }

    public Character getLastCharacter() {
        int endPosition = data.length() - 1;
        return data.charAt(endPosition);
    }

    boolean isEmpty() {
        return data == null || data.length() == 0;
    }

    public void clear() {
        set("");
        flags.setDotAllowed(false);
    }

    public void deleteLastCharacter() {
        if (data.length() <= 1) {
            setInitialData();
        } else {
            if (!isEmpty()) {
                if (getLastCharacter().equals('.')) {
                    flags.setDotAllowed(true);
                }
                this.set(getTextWithoutLastCharacter(data));
            }
        }
    }

    public String getTextWithoutLastCharacter(String text) {
        int endPosition = text.length() - 1;
        return text.substring(0, endPosition);
    }

    public boolean isLastCharacterSymbol() {
        if (!isEmpty()) {
            return symbols.isSymbol(getLastCharacter());
        } else {
            return false;
        }
    }

    public void negate() {
        if (!isEmpty()) {
            char fistCharacter = data.charAt(0);
            if (fistCharacter == '-') {
                set(data.substring(1, data.length()));
            } else {
                appendAtBegining("-");
            }
        }
    }

    private void appendAtBegining(String s) {
        set(s + data);
    }

    private void refresh() {
        tv.setText(data);
    }

    public boolean isInitalData(){
        return getData().length() == 1 && getData().charAt(0) == '0';
    }

    public boolean isPositive(){
        return getData().charAt(0) == '0';
    }

}
