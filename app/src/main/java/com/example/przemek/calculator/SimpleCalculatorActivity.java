package com.example.przemek.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Map;

public class SimpleCalculatorActivity extends AppCompatActivity {

    //Should initialize in onCreate function?
    private SimpleCalculator simpleCalculator;
    private Symbols symbols;
    private Displayer displayer;
    private SimpleCalculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);
        initializeObject();
        initializeEvent();

        if(savedInstanceState != null){
            displayer.set(savedInstanceState.getString("displayerData"));
        }
    }

    protected void initializeObject(){
        simpleCalculator =  new SimpleCalculator();
        TextView textView = (TextView) findViewById(R.id.tv_displayer);
        displayer = new Displayer(textView);
        calculator = new SimpleCalculator();
        symbols = new Symbols();
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
        savedInstanceState.putString("displayerData", displayer.getDataCopy());
    }

    private void createButtonsNumberEvent(){
        final int maxNumberOfButtons = 9;
        for(int i = 0; i <= maxNumberOfButtons; i++){
            final int number = i;
            Button button = (Button) findViewByName("btn_number_" + number);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    displayer.append(String.valueOf(number));
                }
            });
        }
    }

    private Object findViewByName(String name){
        int buttonId = getResources().getIdentifier(name, "id", getPackageName());
        return findViewById(buttonId);
    }

    private void createBackspaceEvent(){
        Button button = (Button) findViewById(R.id.btn_bksp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!displayer.isEmpty()){
                    if(displayer.getLastCharacterCopy().equals('.')){
                        displayer.setDotAllowedFlag(true);
                    }
                    if(symbols.isSymbol(displayer.getLastCharacterCopy())){
                        displayer.setDotAllowedFlag(false);
                    }
                    displayer.deleteLastCharacter();
                }
            }
        });
    }

    private void createClearDisplayerEvent(){
        Button button = (Button) findViewById(R.id.btn_clear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayer.clear();
                calculator.clear();
            }
        });
    }

    private void createBasicMathematicalOperationEvent(){
        Iterator it =
                symbols.getSymbolMap().entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            final Character operationSymbol = (Character) pair.getKey();
            final String operationName = (String) pair.getValue();
            Button button = (Button) findViewByName("btn_operation_"+operationName);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!displayer.isEmpty() && !displayer.isLastCharacterSymbol()){
                        //appendToDisplayer(String.valueOf(operationSymbol));
                        displayer.setDotAllowedFlag(true);
                        displayer.setEqualAllowedFlag(true);
                        calculator.storeNextNumber(displayer.getDataCopy());
                        calculator.storeOperation(String.valueOf(operationSymbol));
                        displayer.clear();
                    }
                }
            });
        }
    }



    private void createDotEvent(){
        Button button = (Button) findViewById(R.id.btn_operation_dot);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(!displayer.isEmpty() && !displayer.isLastCharacterSymbol() && displayer.isDotAllowedFlag()){
                   displayer.setDotAllowedFlag(false);
                    displayer.append(".");
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
                if(displayer.isEqualAllowedFlag()){
                    calculator.storeNextNumber(displayer.getDataCopy());
                    displayer.setEqualAllowedFlag(false);
                    calculator.calculate();
                    displayer.set(calculator.getResult().toString());
                }
            }
        });
    }

}
