package com.kuloud.android.location.common.data

import android.content.Context
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import com.kuloud.android.location.common.LocationClientLocator
import com.kuloud.android.location.common.data.db.LocationDatabase
import com.kuloud.android.location.common.data.db.LocationEntity
import java.util.UUID
import java.util.concurrent.ExecutorService

private const val TAG = "LocationRepository"

/**
 * @author kuloud
 * @date 2023/5/24
 */
class LocationRepository private constructor(
    private val locationDatabase: LocationDatabase,
    private val locationManager: LocationManager,
    private val executor: ExecutorService
) {

    private val locationDao = locationDatabase.locationDao()

    /**
     * Returns all recorded locations from database.
     */
    fun getLocations(): LiveData<List<LocationEntity>> = locationDao.getLocations()

    /**
     * Returns specific location in database.
     */
    fun getLocation(id: UUID): LiveData<LocationEntity> = locationDao.getLocation(id)

    /**
     * Updates location in database.
     */
    fun updateLocation(locationEntity: LocationEntity) {
        executor.execute {
            locationDao.updateLocation(locationEntity)
        }
    }

    /**
     * Adds location to the database.
     */
    fun addLocation(locationEntity: LocationEntity) {
        executor.execute {
            locationDao.addLocation(locationEntity)
        }
    }

    /**
     * Adds list of locations to the database.
     */
    fun addLocations(myLocationEntities: List<LocationEntity>) {
        executor.execute {
            locationDao.addLocations(myLocationEntities)
        }
    }

    /**
     * Status of whether the app is actively subscribed to location changes.
     */
    val receivingLocationUpdates: LiveData<Boolean> = locationManager.receivingLocationUpdates

    /**
     * Subscribes to location updates.
     */
    @MainThread
    fun startLocationUpdates() = locationManager.startLocationUpdates()

    /**
     * Un-subscribes from location updates.
     */
    @MainThread
    fun stopLocationUpdates() = locationManager.stopLocationUpdates()

    companion object {
        @Volatile private var INSTANCE: LocationRepository? = null

        fun getInstance(context: Context, executor: ExecutorService): LocationRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocationRepository(
                    LocationDatabase.getInstance(context),
                    LocationManager.getInstance(context),
                    executor)
                    .also { INSTANCE = it }
            }
        }
    }
}