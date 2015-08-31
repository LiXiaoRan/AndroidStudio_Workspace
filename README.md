
my androidstudio1.3 workspace  <br>
现在不仅不会markdown，而且还没用过gitgui。

`[TOC]`

include {<br>
	**Vollov**---> Volley  自己谢了一个volley库，做其他项目的时候可以拿来用<br>
				:-------VolleyRequst.java： 对Volley的get和post方法进行了很好的封装。<br>
				:-------VolleyHttpUtils.java：只是业余随便写写<br>
				:------- 加载图片用的了imagerequest和imageloader方法<br>
	**revycleView**----> recycleview   recycleview<br>
	**haoba**------> Tablayout 演示一下tablayout的用法  总结了一下<br>
	**app**--->Toolbar  只是试试toolbar的用法<br>
	**app**---> listview super base adapter  这里面的有一个万能适配器和viewholder  具体用法见代码<br>

}<br>



大标题(下面是readme练手部分)
===

中标题
---

添加一个空行 大标题就消失  其实只要不想让下方的文字被上方的格式所改变，只需要在前面加上一个空行就行
 ===


#一级标题
##二级标题
###三级标题
####四级标题
#####五级标题
######六级标题   最多支持六级

		hello,大家好,我在这一句的前面加上两个tab键，然后这一句就会有这样的显示的效果。是不是很酷炫。
		感觉这个编辑器还可以吧,github果然牛逼啊!!!

部分文字的`高亮`,想让文字高亮，就用esc下面的那个键把文字`包围`起来  就这个--> ``

:smile:
[我的博客](http://blog.csdn.net/qq997843911 "悬停显示")  这里演示的是超链接功能

* 姓名：超神冉
* 昵称: LiXiaoRan
* 星号加空格就会变成圆点符号
	* 二级其实就是加了一个tab
		* 三级再加一个
			* 四级。。。。


>数据结构
>>树
>>>二叉树
>>>>平衡二叉树
>>>>>满二叉树

###我尝试下一啊
>有时候我们想显示一个GitHub仓库(或者说项目)里的图片而不是一张其他来源网络图片，因为其他来源的URL很可能会失效。那么如何显示一个GitHub项目里的图片呢？
其实与上面的格式基本一致的，所不同的就是括号里的URL该怎么写。https://github.com/ 你的用户名 / 你的项目名 / raw / 分支名 / 存放图片的文件夹 / 该文件夹下的图片


###普通的显示图片
![gta5](http://s.pro-gmedia.com/videogamer/media/images/xbox360/gta5/screens/gta5_31_605x.jpg "GTA5")
  显示图片 叹号! + 方括号[ ] + 括号( ) 其中叹号里是图片的URL。
如果不加叹号! ,就会变成普通文本baidu了。


###给图片加上超链接
	如果你想使图片带有超链接的功能，即点击一个图片进入一个指定的网页。那么可以这样写：
[![baidu]](http://baidu.com)
[baidu]:http://www.baidu.com/img/bdlogo.gif "百度Logo"


###代码块
```Java
public static void main(String args[]) //java
```

```c
#include<studio> //c
```

```cpp
int main();
```