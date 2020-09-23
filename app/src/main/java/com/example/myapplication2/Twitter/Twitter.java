package com.example.myapplication2.Twitter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication2.R;
import com.example.myapplication2.Slideshow.SlideShow;
import com.example.myapplication2.Slideshow.ViewPagerAdapter;
import com.google.gson.JsonArray;
import com.itextpdf.xmp.impl.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.internal.Util;

public class Twitter extends AppCompatActivity {
    //WebView webView;
    private ArrayList<Tweet> tweetsArrayList;
    private ViewPager viewPager;
    private TweetController tweetController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);
        viewPager = findViewById(R.id.view_pager);
        fillTable();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);
//         webView= (WebView) findViewById(R.id.webview);
//        gotoPage();
    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            Twitter.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Twitter.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(viewPager.getCurrentItem()==0)
                                viewPager.setCurrentItem(1);
                            else if(viewPager.getCurrentItem()==1)
                                viewPager.setCurrentItem(0);
                        }
                    });
                }
            });
        }
    }


    public void fillTable(){
        String text1="#وزارة_الموارد_البشرية_والتنمية_الاجتماعية\u2069 توضح: رفع نسبة حضور الموظفين لمقرات العمل إلى ما لا يزيد عن 75% في جميع مدن ومحافظات المملكة اعتباراً من يوم الأحد 29 شوال";
        String text2="صباح الهمة والنشاط، فلنتذكر ضرورة الاستمرار في تطبيق التعليمات الصحية، واتباع البروتوكولات الوقائية في مقرات أعمالنا.";
        String created2=" 6:00 ص 31 مايو 2020";
        String created1=" 5:34 م 20 يوليو 2020";
        Tweet t1=new Tweet(text1,created1,"198","100"+" يغردون حول هذا");
        Tweet t2=new Tweet(text2,created2,"500","400"+" يغردون حول هذا");
        tweetsArrayList = new ArrayList<>();
        tweetsArrayList.add(t1);
        tweetsArrayList.add(t2);
        tweetController = new TweetController(this,tweetsArrayList);
        viewPager.setAdapter(tweetController);
    }



//    private void gotoPage(){
//        String url = "https://twitter.com/masar";
//
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setBuiltInZoomControls(true);
//
//        webView.setWebViewClient(new Callback());  //HERE IS THE MAIN CHANGE
//        webView.loadUrl(url);
//
//    }



}
