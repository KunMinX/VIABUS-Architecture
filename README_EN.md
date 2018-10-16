![image](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/viabuslogo.png)

![image](https://img.shields.io/badge/jcenter-0.4.0-brightgreen.svg)
![image](https://img.shields.io/badge/api-%2B15-blue.svg)
![image](https://img.shields.io/badge/license-Apache2.0-blue.svg)
![image](https://img.shields.io/badge/author-KunMinX-orange.svg)
![image](https://img.shields.io/badge/qq群-905136432-orange.svg)

### [中文文档](https://github.com/KunMinX/android-viabus-architecture/blob/master/README_CN.md) | [English](https://github.com/KunMinX/android-viabus-architecture/blob/master/README_EN.md)

# What's ViaBus?
⭐ ViaBus is a responsive architecture, driven by message bus programming, to achieve complete decoupling of UI and business.

![image](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/viabus_flow.png)

# Why ViaBus?
- In 1 minute, you can understand the structure and use of ViaBus. 💡
- It can be used at the moment, completely compatible with the original project architecture, plug and play. 🔥
- Allows incremental refactoring in one hour per day, in business or module units. 💧
- Complete decoupling, no matter writing UI or writing business, is no longer interrupted and not dragged together. 🌱
- Real time two-way communication across Activity and cross components can be completed just through the native interface. ⚡
- No compile time annotation, and components written in Viabus can be used directly in any project.🌎
- Less repetitive work, flat data interaction, and code reuse rate up to 100%. 💪
- ...

For details, see [Wiki - Comparison of various architectures](https://github.com/KunMinX/android-viabus-architecture/wiki/Comparison-of-various-architectures.) .

# How to use ViaBus
Add the following dependency to the module build.gradle
```
implementation "com.kunminx.viabus:viabus-android:0.4.0"
```
For details, see [Wiki - how to use ViaBus](https://github.com/KunMinX/android-viabus-architecture/wiki/How-to-use-ViaBus) .


# Who is using Viabus?

If you have works based on viabus refactoring, welcome to contribute or email.

Contributor email: kunminx@gmail.com

|Witness|Logo|Work address or project link|
|:--:|:--:|:--:|
|[MyateJx](https://github.com/MyateJx)|![sakernote](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/icon_sakernote.png)|[SakerNote](https://www.coolapk.com/apk/com.myatejx.sakernote)|
|[MyateJx](https://github.com/MyateJx)|![gankio](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/icon_gank.png)|[gank.io](https://github.com/MyateJx/GankIo-viabus-architecture)|


# Thanks to

RxJava

# License

```
Copyright 2018 KunMinX

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