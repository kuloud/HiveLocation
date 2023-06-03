package com.kuloud.android.location.app

import android.os.Bundle
import android.view.View
import com.kuloud.android.location.baidu.BaiduLocationBackend
import com.kuloud.android.location.common.HiveLocation

class BaiduLocationUpdateFragment : LocationUpdateFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        HiveLocation.setAgreePrivacy(true)
        HiveLocation.setBackend(
            requireContext(),
            BaiduLocationBackend(requireContext().applicationContext)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topAppBar.title = "百度地图"
    }
}