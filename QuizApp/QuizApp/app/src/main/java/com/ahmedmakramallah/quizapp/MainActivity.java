package com.ahmedmakramallah.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   private int score = 0;
    String [] arrAns = new String[]{"Germany ", "Cameroon ", "Burkina Faso "};
    Button submit;
    RadioButton q1_r1, q1_r2, q1_r3, q1_r4 ;
    RadioButton q2_r1, q2_r2, q2_r3, q2_r4 ;
    RadioButton q3_r1, q3_r2, q3_r3, q3_r4 ;
    RadioButton q4_r1, q4_r2, q4_r3, q4_r4 ;
    RadioButton q5_r1, q5_r2, q5_r3, q5_r4 ;
    CheckBox c1, c2, c3, c4;
    EditText q7_e, q8_e2, q9_e3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        q1_r1 = (RadioButton) findViewById(R.id.q1_r1);
        q1_r2 = (RadioButton) findViewById(R.id.q1_r2);
        q1_r3 = (RadioButton) findViewById(R.id.q1_r3);
        q1_r4 = (RadioButton) findViewById(R.id.q1_r4);
        // to compute the score for q1
        q1_r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!q1_r1.isChecked() && q1_r2.isChecked() && !q1_r3.isChecked() && !q1_r4.isChecked()) {
                    score++;
                }
            }
        });


        q2_r1 = (RadioButton) findViewById(R.id.q2_r1);
        q2_r2 = (RadioButton) findViewById(R.id.q2_r2);
        q2_r3 = (RadioButton) findViewById(R.id.q2_r3);
        q2_r4 = (RadioButton) findViewById(R.id.q2_r4);
        // to compute the score for q2
        q2_r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!q2_r1.isChecked() && !q2_r2.isChecked() && q2_r3.isChecked() && !q2_r4.isChecked()) {
                    score++;
                }
            }
        });
        q3_r1 = (RadioButton) findViewById(R.id.q3_r1);
        q3_r2 = (RadioButton) findViewById(R.id.q3_r2);
        q3_r3 = (RadioButton) findViewById(R.id.q3_r3);
        q3_r4 = (RadioButton) findViewById(R.id.q3_r4);
        // to compute the score for q3
        q3_r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q3_r1.isChecked() && !q3_r2.isChecked() && !q3_r3.isChecked() && !q3_r4.isChecked()) {
                    score++;
                }
            }
        });

        q4_r1 = (RadioButton) findViewById(R.id.q4_r1);
        q4_r2 = (RadioButton) findViewById(R.id.q4_r2);
        q4_r3 = (RadioButton) findViewById(R.id.q4_r3);
        q4_r4 = (RadioButton) findViewById(R.id.q4_r4);
        // to compute the score for q4
        q4_r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!q4_r1.isChecked() && q4_r2.isChecked() && !q4_r3.isChecked() && !q4_r4.isChecked()) {
                    score++;
                }
            }
        });

        q5_r1 = (RadioButton) findViewById(R.id.q5_r1);
        q5_r2 = (RadioButton) findViewById(R.id.q5_r2);
        q5_r3 = (RadioButton) findViewById(R.id.q5_r3);
        q5_r4 = (RadioButton) findViewById(R.id.q5_r4);
        // to compute the score for q4
        q5_r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!q5_r1.isChecked() && q5_r2.isChecked() && !q5_r3.isChecked() && !q5_r4.isChecked()) {
                    score++;
                }
            }
        });


        q7_e = (EditText) findViewById(R.id.editText);
        if (arrAns[0].equals(q7_e.getText().toString())){
            score++;
        }
        q8_e2 = (EditText) findViewById(R.id.editText2);
        if (arrAns[1].equals(q8_e2.getText().toString())){
            score++;
        }
        q9_e3 = (EditText) findViewById(R.id.editText3);
        if (arrAns[2].equals(q9_e3.getText().toString())){
            score++;
        }

        final String scoreWord= (getString(R.string.score));


        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), scoreWord + " " + score, Toast.LENGTH_LONG).show();
            }
        });

    }



    // to compute the score of two correct checkBoxes
    public void checkBoxScore(View view) {
        c1 = (CheckBox) findViewById(R.id.q6_c1);
        c2 = (CheckBox) findViewById(R.id.q6_c2);
        c3 = (CheckBox) findViewById(R.id.q6_c3);
        c4 = (CheckBox) findViewById(R.id.q6_c4);

        if (!c1.isChecked() && c2.isChecked() && c3.isChecked() && !c4.isChecked()) {
            score++;
        }

    }


}
