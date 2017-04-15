package com.inititute.flabbybird.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.inititute.flabbybird.MainActivity;
import com.inititute.flabbybird.R;
import com.inititute.flabbybird.bean.User;
import com.inititute.flabbybird.utils.ConastClassUtil;
import com.inititute.flabbybird.utils.MyApplication;
import com.orhanobut.logger.Logger;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements OnClickListener {


    // UI references.
    private EditText mAccunt_edit;
    private EditText mPassword_edit;
    private Button mlogin_btn;//登录按钮
    private Button mRegister_btn;//注册按钮
    private Button mLevel_btn;//调整游戏难度按钮
    private LayoutInflater layoutInflater;
    private View dialogView;
    private String[] items = new String[]{"简单", "一般", "困难"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mPassword_edit = (EditText) findViewById(R.id.password);
        mAccunt_edit = (EditText) findViewById(R.id.email);
        mlogin_btn = (Button) findViewById(R.id.login_button);
        mRegister_btn = (Button) findViewById(R.id.register_button);
        mLevel_btn = (Button) findViewById(R.id.level_button);

        layoutInflater = LayoutInflater.from(this);
        dialogView = layoutInflater.inflate(R.layout.level_dialog, null);


        mlogin_btn.setOnClickListener(this);
        mRegister_btn.setOnClickListener(this);
        mLevel_btn.setOnClickListener(this);


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

            //点击了选择难度按钮后
            case R.id.level_button:

                selectLevel();

                break;


        }


    }

    /**
     * 实时记录当前的难度等级
     */
    private int currWhich = 0;

    /**
     * 选择游戏难度
     */
    private void selectLevel() {

        currWhich = 0;

        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
        aBuilder.setTitle("请选择难度(～￣▽￣)～");
        aBuilder.setCancelable(false);

        aBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(LoginActivity.this, "选择了：" + items[which], Toast.LENGTH_SHORT).show();
                currWhich = which;
            }
        });


        aBuilder.setPositiveButton("确定选择", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(LoginActivity.this, "点击了确定按钮", Toast.LENGTH_SHORT).show();
                Logger.d("此时的确定的是第几个选项: " + currWhich);

                ConastClassUtil.GAME_LEVEL = currWhich;

                switch (ConastClassUtil.GAME_LEVEL) {
                    case 0://简单难度
                        updateGameLevel(250, 4, -16);
                        break;
                    case 1://一般难度
                        updateGameLevel(220, 6, -18);
                        break;
                    case 2://地狱难度
                        updateGameLevel(180, 8, -20);
                        break;
                }

            }
        });

        aBuilder.setNegativeButton("算了吧", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(LoginActivity.this, "什么也没有选择(+﹏+)~", Toast.LENGTH_SHORT).show();

            }
        });


        aBuilder.show();

    }

    /**
     * 更改游戏难度
     *
     * @param pope_distence 管道间距
     * @param speed         游戏速度
     * @param up_distence   小鸟上升距离
     */
    private void updateGameLevel(int pope_distence, int speed, int up_distence) {
        ConastClassUtil.POPE_DISTENCE = pope_distence;
        ConastClassUtil.Game_Speed = speed;
        ConastClassUtil.TOUCH_UP_DISTENCE = up_distence;
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

                ConastClassUtil.logingUsername = name;
                Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                Intent gameIntent = new Intent(this, MainActivity.class);
                startActivity(gameIntent);


            } else {
                Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }


        }


    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder ebuilder = new AlertDialog.Builder(this);
        ebuilder.setTitle("少侠你按下了回退键");
        ebuilder.setMessage("您真的准备退出游戏吗？");
        ebuilder.setCancelable(false);
        ebuilder.setIcon(R.mipmap.b1);

        ebuilder.setPositiveButton("=_=嗯 退了吧", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyApplication.getMyApplication().exit();
            }
        });

        ebuilder.setNegativeButton("┌(。Д。)┐ 算了吧", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(LoginActivity.this, "如果觉得自己很厉害可以试试地狱难度哦", Toast.LENGTH_SHORT).show();
            }
        });

        ebuilder.show();

        Toast.makeText(this, "按下了回退键", Toast.LENGTH_SHORT).show();
    }
}

