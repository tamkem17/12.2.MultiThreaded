package com.example.tam.a122multithreaded;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mViewResult;
    private final static int HEAD_TIMEOUT = 10;
    Result result = new Result();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewResult = (LinearLayout)findViewById(R.id.View);
    }

    public void CoinFlipping(View clickedButton) throws InterruptedException {
        mViewResult.removeAllViews();
        mViewResult.requestLayout();
        ExecutorService taskList = Executors.newFixedThreadPool(100);
        taskList.execute(new Flipper(1000));
        taskList.shutdown();
        taskList.awaitTermination(HEAD_TIMEOUT, TimeUnit.SECONDS);
        TextView txtRessult = new TextView(getApplicationContext());
        txtRessult.setTextColor(Color.BLACK);
        int maxCoin = result.getMaxCoinFlipping();
        txtRessult.setText("Max consecutive heads : "+maxCoin);
        mViewResult.addView(txtRessult);
    }



        public class  Flipper implements Runnable {
        private final int mLoopLimit;

        public Flipper(int LoopLimit) {
            this.mLoopLimit = LoopLimit;
        }

        @Override
        public void run() {
            Random mRandom = new Random();
            int x = 0;
            int MaxNumberCoin = 3;

            for (int i = 0; i < mLoopLimit; i++) {
                int Coin = mRandom.nextInt(2);
                if (Coin == 1) {
                    x++;
                    if (x >= 3) {
                        if (x >= MaxNumberCoin) {
                            MaxNumberCoin = x;
                        }
                    }
                } else {
                    x = 0;
                }
            }
            result.setMaxCoinFlipping(MaxNumberCoin);
        }
    }
}
