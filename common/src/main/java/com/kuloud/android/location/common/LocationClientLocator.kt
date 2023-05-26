package com.kuloud.android.location.common

import android.content.Context
import com.kuloud.android.location.common.data.LocationManager
import com.kuloud.android.location.common.data.LocationRepository
import com.kuloud.android.location.common.data.db.LocationDatabase
import java.util.concurrent.ExecutorService

/**
 * @author kuloud
 * @date 2023/5/26
 */
class LocationClientLocator {
    var fusedLocationProviderClient: FusedLocationProviderClient? = null

    companion object {
        @Volatile
        private var INSTANCE: LocationClientLocator? = null

        fun getInstance(): LocationClientLocator {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocationClientLocator()
                    .also { INSTANCE = it }
            }
        }
    }
}