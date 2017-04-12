package com.inititute.main.test;
/**
 * @author Administrator
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorDemo {
    String lastOption;//保存运算符号
    double a, result, b, c;//保存计算结果
    boolean start;//是否开始输入数字
    int fh;
    JTextField text1;//显示文本框
    //构成计算器的按钮用按钮数组来实现
    JButton[] btn;
    JButton[] btn1;
    private boolean isequal = false;

    public void go() {
        start = true;
        lastOption = "=";
        JFrame myWindow = new JFrame("计算器");
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        text1 = new JTextField(26);
        btn = new JButton[12];
        btn1 = new JButton[7];
        Container cp = myWindow.getContentPane();
        //设置面板的布局方式GridLayout
        p1.setLayout(new GridLayout(4, 3, 12, 13));
        p2.setLayout(new GridLayout(4, 2, 12, 13));
        cp.add(text1, BorderLayout.NORTH);
        //构成面板
        for (int i = 0; i < 7; i++) {
            btn1[i] = new JButton(i + "");
        }
        //构建符号并加入到按钮中
        btn1[6] = new JButton("/");
        btn1[5] = new JButton("C");
        btn1[4] = new JButton("*");
        btn1[3] = new JButton("退格");
        btn1[2] = new JButton("-");
        btn1[1] = new JButton("+/-");
        btn1[0] = new JButton("+");


        //采用for循环添加组件
        cp.add(p1, "West"); //放置中间容器p1
        cp.add(p2, "East"); ////放置中间容器p2
        for (int i = 0; i < 7; i++) {
            p2.add(btn1[i]);
        }
        for (int i = 0; i < 10; i++) {
            btn[i] = new JButton(i + "");
            p1.add(btn[i]);
        }

        btn[11] = new JButton(".");
        p1.add(btn[11]);
        btn[10] = new JButton("=");
        p1.add(btn[10]);


//采用for 循环为组件注册事件监听器
        for (int i = 0; i < 10; i++) {
            btn[i].addActionListener(new NumHandle());
        }
        btn[10].addActionListener(new OPtionHandle());

        btn[11].addActionListener(new OPtionHandle());

        for (int j = 0; j < 7; j++) {
            btn1[j].addActionListener(new OPtionHandle());
        }

        myWindow.pack();
        myWindow.setVisible(start);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    //创建内部类NumHandle监听数字键动作
    class NumHandle implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (isequal) {
                text1.setText("");
                isequal = false;
            }

            String num = e.getActionCommand();//得到数字键上的数字
            if (num.equals("0")) {  //输出0
                text1.setText(text1.getText() + "0");
            }
            if (num.equals("1")) {  //输出1
                if (text1.getText().equals("0")) //判断文本域中显示的数字是否为0
                    text1.setText("1");
                else
                    text1.setText(text1.getText() + "1");
            }
            if (num.equals("2")) {  //输出2
                if (text1.getText().equals("0"))
                    text1.setText("2");
                else
                    text1.setText(text1.getText() + "2");
            }
            if (num.equals("3")) { //输出3
                if (text1.getText().equals("0"))
                    text1.setText("3");
                else
                    text1.setText(text1.getText() + "3");
            }
            if (num.equals("4")) {  //输出4
                if (text1.getText().equals("0"))
                    text1.setText("4");
                else
                    text1.setText(text1.getText() + "4");
            }
            if (num.equals("5")) {  //输出5
                if (text1.getText().equals("0"))
                    text1.setText("5");
                else
                    text1.setText(text1.getText() + "5");
            }
            if (num.equals("6")) { //输出6
                if (text1.getText().equals("0"))
                    text1.setText("6");
                else
                    text1.setText(text1.getText() + "6");
            }
            if (num.equals("7")) {  //输出7
                if (text1.getText().equals("0"))
                    text1.setText("7");
                else
                    text1.setText(text1.getText() + "7");
            }
            if (num.equals("8")) {  //输出8
                if (text1.getText().equals("0"))
                    text1.setText("8");
                else
                    text1.setText(text1.getText() + "8");
            }
            if (num.equals("9")) {  //输出9
                if (text1.getText().equals("0"))
                    text1.setText("9");
                else
                    text1.setText(text1.getText() + "9");
            }
        }
    }

    //内部类OPtionHandle监听符号键的动作
    class OPtionHandle implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (isequal) {
                text1.setText("");
                isequal = false;
            }

            String option = e.getActionCommand();
            if (option.equals(".")) {
                text1.setText(text1.getText() + ".");
            }
                /*if(option.equals("sin")){
                    a=Double.parseDouble(text1.getText());//将文本域上的字符串转换为double型
                    double b=Math.sin(a/180*Math.PI); //现将a转换为弧度制，再进行运算
                    text1.setText(String.valueOf(b));  //输出运算结果
                }
                if(option.equals("cos")){
                    a=Double.parseDouble(text1.getText());
                    double b=Math.cos(a/180*Math.PI);
                    text1.setText(String.valueOf(b));
                }
                if(option.equals("tan")){
                    a=Double.parseDouble(text1.getText());
                    double b=Math.tan(a/180*Math.PI);
                    text1.setText(String.valueOf(b)); 
                }
                if(option.equals("log")){
                    a=Double.parseDouble(text1.getText());
                    double b=Math.log(a)/Math.log(10.0);
                    text1.setText(String.valueOf(b)); 
                }
                if(option.equals("sqrt")){
                    a=Double.parseDouble(text1.getText());
                    double b=Math.sqrt(a);
                    text1.setText(String.valueOf(b));
                }
                if(option.equals("pow")){  //该运算含有两个操作数
                    a=Double.parseDouble(text1.getText());
                    fh=4;  //
                    text1.setText("");  //将文本域的内容清空
                }
                if(option.equals("exp")){
                    a=Double.parseDouble(text1.getText());
                    b=a;
                    double b=Math.exp(a);
                    text1.setText(String.valueOf(b));
                }
                if(option.equals("求倒")){
                    a=Double.parseDouble(text1.getText());
                    text1.setText(String.valueOf(1/a));
                }*/
            if (option.equals("+/-")) {
                if (!"0".equals(text1.getText())) {  //判断文本域的数字是否为0
                    a = Double.parseDouble(text1.getText());
                    a = -a;  //求相反数运算
                    text1.setText(String.valueOf(a));
                }
            }
            if (option.equals("退格")) {
                int i = text1.getText().length();
                text1.setText(text1.getText().substring(0, i - 1));
            }
            if (option.equals("C")) {
                text1.setText("");  //清空文本域中的内容
            }
            if (option.equals("+")) {
                a = Double.parseDouble(text1.getText());
                fh = 0;
                text1.setText("");
                text1.setText("+");

            }
            if (option.equals("-")) {
                a = Double.parseDouble(text1.getText());
                fh = 1;
                text1.setText("");
                text1.setText("-");
            }
            if (option.equals("*")) {
                a = Double.parseDouble(text1.getText());
                fh = 2;
                text1.setText("");
                text1.setText("*");
            }
            if (option.equals("/")) {
                a = Double.parseDouble(text1.getText());
                fh = 3;
                text1.setText("");
                text1.setText("/");
            }
            //有两个操作的运算
            if (option.equals("=")) {
                String str=new String(text1.getText().substring(1));

                double c = Double.parseDouble(str);
                switch (fh) {
                    case 0:  //执行加法运算
                        result = a + c;
                        break;
                    case 1:  //执行减法运算
                        result = a - c;
                        break;
                    case 2:  //执行乘法运算
                        result = a * c;
                        break;
                    case 3:  //执行除法运算
                        result = a / c;
                        break;
                    case 4:  //执行a的b次幂运算
                        result = Math.pow(a, c);
                        break;
                }
                Integer dres=(int)result;
                text1.setText(String.valueOf(Integer.toBinaryString(dres)));//将运算结果转换为字符串形式输出
                isequal = true;
            }
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CalculatorDemo window = new CalculatorDemo();
        window.go();
        // TODO code application logic here

    }
}