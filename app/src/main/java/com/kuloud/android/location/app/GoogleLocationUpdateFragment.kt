package com.kuloud.android.location.app

import android.os.Bundle
import android.view.View
import com.kuloud.android.location.baidu.BaiduFusedLocationClient
import com.kuloud.android.location.common.HiveLocation

class GoogleLocationUpdateFragment : LocationUpdateFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HiveLocation.setClient(requireContext(), BaiduFusedLocationClient(requireContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topAppBar.title = "谷歌地图"
    }
}