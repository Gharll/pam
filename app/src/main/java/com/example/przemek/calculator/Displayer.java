package com.example.przemek.calculator;

import android.widget.TextView;

/**
 * Created by Przemek on 08.03.2018.
 */

public class Displayer {

    private TextView tv;
    private String data;
    private boolean isDotAllowedFlag = true;
    private boolean isEqualAllowedFlag = false;
    private Symbols symbols = new Symbols();

    public Displayer(TextView tv){
        this.tv = tv;
        this.set("0");
    }

    public boolean isDotAllowedFlag() {
        return isDotAllowedFlag;
    }

    public void setDotAllowedFlag(boolean dotAllowedFlag) {
        this.isDotAllowedFlag = dotAllowedFlag;
    }

    public boolean isEqualAllowedFlag() {
        return isEqualAllowedFlag;
    }

    public void setEqualAllowedFlag(boolean equalAllowedFlag) {
        this.isEqualAllowedFlag = equalAllowedFlag;
    }

    public TextView getTextView(){
        return tv;
    }

    public void append(String s){
        this.set(this.data + s);
    }

    public void set(String s){
        this.data = s;
        tv.setText(data);
    }

    public String getDataCopy(){
        return new String(data);
    }

    public Character getLastCharacterCopy(){
        return new Character(data.charAt(data.length() - 1));
    }

    boolean isEmpty(){
        return data == null || data.length() == 0;
    }

    public void clear(){
        this.set("");
        isDotAllowedFlag = true;
    }

    public void deleteLastCharacter(){
        this.set(data.substring(0, data.length() -1));
    }

    public boolean isLastCharacterSymbol(){
        if(!isEmpty()){
            return symbols.isSymbol(data.charAt(data.length()-1));
        } else{
            return false;
        }
    }
}
