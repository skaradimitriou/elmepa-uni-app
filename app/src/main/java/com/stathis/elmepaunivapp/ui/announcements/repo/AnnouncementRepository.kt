package com.stathis.elmepaunivapp.ui.announcements.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.stathis.elmepaunivapp.database.AnnouncementsDatabase
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement
import com.stathis.elmepaunivapp.util.SharedPreferencesHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup


class AnnouncementRepository(app: Application) {

    val context = app
    val announcementList = MutableLiveData<List<Announcement>>()
    val errorParsing = MutableLiveData<Boolean>()
    val database = AnnouncementsDatabase.getDatabase(context).announcementDao()
    private val prefHelper = SharedPreferencesHelper.setHelper(context)
    private val refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    init {
        val updateTime = SharedPreferencesHelper.getUpdateTime()
        val currentTime = System.nanoTime()

        when(updateTime > 0 && currentTime - updateTime < refreshTime) {
            true -> {
                Log.d("","Getting Data From Database")
                getAnnouncementsFromDb()
            }
            false -> {
                Log.d("","Getting Data From Web")
                getDataFromWeb()
            }
        }
    }

    fun getDataFromWeb(){
        CoroutineScope(Dispatchers.IO).launch {
            getAnnouncements()
        }
    }

    private fun getAnnouncementsFromDb() {
        CoroutineScope(Dispatchers.IO).launch {
            val announcements = database.getAll()
            announcementList.postValue(announcements)
        }
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

            SharedPreferencesHelper.saveUpdateTime(System.nanoTime())

            errorParsing.postValue(false)
        } catch (e: Exception) {
            Log.d("", e.toString())
            getAnnouncementsFromDb()
        }
    }
}