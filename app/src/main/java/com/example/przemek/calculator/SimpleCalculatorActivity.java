package com.example.przemek.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Map;

public class SimpleCalculatorActivity extends CalculatorActivity{


    protected SimpleCalculator simpleCalculator;
    private Symbols symbols = new Symbols();

    @Override
    protected void onCreate(Bundle outState) {
        super.onCreate(outState);
        initializeObject();
        initializeEvent();

        if (outState != null) {
            simpleCalculator.displayer.restore(outState);
            simpleCalculator.dataStorage.restore(outState);
        }
    }


    @Override
    protected int getLayoutResourceId(){
        return R.layout.activity_simple_calculator;
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        simpleCalculator.displayer.save(savedInstanceState);
        simpleCalculator.dataStorage.save(savedInstanceState);
    }

    protected void initializeObject() {
        TextView textView = (TextView) findViewById(R.id.tv_displayer);
        simpleCalculator = new SimpleCalculator(new Displayer(textView));
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
                    if(simpleCalculator.displayer.getFlags().isError()){
                        simpleCalculator.handleError();
                    } else {
                        simpleCalculator.handleNumber(number);
                    }

                }
            });
        }
    }

    private void createButtonZeroEvent(){
        Button button = (Button) findViewById(R.id.btn_number_0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(simpleCalculator.displayer.getFlags().isError()){
                    simpleCalculator.handleError();
                } else {
                    simpleCalculator.handleZeroNumber();
                }


            }
        });
    }


    private void createBackspaceEvent() {
        Button button = (Button) findViewById(R.id.btn_bksp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(simpleCalculator.displayer.getFlags().isError()){
                    simpleCalculator.handleError();
                } else {
                    simpleCalculator.handleBackspace();
                }
            }
        });
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                simpleCalculator.displayer.setInitialData();
                return true;
            }
        });
    }

    private void createClearDisplayerEvent() {
        Button button = (Button) findViewById(R.id.btn_clear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(simpleCalculator.displayer.getFlags().isError()){
                    simpleCalculator.handleError();
                } else {
                    simpleCalculator.handleClear();
                }
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
                    if(simpleCalculator.displayer.getFlags().isError()){
                        simpleCalculator.handleError();
                    } else {
                        simpleCalculator.handleOperation(operationSymbol);
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
                if(simpleCalculator.displayer.getFlags().isError()){
                    simpleCalculator.handleError();
                } else {
                    simpleCalculator.handleDot();
                }
            }
        });
    }

    private void createNegateEvent() {
        Button button = (Button) findViewById(R.id.btn_negate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(simpleCalculator.displayer.getFlags().isError()){
                    simpleCalculator.handleError();
                } else {
                    simpleCalculator.handleNegate();
                }
            }
        });
    }

    protected void createEqualEvent() {
        Button button = (Button) findViewById(R.id.btn_operation_equal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(simpleCalculator.displayer.getFlags().isError()){
                    simpleCalculator.handleError();
                } else {
                    simpleCalculator.handleEqual();
                }
            }
        });
    }
}
