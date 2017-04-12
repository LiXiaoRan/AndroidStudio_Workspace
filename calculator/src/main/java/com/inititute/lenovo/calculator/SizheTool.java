package com.inititute.lenovo.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 四则运算，可能不是最优的写法，自己测试没发现bug<br>
 * 前提：正确的算式,因为没做算式的验证<br>
 * 思路：<br>
 * 1、用正则拆分所有的元素例子：2-5+4*5/(2+2)-4<br>
 * 拆分为【2,-5,4,*,5,/,(,2,2,),-4】<br>
 * 2、先计算括号里，乘除 ，最后加减
 * 
 * 总结：总体花了17、8个小时 ，主要原因还是没有仔细总体分析流程导致。<br>
 * 以至于想到点写点代码、修修改改，没有一个完整的把握的思路。所以一个问题一定<br>
 * 先走通整个思路、流程 其实编码是最容易的 ，重点是处理的过程思路
 * 
 * @author 李冉
 * @time 2014-12-4 下午12:50:01
 * 
 */
public class SizheTool {
   /* public static void main(String[] args) {
        try {
            System.out.println(jisuanStr("11.2+3.1*(423-(2+5.7*3.4)+5.6)-6.4/(15.5+24)"));
        } catch (Exception e) {
            System.out.println("请检查你的算式格式");
            e.printStackTrace();
        }
    }*/

    /**
     * 拆分算式里的各个元素并处理对应所在位置<br>
     * 
     * @param
     * @return
     */
    public static List<String> splitStr(String string) throws Exception {
        List<String> listSplit = new ArrayList<String>();
        Matcher matcher = Pattern.compile("\\-?\\d+(\\.\\d+)?|[*/()]|\\-")
                .matcher(string);// 用正则拆分成每个元素
        while (matcher.find()) {
            // System.out.println(matcher.group(0));
            listSplit.add(matcher.group(0));
        }
        return listSplit;
    }

    /**
     * 计算<br>
     * 步骤：1、如果有括号<br>
     * 然后取上一个最近的(坐标 计算当前括号组合里的算式 ），在继续往下查找括号 以此类推,直至循环使用到所有坐标元素
     * 计算完毕（运算顺序括号、乘除、加减）
     * 
     * @param str
     * @return
     */
    public static double jisuanStr(String str) throws Exception {
        double returnDouble = 0;
        List<String> listSplit = splitStr(str); // 拆分好的元素
        List<Integer> zKuohaoIdxList = new ArrayList<Integer>();// 左括号,<所在坐标，>
        if (Pattern.compile(".*\\(|\\).*").matcher(str).find()) {// 如果包含括号运算
            String value = "";// 单个字符值
            int zIdx = 0;// 上一个左括号在zKuoHaoIdxList的下标
            // 此层循环计算完所有括号里的算式
            List<String> tempList = new ArrayList<String>();// 前面没有计算的元素
            int removeL = 0;
            int tempListSize = 0;
            for (int i = 0; i < listSplit.size(); i++) {
                value = listSplit.get(i);
                tempList.add(value);
                tempListSize = tempList.size();
                if ("(".equals(value)) {// 左括号
                    zKuohaoIdxList.add(tempListSize-1);
                } else if (")".equals(value)) {// 遇到右括号就计算与上一左括号间的算式
                    zIdx = zKuohaoIdxList.size() - 1;// 离当前右括号最近的左括号配对
                    int start = zKuohaoIdxList.get(zIdx);
                    returnDouble = jisuan(tempList, start + 1, tempListSize-1); // 开始位置,就是上一个左括号
                    removeL = tempListSize - start;
                    tempList = removeUseList(tempList, removeL);// 移除已使用的元素
                    tempList.add(returnDouble + "");// 刚刚计算的值添加进来
                    zKuohaoIdxList.remove(zIdx);// 计算完毕清除括号
                }
            }
            // 把所有计算完
            returnDouble = jisuan(tempList, 0, tempList.size());
        } else {// 没有括号运算
            returnDouble = jisuan(listSplit, 0, listSplit.size());
        }
        return returnDouble;
    }

    /**
     * 倒序删除已用过的元素
     * 
     * @param list
     * @param removeLength
     *            数量
     * @return
     */
    public static List<String> removeUseList(List<String> list, int removeLength) {
        int le = list.size() - removeLength;
        for (int i = list.size() - 1; i >= le; i--) {
            list.remove(i);
        }
        return list;
    }

    /**
     * 计算算式
     * 
     * @param listSplit
     * @param start
     *            括号算式开始符位置
     * @param end
     *            括号结束符位置
     * @return
     */
    public static double jisuan(List<String> listSplit, int start, int end)
            throws Exception {
        double returnValue = 0;
        String strValue = null;// 临时变量
        List<String> jjValueList = new ArrayList<String>();// 剩下的加减元素
        // 遍历计算乘除法
        for (int i = start; i < end; i++) {
            strValue = listSplit.get(i);
            if ("*".equals(strValue) || "/".equals(strValue)) {// 乘除
                strValue = jisuanValue("*".equals(strValue) ? "*" : "/", Double
                        .parseDouble(jjValueList.get(jjValueList.size() - 1)),
                        Double.parseDouble(listSplit.get(i + 1)))
                        + "";
                jjValueList.remove(jjValueList.size() - 1);
                i++;
            }
            jjValueList.add(strValue);
        }
        // 遍历计算加减
        for (int j = 0; j < jjValueList.size(); j++) {
            strValue = jjValueList.get(j);
            if ("-".equals(strValue) || "+".equals(strValue)) {
                returnValue = jisuanValue("-".equals(strValue) ? "-" : "+",
                        returnValue, Double.parseDouble(jjValueList.get(j + 1)));
                j++;
            } else {
                returnValue += Double.parseDouble(jjValueList.get(j));
            }
        }
        return returnValue;
    }

    /**
     * 计算2个数间的加减乘除操作 如：2*5 ，2/5
     * 
     * @param type
     *            运算符
     * @param start
     *            数 相当于上面2
     * @param end
     *            被数 相当于上面5
     * @return
     */
    public static double jisuanValue(String type, double start, double end)
            throws Exception {
        double d = 0;
        if ("-".equals(type)) {
            d = start - end;
        } else if ("+".equals(type)) {
            d = start + end;
        } else if ("*".equals(type)) {
            d = start * end;
        } else if ("/".equals(type)) {
            if (0 == start || 0 == end)
                d = 0;
            else
                d = start / end;
        }
        return d;
    }

}