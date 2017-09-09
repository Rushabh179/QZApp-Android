package com.example.rushabh.qzapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton,mFalseButton;
    private ImageButton mNextButton,mBackButton;
    private TextView mQuestionText;
    private Questions[] mQuestionBank = new Questions[]{
            new Questions(R.string.question_1, true),
            new Questions(R.string.question_2, false),
            new Questions(R.string.question_3, false),
            new Questions(R.string.question_4, true),
            new Questions(R.string.question_5, true),};
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionText = (TextView) findViewById(R.id.question_text);
        updateQuestion();
        //int question = mQuestionBank[mCurrentIndex].getTextResId();
        //mQuestionText.setText(question);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mBackButton = (ImageButton) findViewById(R.id.back_button);

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
                if(mCurrentIndex!=0)
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
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}