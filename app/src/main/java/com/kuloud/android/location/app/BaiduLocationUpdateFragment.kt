package com.kuloud.android.location.app

import android.os.Bundle
import android.view.View
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topAppBar.title = "百度地图"
    }
}