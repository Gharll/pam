package com.example.przemek.calculator;

import android.widget.TextView;

/**
 * Created by Przemek on 08.03.2018.
 */

public class Displayer {

    private TextView tv;
    private String data;
    private String initialData = "0";

    /*The flags specify the rules how we can use calculator, how we can
    * entering the data and how it should be displayed*/

    /*To prevent entering more than one dot in number e.g. 2.0.0222.3 */
    private boolean isDotAllowedFlag = true;

    /*Does it make sense to do equal operation and calculate the result*/
    private boolean isEqualAllowedFlag = false;

    /*To provide initial value shown on displayer which should disappeared
     * after entering data by user e.g. user is clicking a button */
    private boolean isInitialValueFlag = true;

    private Symbols symbols = new Symbols();

    public Displayer(TextView tv){
        this.tv = tv;
        setInitialData();
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

    public boolean isInitialValueFlag() {
        return isInitialValueFlag;
    }

    public void setInitialValueFlag(boolean initialValueFlag) {
        isInitialValueFlag = initialValueFlag;
    }

    public String getInitialData(){
        return initialData;
    }

    public int getDataLength(){
        return data.length();
    }

    public TextView getTextView(){
        return tv;
    }

    public void append(String s){
        if(isInitialValueFlag){
            clear();
            isInitialValueFlag = false;
        }
        this.set(this.data + s);
    }

    public void setInitialData(){
        isInitialValueFlag = true;
        set(initialData);
    }

    /*set data to display without checking any condition*/
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
        isEqualAllowedFlag = true;
    }

    public void deleteLastCharacter(){
        if(data.length() < 2){
            setInitialData();
        } else {
            if(getLastCharacterCopy().equals('.')){
                isDotAllowedFlag = true;
            }
            this.set(data.substring(0, data.length() -1));
        }

    }

    public boolean isLastCharacterSymbol(){
        if(!isEmpty()){
            return symbols.isSymbol(data.charAt(data.length()-1));
        } else{
            return false;
        }
    }
}
