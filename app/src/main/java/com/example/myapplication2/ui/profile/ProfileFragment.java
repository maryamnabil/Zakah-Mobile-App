package com.example.myapplication2.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication2.ZakahPages.Home;
import com.example.myapplication2.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Home activity = (Home) getActivity();
       final String myDataFromActivity = activity.getMyData();
        Log.i("info",myDataFromActivity);

        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_profile, container, false);
        profileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (myDataFromActivity.equals("ar") ) {
                    LinearLayout lin1 =(LinearLayout) root.findViewById(R.id.lin1);
                    LinearLayout lin2 =(LinearLayout) root.findViewById(R.id.lin2);
                    LinearLayout lin3 =(LinearLayout) root.findViewById(R.id.lin3);
                    LinearLayout lin4 =(LinearLayout) root.findViewById(R.id.lin4);
                    LinearLayout lin5 =(LinearLayout) root.findViewById(R.id.lin5);
                    lin1.setBackgroundResource(R.drawable.input_ar);
                    lin2.setBackgroundResource(R.drawable.input_ar);
                    lin3.setBackgroundResource(R.drawable.input_ar);
                    lin4.setBackgroundResource(R.drawable.input_ar);
                    lin5.setBackgroundResource(R.drawable.input_ar);

                }
                else {
                    LinearLayout lin1 =(LinearLayout) root.findViewById(R.id.lin1);
                    LinearLayout lin2 =(LinearLayout) root.findViewById(R.id.lin2);
                    LinearLayout lin3 =(LinearLayout) root.findViewById(R.id.lin3);
                    LinearLayout lin4 =(LinearLayout) root.findViewById(R.id.lin4);
                    LinearLayout lin5 =(LinearLayout) root.findViewById(R.id.lin5);
                    lin1.setBackgroundResource(R.drawable.input_en);
                    lin2.setBackgroundResource(R.drawable.input_en);
                    lin3.setBackgroundResource(R.drawable.input_en);
                    lin4.setBackgroundResource(R.drawable.input_en);
                    lin5.setBackgroundResource(R.drawable.input_en);

                }
            }
        });
        return root;
    }


}