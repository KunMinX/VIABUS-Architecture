![image](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/viabuslogo.png)

![image](https://img.shields.io/badge/jcenter-0.3.3-brightgreen.svg)
![image](https://img.shields.io/badge/api-%2B15-blue.svg)
![image](https://img.shields.io/badge/license-Apache2.0-blue.svg)
![image](https://img.shields.io/badge/author-KunMinX-orange.svg)

# 什么是 ViaBus
⭐ ViaBus 是一款响应式架构，借助总线完成数据的请求和响应，实现ui、业务的完全解耦。

![image](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/viabus_flow.png)

# 为什么选择 ViaBus
- 1分钟即可掌握，ViaBus 的结构与使用方式。💡
- 当下就可以使用，对原项目架构完全兼容，即插即用。🌱
- 允许以 业务或模块为单位，每天1小时，完成渐进式重构。🔥
- 结构自带约束，先天解耦，再也不用担心同事挖坑。💧
- 通过原生接口即可完成 跨 Activity、跨组件的 实时双向通信。⚡
- 更少的重复工作，扁平的交互模式，代码复用率提升至100%。💪
- ...

更多依据详见 [Wiki - Android 各类架构横向测评](https://github.com/KunMinX/android-viabus-architecture/wiki/Android-%E5%90%84%E7%B1%BB%E6%9E%B6%E6%9E%84%E6%A8%AA%E5%90%91%E6%B5%8B%E8%AF%84)

# 如何使用 ViaBus
在模块的 build.gradle 添加如下依赖
```
implementation "com.kunminx.viabus:viabus-android:0.3.3"
```
使用方法详见 [Wiki - 1分钟掌握 ViaBus 架构和使用](https://github.com/KunMinX/android-viabus-architecture/wiki/1%E5%88%86%E9%92%9F%E6%8E%8C%E6%8F%A1-ViaBus-%E6%9E%B6%E6%9E%84%E7%9A%84%E4%BD%BF%E7%94%A8)


# 见证者成就榜：

|见证者|重构耗时|项目链接|
|:--:|:--:|:--:|
|KunMinX|3小时|[bilibili 第三方客户端](https://github.com/KunMinX/bilibili-viabus-architecture)|
|MyateJx|3小时|[gank.io 客户端](https://github.com/KunMinX/Gank.io-viabus-architecture)|
|KunMinX|3小时|[WanAndroid 客户端](https://github.com/KunMinX/WanAndroid-viabus-architecture)|
