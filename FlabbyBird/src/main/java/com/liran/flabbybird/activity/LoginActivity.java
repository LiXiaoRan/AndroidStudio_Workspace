package com.liran.flabbybird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.liran.flabbybird.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener{


    // UI references.
    private EditText mAccunt_edit;
    private EditText mPassword_edit;
    private Button mlogin_btn;
    private Button mRegister_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mPassword_edit = (EditText) findViewById(R.id.password);
        mAccunt_edit = (EditText) findViewById(R.id.email);
        mlogin_btn = (Button) findViewById(R.id.login_button);
        mRegister_btn = (Button) findViewById(R.id.register_button);


        mlogin_btn.setOnClickListener(this);
        mRegister_btn.setOnClickListener(this);



    



    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            //点击了登陆按钮后的处理
            case R.id.login_button:

            String name=mAccunt_edit.getText().toString().trim();
            String passwd=mPassword_edit.getText().toString().trim();

                Toast.makeText(LoginActivity.this,"name: "+name+" passwd:"+passwd,Toast.LENGTH_LONG).show();

            break;

            //点击了登陆注册后的处理
            case R.id.register_button:

                Intent regisIntent=new Intent();
                regisIntent.setClass(this,RegisterActivity.class);
                startActivity(regisIntent);

            break;


        }


    }


}

