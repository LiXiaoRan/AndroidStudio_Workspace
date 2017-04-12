package com.inititute.lenovo.firstokhttptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OkHttpHandler.OKinterface {

    private static final String TAG = "MainActivity";
    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Log.d(TAG, "onClick: ");


                OkHttpHandler.getInstance().asyncGet("http://www.baidu.com");
                break;

        }

    }

    @Override
    public void dealResponse(Response response, Call call) {
        Log.d(TAG, "dealResponse: ");
        System.out.println("response is " + response.toString());
        System.out.println("call is " + call.toString());
        
    }
}
