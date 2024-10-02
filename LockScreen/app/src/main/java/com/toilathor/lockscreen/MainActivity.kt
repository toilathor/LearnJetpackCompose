package com.toilathor.lockscreen

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
            }
        }

        val deviceManger =
            getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager

        val compName = ComponentName(this, AdminReceiver::class.java)
        val active: Boolean = deviceManger.isAdminActive(compName)

        if (!active) {
            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName)
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "You should enable the app!")
            startActivity(intent)
        } else {
            lockNow() // Lock the device when the admin is active
            finishAffinity()
        }
    }

    private fun lockNow() {
        val deviceManger =
            getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        deviceManger.lockNow()
    }
}