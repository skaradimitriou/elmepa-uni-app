package com.stathis.elmepaunivapp.ui.announcements

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.AnnouncementClickListener
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.database.AnnouncementsDatabaseKt
import com.stathis.elmepaunivapp.model.Announcement
import com.stathis.elmepaunivapp.ui.announcements.adapter.AnnouncementsAdapter
import com.stathis.elmepaunivapp.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class AnnouncementsViewModel(app: Application) : ElmepaViewModel(app), ElmepaClickListener {

    private lateinit var callback: AnnouncementClickListener
    val adapter = AnnouncementsAdapter(this)
    val announcements = MutableLiveData<List<Announcement>>()
    val error = MutableLiveData<Boolean>()
    val database = AnnouncementsDatabaseKt.getDatabase(app).announcementDao()
    private val prefHelper = SharedPreferencesHelper.setHelper(app)
    private val refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    /*
     *  Data fetch logic:
     *  Trying to get data from db,  if this fails, then get from web.
     *  The app decides when to fetch data from web or db from the timer implemented
     */

    init {
        startShimmer()
    }

    private fun startShimmer(){
        adapter.submitList(ShimmerHelper.list)
    }

    fun bindCallback(callback: AnnouncementClickListener) {
        this.callback = callback
    }

    fun refreshData() {
        getData()
    }

    fun observe(owner: LifecycleOwner) {
        announcements.observe(owner) {
            it?.let { adapter.submitList(it) }
        }
    }

    fun release(owner: LifecycleOwner) {
        announcements.removeObservers(owner)
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            val updateTime = SharedPreferencesHelper.getUpdateTime()
            val currentTime = System.nanoTime()

            when (updateTime > 0 && currentTime - updateTime < refreshTime) {
                true -> getAnnouncementsFromDb() //Getting Data From Database
                false -> getAnnouncements() //Getting Data From Web
            }
        }
    }

    private fun getAnnouncementsFromDb() {
        val announcementList = database.getAll()
        announcements.postValue(announcementList)
    }

    private fun getAnnouncements() {
        val announcementList = arrayListOf<Announcement>()

        try {
            val doc = Jsoup.connect(BASE_URL).get().select(DATA_TYPE)
            for (i in 0..12) {
                val imgUrl = doc.select(IMG_HTML_TAG).select(IMG_TYPE).eq(i).attr(IMG_SOURCE)
                val title = doc.select(TITLE_HTML_TAG).select(TITLE_TYPE).eq(i).text()
                val detailUrl = doc.select(URL_HTML_TAG).select(URL_TYPE).eq(i).attr(URL_ATTR)
                announcementList.add(Announcement(title, detailUrl, imgUrl))
            }

            announcements.postValue(announcementList)

            database.deleteAll()
            database.insertAll(announcementList)

            SharedPreferencesHelper.saveUpdateTime(System.nanoTime())
        } catch (e: Exception) {
            Log.d(getString(R.string.app_name), e.toString())
            getAnnouncementsFromDb()
        }
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is Announcement -> callback.onAnnouncementTap(view.tag as Announcement)
        }
    }
}
