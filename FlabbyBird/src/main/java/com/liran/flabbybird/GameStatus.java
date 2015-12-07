package com.liran.flabbybird;

/**
 * 游戏状态      WAITING, RUNNING, OVER<br></br>
 * 1、默认情况下，是WAITING状态，屏幕静止，上面就一只静止的鸟~~<br></br>
 * 2、当用户触摸屏幕时：进入RUNNING状态，游戏开始根据用户的触摸情况进行交互；<br></br>
 * 3、当鸟触碰到管道或者落到地上，那么进入GAMEOVER状态，OVER时，如果触碰的是管道，则让鸟落到地上以后，立即切换为WAITING状态。<br></br>
 * Created by LiRan on 2015-12-07.
 *
 */
public enum GameStatus {
    WAITING, RUNNING, OVER
}
