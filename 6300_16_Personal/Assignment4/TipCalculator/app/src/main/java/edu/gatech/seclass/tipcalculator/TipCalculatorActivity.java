package edu.gatech.seclass.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.tipcalculator.R;

import java.util.regex.*;

public class TipCalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonCompute;
    private TextView fifteenPercentTipValue, twentyPercentTipValue,
            twentyfivePercentTipValue, fifteenPercentTotalValue,
            twentyPercentTotalValue, twentyfivePercentTotalValue;
    private EditText checkAmountValue, partySizeValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_tip);
        init();
    }

    private void init() {
        buttonCompute = (Button) findViewById(R.id.buttonCompute);
        checkAmountValue = (EditText) findViewById(R.id.checkAmountValue);
        partySizeValue = (EditText) findViewById(R.id.partySizeValue);

        fifteenPercentTipValue = (TextView) findViewById(R.id.fifteenPercentTipValue);
        twentyPercentTipValue = (TextView) findViewById(R.id.twentyPercentTipValue);
        twentyfivePercentTipValue = (TextView) findViewById(R.id.twentyfivePercentTipValue);
        fifteenPercentTotalValue = (TextView) findViewById(R.id.fifteenPercentTotalValue);
        twentyPercentTotalValue = (TextView) findViewById(R.id.twentyPercentTotalValue);
        twentyfivePercentTotalValue = (TextView) findViewById(R.id.twentyfivePercentTotalValue);

        buttonCompute.setOnClickListener(this);

    }

    public void onClick(View view) {
        String num1 = checkAmountValue.getText().toString();
        String num2 = partySizeValue.getText().toString();

        final String Digits     = "(\\p{Digit}+)";
        final String HexDigits  = "(\\p{XDigit}+)";
// an exponent is 'e' or 'E' followed by an optionally
// signed decimal integer.
        final String Exp        = "[eE][+-]?"+Digits;
        final String fpRegex    =
                ("[\\x00-\\x20]*"+ // Optional leading "whitespace"
                        "[+-]?(" +         // Optional sign character
                        "NaN|" +           // "NaN" string
                        "Infinity|" +      // "Infinity" string

                        // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
                        "((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|"+

                        // . Digits ExponentPart_opt FloatTypeSuffix_opt
                        "(\\.("+Digits+")("+Exp+")?)|"+

                        // Hexadecimal strings
                        "((" +
                        // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
                        "(0[xX]" + HexDigits + "(\\.)?)|" +

                        // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
                        "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +

                        ")[pP][+-]?" + Digits + "))" +
                        "[fFdD]?))" +
                        "[\\x00-\\x20]*");// Optional trailing "whitespace"

        switch (view.getId()){
            case R.id.buttonCompute:
                if (!Pattern.matches(fpRegex, num1) || !Pattern.matches(fpRegex, num2)) {
                    // Check if input string can be parsed as double
                    //fifteenPercentTipValue.setText(String.valueOf(0.11111));
                    Toast.makeText(getApplicationContext(), "Empty or incorrect value(s)!",
                            Toast.LENGTH_SHORT).show();



                } else {
                    double dnum1 = Double.parseDouble(num1);
                    double dnum2 = Double.parseDouble(num2);

                    if (dnum1 < 0.0 || dnum2 <= 0.0) {
                        Toast.makeText(getApplicationContext(), "Empty or incorrect value(s)!",
                                Toast.LENGTH_SHORT).show();

                    } else {
                        double partValue = dnum1 / dnum2;
                        int tip15 = (int) (Math.round(partValue * 0.15));
                        int tip20 = (int) (Math.round(partValue * 0.2));
                        int tip25 = (int) (Math.round(partValue * 0.25));

                        int total15 = (int) (Math.round(partValue * 1.15));
                        int total20 = (int) (Math.round(partValue * 1.2));
                        int total25 = (int) (Math.round(partValue * 1.25));
                        fifteenPercentTipValue.setText(String.valueOf(tip15));
                        twentyPercentTipValue.setText(String.valueOf(tip20));
                        twentyfivePercentTipValue.setText(String.valueOf(tip25));
                        fifteenPercentTotalValue.setText(String.valueOf(total15));
                        twentyPercentTotalValue.setText(String.valueOf(total20));
                        twentyfivePercentTotalValue.setText(String.valueOf(total25));
                    }
                }

                break;

        }

    }

}



