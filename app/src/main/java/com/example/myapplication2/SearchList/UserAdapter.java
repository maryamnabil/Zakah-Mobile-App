package com.example.myapplication2.SearchList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {

    // Creating a constructor
    public UserAdapter(Context context, ArrayList<User> users){
        //Super is a method that that access the super class in this case the array adapter
        super(context,0,users);
    }
    // over riding a method in super class
    @Override
    public View getView(int position, View viewToconvert, ViewGroup parent){
    // Getting Data item for the position
        User user = getItem(position);
//
//        if (viewToconvert == null){
//            //inflate is the process of adding xml view to parent viewGroup during runtime
//            viewToconvert = LayoutInflater.from(getContext()).inflate(R.layout.my_string_array,parent,false);
//        }
//        ImageView imageView = (ImageView) viewToconvert.findViewById(R.id.avatar);
//        TextView firstName = (TextView) viewToconvert.findViewById(R.id.firstname);
//        TextView lastName = (TextView) viewToconvert.findViewById(R.id.lastname);
//
//        firstName.setText(user.firstName);
//        lastName.setText(user.lastName);

        return viewToconvert;

    }
}
