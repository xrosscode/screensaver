package com.xrosscode.screensaver;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author johnsonlee
 */
public class ScreenSaverActivity extends Activity {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    private Timer timer;

    private TextView txtClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(0
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        super.setContentView(R.layout.screen_saver);
        this.txtClock = (TextView) findViewById(R.id.screen_saver_clock);
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                refresh();
            }
        }, 0, 1000L);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.timer.cancel();
        this.timer = null;
    }

    private void refresh() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtClock.setText(SIMPLE_DATE_FORMAT.format(new Date()));
            }
        });
    }
}
