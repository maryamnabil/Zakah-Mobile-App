package com.example.myapplication2.CardsTwitter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication2.Twitter.Tweet;

import java.util.ArrayList;

public class CardsTwitterAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Tweet> tweetsArrayList;
    ViewHolder holder;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    private class ViewHolder {

        protected TextView text_tweet, n_likes, n_retweets,created_on;
    }
}
