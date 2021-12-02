package com.stathis.elmepaunivapp.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.ui.main.MainActivity
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity

class ScheduleWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        if (appWidgetIds != null) {
            for (appWidgetId in appWidgetIds) {
                val intent = Intent(context, WebviewActivity::class.java)
                    .putExtra(context?.resources?.getString(R.string.url_tag), context?.resources?.getString(R.string.learn_more_url))
                val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

                val views = RemoteViews(context?.packageName, R.layout.schedule_widget)
                views.setOnClickPendingIntent(R.id.schedule_button, pendingIntent)

                appWidgetManager?.updateAppWidget(appWidgetId, views)
            }
        }
    }
}