package com.kuloud.android.location.common.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

/**
 * @author kuloud
 * @date 2023/5/24
 */
fun Context.hasPermission(permission: String): Boolean {

    // Background permissions didn't exit prior to Q, so it's approved by default.
    if (permission == Manifest.permission.ACCESS_BACKGROUND_LOCATION &&
        android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q) {
        return true
    }

    return ActivityCompat.checkSelfPermission(this, permission) ==
            PackageManager.PERMISSION_GRANTED
}

/**
 * Requests permission and if the user denied a previous request, but didn't check
 * "Don't ask again", we provide additional rationale.
 *
 * Note: The Snackbar should have an action to request the permission.
 */
fun Fragment.requestPermissionWithRationale(
    permission: String,
    requestCode: Int,
    listener: OnPermissionRationaleListener
) {
    val provideRationale = shouldShowRequestPermissionRationale(permission)

    if (provideRationale) {
        listener.onPermissionRationale()
    } else {
        requestPermissions(arrayOf(permission), requestCode)
    }
}
interface OnPermissionRationaleListener {
    fun onPermissionRationale()
}
