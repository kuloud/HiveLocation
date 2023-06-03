package com.kuloud.android.location.app

import android.os.Bundle
import android.view.View
import com.kuloud.android.location.common.HiveLocation
import com.kuloud.android.location.google.GoogleLocationBackend

class GoogleLocationUpdateFragment : LocationUpdateFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HiveLocation.setBackend(requireContext(), GoogleLocationBackend(requireContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topAppBar.title = "谷歌地图"
    }
}