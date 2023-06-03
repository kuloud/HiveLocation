
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/kuloud/HiveLocation/blob/master/LICENSE)
[ ![API](https://img.shields.io/badge/API-21+-brightgreen.svg)](https://img.shields.io/badge/API-21+-brightgreen.svg)
![jekyll-gh-pages](https://github.com/kuloud/HiveLocation/actions/workflows/jekyll-gh-pages.yml/badge.svg)

HiveLocation 后台持续定位 (Kotlin)
===========================================
包装高德、百度、谷歌的定位模块，按照 Google 官方定位样例工程 [android/location-samples](https://github.com/android/location-samples) ,将定位回调数据持久化保存。
可以方便的利用 ViewModel 绑定 LiveData，也可以直接数据查询。


功能特性
--------------

- 支持定位SDK

    - [x] Baidu(`v9.4.0`)
    - [x] AMap(`6.3.0`)
    - [ ] Google
- 定位数据持久化
- 定位点数据 LiveData 监听

前置要求
--------------

- Android API Level > v21

开始使用
---------------

### Baidu

在 `build.gradle` 中添加组件依赖:

```gradle
dependencies {
    implementation "io.github.kuloud:location-common:0.0.1"
    implementation "io.github.kuloud:location-baidu:0.0.1"
}
```

在应用的 `Manifest` 文件中神明应用权限:

```xml
<!-- 这个权限用于进行网络定位-->
<uses-permission
    android:name="android.permission.ACCESS_COARSE_LOCATION" /><!-- 这个权限用于访问系统接口提供的卫星定位信息-->
<uses-permission
android:name="android.permission.ACCESS_FINE_LOCATION" /><!-- 用于访问wifi网络信息，wifi信息会用于进行网络定位-->
<uses-permission
android:name="android.permission.ACCESS_WIFI_STATE" /><!-- 获取运营商信息，用于支持提供运营商信息相关的接口-->
<uses-permission
android:name="android.permission.ACCESS_NETWORK_STATE" /><!-- 这个权限用于获取wifi的获取权限，wifi信息会用来进行网络定位-->
<uses-permission
android:name="android.permission.CHANGE_WIFI_STATE" /><!-- 写入扩展存储，向扩展卡写入数据，用于写入离线定位数据-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /><!-- 访问网络，网络定位需要上网-->
<uses-permission android:name="android.permission.INTERNET" />

<uses-permission android:name="android.permission.ACCESS_BACKGROUND_LOCATION" />
```

在 `Manifest` 或者 `local.properties` 中配置 SDK 的 AppKey, 在 **application** 标签中添加 SDK Service 声明:

```xml
<!-- Baidu BEGIN -->
<meta-data
    android:name="com.baidu.lbsapi.API_KEY"
    android:value="${BD_MAP_AK}" />

<service
    android:name="com.baidu.location.f"
    android:enabled="true"
    android:process=":remote" />
<!-- Baidu END -->
```

给 HiveLocation 设置 LocationClient 实例:

```kotlin
HiveLocation.setAgreePrivacy(true)
HiveLocation.setBackend(
  requireContext(),
  BaiduLocationBackend(requireContext().applicationContext)
)
```



### AMap

在 `build.gradle` 中添加组件依赖:

```gradle
dependencies {
    implementation "io.github.kuloud:location-common:0.0.1"
    implementation "io.github.kuloud:location-amap:0.0.1"
}
```

在应用的 `Manifest` 文件中神明应用权限:

```xml
<!--允许访问网络，必选权限-->
<uses-permission android:name="android.permission.INTERNET" /><!--允许获取精确位置，精准定位必选-->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" /><!--允许获取粗略位置，粗略定位必选-->
<uses-permission
android:name="android.permission.ACCESS_COARSE_LOCATION" /><!--允许获取设备和运营商信息，用于问题排查和网络定位（无gps情况下的定位），若需网络定位功能则必选-->
<uses-permission
android:name="android.permission.READ_PHONE_STATE" /><!--允许获取网络状态，用于网络定位（无gps情况下的定位），若需网络定位功能则必选-->
<uses-permission
android:name="android.permission.ACCESS_NETWORK_STATE" /><!--允许获取wifi网络信息，用于网络定位（无gps情况下的定位），若需网络定位功能则必选-->
<uses-permission
android:name="android.permission.ACCESS_WIFI_STATE" /><!--允许获取wifi状态改变，用于网络定位（无gps情况下的定位），若需网络定位功能则必选-->
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" /><!--后台获取位置信息，若需后台定位则必选-->
<uses-permission
android:name="android.permission.ACCESS_BACKGROUND_LOCATION" /><!--用于申请调用A-GPS模块,卫星定位加速-->
<uses-permission
android:name="android.permission.ACCESS_LOCATION_EXTRA_COMMANDS" /><!--允许写设备缓存，用于问题排查-->
<uses-permission android:name="android.permission.WRITE_SETTINGS" /><!--允许写入扩展存储，用于写入缓存定位数据-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /><!--允许读设备等信息，用于问题排查-->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" /> 
```

在 `Manifest` 或者 `local.properties` 中配置 SDK 的 AppKey, 在 **application** 标签中添加 SDK Service 声明:

```xml
<!-- AMap BEGIN -->
<meta-data
    android:name="com.amap.api.v2.apikey"
    android:value="${AMAP_AK}" />

<service android:name="com.amap.api.location.APSService" />
<!-- AMap END -->
```

给 HiveLocation 设置 LocationClient 实例:

```kotlin
HiveLocation.setAgreePrivacy(true)
HiveLocation.setBackend(requireContext(), AMapLocationBackend(requireContext()))
```

### Google

TODO

## 使用方法

Use `LocationUpdateViewModel` to handle with location updates in foreground:

```kotlin
class LocationUpdateFragment : Fragment() {

    private val locationUpdateViewModel by lazy {
        ViewModelProvider(this)[LocationUpdateViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationUpdateViewModel.receivingLocationUpdates.observe(
            viewLifecycleOwner
        ) { receivingLocation ->
            // handle with receivingLocation
        }

        locationUpdateViewModel.locationListLiveData.observe(
            viewLifecycleOwner
        ) { locations ->
            // handle with history locations
        }
        
        // trigger location start / end
        locationUpdateViewModel.startLocationUpdates()
        locationUpdateViewModel.stopLocationUpdates()
    }

}
```

帮助支持
-------

如果您发现这个示例中有错误，请提交 issue 到以下链接：https://github.com/kuloud/HiveLocation/issues

## 版权说明

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