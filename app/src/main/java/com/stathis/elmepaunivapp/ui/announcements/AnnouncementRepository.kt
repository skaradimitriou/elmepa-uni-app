package com.stathis.elmepaunivapp.ui.announcements

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.database.AnnouncementsDatabaseKt
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement
import com.stathis.elmepaunivapp.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class AnnouncementRepository(val app: Application) {

    val announcementList = MutableLiveData<List<Announcement>>()
    val errorParsing = MutableLiveData<Boolean>()
    val database = AnnouncementsDatabaseKt.getDatabase(app).announcementDao()
    private val prefHelper = SharedPreferencesHelper.setHelper(app)
    private val refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    /*
    FIXME: Logic -> Try to get data from db,
                    if this fails, then get from web
                    use the timer as well
     */

    init {
        val updateTime = SharedPreferencesHelper.getUpdateTime()
        val currentTime = System.nanoTime()

        when(updateTime > 0 && currentTime - updateTime < refreshTime) {
            true -> getAnnouncementsFromDb() //Getting Data From Database
            false -> getDataFromWeb() //Getting Data From Web
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

    fun getAnnouncements() {
        val announcements = arrayListOf<Announcement>()

        try {
            val doc = Jsoup.connect(BASE_URL).get().select(DATA_TYPE)
            for (i in 0..12) {
                val imgUrl = doc.select(IMG_HTML_TAG).select(IMG_TYPE).eq(i).attr(IMG_SOURCE)
                val title = doc.select(TITLE_HTML_TAG).select(TITLE_TYPE).eq(i).text()
                val detailUrl = doc.select(URL_HTML_TAG).select(URL_TYPE).eq(i).attr(URL_ATTR)
                announcements.add(Announcement(title, detailUrl, imgUrl))
            }

            announcementList.postValue(announcements)

            database.deleteAll()
            database.insertAll(announcements)

            SharedPreferencesHelper.saveUpdateTime(System.nanoTime())
        } catch (e: Exception) {
            Log.d(app.resources.getString(R.string.app_name), e.toString())
            getAnnouncementsFromDb()
        }
    }
}