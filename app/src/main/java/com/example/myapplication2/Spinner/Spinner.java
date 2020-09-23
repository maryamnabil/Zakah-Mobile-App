package com.example.myapplication2.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.myapplication2.DynamicPaging.DynamicListAdapter;
import com.example.myapplication2.R;

import java.util.ArrayList;

public class Spinner extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public android.widget.Spinner spinner;
    private SpinnerAdapter spinnerAdapter;
    private ArrayList<SpinnerItem> itemsArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        itemsArray = new ArrayList<>();
        spinner= findViewById(R.id.custom_spinner);
        SpinnerItem sp1= new SpinnerItem("choice 1",false);
        SpinnerItem sp2 = new SpinnerItem("choice 2",true);
        itemsArray.add(sp1);
        itemsArray.add(sp2);
        spinnerAdapter = new SpinnerAdapter(this,itemsArray);
        spinner.setAdapter(spinnerAdapter);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
