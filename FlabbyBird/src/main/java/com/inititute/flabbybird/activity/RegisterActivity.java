package com.inititute.flabbybird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.inititute.flabbybird.R;
import com.inititute.flabbybird.bean.User;
import com.inititute.flabbybird.utils.ConastClassUtil;
import com.inititute.flabbybird.utils.MyApplication;

import static com.inititute.flabbybird.utils.ConastClassUtil.userList;

/**
 * 注册页面
 * Created by LiRan on 2017-02-04.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private static final String TAG = "RegisterActivity";
    private EditText userName_ed;
    private EditText passwd_ed;
    private EditText passwd_sec_ed;
    private Button mRegister_btn;
    private CheckBox angree_cb;


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

        userList = MyApplication.getDB().findAll(User.class);

        Log.d(TAG, "onCreate: userlist size is :" + userList.size());

    }

    /**
     * 开始注册的逻辑
     *
     * @param username_str
     * @param passwd_str
     */
    private void onRegisterAccunt(String username_str, String passwd_str) {


        userList = MyApplication.getDB().findAll(User.class);
        boolean isExist = false;

        //遍历数据 查找是否与已经注册的用户用户名相同
        if (ConastClassUtil.userList != null && !ConastClassUtil.userList.isEmpty()) {
            for (User userinfo : ConastClassUtil.userList) {
                if (username_str.equals(userinfo.getUsername().toString().trim())) {
                    isExist = true;
                    Toast.makeText(this, "注册失败，该用户名已经被注册", Toast.LENGTH_SHORT).show();
                    break;
                }

            }
        }

        if (!isExist) {
            User user = new User(username_str, passwd_str, 0);
            MyApplication.getDB().save(user);
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            Intent loginIntent=new Intent(this,LoginActivity.class);
            startActivity(loginIntent);
            finish();

        }


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

                        Toast.makeText(this, "用户名和密码都不能为空", Toast.LENGTH_SHORT).show();

                    } else {


                        if (passwd_str.equals(passwd_sec_str)) {
                            //填写的一切信息合格，开始注册逻辑
                            onRegisterAccunt(username_str, passwd_str);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
