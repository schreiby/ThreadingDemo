package com.example.ettie.threadingdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView tvOutput;
    private static final int SUCCESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = (TextView)findViewById(R.id.tvOutput);
    }

    public void fetchData(View view) {
        tvOutput.setHint("Fetching data from remote server....");
        thread.start();
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            handler.sendEmptyMessage(SUCCESS);
        }
    });

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == SUCCESS) {
                tvOutput.setText("Data fetched from remote server successfully!");
            }
        }
    };

}
