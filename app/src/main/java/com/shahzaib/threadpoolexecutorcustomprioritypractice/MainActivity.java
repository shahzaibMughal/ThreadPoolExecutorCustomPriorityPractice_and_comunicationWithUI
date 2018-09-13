package com.shahzaib.threadpoolexecutorcustomprioritypractice;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ThreadPoolExecutor.ExecutorSupplier;
import ThreadPoolExecutor.PriorityRunnable;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExecutorSupplier.getsInstance().getmForBackgroundTask().submit(new PriorityRunnable(PriorityRunnable.Priority.HIGH) {
            Handler mainThreadHandler = new Handler(Looper.getMainLooper()){
                @Override
                public void handleMessage(Message msg) {
                    // message will be handle in the main thraed
                    SHOW_LoG("Message data: "+msg.arg1+", received in thread: "+getThreadInfo(Thread.currentThread()));

                }
            };
            @Override
            public void run() {
                // do background work here
                Message message = Message.obtain();
                message.arg1 = 55;
                mainThreadHandler.sendMessage(message);
                SHOW_LoG("Message Successfully Transfer from: "+getThreadInfo(Thread.currentThread()));
            }
        });
    }



    private String getThreadInfo(Thread thread)
    {
        return "Thread id: "+thread.getId();
    }


    private void SHOW_LoG(String message)
    {
        Log.i("323232",message);
    }



}
