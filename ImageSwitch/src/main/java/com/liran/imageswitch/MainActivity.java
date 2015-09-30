package com.liran.imageswitch;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liran.imageswitch.Bean.FloderBean;
import com.liran.imageswitch.Utils.ImageLoader;
import com.liran.imageswitch.View.ListImgPopWindow;
import com.liran.imageswitch.adapter.ViewHolder;
import com.liran.imageswitch.adapter.myBaseAdapter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    private String TAG = "MainActivity";

    private Toolbar toolbar;


    private GridView mGridView;
    private RelativeLayout mBottonLy;
    private TextView mDirName;
    private TextView mDirCount;

    private File mCurrentDir;
    private int mMaxCount;

    private ProgressDialog mProgressBarDialog;

    /**
     * popwindow的数据
     */
    private List<FloderBean> mFloderBeans = new ArrayList<FloderBean>();

    /**
     * popWindow
     */
    private ListImgPopWindow mlistImgPopWindow;


    private List<String> mImgs = new ArrayList<String>();

    /**
     * grideview的数据适配器
     */
    private myBaseAdapter ImageAdapter;

    private static final int DATA_LOADED = 0x110;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == DATA_LOADED)
                mProgressBarDialog.dismiss();
            //绑定数据到view中
            dataToView();
            initDirPopWindow();
        }
    };

    /**
     * 初始化popwindow
     */
    private void initDirPopWindow() {
        Log.d(TAG, "进入了initDirPopWindow ");
        mlistImgPopWindow = new ListImgPopWindow(MainActivity.this, mFloderBeans);
        Log.d(TAG, "initDirPopWindow -->ListImgPopWindow构造方法调用结束");
        mlistImgPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //将内容区域变亮   （在popWindow消失时候调用）
                lightOn();

            }
        });

    }

    /**
     * 将内容区域变亮
     */
    private void lightOn() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1.0f;
        getWindow().setAttributes(lp);
    }

    private void dataToView() {
        if (mCurrentDir == null) {
            Toast.makeText(MainActivity.this, "未扫描到图片", Toast.LENGTH_SHORT).show();
        }

        mImgs = Arrays.asList(mCurrentDir.list());
        Log.d(TAG, "dataToView mImgs= " + mImgs);
        Log.d(TAG, "dataToView size= " + mImgs.size());
        Log.d(TAG, "dataToView mCurrentDir= " + mCurrentDir);
        Log.d(TAG, "dataToView mMaxCount= " + mMaxCount);
        initAdapter();
        mGridView.setAdapter(ImageAdapter);

        mDirCount.setText(mMaxCount + "");
        mDirName.setText(mCurrentDir.getName());

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        initDatas();
        initEvents();


    }

    /**
     * 初始化grideView的适配器
     */
    private void initAdapter() {
        ImageAdapter = new myBaseAdapter<String>(MainActivity.this, mImgs, mCurrentDir.getAbsolutePath(), R.layout.item_gridview) {
            @Override
            public void convert(final ViewHolder holder, final String ImagePath, final String mDirPath, final Set<String> mSelectImg) {

                //重置状态
                holder.setImageViewResourse(R.id.iv_item_image, R.mipmap.picture_no);
                holder.setImageButtonResourse(R.id.ib_item_select, R.mipmap.picture_unsekect);
                ((ImageView) holder.getView(R.id.iv_item_image)).setColorFilter(null);
                //加载图片
                ImageLoader.getInstance(3, ImageLoader.Type.LIFO).loadImage(mDirPath + "/" + ImagePath, (ImageView) holder.getView(R.id.iv_item_image));

                final String filePath = mDirPath + "/" + ImagePath;
                holder.getView(R.id.iv_item_image).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //已经被选择
                        if (mSelectImg.contains(filePath)) {
                            mSelectImg.remove(filePath);
                            ((ImageView) holder.getView(R.id.iv_item_image)).setColorFilter(null);
                            ((ImageView) holder.getView(R.id.ib_item_select)).setImageResource(R.mipmap.picture_unsekect);

                        } else {//未被选择
                            mSelectImg.add(filePath);
                            ((ImageView) holder.getView(R.id.iv_item_image)).setColorFilter(Color.parseColor("#77000000"));
                            ((ImageView) holder.getView(R.id.ib_item_select)).setImageResource(R.mipmap.picture_select);
                        }
//                        ImageAdapter.notifyDataSetChanged();  //通过这种方式更新UI会导致黑闪
                    }
                });
                //这一段代码是用来解决服用问题的
                if (mSelectImg.contains(filePath)) {
                    ((ImageView) holder.getView(R.id.iv_item_image)).setColorFilter(Color.parseColor("#77000000"));
                    ((ImageView) holder.getView(R.id.ib_item_select)).setImageResource(R.mipmap.picture_select);
                }
            }
        };


    }

    /**
     * 初始化所有的事件
     */
    private void initEvents() {
        mBottonLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistImgPopWindow.showAsDropDown(mBottonLy,0,0);
                Log.d(TAG, "onClick 在这里Show   popwindow");
                //在popWindow弹出时内容区域变暗
                ligitOff();
            }
        });
    }

    private void ligitOff() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().setAttributes(lp);
    }

    /**
     * 遍历存储卡中所有的图片  (利用contentPrivider)
     */
    private void initDatas() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(MainActivity.this, "当前存储卡不可用", Toast.LENGTH_SHORT).show();
        }
        mProgressBarDialog = ProgressDialog.show(this, "", "正在加载...");
        new Thread() {
            @Override
            public void run() {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver contentResolver = MainActivity.this.getContentResolver();
                Cursor cursor = contentResolver.query(mImageUri, null, MediaStore.Images.Media.MIME_TYPE + " = ? or " +
                                MediaStore.Images.Media.MIME_TYPE + " = ? ", new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);
                Set<String> mDirpaths = new HashSet<String>();
                while (cursor.moveToNext()) {
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));//获得图片路径
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null) {
                        continue;
                    }

                    String dirPath = parentFile.getAbsolutePath();
                    FloderBean floderBean = null;
                    //由于同一个路径下可能放着很多图片，所以为了防止重复遍历需要这样
                    if (mDirpaths.contains(dirPath)) {
                        continue;
                    } else {
                        mDirpaths.add(dirPath);
                        floderBean = new FloderBean();
                        floderBean.setDir(dirPath);
                        floderBean.setFirstImagePath(path);

                    }

                    if (parentFile.list() == null) {
                        continue;
                    }

                    //获取这个目录下图片的数量
                    int picSize = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {

//                            if (filename.endsWith(".jeg") || filename.endsWith("jpeg") || filename.endsWith("png")) {
                            if (filename.toLowerCase().endsWith("jpg") || filename.toLowerCase().endsWith("png") || filename.toLowerCase().endsWith("jpeg")) {
                                return true;
                            }
                            return false;
                        }
                    }).length;
                    floderBean.setCount(picSize);
                    mFloderBeans.add(floderBean);

                    if (picSize > mMaxCount) {
                        mMaxCount = picSize;
                        mCurrentDir = parentFile;
                    }

                }
                cursor.close();
                //通知Handler扫描图片完成
                mHandler.sendEmptyMessage(DATA_LOADED);
            }
        }.start();
    }

    /**
     * 初始化所有控件
     */
    private void initView() {
        mGridView = (GridView) findViewById(R.id.grideview);
        mBottonLy = (RelativeLayout) findViewById(R.id.relayout_bettom);
        mDirName = (TextView) findViewById(R.id.tv_dir_name);
        mDirCount = (TextView) findViewById(R.id.tv_dir_count);
    }


}
