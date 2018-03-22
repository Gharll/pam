package com.example.przemek.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Map;

public class SimpleCalculatorActivity extends AppCompatActivity {

    private SimpleCalculator simpleCalculator;
    private Symbols symbols;
    private Displayer displayer;
    private SimpleCalculator calculator;

    @Override
    protected void onCreate(Bundle outState) {
        super.onCreate(outState);
        setContentView(R.layout.activity_simple_calculator);
        initializeObject();
        initializeEvent();

        if (outState != null) {
            displayer.restore(outState);
            calculator.restore(outState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        displayer.save(savedInstanceState);
        calculator.save(savedInstanceState);
    }

    protected void initializeObject() {
        TextView textView = (TextView) findViewById(R.id.tv_displayer);
        displayer = new Displayer(textView);
        calculator = new SimpleCalculator();
        symbols = new Symbols();
    }

    protected void initializeEvent() {
        createButtonsNumberEvent();
        createButtonZeroEvent();
        createBackspaceEvent();
        createClearDisplayerEvent();
        createBasicMathematicalOperationEvent();
        createDotEvent();
        createNegateEvent();
        createEqualEvent();
    }



    private Object findViewByName(String name) {
        int id = getResources().getIdentifier(name, "id", getPackageName());
        return findViewById(id);
    }

    private void createButtonsNumberEvent() {
        final int maxNumberOfButtons = 9;
        for (int i = 1; i <= maxNumberOfButtons; i++) {
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

    private void createButtonZeroEvent(){
        Button button = (Button) findViewById(R.id.btn_number_0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!displayer.isEmpty() && !displayer.getFlags().isInitialValue()){
                    displayer.append("0");
                }
            }
        });
    }


    private void createBackspaceEvent() {
        Button button = (Button) findViewById(R.id.btn_bksp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    displayer.deleteLastCharacter();
            }
        });
    }

    private void createClearDisplayerEvent() {
        Button button = (Button) findViewById(R.id.btn_clear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayer.clear();
                calculator.clear();
                displayer.setInitialData();
            }
        });
    }

    private void createBasicMathematicalOperationEvent() {
        Iterator it =
                symbols.getSymbolMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            final Character operationSymbol = (Character) pair.getKey();
            final String operationName = (String) pair.getValue();
            Button button = (Button) findViewByName("btn_operation_" + operationName);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!displayer.isEmpty() && !displayer.isLastCharacterSymbol()) {
                        try {
                            calculator.storeNumber(displayer.getData());
                            calculator.pointAtNextNumber();
                            calculator.storeOperation(String.valueOf(operationSymbol));
                            displayer.clear();
                            displayer.setInitialData();
                        }catch (NumberFormatException e) {
                            handleBadInput();
                        }

                    }
                }
            });
        }
    }

    private void createDotEvent() {
        Button button = (Button) findViewById(R.id.btn_operation_dot);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!displayer.isEmpty()
                        && !displayer.isLastCharacterSymbol()
                        && displayer.getFlags().isDotAllowed()
                        && !displayer.getFlags().isInitialValue()) {
                    displayer.getFlags().setDotAllowed(false);
                    displayer.append(".");
                }
            }
        });
    }

    private void createNegateEvent() {
        Button button = (Button) findViewById(R.id.btn_negate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayer.negate();
            }
        });
    }

    private void createEqualEvent() {
        Button button = (Button) findViewById(R.id.btn_operation_equal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!displayer.isLastCharacterSymbol()){
                    try{
                        calculator.storeNumber(displayer.getData());
                        calculator.resetStoredNumberPointer();
                        displayer.getFlags().setEqualAllowed(false);
                        calculator.calculate();
                        String result = calculator.getResult().toString();
                        result = formatResult(result);
                        displayer.set(result);
                        calculator.storeNumber(result);
                        if (displayer.getData().contains(".")) {
                            displayer.getFlags().setDotAllowed(false);
                        } else {
                            displayer.getFlags().setDotAllowed(true);
                        }
                    } catch(NumberFormatException e){
                        handleBadInput();
                    }

                }
            }
        });
    }

    private void handleBadInput(){
        displayer.set("Bad input");
        displayer.getFlags().setError(true);
        calculator.clear();
    }

    private String formatResult(String result){
        if(result.charAt(result.length()-2) == '.'
                && result.charAt(result.length()-1) == '0'){
            result = result.substring(0, result.length()-2);
        }
        return result;
    }

}
