![image](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/viabuslogo.png)

![image](https://img.shields.io/badge/jcenter-0.4.8-brightgreen.svg)
![image](https://img.shields.io/badge/api-%2B15-blue.svg)
![image](https://img.shields.io/badge/license-Apache2.0-blue.svg)
![image](https://img.shields.io/badge/author-KunMinX-orange.svg)

### [中文文档](https://github.com/KunMinX/android-viabus-architecture/blob/master/README_CN.md) | [English](https://github.com/KunMinX/android-viabus-architecture/blob/master/README_EN.md)

# 什么是 ViaBus
⭐ ViaBus 是一款响应式架构，借助总线转发数据的请求和响应，实现 UI、业务的完全解耦。

![image](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/viabus_flow_cn.png)

# 为什么选择 ViaBus 
- 1分钟即可掌握，ViaBus 的结构与使用方式。💡
- 当下就可使用，对原项目架构完全兼容，即插即用。🔥
- 允许以 业务或模块为单位，每天1小时，完成渐进式重构。💧
- 职责边界明确，无论是写 UI 还是业务，不再被打断、相互不拖累。🌱
- 原生接口即可实现 跨 Activity、跨组件的 实时双向通信。⚡
- 无编译时注解，基于 Viabus 编写的组件可在任何项目中直接使用。🌎
- 更少的重复工作，扁平的交互模式，代码复用率提升至100%。💪
- ...

更多依据详见 [你一定想知道的，如何为项目挑选合适的架构](https://juejin.im/post/5bcd58b6e51d45404c71d23f)

# 如何使用 ViaBus 
在模块的 build.gradle 添加如下 任一 依赖
```java
//持续集成的 ViaBus 快速开发框架（包含 viabus 内核以及热门框架）
implementation "com.kunminx.viabus:architecture:0.4.8"

//纯粹的 ViaBus 内核（依赖它你就可以定制属于你的 ViaBus 架构啦）
implementation "com.kunminx.viabus:core:0.4.8"

```
使用方法详见 [1分钟掌握 ViaBus 架构和使用](https://www.jianshu.com/p/6545767d3e54)

# 谁在用 Viabus

|见证者|Logo|作品地址或项目链接|
|:--:|:--:|:--:|
|[MyateJx](https://github.com/MyateJx)|![sakernote](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/icon_sakernote.png)|[舒心录 - 酷安老牌记事本 app](https://www.coolapk.com/apk/com.myatejx.sakernote)|
|[MyateJx](https://github.com/MyateJx)|![gankio](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/icon_gank.png)|[gank.io 客户端](https://github.com/MyateJx/GankIo-viabus-architecture)|


# 鸣谢

RxJava

# 更多访问

Email：[kunminx@gmail.com](mailto:kunminx@gmail.com)

Home：[KunMinX 的个人博客](https://kunminx.github.io/)

Juejin：[KunMinX 在掘金](https://juejin.im/user/58ab0de9ac502e006975d757/posts)

[《重学安卓》 专栏](https://xiaozhuanlan.com/kunminx?rel=kunminx)

[![重学安卓小专栏](https://i.loli.net/2019/06/17/5d067596c2dbf49609.png)](https://xiaozhuanlan.com/kunminx?rel=kunminx)

# 特别推荐

![shejizhilu.png](https://i.loli.net/2019/09/16/czf5obHZILSVmDn.png)

### [致 独立开发者] 推荐一个 科普 设计知识 的专栏

原本只是提个需求，没有抱太大期望，没想到很快就在评论区收到作者的答复，并在假期内特别准备了这篇文章，在此非常感谢作者对设计的热爱和付出！

本人从接触到从事 Android 开发已有 4 年之久，在经历过近 3 年的架构实践、琢磨 和 反复设计后，**标准化的开发模式已完全确立**。

现如今，我独立完成一款 29 个页面、34 个 API、350 余项 细节的项目，在自动化脚本的帮助下，只需令人咂舌的 N 天时间。

我承认比起 工程设计，我在 视觉设计 和 交互设计 上毫无天分。**我常常为 此处应摆放什么样的控件、控件应呈现什么样式 纠结不已**。

网上收集的飞机稿看似都可以，但我没有一个唯一正确的标准来指导、来相信、从而毫无疑问地做出正确的、自己能够笃信的 视觉设计。**因而只要谈到 产品设计，我随时处于崩溃的边缘**。

然而我没有告诉作者的是，作者在文中对 “为什么” 的阐述，让我重新燃起了学习设计的希望，因为 **原来设计也是可以通过思考掌握的，被设计的样式 绝非凭空存在，是有客观的依据和考虑在里边的**。

![](https://i.loli.net/2019/09/16/5vmsuwJjL3RnHYr.png)

![](https://i.loli.net/2019/09/16/vznLRw4UHdSitMZ.png)

最后，再次感谢作者的分享！

《设计知录》 https://xiaozhuanlan.com/ui-x?rel=kunminx

# License

```
Copyright 2018-2019 KunMinX

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

