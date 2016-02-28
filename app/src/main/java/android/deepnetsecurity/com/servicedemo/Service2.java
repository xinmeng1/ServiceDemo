package android.deepnetsecurity.com.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Android Studio.
 * User: xin
 * Date: 26/02/2016 0026
 * Time: 01:27:50 PM
 * Version: V 1.0
 */

public class Service2 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Tag", "Service 2 create");

    }
}
