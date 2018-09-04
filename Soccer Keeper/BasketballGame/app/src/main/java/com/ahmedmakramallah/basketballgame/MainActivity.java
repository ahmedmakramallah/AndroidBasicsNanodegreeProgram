package com.ahmedmakramallah.basketballgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int A_Score;
    int B_Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void score_1_A(View v){
        A_Score += 1;
        Display_A(A_Score);
    }

    public void score_2_A(View v){
        A_Score = A_Score + 2 ;
        Display_A(A_Score);
    }

    public void score_3_A(View v){
        A_Score = A_Score + 3 ;
        Display_A(A_Score);
    }

    public void score_4_A(View v){
        A_Score = A_Score + 4 ;
        Display_A(A_Score);
    }

    public void score_1_B(View v){
        B_Score += 1 ;
        Display_B(B_Score);
    }

    public void score_2_B(View v){
        B_Score = B_Score + 2 ;
        Display_B(B_Score);
    }

    public void score_3_B(View v){
        B_Score = B_Score + 3 ;
        Display_B(B_Score);
    }

    public void score_4_B(View v){
        B_Score = B_Score + 4 ;
        Display_B(B_Score);
    }

    public void reset(View v){
      A_Score=0;
      B_Score=0;
      Display_A(0);
      Display_B(0);
    }

    public void Display_A(int a){
        TextView text1 = (TextView) findViewById(R.id.text1);
        text1.setText(String.valueOf(a));
    }

    public void Display_B(int a){
        TextView text2 = (TextView) findViewById(R.id.text2);
        text2.setText(String.valueOf(a));
    }
}
