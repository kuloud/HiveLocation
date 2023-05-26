package com.kuloud.android.location.app

import android.os.Bundle
import com.kuloud.android.location.baidu.BaiduFusedLocationProviderClient
import com.kuloud.android.location.common.HiveLocation

class BaiduLocationUpdateFragment : LocationUpdateFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HiveLocation.setClient(requireContext(), BaiduFusedLocationProviderClient(requireContext()))
    }
}