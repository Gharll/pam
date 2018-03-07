package com.example.przemek.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Map;

public class SimpleCalculatorActivity extends AppCompatActivity {

    private String displayerData = "";
    private String storedValue[] = new String[2];
    private Character storedOperation;

    private SimpleCalculator simpleCalculator = new SimpleCalculator();
    private boolean isDotAllowedFlag = true;
    private boolean isEqualAllowedFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);
        initializeEvent();

        if(savedInstanceState != null){
            displayerData = savedInstanceState.getString("displayerData");
            updateDisplayer(displayerData);
        }
    }

    protected void initializeEvent(){
        createButtonsNumberEvent();
        createBackspaceEvent();
        createClearDisplayerEvent();
        createBasicMathematicalOperationEvent();
        createDotEvent();
        createNegateEvent();
        createEqualEvent();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("displayerData", displayerData);
    }

    private void createButtonsNumberEvent(){
        final int maxNumberOfButtons = 9;
        for(int i = 0; i <= maxNumberOfButtons; i++){
            final int number = i;
            Button button = (Button) findViewByName("btn_number_" + number);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appendToDisplayer(String.valueOf(number));
                }
            });
        }
    }

    private Object findViewByName(String name){
        int buttonId = getResources().getIdentifier(name, "id", getPackageName());
        return findViewById(buttonId);
    }

    //append new data at the end of the displayer
    private void appendToDisplayer(String data){
        updateDisplayer(displayerData += data);
    }

    //clear data of displayer and assign new data
    private void updateDisplayer(String data){
        TextView displayer = (TextView) findViewById(R.id.tv_displayer);
        displayerData = data;
        displayer.setText(displayerData);
    }

    private void createBackspaceEvent(){
        Button button = (Button) findViewById(R.id.btn_bksp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isDisplayerEmpty()){
                    if(getLastCharacterOfDisplayer().equals('.')){
                        isDotAllowedFlag = true;
                    }
                    if(simpleCalculator.isSymbol(getLastCharacterOfDisplayer())){
                        isDotAllowedFlag = false;
                    }
                    displayerData = displayerData.substring(0, displayerData.length() -1);
                    updateDisplayer(displayerData);
                }
            }
        });
    }

    private Character getLastCharacterOfDisplayer(){
        return displayerData.charAt(displayerData.length() - 1);
    }

    private boolean isDisplayerEmpty(){
        return displayerData == null || displayerData.length() == 0;
    }

    private void createClearDisplayerEvent(){
        Button button = (Button) findViewById(R.id.btn_clear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDisplayer();
            }
        });
    }

    private void clearDisplayer(){
        updateDisplayer("");
        isDotAllowedFlag = true;
    }

    private void createBasicMathematicalOperationEvent(){
        Iterator it =
                simpleCalculator.getSymbolsMap().entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            final Character operationSymbol = (Character) pair.getKey();
            final String operationName = (String) pair.getValue();
            Button button = (Button) findViewByName("btn_operation_"+operationName);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isDisplayerEmpty() && !isSymbolLastCharacterOfDisplayer()){
                        //appendToDisplayer(String.valueOf(operationSymbol));
                        isDotAllowedFlag = true;
                        isEqualAllowedFlag = true;
                        storedValue[0] = displayerData;
                        storedOperation = operationSymbol;
                        clearDisplayer();
                    }
                }
            });
        }
    }

    private boolean isSymbolLastCharacterOfDisplayer(){
        return simpleCalculator.isSymbol(displayerData.charAt(displayerData.length()-1));
    }

    private void createDotEvent(){
        Button button = (Button) findViewById(R.id.btn_operation_dot);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(!isDisplayerEmpty() && !isSymbolLastCharacterOfDisplayer() && isDotAllowedFlag){
                   isDotAllowedFlag = false;
                    appendToDisplayer(".");
               }
            }
        });
    }

    private void createNegateEvent(){

    }

    private void createEqualEvent(){
        Button button = (Button) findViewById(R.id.btn_operation_equal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEqualAllowedFlag){
                    storedValue[1] = displayerData;
                    isEqualAllowedFlag = false;
                    switch(storedOperation){
                        case '+' :
                            Double result = Double.parseDouble(storedValue[0]) + Double.parseDouble(storedValue[1]);
                            updateDisplayer(result.toString());
                            break;
                    }
                }
            }
        });
    }

}
