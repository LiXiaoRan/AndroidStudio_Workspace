package com.liran.flabbybird.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.liran.flabbybird.R;

/**
 * 注册页面
 * Created by LiRan on 2017-02-04.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    EditText userName_ed;
    EditText passwd_ed;
    EditText passwd_sec_ed;
    Button mRegister_btn;
    CheckBox angree_cb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName_ed = (EditText) findViewById(R.id.edit_username);
        passwd_ed = (EditText) findViewById(R.id.edit_passwd);
        passwd_sec_ed = (EditText) findViewById(R.id.edit_passwd_sec);
        mRegister_btn = (Button) findViewById(R.id.register_button);
        angree_cb = (CheckBox) findViewById(R.id.angree_cb);

        mRegister_btn.setOnClickListener(this);

    }

    /**
     *开始注册的逻辑
     * @param username_str
     * @param passwd_str
     */
    private void onRegisterAccunt(String username_str, String passwd_str) {

        Toast.makeText(this, "后续功能正在开发中。。。", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.register_button:
                //点击注册按钮后的逻辑处理

                if (angree_cb.isChecked()) {

                    String username_str = userName_ed.getText().toString().trim();
                    String passwd_str = passwd_ed.getText().toString().trim();
                    String passwd_sec_str = passwd_sec_ed.getText().toString().trim();

                    if ("".equals(username_str) || "".equals(passwd_str) || "".equals(passwd_sec_str)) {

                        Toast.makeText(this, "没有任何一项可以为空", Toast.LENGTH_SHORT).show();

                    } else {


                        if (passwd_str.equals(passwd_sec_str)) {
                            //填写的一切信息合格，开始注册逻辑
                                    onRegisterAccunt(username_str,passwd_str);

                        } else {

                            Toast.makeText(this, "两次密码输入不同", Toast.LENGTH_SHORT).show();
                        }


                    }


                } else {

                    Toast.makeText(this, "请勾选同意我们的协议", Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }

}
