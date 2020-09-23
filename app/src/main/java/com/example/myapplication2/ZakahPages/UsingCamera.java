package com.example.myapplication2.ZakahPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class UsingCamera extends AppCompatActivity {

    public static final int GET_FROM_GALLERY = 1;
    public static final int GET_MULTIPLE= 2;
    private static final int GET_FROM_CAMERA = 0;
    Button camera_open_id;
    ImageView click_image_id;
    Button gallery_open_id;
    Button multiple_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usingcamera);
        camera_open_id = (Button) findViewById(R.id.camera_button);
        click_image_id = (ImageView) findViewById(R.id.click_image);
        gallery_open_id = (Button) findViewById(R.id.pic_upload);
        multiple_id=findViewById((R.id.multiple));
        camera_open_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent
                        = new Intent(MediaStore
                        .ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent, GET_FROM_CAMERA);
            }
        });

        gallery_open_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pick = new Intent();
                pick.setType("image/*");
                pick.setAction(Intent.ACTION_GET_CONTENT);
                pick.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(
                 Intent.createChooser(pick,"Select Pictures"),
                        GET_FROM_GALLERY);
            }
        });

        multiple_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pick = new Intent();
                pick.setType("image/*");
                pick.setAction(Intent.ACTION_GET_CONTENT);
                pick.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                pick.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(
                        Intent.createChooser(pick,"Select Pictures"),
                        GET_MULTIPLE);
            }
        });
    }



    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        if (requestCode == GET_FROM_GALLERY) {
                 try{
                     final Uri contentURI = data.getData();
                     ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                click_image_id.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
            }

        }
        else if (requestCode == GET_FROM_CAMERA) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            click_image_id.setImageBitmap(photo);
        }

        else if (requestCode == GET_MULTIPLE){
            TextView uri_tv = findViewById(R.id.uris);
                ClipData clipData = data.getClipData();
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    ClipData.Item item = clipData.getItemAt(i);
                    Uri uri = item.getUri();
                    uri_tv.append(getFileName(uri)+"/");
                }
        }
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}

