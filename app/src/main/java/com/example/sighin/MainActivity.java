package com.example.sighin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private ImageView mImgHead;
    private EditText mEtUsername;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private Button mBtnRegister;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgHead=findViewById(R.id.img_main_head);
        mEtUsername=findViewById(R.id.et_main_username);
        mEtPassword=findViewById(R.id.et_main_password);
        pref=PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass=(CheckBox) findViewById(R.id.remeber_password);
        mBtnLogin=(Button) findViewById(R.id.btn_main_login);
        mBtnRegister=(Button) findViewById(R.id.btn_main_register);




        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        boolean isRemember=pref.getBoolean("remember_password",false);
        if(isRemember){
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            mEtUsername.setText(account);
            mEtPassword.setText(password);
            rememberPass.setChecked(true);
        }
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=mEtUsername.getText().toString();
                String password=mEtPassword.getText().toString();

                Intent intent=getIntent();
                String username=intent.getStringExtra("name");
                String passkey=intent.getStringExtra("password");

                if(account.equals(username)&&password.equals(passkey)){
                    editor=pref.edit();
                    if(rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("acconut",account);
                        editor.putString("password",password);
                    }else{
                        editor.clear();
                    }
                    editor.apply();
                    loginSuccess(account, password);
                    finish();
                }else{
                    loginFailure();
                }
            }
        });
    }

    private void loginSuccess(String username,String password){
        Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
        ContentActivity.startActivity(this, username, password);
    }
    private void loginFailure(){
        Toast.makeText(this, "登录失败！", Toast.LENGTH_SHORT).show();
    }

}