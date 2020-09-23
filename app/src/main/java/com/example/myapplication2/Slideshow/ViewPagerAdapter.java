package com.example.myapplication2.Slideshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication2.JsonRetrieval_Sort_PDF.DataModel;
import com.example.myapplication2.JsonRetrieval_Sort_PDF.ListAdapter;
import com.example.myapplication2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private String[] imageUrls;
    private ArrayList<DataModel> dataModelArrayList;

    ViewPagerAdapter(Context context,ArrayList<DataModel> dataModelArrayList){
        this.context=context;
        this.dataModelArrayList = dataModelArrayList;

    }
    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    private class ViewHolder {

        protected TextView tvname, tvcountry, tvcity;
        protected ImageView iv;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewHolder holder= new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           View convertView = inflater.inflate(R.layout.item_v, null, true);

            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvcountry = (TextView) convertView.findViewById(R.id.country);
            holder.tvcity = (TextView) convertView.findViewById(R.id.city);
            convertView.setTag(holder);
            holder = (ViewHolder)convertView.getTag();

        Picasso.get().load(dataModelArrayList.get(position).getImgURL()).into(holder.iv);
        holder.tvname.setText(dataModelArrayList.get(position).getName());
        holder.tvcountry.setText(dataModelArrayList.get(position).getCountry());
        holder.tvcity.setText(dataModelArrayList.get(position).getCity());
        container.addView(convertView, 0);
        return convertView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
