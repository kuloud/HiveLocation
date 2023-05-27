package com.kuloud.android.location.baidu

import android.os.Bundle
import com.baidu.location.BDLocation
import com.kuloud.android.location.common.FusedLocation

/**
 * @author kuloud
 * @date 2023/5/27
 */
fun BDLocation.toFusedLocation(): FusedLocation {
    val extra = Bundle()
    extra.putParcelable("", this)
    return FusedLocation(
        gnssProvider, timeStamp, latitude, longitude, altitude.toFloat(), speed, radius, extra
    )
}