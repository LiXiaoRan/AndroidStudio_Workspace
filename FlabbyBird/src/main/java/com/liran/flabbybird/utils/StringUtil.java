package com.liran.flabbybird.utils;

import com.liran.flabbybird.bean.Info_score;

import java.util.List;

/**
 * 用来处理字符串相关的类
 * Created by LiRan on 2017-04-11.
 */

public class StringUtil {


    public static String infoListToString(List<Info_score> info_scoreList){

        String strinfo=" ";
        for (Info_score info_score:info_scoreList){

            strinfo=strinfo+"name is "+info_score.getUsername()+" score is "+info_score.getScore()+" \n";
        }

        return strinfo;

    }


}
