package com.kuloud.android.location.app

import android.os.Bundle
import com.baidu.location.LocationClient
import com.kuloud.android.location.baidu.BaiduFusedLocationClient
import com.kuloud.android.location.common.HiveLocation

class BaiduLocationUpdateFragment : LocationUpdateFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocationClient.setAgreePrivacy(true)
        HiveLocation.setClient(
            requireContext(),
            BaiduFusedLocationClient(requireContext().applicationContext)
        )
    }
}