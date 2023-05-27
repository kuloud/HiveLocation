package com.kuloud.android.location.app

import android.os.Bundle
import android.view.View
import com.amap.api.location.AMapLocationClient
import com.kuloud.android.location.amap.AMapFusedLocationClient
import com.kuloud.android.location.common.HiveLocation

class AMapLocationUpdateFragment : LocationUpdateFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AMapLocationClient.updatePrivacyShow(context, true, true)
        AMapLocationClient.updatePrivacyAgree(context, true)
        HiveLocation.setClient(requireContext(), AMapFusedLocationClient(requireContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topAppBar.title = "高德地图"
    }
}