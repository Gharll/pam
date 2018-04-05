package com.example.przemek.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureActivity(R.id.btn_simple, SimpleCalculatorActivity.class);
        configureActivity(R.id.btn_advanced, AdvancedCalculatorActivity.class);
        configureActivity(R.id.btn_about, AboutActivity.class);
        handleExit();
    }

    private void configureActivity(int resource, final Class activityClass){
        Button button = (Button) findViewById(resource);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, activityClass));
            }
        });
    }

    private void handleExit(){
        Button exitButton = (Button) findViewById(R.id.btn_exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }

}
