package com.example.myapplication2.DynamicPaging;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DynamicPaging extends AppCompatActivity {
    private String URLstring = "https://api.stackexchange.com/2.2/users/moderators?site=stackoverflow&page=";
    public ListView lv;
    public boolean loading = false;
    ArrayList<DynamicListItem> itemsArray;
    Integer pageNumber =1;
    private DynamicListAdapter dynamicListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_paging);
        lv = findViewById(R.id.lv);
//        retrieveJSON(pageNumber);
//        next = findViewById(R.id.next_page_btn);
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pageNumber++;
//                retrieveJSON();
//            }
//        });
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // Don't take any action on changed
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int lastInScreen = firstVisibleItem + visibleItemCount;
                Log.i("total", Integer.toString(totalItemCount));
                Log.i("last", Integer.toString(lastInScreen));
                Log.i("loading", Boolean.toString(loading));
                if (lastInScreen == totalItemCount && !loading) {
                    if(pageNumber<=3){
                        Log.i("info","number"+pageNumber.toString());
                        loading = true;
                        retrieveJSON(pageNumber);
                        pageNumber++;
                    }

                }
            }
        });

    }

    public void retrieveJSON(Integer offset) {
        Log.i("inside retrieve",offset.toString());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring+offset.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            itemsArray = new ArrayList<>();
                            JSONArray dataArray  = obj.getJSONArray("items");
                            Log.d("data", ">>" + dataArray);
                            for (int i = 0; i < dataArray.length(); i++) {

                                DynamicListItem userModel = new DynamicListItem();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                Log.d("obj", ">>" + dataobj);
                                if(dataobj.has("display_name")) userModel.setDisplayName(dataobj.getString("display_name"));else userModel.setDisplayName("null");
                                if(dataobj.has("location")) userModel.setLocation(dataobj.getString("location"));else userModel.setLocation("null");
                                if(dataobj.has("website_url"))userModel.setWebsiteUrl(dataobj.getString("website_url")); else userModel.setWebsiteUrl("null");
                                if(dataobj.has("user_type"))userModel.setUserType(dataobj.getString("user_type")); else userModel.setUserType("null");
                                if(dataobj.has("profile_image"))userModel.setProfileURL(dataobj.getString("profile_image")); else userModel.setProfileURL("null");

                                itemsArray.add(userModel);

                            }

                            setupListview();



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
        dynamicListAdapter = new DynamicListAdapter(this, itemsArray);
        lv.setAdapter(dynamicListAdapter);
        loading=false;


    }

}
