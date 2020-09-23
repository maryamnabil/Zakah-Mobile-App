package com.example.myapplication2.Chart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication2.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Chart extends AppCompatActivity {

    private float[] ydata={12.9f,60.6f,40.0f};
    private String[] xdata={"admins","users","Vip users"};
    PieChart pieChart;
    BarChart barChart;
    private ArrayList employeesNumber;
    public ArrayList year;
    Button pieBtn;
    Button barBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        pieChart =findViewById(R.id.pie);
        barChart = findViewById(R.id.bar);
        pieBtn = findViewById(R.id.pie_btn);
        barBtn = findViewById(R.id.bar_btn);
        // Pie Btn listener
        pieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pie();
            }
        });
        // Bar btn listener
        barBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar();
            }
        });
    }

    private void bar() {
        pieChart.clear();
        employeesNumber = new ArrayList();
        employeesNumber.add(new BarEntry(945f, 0));
        employeesNumber.add(new BarEntry(1040f, 1));
        employeesNumber.add(new BarEntry(1133f, 2));
        year = new ArrayList();
        year.add("2008");
        year.add("2009");
        year.add("2010");
        addBarDataSet();
    }

    private void Pie() {
        barChart.clear();
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("System users");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);
        addPieDataSet();
    }

    private void addBarDataSet() {
        BarDataSet bardataset = new BarDataSet(employeesNumber, "No Of Employee");
        barChart.animateY(5000);
        BarData data = new BarData(bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(data);
    }

    private void addPieDataSet() {
        ArrayList<PieEntry> yEntries = new ArrayList<>();
        ArrayList<String> xEntries = new ArrayList<>();
        for (int i =0;i< ydata.length;i++){
            yEntries.add(new PieEntry(ydata[i],i));
        }
        for (int i =0;i< xdata.length;i++){
            xEntries.add(xdata[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntries,"users");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //colors
        ArrayList<Integer> colors= new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        pieDataSet.setColors(colors);

        //set data
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
