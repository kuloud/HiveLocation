package com.kuloud.android.location.amap

import com.amap.api.location.AMapLocationClientOption
import com.kuloud.android.location.common.LocationRequest

/**
 * @author kuloud
 * @date 2023/5/27
 */
fun LocationRequest.convertToLocationClientOption(): AMapLocationClientOption {
    val option = AMapLocationClientOption()
    option.locationMode = when (priority) {
        LocationRequest.PRIORITY_HIGH_ACCURACY -> AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY -> AMapLocationClientOption.AMapLocationMode.Battery_Saving
        LocationRequest.PRIORITY_DEVICE_SENSORS -> AMapLocationClientOption.AMapLocationMode.Device_Sensors
        else -> AMapLocationClientOption.AMapLocationMode.Battery_Saving
    }
    option.interval = interval
    return option
}