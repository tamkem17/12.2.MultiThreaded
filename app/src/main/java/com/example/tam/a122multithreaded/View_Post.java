package com.example.tam.a122multithreaded;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

public class View_Post extends AppCompatActivity {
    protected LinearLayout mResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__post);
        mResults = (LinearLayout)findViewById(R.id.ll_result);
        Button btnClick = (Button)findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImages();
            }
        });
    }

    protected void addImages() {
        ExecutorService taskList = Executors.newFixedThreadPool(50);
        taskList.execute(new Flipper());
        }



    public class  Flipper implements Runnable {

        @Override
        public void run() {
            View viewToAdd = MaxNumberCoin(10, View_Post.this);
            mResults.post(new ViewAdder(viewToAdd));

        }
    }

    private class ViewAdder implements Runnable {
        private View mViewToAdd;
        public ViewAdder(View viewToAdd) {
            mViewToAdd = viewToAdd;
        }
        @Override
        public void run() {
            mResults.addView(mViewToAdd);
        }
    }

    private View MaxNumberCoin (int FlipLoop, Context context) {

        Random mRandom = new Random();
        int x = 0;
        TextView displayedMessage = new TextView(context);
        int MaxNumberCoin = 3;

        for (int i = 1; i <= FlipLoop; i++) {
            int Coin = mRandom.nextInt(2);
            if (Coin == 1) {
                x++;
                if (x >= MaxNumberCoin) {
                    MaxNumberCoin = x;
                    displayedMessage.setText("Max consecutive heads : " + String.valueOf(MaxNumberCoin));
                }
            } else {
                x = 0;
            }
        }
        return displayedMessage;
    }
}
