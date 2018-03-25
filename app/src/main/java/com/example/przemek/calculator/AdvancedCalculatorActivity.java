package com.example.przemek.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdvancedCalculatorActivity extends SimpleCalculatorActivity {

    private AdvancedCalculator advancedCalculator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
        initializeObject();
        initializeEvent();
    }

    @Override
    protected void initializeObject(){
        super.initializeObject();
        advancedCalculator = new AdvancedCalculator(super.displayer);

    }

    @Override
    protected int getLayoutResourceId(){
        return R.layout.activity_advanced_calculator;
    }

    @Override
    protected void initializeEvent(){
        super.initializeEvent();
        createSinEvent();
        createCosEvent();
        createTanEvent();
        createLogEvent();
        createLnEvent();
        createSqrtEvent();
        createPowerEvent();
        createPowerSquareEvent();
        createPercentEvent();
    }

    void createSinEvent(){
        Button button = (Button) findViewById(R.id.btn_operation_sin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advancedCalculator.handleSinEvent();
            }
        });

    }

    void createCosEvent(){
        Button button = (Button) findViewById(R.id.btn_operation_cos);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advancedCalculator.handleCosEvent();
            }
        });
    }

    void createTanEvent(){
        Button button = (Button) findViewById(R.id.btn_operation_tan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advancedCalculator.handleTanEvent();
            }
        });
    }

    void createLogEvent(){
        Button button = (Button) findViewById(R.id.btn_operation_log);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advancedCalculator.handleLogEvent();
            }
        });
    }

    void createLnEvent(){
        Button button = (Button) findViewById(R.id.btn_operation_ln);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advancedCalculator.handleLnEvent();
            }
        });
    }

    void createSqrtEvent(){
        Button button = (Button) findViewById(R.id.btn_operation_sqrt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advancedCalculator.handleSqrtEvent();
            }
        });
    }

    void createPowerSquareEvent(){
        Button button = (Button) findViewById(R.id.btn_operation_power_two);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advancedCalculator.handlePowerSquareEvent();
            }
        });
    }

    void createPowerEvent(){
        Button button = (Button) findViewById(R.id.btn_operation_power_y);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advancedCalculator.handlePowerEvent();
            }
        });
    }

    void createPercentEvent(){
        Button button = (Button) findViewById(R.id.btn_operation_percent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advancedCalculator.handlePercentEvent();
            }
        });
    }
}
