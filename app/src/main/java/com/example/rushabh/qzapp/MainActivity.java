package com.example.rushabh.qzapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE = 0;

    private RadioGroup mOptions;
    private ImageButton mNextButton, mBackButton;
    private Button mHintButton;
    private TextView mQuestionText;
    private Questions[] mQuestionBank;
    private int mCurrentIndex = 0;
    private boolean mIsHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionBank = new Questions[]{
                new Questions(getResources().getString(R.string.question_1),
                        getResources().getString(R.string.option_1),
                        getResources().getString(R.string.hint_1)),
                new Questions(getResources().getString(R.string.question_2),
                        getResources().getString(R.string.option_2),
                        getResources().getString(R.string.hint_2)),
                new Questions(getResources().getString(R.string.question_3),
                        getResources().getString(R.string.option_3),
                        getResources().getString(R.string.hint_3)),
                new Questions(getResources().getString(R.string.question_4),
                        getResources().getString(R.string.option_4),
                        getResources().getString(R.string.hint_4)),
                new Questions(getResources().getString(R.string.question_5),
                        getResources().getString(R.string.option_1),
                        getResources().getString(R.string.hint_5)),};

        if (savedInstanceState!=null){
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
        }

        mQuestionText = (TextView) findViewById(R.id.question_text);
        updateQuestion();

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mBackButton = (ImageButton) findViewById(R.id.back_button);
        mOptions = (RadioGroup) findViewById(R.id.options);
        mHintButton = (Button) findViewById(R.id.hint_button);

        //On selecting an answer
        mOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                String answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                RadioButton mOptionAny = (RadioButton) findViewById(checkedId);
                String checkedAnswer = (String) mOptionAny.getText();
                String messageResId;
                if(checkedAnswer.equals(answerIsTrue))
                    messageResId = getResources().getString(R.string.correct_toast);
                else
                    messageResId= getResources().getString(R.string.incorrect_toast);
                Toast.makeText(MainActivity.this, messageResId, Toast.LENGTH_SHORT).show();
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

        mHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hint=mQuestionBank[mCurrentIndex].getHintRes();
                Intent i=HintActivity.newIntent(MainActivity.this, hint);
                //startActivity(i);
                startActivityForResult(i,REQUEST_CODE);
            }
        });
    }


    //Next Question
    private void updateQuestion() {
        String question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionText.setText(question);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE) {
            if (data == null) {
                return;
            }
            mIsHint = HintActivity.wasHintUsed(data);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }
}