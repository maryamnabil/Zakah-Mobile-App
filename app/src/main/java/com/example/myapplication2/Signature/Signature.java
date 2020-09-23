package com.example.myapplication2.Signature;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication2.R;
import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.File;
import java.io.FileOutputStream;

public class Signature extends AppCompatActivity {
        protected SignaturePad mSignaturePad;
        protected Button bSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);
        bSave = findViewById(R.id.bsave);
        bSave.setEnabled(false);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {

            @Override
            public void onStartSigning() {
                //Event triggered when the pad is touched
            }

            @Override
            public void onSigned() {
                bSave.setEnabled(true);

            }

            @Override
            public void onClear() {
                //Event triggered when the pad is cleared
            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(mSignaturePad.getSignatureBitmap(),"Signature");
                mSignaturePad.getSignatureBitmap();
                Toast.makeText(Signature.this,"Saved",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void saveImage(Bitmap finalBitmap, String image_name) {
        if(Build.VERSION.SDK_INT>22){
            requestPermissions(new String[] {"android.permission.WRITE_EXTERNAL_STORAGE","android.permission.READ_EXTERNAL_STORAGE"}, 1);
        }

        String root = Environment.getExternalStorageDirectory().getAbsolutePath()+'/';
        File myDir = new File(root);
        myDir.mkdirs();
        String fname = "Image-" + image_name+ ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        Log.i("LOAD", root + fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
