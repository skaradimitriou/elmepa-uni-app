package com.stathis.elmepaunivapp.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.stathis.elmepaunivapp.MainActivity
import com.stathis.elmepaunivapp.R

class PersonnelWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.let { appWidgets ->
            appWidgets.forEach { appWidgetId ->
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra(context?.resources?.getString(R.string.open_personnel), true)
                }

                val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
                val views = RemoteViews(context?.packageName, R.layout.personnel_widget)
                views.setOnClickPendingIntent(R.id.widget_button, pendingIntent)

                appWidgetManager?.updateAppWidget(appWidgetId, views)
            }
        }
    }
}