package com.inititute.remoteviews;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview1);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里是普通的view动画
                Toast.makeText(MainActivity.this, "aaaaa", Toast.LENGTH_SHORT).show();
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anmi_list);
                button.startAnimation(animation);

                startTextAnimator();

            }
        });

    }

    //属性动画
    private void startTextAnimator() {

        /**
         * 使用属性动画的时候，所操作的属性必须提供了get和set方法，如果这个属性拥有初始值，则不需要get方法，
         * 不然会闪退，然后就是需要对属性的修改通过某种变化反映出来(例如UI的变化)，不然没效果
         *
         * 这里有三种办法达到上面的效果：
         * 1、添加get和set方法  (这个很多时候并不可行，比如系统控件)
         * 2、用一个类来包装原始对象，间接提供get、set方法
         * 3、 采用valueAnimator,监听动画过程，自己实现属性的改变。
         * */
        ViewWrapper viewWrapper = new ViewWrapper(textView);//这里是采用了第二种
        ObjectAnimator.ofInt(viewWrapper, "width", 800).setDuration(5000).start();
    }


}


class ViewWrapper {

    private View target;

    public ViewWrapper(View target) {
        this.target = target;
    }

    public int getWidth() {
        return target.getLayoutParams().width;
    }

    public void setWidth(int width) {
        target.getLayoutParams().width = width;
        target.requestLayout();
    }
}