package com.youlayhang.threads_android;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView resultTextView;
    Button startButton;
    Handler objHandle = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle objB = msg.getData();
            String message = objB.getString("MSG_KEY");

            resultTextView = (TextView) findViewById(R.id.result_text_view);
            resultTextView.setText(message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }
    public void backgroundTask(View view) {
        Runnable objRunnable = new Runnable() {
            Message objSms = objHandle.obtainMessage();
            Bundle objBundle = new Bundle();
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                objBundle.putString("MSG_KEY","My Specific message from Background");
                objSms.setData(objBundle);
                objHandle.sendMessage(objSms);
            }
        };
        Thread objThread = new Thread(objRunnable);
        objThread.start();
        resultTextView = (TextView) findViewById(R.id.result_text_view);
        resultTextView.setText("Button is clicked");
    }
}