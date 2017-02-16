package com.liran.flabbybird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.liran.flabbybird.MainActivity;
import com.liran.flabbybird.R;
import com.liran.flabbybird.bean.User;
import com.liran.flabbybird.utils.ConastClassUtil;
import com.liran.flabbybird.utils.MyApplication;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements OnClickListener {


    // UI references.
    private EditText mAccunt_edit;
    private EditText mPassword_edit;
    private Button mlogin_btn;//登录按钮
    private Button mRegister_btn;//注册按钮


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

        switch (v.getId()) {
            //点击了登陆按钮后的处理
            case R.id.login_button:

                String name = mAccunt_edit.getText().toString().trim();
                String passwd = mPassword_edit.getText().toString().trim();

                //登录处理
                onLogin(name, passwd);

                break;

            //点击了登陆注册后的处理
            case R.id.register_button:

                Intent regisIntent = new Intent();
                regisIntent.setClass(this, RegisterActivity.class);
                startActivity(regisIntent);

                break;


        }


    }




    /**
     * 登录逻辑处理
     *
     * @param name   用户名
     * @param passwd 密码
     */
    private void onLogin(String name, String passwd) {

        //从数据库中读数据
        ConastClassUtil.userList = MyApplication.getDB().findAll(User.class);

         boolean isLogin = false;

        if ("".equals(name) || "".equals(passwd)) {

            Toast.makeText(this, "用户名和密码都不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //用户名和密码不为空

            for (User user : ConastClassUtil.userList) {

                if (name.equals(user.getUsername()) && passwd.equals(user.getPasswd())) {
                    isLogin = true;
                    break;
                }

            }

            if (isLogin) {
                //登陆成功

                ConastClassUtil.logingUsername=name;
                Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                Intent gameIntent=new Intent(this, MainActivity.class);
                startActivity(gameIntent);


            } else {
                Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }


        }


    }


}

