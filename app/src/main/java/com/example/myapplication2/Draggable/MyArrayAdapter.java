package com.example.myapplication2.Draggable;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication2.R;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<String> {

    public ArrayList<String> mStrings = new ArrayList<String>();
    public LayoutInflater mInflater;
    public int mLayout;
    public boolean mSortable = false;
    public String mDragString;
    public int mPosition = -1;

    public MyArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mLayout = textViewResourceId;
    }

    public void startDrag(String string) {
        mPosition = -1;
        mSortable = true;
        mDragString = string;
    }

    public void stopDrag() {
        mPosition = -1;
        mSortable = false;
        mDragString = null;
    }


    @Override
    public void add(String row) {
        super.add(row);
        mStrings.add(row);
    }

    @Override
    public void insert(String row, int position) {
        super.insert(row, position);
        mStrings.add(position, row);
    }

    @Override
    public void remove(String row) {
        super.remove(row);
        mStrings.remove(row);
    }

    @Override
    public void clear() {
        super.clear();
        mStrings.clear();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(this.mLayout, null);
            assert view != null;
            holder = new ViewHolder();
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.handle = (TextView) view.findViewById(R.id.handle);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final String string = mStrings.get(position);
        holder.title.setText(string);
        holder.handle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    startDrag(string);
                    notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });

        if (mDragString != null && mDragString.equals(string)) {
            view.setBackgroundColor(Color.parseColor("#9933b5e5"));
        } else {
            view.setBackgroundColor(Color.TRANSPARENT);
        }

        return view;
    }

    static class ViewHolder {
        TextView title;
        TextView handle;
    }
}
