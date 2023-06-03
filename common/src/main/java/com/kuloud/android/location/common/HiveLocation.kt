package com.kuloud.android.location.common

import android.content.Context
import com.kuloud.android.location.common.data.LocationManager

/**
 * @author kuloud
 * @date 2023/5/26
 */
class HiveLocation {
    companion object {
        var bAgreePrivacy = false
        /**
         * 隐私合规设置, 默认 false，功能静默
         */
        fun setAgreePrivacy(agreed: Boolean) {
            bAgreePrivacy = agreed
            LocationClientLocator.getInstance().locationBackend?.setAgreePrivacy(agreed)
        }

        /**
         * 装配定位组件 Backend 实例
         */
        fun setBackend(context: Context, locationBackend: LocationBackend) {
            setAgreePrivacy(bAgreePrivacy)

            LocationManager.getInstance(context).stopLocationUpdates()

            LocationClientLocator.getInstance().locationBackend =
                locationBackend;
        }
    }
}