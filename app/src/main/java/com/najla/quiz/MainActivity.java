package com.najla.quiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button start , selected, result;
    Group group1, group2,group3;
    int count = 1,truenum;
    String[] question ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        group1 = findViewById(R.id.group);
        group2 = findViewById(R.id.group2);
        group3=findViewById(R.id.group3);
        result= findViewById(R.id.result);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                group1.setVisibility(View.GONE);
                group2.setVisibility(View.VISIBLE);

            }
        });

        question = getResources().getStringArray(R.array.chq1);
        final TextView ques = findViewById(R.id.question);

        ques.setText(question[0]);

        final TextView counter = findViewById(R.id.count);
        counter.setText(count + "/5");

        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
        final Button confirm = findViewById(R.id.confirm);
        final RadioButton ch1 = findViewById(R.id.choice1);
        final RadioButton ch2 = findViewById(R.id.choice2);
        final RadioButton ch3 = findViewById(R.id.choice3);

        ch1.setText(question[1]);
        ch2.setText(question[2]);
        ch3.setText(question[3]);

        confirm.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {



                if((confirm.getText().equals(getResources().getString(R.string.confirm)))) {

                    selected = findViewById(radioGroup.getCheckedRadioButtonId());

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        try {
                            if ((selected.getText()).equals(question[4])) {
                                selected.setBackground(getDrawable(R.drawable.backtrue));
                                truenum++;

                            } else {
                                selected.setBackground(getDrawable(R.drawable.backfalse));
                            }
                        }catch (Exception e){

                        }
                    }
                    if(count<=4) {
                        confirm.setText(getResources().getString(R.string.next));
                     }else {
                    confirm.setText(getResources().getString(R.string.finish));


                }

            }else if((confirm.getText().equals(getResources().getString(R.string.next)))) {
                    try {
                        selected.setBackground(null);
                        radioGroup.clearCheck();
                        radioGroup.clearAnimation();
                    }catch (Exception e){

                    }
                        count++;

                        confirm.setText(getResources().getString(R.string.confirm));
                        String qch = "chq" + count;
                        question = getResources().getStringArray(getResources().getIdentifier(qch, "array", getPackageName()));
                        ques.setText(question[0]);
                        counter.setText(count + "/5");
                        ch1.setText(question[1]);
                        ch2.setText(question[2]);
                        ch3.setText(question[3]);




                }else if((confirm.getText().equals(getResources().getString(R.string.finish)))) {
                    group2.setVisibility(View.GONE);
                    group3.setVisibility(View.VISIBLE);

                    result.setText(truenum+"/"+getResources().getString(R.string.num));

                }


            }

        });


    }
}
