package com.liran.touchscrollevent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyView myView;
    private Button button;

    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 300;
    private static final int DELAYUED_TIME = 33;

    private int count = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case MESSAGE_SCROLL_TO:
                    count++;
                    if(count<FRAME_COUNT){
                        float fraction=count/(float)FRAME_COUNT;
                        int scrollX= (int) (fraction*100);
                        myView.scrollTo(scrollX,0);
                        handler.sendEmptyMessageAtTime(MESSAGE_SCROLL_TO,DELAYUED_TIME);
                    }

                    break;

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = (MyView) findViewById(R.id.myview);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                handler.sendEmptyMessageAtTime(MESSAGE_SCROLL_TO,DELAYUED_TIME);

                break;
        }


    }
}
