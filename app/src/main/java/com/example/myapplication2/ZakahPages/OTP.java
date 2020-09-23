package com.example.myapplication2.ZakahPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication2.R;

public class OTP extends AppCompatActivity {
    private EditText otp1;
    private EditText otp2;
    private EditText otp3;
    private EditText otp4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        otp1 =  findViewById(R.id.otp1);
        otp2 =  findViewById(R.id.otp2);
        otp3 =  findViewById(R.id.otp3);
        otp4 =  findViewById(R.id.otp4);
        // Back Arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        otp1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() ==1) {
                    otp2.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otp3.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otp4.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        });
    }

    public void Validate(View view){
        Intent myIntent = new Intent(OTP.this, Home.class);
        startActivity(myIntent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
