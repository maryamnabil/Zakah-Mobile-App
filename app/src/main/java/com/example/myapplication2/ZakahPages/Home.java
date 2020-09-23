package com.example.myapplication2.ZakahPages;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.BookstoreDatabase.BookStoreDatabase;
import com.example.myapplication2.CardsTwitter.CardsTwitter;
import com.example.myapplication2.Dialog.Dialog;
import com.example.myapplication2.Draggable.Draggable;
import com.example.myapplication2.FingerPrint.FaceDetection;
import com.example.myapplication2.FingerPrint.FingerPrint;
import com.example.myapplication2.ShareLink.ShareLink;
import com.example.myapplication2.Signature.Signature;
import com.example.myapplication2.Twitter.Twitter;
import com.example.myapplication2.card.Card;
import com.example.myapplication2.chatBot.Bot;
import com.example.myapplication2.Chart.Chart;
import com.example.myapplication2.DynamicPaging.DynamicPaging;
import com.example.myapplication2.Expandable.Expandable;
import com.example.myapplication2.Slideshow.SlideShow;
import com.example.myapplication2.Spinner.Spinner;
import com.example.myapplication2.Capatcha.Capatcha;
import com.example.myapplication2.Date.HijriDate;
import com.example.myapplication2.JsonRetrieval_Sort_PDF.ConsumeWebService;
import com.example.myapplication2.Maps.MapsActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.ReadFeed.Feed;
import com.example.myapplication2.ScanQRCode.ScanQRCode;
import com.example.myapplication2.SearchList.UsingAdapter;
import com.example.myapplication2.trial.Trial;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {

    Intent myFileIntent;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    public void GotoNotifications(View view){
        Intent myIntent = new Intent(this, Notifications.class);
        startActivity(myIntent);
    }

    public void GotoEditProfile(View view){
        Intent myIntent = new Intent(this, EditProfile.class);
        startActivity(myIntent);
    }

    public void GotoHome(View view){
        Intent myIntent = new Intent(this, Home.class);
        startActivity(myIntent);
    }

    public void GoToMyBills(View view){
        Intent myIntent = new Intent(this, MyBills.class);
        startActivity(myIntent);
    }

    public void GotoMyCertificates(View view){
        Intent myIntent = new Intent(this, MyCertificates.class);
        startActivity(myIntent);
    }
    public void GotoApproval(View view){
        Intent myIntent = new Intent(this, ZakatApproval.class);
        startActivity(myIntent);
    }


    public void FilePick(View view){
        Intent myIntent = new Intent(this, UsingCamera.class);
        startActivity(myIntent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Log.i("info",settings.getString("locale",""));
        if (settings.getString("locale","").equals("ar") ) {
            if (item.getItemId() == android.R.id.home) {
                if(drawer.isDrawerOpen(Gravity.RIGHT)) {
                    drawer.closeDrawer(Gravity.RIGHT);
                }else{
                    drawer.openDrawer(Gravity.RIGHT);
                }
            }else {
                return true;
            }

        }
        else {
            if (item.getItemId() == android.R.id.home) {
                if(drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.closeDrawer(Gravity.LEFT);
                }else{
                    drawer.openDrawer(Gravity.LEFT);
                }

            }else {
                return true;
            }
        }



        return super.onOptionsItemSelected(item);
    }


    // Radio Button Action
    public void onRadioButtonClicked(View view){
        boolean checked = ( (RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.admin:
                Toast.makeText(this, "You are an Admin", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user:
                Toast.makeText(this, "You are an User", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 0:
                if(resultCode==RESULT_OK){
                    String path= data.getData().getLastPathSegment();
                    Log.i("info",path);
                    TextView filename = (TextView) findViewById(R.id.filename);
                    filename.setText(path);

                }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_home));
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.nav_app_bar_open_drawer_description, R.string.nav_app_bar_open_drawer_description);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        drawer.closeDrawers();
                        Log.i("info", "HOME");
                        startActivity(new Intent(Home.this, Home.class));
                        break;
                    case R.id.nav_settings:
                        drawer.closeDrawers();
                        Log.i("info", "settings");
                        startActivity(new Intent(Home.this,Settings.class));
                        break;
                    case R.id.nav_notification:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, Notifications.class));
                        break;
                    case R.id.nav_profile:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, EditProfile.class));
                        break;
                    case R.id.nav_myBills:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, MyBills.class));
                        break;
                    case R.id.nav_mycertificates:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, MyCertificates.class));
                        break;
                    case R.id.nav_approval:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, ZakatApproval.class));
                        break;
                    case R.id.nav_consume:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, ConsumeWebService.class));
                        break;

                    case R.id.nav_scan:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, ScanQRCode.class));
                        break;

                    case R.id.nav_map:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, MapsActivity.class));
                        break;

                    case R.id.nav_adapter:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, UsingAdapter.class));
                        break;
                    case R.id.nav_feed:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, Feed.class));
                        break;
                    case R.id.nav_bookstore:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, BookStoreDatabase.class));
                        break;
                    case R.id.nav_capatcha:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, Capatcha.class));
                        break;
                    case R.id.nav_date:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, HijriDate.class));
                        break;
                    case R.id.nav_camera:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, UsingCamera.class));
                        break;
                    case R.id.nav_chart:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, Chart.class));
                        break;
                    case R.id.nav_paging:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, DynamicPaging.class));
                        break;
                    case R.id.nav_expand:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, Expandable.class));
                        break;
                    case R.id.slideshow:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, SlideShow.class));
                        break;
                    case R.id.twitter:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, Twitter.class));
                        break;
                    case R.id.bot:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, Bot.class));
                        break;
                    case R.id.spinner:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, Spinner.class));
                        break;
                    case R.id.signature:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, Signature.class));
                        break;
                    case R.id.share:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, ShareLink.class));
                        break;
                    case R.id.dialog:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, Dialog.class));
                        break;
                    case R.id.draggable:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, Draggable.class));
                        break;
                    case R.id.card:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, CardsTwitter.class));
                        break;
                    case R.id.trial:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, Trial.class));
                        break;
                    case R.id.fingerprint:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, FingerPrint.class));
                        break;
                    case R.id.face:
                        drawer.closeDrawers();
                        startActivity(new Intent(Home.this, FaceDetection.class));
                        break;
            }
                return true;
            }


        });

    }

    public String getMyData() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        return settings.getString("locale","");

    }
}
