package com.kuloud.android.location.app

import android.os.Bundle
import android.view.View
import com.kuloud.android.location.amap.AMapLocationBackend
import com.kuloud.android.location.common.HiveLocation

class AMapLocationUpdateFragment : LocationUpdateFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HiveLocation.setAgreePrivacy(true)
        HiveLocation.setBackend(requireContext(), AMapLocationBackend(requireContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topAppBar.title = "高德地图"
    }
}