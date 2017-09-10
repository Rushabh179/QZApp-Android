package com.example.rushabh.qzapp;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //TODO private Button mTrueButton, mFalseButton;
    private RadioGroup mOptions;
    private ImageButton mNextButton, mBackButton;
    private TextView mQuestionText;
    private Questions[] mQuestionBank = new Questions[]{
            new Questions(R.string.question_1, R.string.option_1),
            new Questions(R.string.question_2, R.string.option_1),
            new Questions(R.string.question_3, R.string.option_2),
            new Questions(R.string.question_4, R.string.option_3),
            new Questions(R.string.question_5, R.string.option_4),};
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionText = (TextView) findViewById(R.id.question_text);
        updateQuestion();

        //TODO mTrueButton = (Button) findViewById(R.id.true_button);
        //TODO mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mBackButton = (ImageButton) findViewById(R.id.back_button);
        mOptions = (RadioGroup) findViewById(R.id.options);///

        /*TODO
        //On selecting true
         mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        //On selecting False
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });*/

        mOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                RadioButton mOptionAny = (RadioButton) findViewById(checkedId);
                int checkedAnswer = mOptionAny.getId();
                int messageResId;
                if(checkedAnswer == answerIsTrue)
                    messageResId = R.string.correct_toast;
                else
                    messageResId= R.string.incorrect_toast;
                Toast.makeText(MainActivity.this, answerIsTrue, Toast.LENGTH_SHORT).show();
            }
        });


        //Next question on tapping the next button
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        //Previous question on tapping the back button
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex != 0)
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                else
                    mCurrentIndex = mQuestionBank.length - 1;
                updateQuestion();
            }
        });

        //Next question on tapping the question
        mQuestionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    //Next Question
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionText.setText(question);
    }


    //Check the correct answer
    /*private void checkAnswer(int userPressedTrue) {
        int answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }*/
}