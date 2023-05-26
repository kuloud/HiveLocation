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

Support
-------

If you've found an error in this sample, please file an issue:
https://github.com/kuloud/HiveLocation/issues

Patches are encouraged, and may be submitted according to the instructions in CONTRIBUTING.md.
