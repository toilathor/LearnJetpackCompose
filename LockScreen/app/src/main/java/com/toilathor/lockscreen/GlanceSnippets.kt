package com.toilathor.lockscreen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.components.CircleIconButton
import androidx.glance.appwidget.provideContent
import com.google.androidgamesdk.gametextinput.Settings


class MyAppWidgetReceiver : GlanceAppWidgetReceiver() {
    // Let MyAppWidgetReceiver know which GlanceAppWidget to use
    override val glanceAppWidget: GlanceAppWidget = MyAppWidget()
}

class MyAppWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // Load data needed to render the AppWidget.
        // Use `withContext` to switch to another thread for long running
        // operations.

        provideContent {
            // create your AppWidget here
            MyContent()
        }
    }

    @Composable
    private fun MyContent() {
        CircleIconButton(
            ImageProvider(R.drawable.locks_icon),
            "Lock Screen",
            onClick = actionStartActivity<MainActivity>()
        )
    }
}