package com.example.tam.a122multithreaded;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class Asyntask_Activity extends AppCompatActivity {
    private LinearLayout mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyntask_);
        mView = (LinearLayout)findViewById(R.id.View);
        Button btnCoinFlipping = (Button)findViewById(R.id.btn_CoinFipping);
        btnCoinFlipping.setOnClickListener(new ClickCoin());
    }

    private class ClickCoin implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            new CoinFlipping().execute(100);
        }
    }

    class CoinFlipping extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            Random mRandom = new Random();
            int x = 0;
            for (int i = 0; i < params[0]; i++) {
                int Coin = mRandom.nextInt(2);
                if (Coin == 1) {
                    x++;
                    if (x >= 3) {
                        int numberCoin = x;
                        if (x > numberCoin) {
                            return x;
                        }
                    }
                } else {
                    x = 0;
                }
            }
            return x;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            TextView txtCoin = new TextView(getApplicationContext());
            txtCoin.setText("the maximum number of consecutive heads: "+String.valueOf(integer));
            mView.addView(txtCoin);
            super.onPostExecute(integer);
        }
    }
}

