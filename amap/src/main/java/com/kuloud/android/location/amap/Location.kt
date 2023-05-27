package com.kuloud.android.location.amap

import android.os.Bundle
import com.amap.api.location.AMapLocation
import com.kuloud.android.location.common.FusedLocation

/**
 * @author kuloud
 * @date 2023/5/27
 */
/**
 * @author kuloud
 * @date 2023/5/27
 */
fun AMapLocation.toFusedLocation(): FusedLocation {
    val extra = Bundle()
    extra.putParcelable("extra", this)
    return FusedLocation(
        provider ?: "", time, latitude, longitude, altitude.toFloat(), speed, bearing, extra
    )
}