package com.example.myapplication2.card;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.example.myapplication2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Card extends AppCompatActivity {
    private ArrayList<String> sList = new ArrayList<>();
    private ToggleButton toggle;
    private ListView lv;
    private ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        fillList();
        toggle= findViewById(R.id.toggle_btn);
        lv= findViewById(R.id.Listv);
        arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, sList);
        lv.setAdapter(arrayAdapter);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggle.getText().equals("Ascending")){
                    sortDescending();
                    arrayAdapter.notifyDataSetChanged();
                    lv.setAdapter(arrayAdapter);

                } else {
                    sortAscending();
                    arrayAdapter.notifyDataSetChanged();
                    lv.setAdapter(arrayAdapter);

                }
            }
        });
    }

    private void sortAscending(){
        Collections.sort(sList, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });
    }

    private void sortDescending(){
        Collections.sort(sList, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return rhs.compareTo(lhs);
            }
        });
    }
    private void fillList(){
        sList.add("chandru");
        sList.add("mani");
        sList.add("vivek");
        sList.add("david");

    }
}
