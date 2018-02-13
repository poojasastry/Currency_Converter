package com.example.pooja.assignment1.convert;

import android.support.v7.app.AppCompatActivity;
import android.content.res.Configuration;
import android.text.TextWatcher;
import android.widget.EditText;
import java.text.DecimalFormat;
import android.text.Editable;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText valueOfINR;
    EditText valueOfUSD;
    DecimalFormat setPrecision = new DecimalFormat(("0.00"));
    String getINR;
    String getUSD;
    String storedINR;
    String storedUSD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        valueOfINR = this.findViewById(R.id.valueOfINR);
        valueOfUSD = this.findViewById(R.id.valueOfUSD);

        valueOfINR.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    valueOfUSD.setText("");
                }
            }
        });

        valueOfUSD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    valueOfINR.setText("");
                }
            }
        });

        //Below code works too,however only one of the fields are shown at a time.
        /*valueOfINR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    valueOfUSD.setText("");
                }
            }
        });

        valueOfUSD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    valueOfINR.setText("");
                }
            }
        });*/
    }

    public void convertINRToUSD() {
        Log.i("ps", "INRToUSDConvert");
        Double fetchINRValue = Double.parseDouble(valueOfINR.getText().toString());
        Double convertedINRValue = 0.016 * fetchINRValue;
        valueOfUSD.setText(setPrecision.format(convertedINRValue));
    }

    public void convertUSDToINR() {
        Log.i("ps", "USDToINRConvert");
        Double fetchUSDValue = Double.parseDouble(valueOfUSD.getText().toString());
        Double convertedUSDValue = 63.89 * fetchUSDValue;
        valueOfINR.setText(setPrecision.format(convertedUSDValue));
    }

    public void convertCurrency(View button) {
        Log.i("ps", "convert");
        if ((valueOfINR.getText().toString()).matches("[0-9]+.?[0-9]*")) {
            convertINRToUSD();
        }
        else if (((valueOfUSD.getText().toString()).matches("[0-9]+.?[0-9]*"))) {
            convertUSDToINR();
        }
        else if ((valueOfINR.getText().toString()).matches("[.]+")) {
            Toast.makeText(getApplicationContext(), "Please enter a valid INR currency.", Toast.LENGTH_LONG).show();
        }
        else if ((valueOfUSD.getText().toString()).matches("[.]+")) {
            Toast.makeText(getApplicationContext(), "Please enter a valid USD currency.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Please enter an INR/USD currency value to convert.", Toast.LENGTH_LONG).show();
        }
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i("ps", "stateSaved");
        storedINR = valueOfINR.getText().toString();
        storedUSD = valueOfUSD.getText().toString();
        outState.putString(getINR,storedINR);
        outState.putString(getUSD,storedUSD);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("ps", "stateRestored");
        storedINR = savedInstanceState.getString(getINR);
        storedUSD = savedInstanceState.getString(getUSD);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}


