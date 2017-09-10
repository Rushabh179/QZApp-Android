package com.example.rushabh.qzapp;

/**
 * Created by Rushabh on 09-09-2017.
 */

public class Questions {
    private int mTextResId;
    private int mAnswerTrue;

    public Questions(int textResId, int answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public void setAnswerTrue(int answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public int isAnswerTrue() {
        return mAnswerTrue;
    }
}
