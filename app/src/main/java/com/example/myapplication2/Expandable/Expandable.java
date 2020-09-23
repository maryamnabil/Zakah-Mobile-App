package com.example.myapplication2.Expandable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.myapplication2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Expandable extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandableAdapter expandableAdapter;
    private List<String> headerDList;
    private HashMap<String,List<String>> listHashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);
        expandableListView = findViewById(R.id.lv_exp);
        initData();
        expandableAdapter = new ExpandableAdapter(this,headerDList,listHashMap);
        expandableListView.setAdapter(expandableAdapter);
    }
    public void initData(){
        headerDList= new ArrayList<>();
        listHashMap = new HashMap<>();
        headerDList.add("users");
        headerDList.add("admins");
        List<String> users = new ArrayList<>();
        users.add("Nour");
        users.add("Mohammed");
        users.add("Moaz");
        List<String> admins = new ArrayList<>();
        admins.add("Maryam");
        admins.add("Maram");
        listHashMap.put(headerDList.get(0),users);
        listHashMap.put(headerDList.get(1),admins);
    }
}
