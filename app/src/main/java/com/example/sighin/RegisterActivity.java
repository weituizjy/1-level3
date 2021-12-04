package com.example.sighin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText mEtusername;
    private EditText mEtpassword;
    private Button mBtnregister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mBtnregister=(Button) findViewById(R.id.btn_register_re);
        mEtusername=findViewById(R.id.et_register_username);
        mEtpassword=findViewById(R.id.et_register_password);

        mBtnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=mEtusername.getText().toString();
                String password=mEtpassword.getText().toString();
                SharedPreferences pref=getSharedPreferences("date",MODE_PRIVATE);
                SharedPreferences.Editor editor=getSharedPreferences("date",MODE_PRIVATE).edit();
                editor.putString("name",username);
                editor.putString("password",password);
                editor.apply();

                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                intent.putExtra("name",pref.getString("name",""));
                intent.putExtra("password",pref.getString("password",""));
                startActivity(intent);


                Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                finish();
        }
        });
    }
}