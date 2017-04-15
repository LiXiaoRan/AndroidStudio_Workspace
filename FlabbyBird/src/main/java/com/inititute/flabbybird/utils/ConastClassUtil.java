package com.inititute.flabbybird.utils;

import com.inititute.flabbybird.bean.Info_score;
import com.inititute.flabbybird.bean.User;

import java.util.List;

/**
 * Created by LiRan on 2017-02-05.
 */

public class ConastClassUtil {


    /**
     * 有米广告appid
     */
    public static final String appId="f4a63522ed52c370";

    /**
     * 有米广告秘钥
     */
    public static final String appSecret="eddea07c9e61070f";


    /**
     * 存储用户数据的list
     */
    public static List<User> userList;

    /**
     * 存储排行榜数据
     */
    public static  List<Info_score> infoScoreList;


    /**
     * 当前处于登录状态的用户名
     */
    public static String logingUsername;


    /**
     * 鸟死亡时的分数
     */
    public static int conastGrade;


    /**
     * 两个管道之间的距离 用来调整游戏难度的一个因素 简单250 一般 220 地狱 180
     */
    public static  int POPE_DISTENCE=250;

    /**
     *游戏的速度 用来调整游戏难度的一个因素 简单4 一般 6  地狱  8
     */
    public static  int Game_Speed=4;


    /**
     *点击屏幕后小鸟上升的高度 用来调整游戏难度的一个因素 简单-16 一般 -18  地狱  -20
     */
    public static  int TOUCH_UP_DISTENCE=-16;

    /**
     *游戏难度等级
     */
    public static  int GAME_LEVEL=0;

    /**
     * 记录小鸟死亡时间
     */
    public static String deadTime;

}
