package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText billTotal;
    private EditText numPeople;
    private TextView tipamount;
    private TextView totalWithTip;
    private TextView pPt;
    private TextView ovrg;
    private RadioGroup rad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ovrg = findViewById(R.id.overage);
        pPt = findViewById(R.id.perPersTot);
        numPeople = findViewById(R.id.noPeople);
        billTotal = findViewById(R.id.billTotal);
        tipamount = findViewById(R.id.calculatedTip);
        totalWithTip = findViewById(R.id.totalTip);
        rad = findViewById(R.id.radButtons);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("TPP", pPt.getText().toString());
        outState.putString("TA", tipamount.getText().toString());
        outState.putString("OVG", ovrg.getText().toString());
        outState.putString("TWT", totalWithTip.getText().toString());
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pPt.setText(savedInstanceState.getString("TPP"));
        tipamount.setText(savedInstanceState.getString("TA"));
        ovrg.setText(savedInstanceState.getString("OVG"));
        totalWithTip.setText(savedInstanceState.getString("TWT"));


    }

    public void calculations(View v) {

        if (billTotal.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Total Bill with Tax", Toast.LENGTH_SHORT).show();
            rad.clearCheck();
            return;
        }
        if (numPeople.getText().toString().isEmpty() && !(billTotal.getText().toString().isEmpty())) {
            Toast.makeText(this, "Please Enter The Number of People", Toast.LENGTH_SHORT).show();
            return;
        }
        if ((numPeople.getText().toString().isEmpty()) && (billTotal.getText().toString().isEmpty())) {
            Toast.makeText(this, "Please Enter Total Bill with Tax", Toast.LENGTH_SHORT).show();
            rad.clearCheck();
            return;
        }
        if ((rad.getCheckedRadioButtonId() == -1) && !(billTotal.getText().toString().isEmpty())) {
            Toast.makeText(this, "Please select a Tip Percentage above", Toast.LENGTH_SHORT).show();
            return;
        }
        if (numPeople.getText().toString().charAt(0) == '0') {
            numPeople.setText("");
            Toast.makeText(this, "Number of People should be greater than zero.", Toast.LENGTH_SHORT).show();
            return;
        } else {

            int nump = Integer.parseInt(numPeople.getText().toString());
            double totWtip = Double.parseDouble(totalWithTip.getText().toString().substring(1));
            double totPerP = totWtip / nump;
            BigDecimal roundedTpp;
            roundedTpp = new BigDecimal(totPerP).setScale(1, RoundingMode.CEILING);
            pPt.setText(String.format("$%.2f", roundedTpp));
            double overage = ((nump * Double.parseDouble(roundedTpp.toString())) - totWtip);
            ovrg.setText(String.format("$%.2f", overage));
        }
    }
    public void radioTip(View v){
        if (billTotal.getText().toString().isEmpty()){
            Toast.makeText( this, "Please Enter Total Bill with Tax", Toast.LENGTH_SHORT).show();
            rad.clearCheck();
            return;
        }
        if (v.getId() == R.id.twelve) {
            do12(v);
        } else if (v.getId() == R.id.fifteen){
            do15(v);
        } else if (v.getId() == R.id.eighteen){
            do18(v);
        }else if (v.getId() == R.id.twenty){
            do20(v);
        }

    }
    public void clearScreen(View v){
        rad.clearCheck();
        billTotal.setText("");
        tipamount.setText("");
        totalWithTip.setText("");
        numPeople.setText("");
        pPt.setText("");
        ovrg.setText("");
    }
    public void do12(View v){
        double billTot = Double.parseDouble(billTotal.getText().toString());
        double tip_amt= (billTot * 0.12);
        double finaltotal = billTot + tip_amt;
        totalWithTip.setText(String.format("$%.2f",finaltotal));
        tipamount.setText(String.format("$%.2f",tip_amt));
    }

    public void do15(View v){
        double billTot = Double.parseDouble(billTotal.getText().toString());
        double tip_amt= (billTot * 0.15);
        double finaltotal = billTot + tip_amt;
        totalWithTip.setText(String.format("$%.2f",finaltotal));
        tipamount.setText(String.format("$%.2f",tip_amt));
    }

    public void do18(View v){
        double billTot = Double.parseDouble(billTotal.getText().toString());
        double tip_amt= (billTot * 0.18);
        double finaltotal = billTot + tip_amt;
        totalWithTip.setText(String.format("$%.2f",finaltotal));
        tipamount.setText(String.format("$%.2f",tip_amt));
    }

    public void do20(View v){
        double billTot = Double.parseDouble(billTotal.getText().toString());
        double tip_amt= (billTot * 0.20);
        double finaltotal = billTot + tip_amt;
        totalWithTip.setText(String.format("$%.2f",finaltotal));
        tipamount.setText(String.format("$%.2f",tip_amt));
    }
}