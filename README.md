# 自定义TabLayout
## 功能

自定义tab线的长度以及移动速度,和屏蔽指定tab点击移动事件(tablayout基本功能)

## Xml属性定义

线的颜色 ： indicatorColor (默认red)

textview显示数组 ：array

字体默认颜色：text_nomal_color (默认gray)

字体选中颜色：text_press_color （默认black）

字体默认大小：text_press_color (默认12)

字体选中大小：text_press_size (默认13)

默认选中：selected (默认0)

线是否铺满：isFull (默认false)

移动速度：speed (默认300)

线是字体的倍数：multiply (默认1.2)

线的高度： multiply （默认5）

## 后续

如果有什么特殊需求以及特殊想法可以留言
博客地址:[简书](http://www.jianshu.com/p/54c68ae64f77)
## 依赖

>项目下的

```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
        jcenter()
    }
}
```

>Moudle 下的

```
 compile 'com.github.q1104133609:MyTabLayout:v0.0.1'
```

