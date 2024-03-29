package com.stathis.elmepaunivapp.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.ui.home.HomeActivity

class ProfessorWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        if (appWidgetIds != null) {
            for (appWidgetId in appWidgetIds) {
                val intent = Intent(context, HomeActivity::class.java).putExtra(context?.resources?.getString(R.string.widget_professor_intent), true)
                val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

                val views = RemoteViews(context?.packageName, R.layout.professor_widget)
                views.setOnClickPendingIntent(R.id.widget_button, pendingIntent)

                appWidgetManager?.updateAppWidget(appWidgetId, views)
            }
        }
    }
}