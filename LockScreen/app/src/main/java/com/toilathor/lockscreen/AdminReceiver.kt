package com.toilathor.lockscreen

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AdminReceiver : DeviceAdminReceiver() {
    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)

        Toast.makeText(context, "Sample Device Admin: enabled", Toast.LENGTH_SHORT).show()
    }

    override fun onDisabled(context: Context, intent: Intent) {
        super.onDisabled(context, intent)

        Toast.makeText(context, "Sample Device Admin: Disable", Toast.LENGTH_SHORT).show()
    }
}