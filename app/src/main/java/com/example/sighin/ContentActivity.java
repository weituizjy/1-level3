package com.example.sighin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ContentActivity extends AppCompatActivity {

    private static final String INTENT_USER_NAME = "username"; // intent 中的数据标记
    private static final String INTENT_PASSWORD = "password"; // intent 中的数据标记


    public static void startActivity(Context context, String username, String password) {
        Intent intent = new Intent(context, ContentActivity.class);
        intent.putExtra(INTENT_USER_NAME, username);
        intent.putExtra(INTENT_PASSWORD, password);
        context.startActivity(intent);
    }

    private String mUserName;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Intent intent = getIntent();
        mUserName = intent.getStringExtra(INTENT_USER_NAME);
        mPassword = intent.getStringExtra(INTENT_PASSWORD);
    }
}