package com.example.myapplication2.Capatcha;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.myapplication2.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

public class Capatcha extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
    boolean connected;
    CheckBox checkBox;
    GoogleApiClient googleApiClient;
    String SiteKey = "6Lc46eMUAAAAAA1j23olCIOLjWYqWss_7olP-DiF";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capatcha);
        connected = false;
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        CheckConnectivity();
        CreateGoogleAPIClient();
        checkBoxClickListner();
    }

    public void checkBoxClickListner(){
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient,SiteKey)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status =recaptchaTokenResult.getStatus();
                                    if((status!= null) && status.isSuccess()){
                                        Toast.makeText(getApplicationContext(), "successfully verfied", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
    public void CreateGoogleAPIClient(){
        googleApiClient= new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(Capatcha.this)
                .build();
        googleApiClient.connect();

    }


    public void CheckConnectivity(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
            Toast.makeText(this, "You are connected", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "You are not connected", Toast.LENGTH_SHORT).show();
            connected = false;
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
