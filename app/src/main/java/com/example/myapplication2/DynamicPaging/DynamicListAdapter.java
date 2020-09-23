package com.example.myapplication2.DynamicPaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DynamicListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DynamicListItem> itemsArray;


    // constructor
    // Constructor
    public DynamicListAdapter(Context context, ArrayList<DynamicListItem> itemsArray) {

        this.context = context;
        this.itemsArray = itemsArray;
    }
    @Override
    public int getCount() {
        return itemsArray.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder {

        protected TextView displayNameTv, locationTv ,websiteUrlTv , userTypeUrlTv;
        protected ImageView profileUrlIv;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dynamiclist_item, null, true);

            holder.profileUrlIv = (ImageView) convertView.findViewById(R.id.iv);
            holder.displayNameTv = (TextView) convertView.findViewById(R.id.displayNameTv);
            holder.websiteUrlTv = (TextView) convertView.findViewById(R.id.websiteUrlTv);
            holder.locationTv = (TextView) convertView.findViewById(R.id.locationTv);
            holder.userTypeUrlTv = (TextView) convertView.findViewById(R.id.userTypeTv);
            convertView.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        Picasso.get().load(itemsArray.get(position).getProfileURL()).into(holder.profileUrlIv);
        holder.displayNameTv.setText(itemsArray.get(position).getDisplayName());
        holder.websiteUrlTv.setText(itemsArray.get(position).getWebsiteUrl());
        holder.locationTv.setText(itemsArray.get(position).getLocation());
        holder.userTypeUrlTv.setText(itemsArray.get(position).getUserType());

        return convertView;
    }
}
