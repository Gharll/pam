package com.example.przemek.calculator;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Przemek on 23.03.2018.
 */

public abstract class CalculatorActivity extends Activity {

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(getLayoutResourceId());
    }

    protected abstract int getLayoutResourceId();
}
