package com.stathis.elmepaunivapp.ui.announcements.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.stathis.elmepaunivapp.database.AnnouncementsDatabaseKt
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class AnnouncementRepository(app: Application) {

    val context = app
    val announcementList = MutableLiveData<List<Announcement>>()
    val errorParsing = MutableLiveData<Boolean>()
    val database = AnnouncementsDatabaseKt.getDatabase(context).announcementDao()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val data = database.getAll()
            when (data.isEmpty()) {
                true -> getAnnouncements()
                false -> getAnnouncementsFromDb()
            }
        }
    }

    private suspend fun getAnnouncementsFromDb() {
        val announcements = database.getAll()
        announcementList.postValue(announcements)
    }

    suspend fun getAnnouncements() {
        val announcements = arrayListOf<Announcement>()

        try {
            val url = "https://mst.hmu.gr/news_gr/"
            val doc = Jsoup.connect(url).get()
            val data = doc.select("article")
            for (i in 0..12) {
                val imgUrl = data.select("a.entry-featured-image-url")
                    .select("img")
                    .eq(i)
                    .attr("src")
                val title = data.select("h2.entry-title")
                    .select("h2")
                    .eq(i)
                    .text()
                val detailUrl = data.select("h2.entry-title")
                    .select("a")
                    .eq(i)
                    .attr("href")
                announcements.add(Announcement(title, detailUrl, imgUrl))
                Log.d("items", "img: $imgUrl . title: $title")
            }

            announcementList.postValue(announcements)

            database.deleteAll()
            database.insertAll(announcements)

            errorParsing.postValue(false)
        } catch (e: Exception) {
            Log.d("", e.toString())
            getAnnouncementsFromDb()
        }
    }
}