package com.example.rushabh.qzapp;

/**
 * Created by Rushabh on 09-09-2017.
 */

public class Questions {
    private String mTextResId;
    private String mAnswerTrue;
    private String mHintRes;

    public Questions(String textResId, String answerTrue, String hintRes) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mHintRes = hintRes;
    }

    public String getTextResId() {
        return mTextResId;
    }

    public String isAnswerTrue() {
        return mAnswerTrue;
    }

    public String getHintRes() {
        return mHintRes;
    }
}
