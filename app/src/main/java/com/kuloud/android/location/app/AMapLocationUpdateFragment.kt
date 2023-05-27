package com.kuloud.android.location.app

import android.os.Bundle
import com.amap.api.location.AMapLocationClient
import com.kuloud.android.location.amap.AMapFusedLocationClient
import com.kuloud.android.location.common.HiveLocation

class AMapLocationUpdateFragment : LocationUpdateFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AMapLocationClient.updatePrivacyShow(context, true, false)
        AMapLocationClient.updatePrivacyAgree(context, true)
        HiveLocation.setClient(requireContext(), AMapFusedLocationClient(requireContext()))
    }
}