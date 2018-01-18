package tw.com.pcschool.dd2018010902;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    String idLove = "LOVE";
    NotificationChannel channelLove;
    NotificationManager nm;
    final int NOTIFICATION_ID = 123123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        channelLove = new NotificationChannel(
//                idLove,
//                "Channel Love",
//                NotificationManager.IMPORTANCE_HIGH
//        );
//        channelLove.setDescription("最重要的人");
//        channelLove.enableLights(true);
//        channelLove.enableVibration(true);
//
//        //nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        nm.createNotificationChannel(channelLove);

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26)
        {
            regChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void regChannel()
    {
        channelLove = new NotificationChannel(
                idLove,
                "Channel Love",
                NotificationManager.IMPORTANCE_HIGH
        );
        channelLove.setDescription("最重要的人");
        channelLove.enableLights(true);
        channelLove.enableVibration(true);
        nm.createNotificationChannel(channelLove);
    }

    @TargetApi(Build.VERSION_CODES.O)
    @SuppressWarnings("deprecation")
    public void click1(View v)
    {
//        Notification.Builder builder1 = new Notification.Builder(MainActivity.this, idLove);
//        builder1.setContentTitle("警告!!!");
//        builder1.setContentText("沒事...");
//        builder1.setSmallIcon(R.drawable.ic_launcher_foreground);
//        Notification notify = builder1.build();
//        nm.notify(1, notify);

        Notification.Builder builder1;
        if (Build.VERSION.SDK_INT >= 26)
        {
            builder1 = new Notification.Builder(MainActivity.this, idLove);
        }
        else
        {
            builder1 = new Notification.Builder(MainActivity.this);
        }

        Intent it1 = new Intent(MainActivity.this, InfoActivity.class);
        it1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP |
        Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pi1 = PendingIntent.getActivity(
                MainActivity.this,
                123,
                it1,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        builder1.setContentTitle("警告!!!");
        builder1.setContentText("沒事...");
        if (Build.VERSION.SDK_INT >= 26)
        {
            builder1.setSmallIcon(R.drawable.ic_launcher_foreground);
        }
        else
        {
            builder1.setSmallIcon(R.mipmap.ic_launcher);
        }

        builder1.setAutoCancel(true);
        builder1.setContentIntent(pi1);

        Notification notify = builder1.build();
        nm.notify(NOTIFICATION_ID, notify);
    }
    public void click2(View v)
    {
        nm.cancel(NOTIFICATION_ID);
    }
}
