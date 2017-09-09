package com.example.rushabh.qzapp;

/**
 * Created by Rushabh on 09-09-2017.
 */

public class Questions {
    private int mTextResId;
    private boolean mAnswerTrue;

    public Questions(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }
}
