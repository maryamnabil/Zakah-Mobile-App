package com.example.myapplication2.ShareLink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication2.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ShareLink extends AppCompatActivity {
    protected EditText link;
    protected ImageView image;
    protected Button shareBtn;
    protected Button share_picBtn;
    protected String imageUri;
    protected URL url;
    protected Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_link);
        link = findViewById(R.id.link_text);
        image= findViewById(R.id.image);
        imageUri = "https://i.imgur.com/tGbaZCY.jpg";
        Picasso.get().load(imageUri).into(image);
        shareBtn = findViewById(R.id.share);
        share_picBtn = findViewById(R.id.share_pic);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareLink();
            }
        });

        share_picBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    sharePic();
            }
        });
    }


    void shareLink(){
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Link");
            String shareMessage= "\nLet me share this link with you\n\n";
            shareMessage = shareMessage + link.getText().toString();
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch(Exception e) {
            //e.toString();
        }
    }

    void fromUrlToUri(){
        try {
            url = new URL(imageUri);
            uri = Uri.parse( url.toURI().toString() );
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    void sharePic(){
        Drawable mDrawable = image.getDrawable();
        Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

        String path = MediaStore.Images.Media.insertImage(getContentResolver(), mBitmap, "Image Description", null);
        Uri uri = Uri.parse(path);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent, "Share Image"));
    }


}
