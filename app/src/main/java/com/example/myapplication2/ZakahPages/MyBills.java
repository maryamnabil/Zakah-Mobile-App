package com.example.myapplication2.ZakahPages;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication2.R;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;

import com.example.myapplication2.ui.main.SectionsPagerAdapter;

public class MyBills extends AppCompatActivity{


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void goToNotifications(View view){
        Intent myIntent = new Intent(MyBills.this, Notifications.class);
        MyBills.this.startActivity(myIntent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybills);
        getSupportActionBar().setTitle(getResources().getString(R.string.MyBills));
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}