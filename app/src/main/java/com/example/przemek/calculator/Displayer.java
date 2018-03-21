package com.example.przemek.calculator;

import android.widget.TextView;

public class Displayer {

    private TextView tv;
    private String data;
    private final String INITIAL_DATA = "0";
    private final int MAX_NUMBER_LENGTH = 16;
    private Symbols symbols = new Symbols();
    private Flags flags = new Flags();


    public Displayer(TextView tv){
        this.tv = tv;
        setInitialData();
    }

    public Flags getFlags(){
        return flags;
    }

    public void append(String s){
        int max;
        if(data.charAt(0) == '-'){
            max = MAX_NUMBER_LENGTH + 1;
        } else {
            max = MAX_NUMBER_LENGTH;
        }

        if(data.length() < max){
            if(flags.isInitialValue()){
                clear();
                flags.setInitialValue(false);
                flags.setDotAllowed(true);
            }
            String appended = this.data + s;
            set(appended);
        }
    }

    public void setInitialData(){
        flags.setInitialValue(true);
        set(INITIAL_DATA);
    }

    public void set(String s){
        if(getFlags().isError()){
            getFlags().setError(false);
            char lastCharacter = s.charAt(s.length()-1);
            if(Character.isDigit(lastCharacter)) {
                set(String.valueOf(lastCharacter));
            } else {
                setInitialData();
            }
        } else {
            this.data = s;
            tv.setText(data);
        }

    }

    public String getData(){
        return data;
    }

    public Character getLastCharacter(){
        int endPosition = data.length() - 1;
        return data.charAt(endPosition);
    }

    boolean isEmpty(){
        return data == null || data.length() == 0;
    }

    public void clear(){
        set("");
        flags.setDotAllowed(false);
        flags.setEqualAllowed(true);
    }

    public void deleteLastCharacter(){
        if(data.length() <= 1){
            setInitialData();
        } else {
            if (!isEmpty()) {
                if(getLastCharacter().equals('.')){
                    flags.setDotAllowed(true);
                }
                this.set(getTextWithoutLastCharacter(data));
            }
        }
    }

    public String getTextWithoutLastCharacter(String text){
        int endPosition = text.length() -1;
        return text.substring(0, endPosition);
    }

    public boolean isLastCharacterSymbol(){
        if(!isEmpty()){
            return symbols.isSymbol(getLastCharacter());
        } else{
            return false;
        }
    }

    public void negate(){
        if(!isEmpty() && !getFlags().isInitialValue()){
            char fistCharacter = data.charAt(0);
            if(fistCharacter == '-'){
                set(data.substring(1, data.length()));
            } else {
                appendAtBegining("-");
            }
        }
    }

    private void appendAtBegining(String s){
        set(s + data);
    }
}
