package com.kuloud.android.location.common

import android.content.Context
import com.kuloud.android.location.common.data.LocationManager

/**
 * @author kuloud
 * @date 2023/5/26
 */
class HiveLocation {
    companion object {
        fun setClient(context: Context, fusedLocationProviderClient: FusedLocationProviderClient) {
            LocationManager.getInstance(context).stopLocationUpdates()

            LocationClientLocator.getInstance().fusedLocationProviderClient =
                fusedLocationProviderClient;
        }
    }
}