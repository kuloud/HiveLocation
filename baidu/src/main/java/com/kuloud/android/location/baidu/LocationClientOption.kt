package com.kuloud.android.location.baidu

import com.baidu.location.LocationClientOption
import com.kuloud.android.location.common.LocationRequest

/**
 * @author kuloud
 * @date 2023/5/27
 */
fun LocationRequest.convertToLocationClientOption(): LocationClientOption {
    val option = LocationClientOption()
    option.locationMode = when (priority) {
        LocationRequest.PRIORITY_HIGH_ACCURACY -> LocationClientOption.LocationMode.Hight_Accuracy
        LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY -> LocationClientOption.LocationMode.Battery_Saving
        LocationRequest.PRIORITY_DEVICE_SENSORS -> LocationClientOption.LocationMode.Device_Sensors
        else -> LocationClientOption.LocationMode.Fuzzy_Locating
    }
    //可选，设置定位模式，默认高精度
    //LocationMode.Hight_Accuracy：高精度；
    //LocationMode. Battery_Saving：低功耗；
    //LocationMode. Device_Sensors：仅使用设备；
    //LocationMode.Fuzzy_Locating, 模糊定位模式；v9.2.8版本开始支持，可以降低API的调用频率，但同时也会降低定位精度；

    option.setCoorType("GCJ02");
    //可选，设置返回经纬度坐标类型，默认GCJ02
    //GCJ02：国测局坐标；
    //BD09ll：百度经纬度坐标；
    //BD09：百度墨卡托坐标；
    //海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标

    option.setFirstLocType(LocationClientOption.FirstLocType.SPEED_IN_FIRST_LOC)
    //可选，首次定位时可以选择定位的返回是准确性优先还是速度优先，默认为速度优先
    //可以搭配setOnceLocation(Boolean isOnceLocation)单次定位接口使用，当设置为单次定位时，setFirstLocType接口中设置的类型即为单次定位使用的类型
    //FirstLocType.SPEED_IN_FIRST_LOC:速度优先，首次定位时会降低定位准确性，提升定位速度；
    //FirstLocType.ACCUARACY_IN_FIRST_LOC:准确性优先，首次定位时会降低速度，提升定位准确性；

    option.setScanSpan(interval.toInt());
    //可选，设置发起定位请求的间隔，int类型，单位ms
    //如果设置为0，则代表单次定位，即仅定位一次，默认为0
    //如果设置非0，需设置1000ms以上才有效

    option.isOpenGnss = true;
    //可选，设置是否使用卫星定位，默认false
    //使用高精度和仅用设备两种定位模式的，参数必须设置为true

    option.isLocationNotify = true;
    //可选，设置是否当卫星定位有效时按照1S/1次频率输出卫星定位结果，默认false

    option.setIgnoreKillProcess(false);
    //可选，定位SDK内部是一个service，并放到了独立进程。
    //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

    option.SetIgnoreCacheException(false);
    //可选，设置是否收集Crash信息，默认收集，即参数为false

    option.setWifiCacheTimeOut(5 * 60 * 1000);
    //可选，V7.2版本新增能力
    //如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位

    option.setEnableSimulateGnss(false);
    //可选，设置是否需要过滤卫星定位仿真结果，默认需要，即参数为false

    option.setNeedNewVersionRgc(true);
    //可选，设置是否需要最新版本的地址信息。默认需要，即参数为true

    return option
}