package com.example.myapplication2.Dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.myapplication2.R;

public class Dialog extends AppCompatActivity {
    protected Button showDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        showDialog=findViewById(R.id.show_dialogBtn);
        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePopupWindow(v);

            }
        });
    }

    private void initiatePopupWindow(View v) {
        try {
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup, (ViewGroup) findViewById(R.id.container));
           final PopupWindow pw = new PopupWindow(layout, 600, 500, true);
            pw.showAtLocation(v, Gravity.CENTER, 0, 0);
            Button close =layout.findViewById(R.id.exit_btn);
            close.setOnClickListener(new View.OnClickListener() {
                public void onClick(View popupView) {
                    pw.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }
