package com.example.myapplication2.ZakahPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication2.R;

import java.util.Locale;

public class Settings extends AppCompatActivity {

    public void onToggleClicked(View view) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Log.i("info","current Language is"+settings.getString("locale",""));
        if(settings.getString("locale","").equals("en") ||settings.getString("locale","").equals("")  ){
            ChangeLang("ar");
            Log.i("info","********************* changed to ar");
        } else {
            ChangeLang("en");
            Log.i("info","********************* changed to en");
        }
    }
    public void ChangeLang(String Lang){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Locale locale = new Locale(Lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        settings.edit().putString("locale",Lang).commit();
        finish();
        Intent refresh = new Intent(this, Login.class);
        startActivity(refresh);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle(getResources().getString(R.string.Settings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
