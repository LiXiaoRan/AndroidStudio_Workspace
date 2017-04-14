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


    public static String deadTime;

}
