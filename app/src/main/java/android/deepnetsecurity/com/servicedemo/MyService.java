package android.deepnetsecurity.com.servicedemo;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }
    Notification notification;
    NotificationManager notificationManager;
    private DownloadBinder mBinder = new DownloadBinder();
    class DownloadBinder extends Binder {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public void startDownload(int para1) {
            Log.d("MyService", "startDownload executed");
            Intent notificationIntent = new Intent(MyService.this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0,
                    notificationIntent, 0);
            Notification.Builder builder = new Notification.Builder(MyService.this);
            builder.setAutoCancel(false);
            builder.setTicker("Update this is ticker text");
            builder.setContentTitle("Update WhatsApp Notification");
            builder.setContentText("Update You have a new message");
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentIntent(pendingIntent);
            builder.setOngoing(true);
            builder.setSubText("Update subtext...");   //API level 16
            builder.setNumber(para1);
            builder.build();
            notification = builder.build();
            notificationManager = (NotificationManager) getApplicationContext()
                    .getSystemService(getApplicationContext().NOTIFICATION_SERVICE);

            notificationManager.notify(1, notification);

        }
        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate executed");

/*        Notification notification = new Notification(R.mipmap.ic_launcher,
                "Notification comes", System. currentTimeMillis());*/
//        Notification noti = new Notification.Builder(getApplicationContext())
//                .setContentTitle("New mail from " + sender.toString())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .build();
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
//                notificationIntent, 0);
        //notification.setLatestEventInfo(this, "This is title", "This is content", pendingIntent);
        //startForeground(1, notification);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setAutoCancel(false);
        builder.setTicker("this is ticker text");
        builder.setContentTitle("WhatsApp Notification");
        builder.setContentText("You have a new message");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
        builder.setSubText("This is subtext...");   //API level 16
        builder.setNumber(100);
        builder.build();

        notification = builder.build();
        startForeground(1, notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy executed");
        notificationManager = (NotificationManager) getApplicationContext()
                .getSystemService(getApplicationContext().NOTIFICATION_SERVICE);

        notificationManager.cancelAll();
        // TODO Auto-generated method stub
        //clockOut();
        Intent i = new Intent("checkout");
        // Data you need to pass to activity
        i.putExtra("action", "checkout_action");
        i.putExtra("msg", "send to activity!");
        getApplicationContext().sendBroadcast(i);
    }
}
