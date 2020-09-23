package com.example.myapplication2.Spinner;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication2.DynamicPaging.DynamicListAdapter;
import com.example.myapplication2.DynamicPaging.DynamicListItem;
import com.example.myapplication2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {
    Context context;
    private ArrayList<SpinnerItem> itemsArray;
    LayoutInflater inflter;


    public SpinnerAdapter(Context applicationContext, ArrayList<SpinnerItem> itemsArray) {
        this.context = applicationContext;
        this.itemsArray = itemsArray;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return itemsArray.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinner_item, null);
        CheckBox checkBox =  view.findViewById(R.id.check_item);
        TextView title = (TextView) view.findViewById(R.id.textView);
        checkBox.setChecked(itemsArray.get(i).getChecked());
        title.setText(itemsArray.get(i).getTitle());
        return view;
    }
}

