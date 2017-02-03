package com.liran.flabbybird.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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


        mPassword_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    // attemptLogin();
                    return true;
                }
                return false;
            }
        });

    



    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            //点击了登陆按钮后的处理
            case R.id.login_button:




            break;

            //点击了登陆注册后的处理
            case R.id.register_button:
            break;


        }


    }


}

