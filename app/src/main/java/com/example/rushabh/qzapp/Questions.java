package com.example.rushabh.qzapp;

/**
 * Created by Rushabh on 09-09-2017.
 */

public class Questions {
    private String mTextResId;
    private String mAnswerTrue;

    public Questions(String textRes, String answerTrue) {
        mTextResId = textRes;
        mAnswerTrue = answerTrue;
    }

    public String getTextResId() {
        return mTextResId;
    }

    public String isAnswerTrue() {
        return mAnswerTrue;
    }
}
