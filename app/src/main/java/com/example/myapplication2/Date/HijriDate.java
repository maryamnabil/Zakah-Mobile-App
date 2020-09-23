package com.example.myapplication2.Date;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.myapplication2.R;
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;

import net.alhazmy13.hijridatepicker.date.hijri.HijriDatePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class HijriDate extends AppCompatActivity  {
    private Calendar cal_hij;
    private Button btn;
    private Button convert;
    private HijriDatePickerDialog dialog;
    private TextView date_txt;
    private TextView resulted_date;
    private EditText input_date;
    private ToggleButton toggleButton;
    private Boolean flag_fromGerToHij=true;
    private Integer day_to_convert;
    private Integer month_to_convert;
    private Integer year_to_convert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hijridate);
        btn = (Button) findViewById(R.id.date_picker_btn);
        date_txt = (TextView) findViewById(R.id.date_view);
        resulted_date= findViewById(R.id.resulted_date);
        input_date= findViewById(R.id.input_date);
        convert = findViewById(R.id.convert);
        toggleButton=findViewById(R.id.toggle_btn);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag_fromGerToHij)
                    flag_fromGerToHij=true;
                else
                    flag_fromGerToHij=false;
            }
        });
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splitDate();
                if(flag_fromGerToHij){
                    fromGreogianToHijri();
                }
                else{
                    fromHijriToGerorgian();

                }
            }
        });;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal_hij = new UmmalquraCalendar();
                dialog =  HijriDatePickerDialog.newInstance(
                        new HijriDatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(HijriDatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                date_txt.setText(String.valueOf(year)+"/"+ String.valueOf(monthOfYear+1)+ "/"+ String.valueOf(dayOfMonth));
                            }
                        },
                        cal_hij.get(UmmalquraCalendar.YEAR),
                        cal_hij.get(UmmalquraCalendar.MONTH),
                        cal_hij.get(UmmalquraCalendar.DAY_OF_MONTH));
                dialog.show(getFragmentManager(), "HijriDatePickerDialog");

    }

    });


}
    protected void fromHijriToGerorgian() {
        Calendar uCal = new UmmalquraCalendar(year_to_convert, month_to_convert, day_to_convert);
        GregorianCalendar gCal = new GregorianCalendar();
        gCal.setTime(uCal.getTime());
        gCal.get(Calendar.YEAR);
        gCal.get(Calendar.MONTH);
        gCal.get(Calendar.DAY_OF_MONTH);
        int month = gCal.get(Calendar.MONTH)+1;
        Log.i("info ","Georgian Date is"+ gCal.get(Calendar.DAY_OF_MONTH)+"/"+ String.valueOf(month) +"/"+ gCal.get(Calendar.YEAR) );
        resulted_date.setText(gCal.get(Calendar.DAY_OF_MONTH)+"/"+ String.valueOf(month) +"/"+ gCal.get(Calendar.YEAR) );

    }


    protected void splitDate(){
        String date = input_date.getText().toString();
        Log.i("info",date);
        String[] tokens = date.split("/");
        day_to_convert= Integer.valueOf(tokens[0]);
        month_to_convert= Integer.valueOf(tokens[1])-1;
        year_to_convert= Integer.valueOf(tokens[2]);
        Log.i("day",day_to_convert.toString());
        Log.i("month",month_to_convert.toString());
        Log.i("year",year_to_convert.toString());

    }
    protected void fromGreogianToHijri(){
            GregorianCalendar gCal = new GregorianCalendar(year_to_convert, month_to_convert, day_to_convert);
            Calendar uCal = new UmmalquraCalendar();
            uCal.setTime(gCal.getTime());
            uCal.get(Calendar.YEAR);
            uCal.get(Calendar.MONTH);
            uCal.get(Calendar.DAY_OF_MONTH);
//        uCal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH);
            int month = uCal.get(Calendar.MONTH)+1;
            Log.i("info ","Hijri Date is"+ uCal.get(Calendar.DAY_OF_MONTH)+"/"+ String.valueOf(month) +"/"+ uCal.get(Calendar.YEAR) );
            resulted_date.setText(uCal.get(Calendar.DAY_OF_MONTH)+"/"+ String.valueOf(month) +"/"+ uCal.get(Calendar.YEAR) );

    }
}