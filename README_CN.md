![image](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/viabuslogo.png)

![image](https://img.shields.io/badge/jcenter-0.3.3-brightgreen.svg)
![image](https://img.shields.io/badge/api-%2B15-blue.svg)
![image](https://img.shields.io/badge/license-Apache2.0-blue.svg)
![image](https://img.shields.io/badge/author-KunMinX-orange.svg)

# 什么是 ViaBus
⭐ ViaBus 是一款响应式架构，借助总线完成数据的请求和响应，实现ui、业务的完全解耦。

![image](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/viabus_flow.png)

# 为什么选择 ViaBus
- 你我都是勇于抓住事物本质、不断迭代精进的高效能人士。🌱
- 该架构明确界定了什么是 ui、什么是业务，为代码解耦提供了现实依据。💡
- 从前改需求，需倒腾 2小时，现加上单元测试，也仅需 30分钟。🔥
- 从前项目重构，需 3个人连续加班 1个月，现只需 1个人每天抽 2小时 渐进式重构。💧
- 从前临时改需求，ui、presenter编写者 都得加班，现在互不拖累，独立自治，专注深耕各自领域。🌲
- 从前跨 Activity、跨组件的实时双向通信，只能靠第三方库，现通过原生接口即可完成。⚡
- 从前架构中包含大量重复工作，且代码冗余，现在你写的每个类都被充分利用。💪
- ...

更多依据详见 [Wiki - Android各类架构横向测评](https://github.com/KunMinX/android-viabus-architecture/wiki/Android-%E5%90%84%E7%B1%BB%E6%9E%B6%E6%9E%84%E6%A8%AA%E5%90%91%E6%AF%94%E8%BE%83)

# 如何使用 ViaBus
在模块的 build.gradle 添加如下依赖
```
implementation "com.kunminx.viabus:viabus-android:0.3.3"
```
使用方法详见 [Wiki - 1分钟掌握 ViaBus 架构和使用](https://github.com/KunMinX/android-viabus-architecture/wiki/How-to-use-ViaBus)


# 见证者成就榜：

|见证者|重构耗时|项目链接|
|:--:|:--:|:--:|
|KunMinX|3小时|[bilibili 第三方客户端](https://github.com/KunMinX/bilibili-viabus-architecture)|
|MyateJx|3小时|[gank.io 客户端](https://github.com/KunMinX/Gank.io-viabus-architecture)|
|KunMinX|3小时|[WanAndroid 客户端](https://github.com/KunMinX/WanAndroid-viabus-architecture)|
