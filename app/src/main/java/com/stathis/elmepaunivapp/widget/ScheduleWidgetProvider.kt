package com.stathis.elmepaunivapp.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import com.stathis.elmepaunivapp.util.SCHEDULE_URL
import com.stathis.elmepaunivapp.util.TITLE
import com.stathis.elmepaunivapp.util.URL

class ScheduleWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        if (appWidgetIds != null) {
            for (appWidgetId in appWidgetIds) {
                val intent = Intent(context, WebviewActivity::class.java).apply {
                    putExtra(URL, SCHEDULE_URL)
                    putExtra(TITLE, context?.getString(R.string.schedule_txt))
                }

                val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

                val views = RemoteViews(context?.packageName, R.layout.schedule_widget)
                views.setOnClickPendingIntent(R.id.schedule_button, pendingIntent)

                appWidgetManager?.updateAppWidget(appWidgetId, views)
            }
        }
    }
}