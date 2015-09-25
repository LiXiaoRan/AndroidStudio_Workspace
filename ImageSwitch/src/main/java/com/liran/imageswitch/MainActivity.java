package com.liran.imageswitch;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liran.imageswitch.Bean.FloderBean;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


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

    private List<String> mImgs=new ArrayList<String>();

    private static final int DATA_LOADED=0x110;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
                super.handleMessage(msg);
            if(msg.what==DATA_LOADED)
            mProgressBarDialog.dismiss();
            //绑定数据到view中
            dataToView();
        }
    };

    private void dataToView() {
        if(mCurrentDir==null){
            Toast.makeText(MainActivity.this, "未扫描到图片", Toast.LENGTH_SHORT).show();
        }

        mImgs= Arrays.asList(mCurrentDir.list());

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
     * 初始化所有的事件
     */
    private void initEvents() {

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
                Cursor cursor = contentResolver.query(mImageUri, null, MediaStore.Images.Media.MIME_TYPE + "= ? or" +
                                MediaStore.Images.Media.MIME_TYPE + "= ? ", new String[]{"image/jpeg", "image/png"},
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

                            if (filename.endsWith(".jeg") || filename.endsWith("jpeg") || filename.endsWith("png")) {
                                return true;
                            }
                            return false;
                        }
                    }).length;
                    floderBean.setCount(picSize);
                    mFloderBeans.add(floderBean);

                    if(picSize>mMaxCount){
                        mMaxCount=picSize;
                        mCurrentDir=parentFile;
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
