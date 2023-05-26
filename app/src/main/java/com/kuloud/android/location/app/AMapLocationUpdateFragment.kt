package com.kuloud.android.location.app

import android.os.Bundle
import com.kuloud.android.location.baidu.BaiduFusedLocationClient
import com.kuloud.android.location.common.HiveLocation

class AMapLocationUpdateFragment : LocationUpdateFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HiveLocation.setClient(requireContext(), BaiduFusedLocationClient(requireContext()))
    }
}