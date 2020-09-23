package com.example.myapplication2.ZakahPages;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.example.myapplication2.R;
import com.example.myapplication2.ZakahPages.Home;
import com.example.myapplication2.ZakahPages.Login;
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;

//public class MyFirebaseMessagingService extends FirebaseMessagingService {
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//        if( remoteMessage.getData().size()>0)
//        notifyUser(remoteMessage.getData().get("title"),remoteMessage.getData().get("message"));
//
//        if(remoteMessage.getNotification()!=null){
//            notifyUser(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
//        }
//    }
//
//
//    private RemoteViews getCustomDesign(String title , String message){
//        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notifiction);
//        remoteViews.setTextViewText(R.id.notification_title,title);
//        remoteViews.setTextViewText(R.id.notification_body,message);
//        remoteViews.setImageViewResource(R.id.notification_icon,R.drawable.settings_icon);
//
//        return  remoteViews;
//    }
//    public void notifyUser(String title, String notification){
//        Intent intent = new Intent(this,Login.class);
//        String Chanel_ID ="Gazet";
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
//        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),Chanel_ID)
//                .setSmallIcon(R.drawable.settings_icon)
//                .setSound(uri)
//                .setAutoCancel(true)
//                .setVibrate(new long[]{100,100,100,100})
//                .setOnlyAlertOnce(true)
//                .setContentIntent(pendingIntent);
//        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.JELLY_BEAN){
//            builder = builder.setContent(getCustomDesign(title,notification));
//        } else {
//            builder =builder.setContentTitle(title).setContentText(notification).setSmallIcon(R.drawable.settings_icon);
//        }
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
//            NotificationChannel notificationChannel = new NotificationChannel(Chanel_ID,"gazt",NotificationManager.IMPORTANCE_HIGH);
//            notificationChannel.setSound(uri,null);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//        notificationManager.notify(0 , builder.build());
//    }
//}
