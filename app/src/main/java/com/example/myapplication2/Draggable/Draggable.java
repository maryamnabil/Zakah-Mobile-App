package com.example.myapplication2.Draggable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;

import android.widget.ListView;


import com.example.myapplication2.R;
public class Draggable extends AppCompatActivity {

    public MyArrayAdapter mAdapter;
    public ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draggable);
        initiateConponenets();
        setListListeners();

    }

    private void initiateConponenets(){
        mListView = findViewById(R.id.listall);
        mAdapter = new MyArrayAdapter(this, R.layout.draggable_item);

        for (int i = 0; i < 100; i++) {
            mAdapter.add("Dummy Item ".concat(String.valueOf(i)));
        }

        mListView.setAdapter(mAdapter);

    }

    private void setListListeners(){
        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (!mAdapter.mSortable) {
                    return false;
                }
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        int position = mListView.pointToPosition((int) event.getX(), (int) event.getY());
                        if (position < 0) {
                            break;
                        }
                        if (position != mAdapter.mPosition) {
                            mAdapter.mPosition = position;
                            mAdapter.remove(mAdapter.mDragString);
                            mAdapter.insert(mAdapter.mDragString, mAdapter.mPosition);
                        }
                        return true;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_OUTSIDE: {
                        mAdapter.stopDrag();
                        mAdapter.notifyDataSetChanged();
                        return true;
                    }
                }
                return false;
            }
        });

    }



}

