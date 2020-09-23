package com.example.myapplication2.ui.contactus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication2.ZakahPages.Home;
import com.example.myapplication2.R;

public class ContactusFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private String[] test = {"1","2","3","4","5"};
    private ContactusViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Spinner
        final View root = inflater.inflate(R.layout.fragment_contactus, container, false);

        String [] values =
                {"","Male","Female",};
        // Create Spinner
        Spinner spinner = (Spinner) root.findViewById(R.id.spinner);
        // Create a n array adapter
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.spinner_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        Home activity = (Home) getActivity();

        final String myDataFromActivity = activity.getMyData();
        dashboardViewModel =
                ViewModelProviders.of(this).get(ContactusViewModel.class);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (myDataFromActivity.equals("ar") ) {
                    LinearLayout lin1 =(LinearLayout) root.findViewById(R.id.lin1);
                    LinearLayout lin2 =(LinearLayout) root.findViewById(R.id.lin2);
                    LinearLayout lin3 =(LinearLayout) root.findViewById(R.id.lin3);
                    LinearLayout lin4 =(LinearLayout) root.findViewById(R.id.lin7);
                    lin1.setBackgroundResource(R.drawable.input_ar);
                    lin2.setBackgroundResource(R.drawable.input_ar);
                    lin3.setBackgroundResource(R.drawable.input_ar);
                    lin4.setBackgroundResource(R.drawable.input_ar);

                }
                else {
                    LinearLayout lin1 =(LinearLayout) root.findViewById(R.id.lin1);
                    LinearLayout lin2 =(LinearLayout) root.findViewById(R.id.lin2);
                    LinearLayout lin3 =(LinearLayout) root.findViewById(R.id.lin3);
                    LinearLayout lin4 =(LinearLayout) root.findViewById(R.id.lin7);
                    lin1.setBackgroundResource(R.drawable.input_en);
                    lin2.setBackgroundResource(R.drawable.input_en);
                    lin3.setBackgroundResource(R.drawable.input_en);
                    lin4.setBackgroundResource(R.drawable.input_en);

                }
            }
        });

        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity().getBaseContext(), "Hello", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}