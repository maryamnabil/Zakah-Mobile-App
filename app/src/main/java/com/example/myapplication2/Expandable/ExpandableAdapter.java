package com.example.myapplication2.Expandable;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.myapplication2.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataheader;
    private HashMap<String,List<String>> listHashMap;

    public ExpandableAdapter(Context context , List<String> listDataheader,HashMap<String,List<String>> listHashMap){
        this.context=context;
        this.listDataheader= listDataheader;
        this.listHashMap = listHashMap;
    }
    @Override
    public int getGroupCount() {
        return listDataheader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataheader.get(i)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataheader.get(groupPosition);
    }

    @Override
    public Object getChild(int i, int j) {
        return listHashMap.get(listDataheader.get(i)).get(j);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if(convertView ==null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_header,null);
        }
        TextView header = (TextView) convertView.findViewById(R.id.item_header);
        header.setTypeface(null, Typeface.BOLD);
        header.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childText = (String) getChild(groupPosition,childPosition);
        if( convertView ==null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_child,null);
        }
        TextView child = (TextView) convertView.findViewById(R.id.child);
        child.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
