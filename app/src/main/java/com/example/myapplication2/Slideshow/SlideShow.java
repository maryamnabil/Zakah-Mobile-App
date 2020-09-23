package com.example.myapplication2.Slideshow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication2.JsonRetrieval_Sort_PDF.DataModel;
import com.example.myapplication2.JsonRetrieval_Sort_PDF.ListAdapter;
import com.example.myapplication2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SlideShow extends AppCompatActivity {
    private String URLstring = "https://demonuts.com/Demonuts/JsonTest/Tennis/json_parsing.php";
    ViewPager viewPager;
    private ArrayList<DataModel> dataModelArrayList;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);
        viewPager = findViewById(R.id.view_pager);
        retrieveJSON();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);
    }


     class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            SlideShow.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                        SlideShow.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(viewPager.getCurrentItem()==0)
                                    viewPager.setCurrentItem(1);
                                else if (viewPager.getCurrentItem()==1)
                                    viewPager.setCurrentItem(2);
                                else if (viewPager.getCurrentItem()==2)
                                    viewPager.setCurrentItem(3);
                                else
                                    viewPager.setCurrentItem(0);
                            }
                        });
                }
            });
        }
    }

    private void retrieveJSON() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("status").equals("true")){

                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel.setName(dataobj.getString("name"));
                                    playerModel.setCountry(dataobj.getString("country"));
                                    playerModel.setCity(dataobj.getString("city"));
                                    playerModel.setImgURL(dataobj.getString("imgURL"));
                                    playerModel.setId(dataobj.getString("id"));

                                    dataModelArrayList.add(playerModel);

                                }

                                setupListview();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }

    private void setupListview(){
        viewPagerAdapter = new ViewPagerAdapter(this,dataModelArrayList);
        viewPager.setAdapter(viewPagerAdapter);

    }

}
