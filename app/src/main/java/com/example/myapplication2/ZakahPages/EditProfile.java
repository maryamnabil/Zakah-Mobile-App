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
import com.example.myapplication2.ui.home.HomeFragment;

public class EditProfile extends AppCompatActivity {

    public void SaveProfile(View view){
        Intent myIntent = new Intent(EditProfile.this, HomeFragment.class);
        EditProfile.this.startActivity(myIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        getSupportActionBar().setTitle(getResources().getString(R.string.editProfile));
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Log.i("info",settings.getString("locale",""));
        if (settings.getString("locale","").equals("ar") ) {
            LinearLayout lin1 =(LinearLayout) findViewById(R.id.lin1);
            LinearLayout lin2 =(LinearLayout) findViewById(R.id.lin2);
            LinearLayout lin3 =(LinearLayout) findViewById(R.id.lin3);
            LinearLayout lin4 =(LinearLayout) findViewById(R.id.lin4);
            LinearLayout lin5 =(LinearLayout) findViewById(R.id.lin5);
            lin1.setBackgroundResource(R.drawable.input_ar);
            lin2.setBackgroundResource(R.drawable.input_ar);
            lin3.setBackgroundResource(R.drawable.input_ar);
            lin4.setBackgroundResource(R.drawable.input_ar);
            lin5.setBackgroundResource(R.drawable.input_ar);

        }
        else {
            LinearLayout lin1 =(LinearLayout) findViewById(R.id.lin1);
            LinearLayout lin2 =(LinearLayout) findViewById(R.id.lin2);
            LinearLayout lin3 =(LinearLayout) findViewById(R.id.lin3);
            LinearLayout lin4 =(LinearLayout) findViewById(R.id.lin4);
            LinearLayout lin5 =(LinearLayout) findViewById(R.id.lin5);
            lin1.setBackgroundResource(R.drawable.input_en);
            lin2.setBackgroundResource(R.drawable.input_en);
            lin3.setBackgroundResource(R.drawable.input_en);
            lin4.setBackgroundResource(R.drawable.input_en);
            lin5.setBackgroundResource(R.drawable.input_en);

        }
    }

    public void GotoNotifications(View view){
        Intent myIntent = new Intent(EditProfile.this, Notifications.class);
        EditProfile.this.startActivity(myIntent);
    }


}
