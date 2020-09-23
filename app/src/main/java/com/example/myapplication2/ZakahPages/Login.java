package com.example.myapplication2.ZakahPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.example.myapplication2.R;

public class Login extends AppCompatActivity {
    private LinearLayout username;
    private LinearLayout password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username =findViewById(R.id.usernameLayout);
        password = findViewById(R.id.passwordLayout);
        //set actionbar title
        getSupportActionBar().setTitle(getResources().getString(R.string.Login));
        checkLanguageDisplay();
    }

    public void checkLanguageDisplay(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Log.i("info",settings.getString("locale",""));
        if (settings.getString("locale","").equals("ar") ) {
            username.setBackgroundResource(R.drawable.input_ar);
            password.setBackgroundResource(R.drawable.input_ar);

        }
        else {
            username.setBackgroundResource(R.drawable.input_en);
            password.setBackgroundResource(R.drawable.input_en);
        }

    }
    public void Login(View view){
//        throw new RuntimeException("Test Crash"); // Force a crash
        Intent myIntent = new Intent(Login.this, OTP.class);
        Login.this.startActivity(myIntent);

    }

}
