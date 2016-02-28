package android.deepnetsecurity.com.servicedemo;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.logentries.logger.AndroidLogger;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startService;
    private Button stopService;
    private Button updateNotificationBar;


    private Button bindService;
    private Button unbindService;
    private MyService.DownloadBinder downloadBinder;
    AndroidLogger logger = null;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload(3);
            downloadBinder.getProgress();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        updateNotificationBar = (Button) findViewById(R.id.change_notification);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);

        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);

        try {
            logger = AndroidLogger.createInstance(this,
                    false, false, false, null, 0, "64a28ed6-6c15-49e5-860e-dd59d172238a", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.log("Welcome First Load the app to TEST!");


        // Add this inside your class
        BroadcastReceiver broadcastReceiver =  new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Bundle b = intent.getExtras();

                String message = b.getString("action");

                Log.i("Test", "action: " + message);
                assert message != null;
                if (message.equals("checkout_action")) {
                    Log.i("Test", "action: " + message);
                    String msg = b.getString("msg");
                    Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        };
        // Inside OnCreate Method
        registerReceiver(broadcastReceiver, new IntentFilter("checkout"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent); // start service
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent); // stop service
                break;
            case R.id.change_notification:
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE); // 绑定服务
                unbindService(connection); // 解绑服务
                break;
            case R.id.unbind_service:
                unbindService(connection); // 解绑服务
                break;
            default:
                break;
        }
    }
}
