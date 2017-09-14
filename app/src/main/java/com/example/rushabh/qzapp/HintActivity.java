package com.example.rushabh.qzapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    private static final String EXTRA_HINT = "com.example.rushabh.qzapp.hint";
    private static final String EXTRA_HINT_USED = "com.example.rushabh.qzapp.hint_used";
    private String mHint;
    private TextView mHintText;
    private Button mShowHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        mHint=getIntent().getStringExtra(EXTRA_HINT);

        mHintText = (TextView) findViewById(R.id.hint_text);
        mShowHint = (Button) findViewById(R.id.show_hint_button);
        mShowHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHintText.setText(mHint);
                setHintUsedResult(true);
            }
        });
    }

    public static Intent newIntent(Context packageContext, String hint) {
        Intent i = new Intent(packageContext, HintActivity.class);
        i.putExtra(EXTRA_HINT, hint);
        return i;
    }

    private void setHintUsedResult(boolean isHintUsed){
        Intent info=new Intent();
        info.putExtra(EXTRA_HINT_USED,isHintUsed);
        setResult(RESULT_OK,info);
    }

    public static boolean wasHintUsed(Intent result) {
        return result.getBooleanExtra(EXTRA_HINT_USED, false);
    }
}
