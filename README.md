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

![xzl](https://upload-images.jianshu.io/upload_images/57036-1483345344941837.jpg)

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

