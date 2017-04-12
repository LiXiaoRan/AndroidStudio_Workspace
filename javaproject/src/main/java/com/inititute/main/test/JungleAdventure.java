package com.inititute.main.test;/*
    随机产生一只怪物，有幼年的、发育不良的、成年的、强壮的、
    悍勇的、精英、Boss等级别。
    基础血量是100点，也就是按照怪物的级别不同，
    分别有血量加成。倍率如下：
    幼年的：5；发育不良的：8；成年的：10；强壮的：12；
    悍勇的：15；精英：20；Boss：30。
     
    每次对怪物的攻击造成1-100点伤害。直至杀死
*/
import java.util.Scanner;
class JungleAdventure 
{
    public static void main(String[] args) 
    {
        //初始化场景地形。
        String map = getMap();
        //创建怪物，包含名字、称号、属性等。
        String monstName = getMonstName();
        int monstLevel = getMonstLevel();
        String monst = getMonst(monstName,monstLevel);
        int monstHP = getMonstHP(monstLevel);
        int monstAttack = getMonstAttack(monstLevel);
        //生成人物信息
 
 
        int personAttack = (int)(Math.random()*40+10) , count =0;
 
 
        int max = 0 ,damage = 0;
 
        System.out.println("你正在"+map+"中冒险，不幸进入了"+monst+"的领地，在它反应过来之前，你率先发起了攻击");
        while (monstHP > 0)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("请选择攻击力度（1-5）：");
            int baoJi = (int)(Math.random()*2+1);
            String flagBaoJi = baoJi>1 ? "双倍暴击！" : "";
            int attack = (int)(Math.random()*70+personAttack+sc.nextInt());
            System.out.println("此次攻击对"+monst+"造成："+ flagBaoJi +attack * baoJi +"点伤害！");
            int monstDamage = (int)((Math.random()-0.5)*50);
            System.out.println(monst+"狠狠地对你进行了还击，造成"+(monstDamage+monstAttack)+"点伤害。");
            System.out.println("==========马上进入下一环节==========");
            monstHP -= attack * baoJi;
            max += attack * baoJi;
            damage += monstDamage+monstAttack;
            count++;
        }
        System.out.println("终于胜利了，经过漫长而艰苦的战斗，"+monst+"倒在了你的脚下！恭喜你");
        String reward = getReward(monstLevel,monstName,count);
        System.out.println("你打扫了战场，发现了"+reward);
        System.out.println("这场战斗您共发动了"+count+"次攻击，造成了"+max+"点的巨额伤害。");
        String hurt = getHurt(damage);
        System.out.println("您遭受到了"+damage+"点的伤害，"+hurt);
         
    }
    public static String getReward(int monstLevel,String monstName,int count)
    {
        //初始化装备兑换点数100点，根据怪物的强度和攻击回合数决定最终点数
        //怪物越强奖励会更高，攻击回合数越多，奖励相对会更差
        //掉落的物品与怪物的种族有关
        String reward;
        int point = 100;
        point = point * monstLevel;//计算怪物强度对应的奖励积分公式
        point = point +(200- count*10);//计算回合数的积分情况，超过20汇合，这项会被扣分。
        if (point < 50)
        {
            reward = monstName + "在紧张战斗中流下的血液，而且早已凝固毫无采集可能。";
        }
        else if (point < 60)
        {
            reward = monstName + "用过的破烂头盔";
        }
        else if (point < 70)
        {
            reward = monstName + "剩下的牙齿，似乎还可以用来挠痒痒";
        }
        else if (point < 80)
        {
            reward = monstName + "残缺的头骨，你决定回到小镇后找工匠制作成一直酒杯";
        }
        else if (point < 90)
        {
            reward = monstName + "为他的后代准备传家宝，貌似可以跟吉普赛人换个三瓜俩枣";
        }
        else if (point < 100)
        {
            reward = monstName + "在历次战斗中的缴获，他把这些战利品都留给了你，发财了！";
        }
        else if (point < 110)
        {
            reward = monstName + "嘴里的半个棒棒糖，悻悻然之下，你把糖扔向远处，却发现落点那里竟然有一块狗头金，海森！";
        }
        else if (point < 120)
        {
            reward = monstName + "损毁严重的全身装甲和武器，也算是小发一笔了";
        }
        else if (point < 130)
        {
            reward = "以"+monstName + "命名的雕像，看起来是个不错的收藏";
        }
        else if (point < 140)
        {
            reward = monstName + "留下的较为完整的葫芦，有点像萨满们挂在棒子上面的容器，不知道里面装的是些什么玩意。有消息说要塞的维尼比较擅长巫术";
        }
        else if (point < 150)
        {
            reward = monstName + "残留的阴魂，它似乎久久不愿离去";
        }
        else if (point < 160)
        {
            reward = monstName + "留下的大批金币";
        }
        else if (point < 170)
        {
            reward = monstName + "留下的一所小木屋，里面的家具看起来还不错";
        }
        else if (point < 200)
        {
            reward = "一把闪闪发亮的长刀，上面用弯曲的文字隐约的写着“屠龙！”";
        }
        else if (point < 240)
        {
            reward = monstName + "是个穷鬼，什么都没给你留下，见鬼！！";
        }
        else if (point < 320)
        {
            reward = "有金光环绕的巫妖中亚发穿仗";
        }
        else if (point < 350)
        {
            reward = "无尽 红叉";
        }
        else if (point < 390)
        {
            reward = "三项之力";
        }
        else if (point < 410)
        {
            reward = "神圣武装套装";
        }
        else if (point < 450)
        {
            reward = "天使联盟套装";
        }
        else if (point < 490)
        {
            reward = "七彩神器套装";
        }
        else if (point < 500)
        {
            reward = monstName + "的全部家当，包括一只可爱的小"+monstName;
        }
        else if (point < 530)
        {
            reward = monstName + "半个屁股，还算值点钱";
        }
        else if (point < 550)
        {
            reward = monstName + "的门牙，哈哈哈哈……";
        }
        else if (point < 580)
        {
            reward = "以"+monstName + "命名的雕像，看起来是个不错的收藏";
        }
        else if (point < 600)
        {
            reward = "霸下套装";
        }
        else if (point < 630)
        {
            reward = "金顶枣阳槊";
        }
        else if (point < 650)
        {
            reward = "雌雄双股剑之雄剑";
        }
        else if (point < 670)
        {
            reward = "盘龙紫金棍";
        }
        else if (point < 690)
        {
            reward = "牛耳尖刀";
        }
        else if (point < 700)
        {
            reward = "一架飞机";
        }
        else if (point < 710)
        {
            reward = "坦克";
        }
        else if (point < 730)
        {
            reward = "编不下去了";
        }
        else
        {
            reward = "七龙珠！";
        }
        return reward;
    }
 
    public static String getHurt(int damage)
    {
        String hurt;
        switch (damage%4)
        {
        case 0:
            hurt = "但丝毫不受影响，依然精力充沛，活力四射！";
            break;
        case 1:
            hurt = "受了点轻伤，需要找个安静的地方休息一会。";
            break;
        case 2:
            hurt = "并且伤情严重，经过一周的痛苦挣扎，你的冒险终结于此，临死前你望向天空，看到了几只秃鹫正在上面盘旋~";
            break;
        case 3:
            hurt = "此时你已精疲力竭，好在不久之后一位精灵般的少女出现了，把你从死亡线上拉了回来，并且成为了你的爱人。";
            break;
        default:
            hurt = "但丝毫不受影响，依然精力充沛，活力四射！";
         
        }
        return hurt;
    }
 
    public static String getMap()
    {
        String map;
        int a = (int)(Math.random()*5);
        switch (a)
        {
        case 0:
            map = "平原";
            break;
             
        case 1:
            map = "河滩";
            break;
             
        case 2:
            map = "丛林";
            break;
             
        case 3:
            map = "荒原";
            break;
             
        case 4:
            map = "山地";
            break;
 
        default:
            map = "混沌";
            break;
        }
        return map;
    }
 
    public static String getMonstName()
    {
        int monstLevel = (int)(Math.random()*17);
        String monstName = "小怪兽";
 
        switch (monstLevel)
        {
        case 0:         
            break;
 
        case 1:         
            monstName = "髭狗";
            break;
        case 2:
            monstName = "蛮牛";
            break;
        case 3:
            monstName = "影猫";
            break;
        case 4:
            monstName = "蜥象";
            break;
        case 5:
            monstName = "半人马";
            break;
        case 6:
            monstName = "冰狼";
            break;
        case 7:
            monstName = "独角兽";
            break;
        case 8:
            monstName = "剑齿虎";
            break;
        case 9:
            monstName = "蝎狮";
            break;
        case 10:
            monstName = "烈火精灵";
            break;
        case 11:
            monstName = "刺刀螳螂";
            break;
        case 12:
            monstName = "混乱九头蛇";
            break;
        case 13:
            monstName = "魅惑女妖";
            break;
        case 14:
            monstName = "黑龙";
            break;
        case 15:
            monstName = "树妖";
            break;
        case 16:
            monstName = "猛犸";
            break;
        case 17:
            monstName = "邪骨马";
            break;
         
        default:
            monstName = "洪荒巨兽";
            break;
        }
        return monstName;
    }
 
    public static int getMonstLevel()
    {
        int monstLevel = (int)(Math.random()*7);
        return monstLevel;
    }
 
    public static int getMonstAttack(int monstLevel)
    {
        int monstAttack = (int)(Math.random()*100+10*monstLevel);
        return monstAttack;
    }
 
    public static String getMonst(String monstName,int monstLevel)
    {
        String monst;
 
        switch (monstLevel)
        {
        case 0:
            monst = "幼年的"+monstName;
            break;
 
        case 1:
            monst = "发育不良的"+monstName;
            break;
        case 2:
            monst = "成年的"+monstName;
            break;
        case 3:
            monst = "强壮的"+monstName;
            break;
        case 4:
            monst = "悍勇的"+monstName;
            break;
        case 5:
            monst = "精英"+monstName;
            break;
        case 6:
            monst = monstName + "Boss（危险级别！）";
            break;
         
        default:
            monst = monstName + "洪荒巨兽（赶快投降）";
            break;
        }
        return monst;
    }
     
    public static int getMonstHP(int monstLevel)
    {
        int monstHP;
        switch (monstLevel)
        {
        case 0:
            monstHP = 100 * 5;
            break;
 
        case 1:
            monstHP = 100 * 8;
            break;
        case 2:
            monstHP = 100 * 10;
            break;
        case 3:
            monstHP = 100 * 12;
            break;
        case 4:
            monstHP = 100 * 15;
            break;
        case 5:
            monstHP = 100 * 20;
            break;
        case 6:
            monstHP = 100 * 30;
            break;
         
        default:
            monstHP = 100 * 30000;
            break;
        }
        return monstHP;
    }
 
}