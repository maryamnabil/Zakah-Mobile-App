package com.example.myapplication2.Twitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.example.myapplication2.R;

import java.util.ArrayList;

public class TweetController extends PagerAdapter {
    private Context context;
    private ArrayList<Tweet> tweetsArrayList;
    ViewHolder holder;
    // Constructor
    public TweetController(Context context, ArrayList<Tweet> tweetsArrayList) {
        this.context = context;
        this.tweetsArrayList = tweetsArrayList;
    }

    @Override
    public int getCount() {
        return tweetsArrayList.size();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.tweet, null, true);

            holder.text_tweet = (TextView) convertView.findViewById(R.id.text_tweet);
            holder.created_on = (TextView) convertView.findViewById(R.id.created_on);
            holder.n_likes = (TextView) convertView.findViewById(R.id.number_likes);
            holder.n_retweets = (TextView) convertView.findViewById(R.id.number_retweets);

            convertView.setTag(holder);

        holder.text_tweet.setText(tweetsArrayList.get(position).getText());
        holder.created_on.setText(tweetsArrayList.get(position).getCreated_on());
        holder.n_retweets.setText(tweetsArrayList.get(position).getNumber_retweets());
        holder.n_likes.setText(tweetsArrayList.get(position).getNumber_likes());
        container.addView(convertView, 0);
        return convertView;

    }

    private class ViewHolder {

        protected TextView text_tweet, n_likes, n_retweets,created_on;
    }

}
