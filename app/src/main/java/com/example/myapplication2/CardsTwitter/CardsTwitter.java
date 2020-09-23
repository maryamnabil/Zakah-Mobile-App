package com.example.myapplication2.CardsTwitter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication2.R;
import com.example.myapplication2.Twitter.Tweet;
import com.example.myapplication2.Twitter.TweetController;

import java.util.ArrayList;

public class CardsTwitter extends AppCompatActivity implements SimpleGestureFilter.SimpleGestureListener {
    private CardView cardView;
    private ArrayList<Tweet> tweetsArrayList;
    private ViewPager viewPager;
    private TweetController tweetController;
    private CardView card1,card2;
    public SimpleGestureFilter detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_twitter);
        detector = new SimpleGestureFilter(this,this);
        cardView = findViewById(R.id.card_view_inner3);
        card1=findViewById(R.id.card_view_inner1);
        card2 = findViewById(R.id.card_view_inner2);
        cardView.setBackgroundResource(R.drawable.card_back);
        card1.setBackgroundResource(R.drawable.card_back);
        card2.setBackgroundResource(R.drawable.card_back);
        viewPager = findViewById(R.id.view_pager1);
        fillTable();
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            private boolean moved;
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    moved = false;
//                    Log.i("info","down");
//                }
//                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
//                    moved = true;
//                    Log.i("info","move");
//
//                }
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    Log.i("info", "up");
//                    viewPager.setCurrentItem(1);
//                }
//                return false;
//            }
//
//
//        });



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

    @Override
    public void onSwipe(int direction) {
        String str = "";
        switch (direction) {
            case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
                break;
            case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
                break;
            case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
                // your code for Swipe Down
                break;
            case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
                if(viewPager.getCurrentItem()==0)
                    viewPager.setCurrentItem(1);
                else
                    viewPager.setCurrentItem(0);

                // your code for Swipe Up
                break;
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDoubleTap() {
        Toast.makeText(this, "Try swiping left", Toast.LENGTH_LONG).show();

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent me){

        this.detector.onTouchEvent(me);

        return super.dispatchTouchEvent(me);

    }

}
