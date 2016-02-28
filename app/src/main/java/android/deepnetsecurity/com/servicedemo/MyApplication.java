package android.deepnetsecurity.com.servicedemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by Android Studio.
 * User: Xin Meng
 * Date: 28/02/2016
 * Time: 16:51
 * Version: V 1.0
 */

public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
