Location Updates in the Background (Kotlin)
===========================================
Demonstrates retrieving location updates ( Baidu / AMap / Google ) in the background.

Introduction
============
This sdk allows a user to receive location updates in the background via a `PendingIntent`.

In addition to the FINE location permission (`android.permission.ACCESS_FINE_LOCATION`), if you do
have an approved use case for receiving location updates in the background, it will require an
additional permission (`android.permission.ACCESS_BACKGROUND_LOCATION`).

To run this sample, **location must be enabled**.

**IMPORTANT NOTE**: You should generally prefer 'while-in-use' for location updates, i.e., receiving
location updates while the app is in use and create a foreground service (tied to a Notification)
when the user navigates away from the app. To learn how to do that instead, review the
[Receive location updates in Android 10 with Kotlin](https://codelabs.developers.google.com/codelabs/while-in-use-location/index.html?index=..%2F..index#0)
codelab.

Features
--------------
- Support demonstrates
  - [ ] Baidu
  - [ ] AMap
  - [ ] Google

Prerequisites
--------------

- Android API Level > v21


Getting Started
---------------

### Baidu

Add the dependency below into your **module**'s `build.gradle` file:

```gradle
dependencies {
    implementation "com.kuloud.android:location-common:latest"
    implementation "com.kuloud.android:location-baidu:latest"
}
```

Add permissions into your **application**'s `Manifest` file:

```xml
<!-- 这个权限用于进行网络定位-->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<!-- 这个权限用于访问系统接口提供的卫星定位信息-->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<!-- 用于访问wifi网络信息，wifi信息会用于进行网络定位-->
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<!-- 获取运营商信息，用于支持提供运营商信息相关的接口-->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<!-- 这个权限用于获取wifi的获取权限，wifi信息会用来进行网络定位-->
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
<!-- 写入扩展存储，向扩展卡写入数据，用于写入离线定位数据-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<!-- 访问网络，网络定位需要上网-->
<uses-permission android:name="android.permission.INTERNET" />

<uses-permission android:name="android.permission.ACCESS_BACKGROUND_LOCATION" />
```

### AMap

Add the dependency below into your **module**'s `build.gradle` file:

```gradle
dependencies {
    implementation "com.kuloud.android:location-common:latest"
    implementation "com.kuloud.android:location-amap:latest"
}
```

Add permissions into your **application**'s `Manifest` file:

```xml
<!--允许访问网络，必选权限-->
<uses-permission android:name="android.permission.INTERNET" />
<!--允许获取精确位置，精准定位必选-->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<!--允许获取粗略位置，粗略定位必选-->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<!--允许获取设备和运营商信息，用于问题排查和网络定位（无gps情况下的定位），若需网络定位功能则必选-->
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<!--允许获取网络状态，用于网络定位（无gps情况下的定位），若需网络定位功能则必选-->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<!--允许获取wifi网络信息，用于网络定位（无gps情况下的定位），若需网络定位功能则必选-->
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<!--允许获取wifi状态改变，用于网络定位（无gps情况下的定位），若需网络定位功能则必选-->
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
<!--后台获取位置信息，若需后台定位则必选-->
<uses-permission android:name="android.permission.ACCESS_BACKGROUND_LOCATION" />
<!--用于申请调用A-GPS模块,卫星定位加速-->
<uses-permission android:name="android.permission.ACCESS_LOCATION_EXTRA_COMMANDS" />
<!--允许写设备缓存，用于问题排查-->
<uses-permission android:name="android.permission.WRITE_SETTINGS" />
<!--允许写入扩展存储，用于写入缓存定位数据-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<!--允许读设备等信息，用于问题排查-->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" /> 
```

### Google


Support
-------

If you've found an error in this sample, please file an issue:
https://github.com/kuloud/HiveLocation/issues

Patches are encouraged, and may be submitted according to the instructions in CONTRIBUTING.md.

# License
```xml
Copyright 2023 kuloud (kuloud@outlook.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.