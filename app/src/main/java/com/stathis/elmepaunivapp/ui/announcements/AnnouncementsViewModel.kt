package com.stathis.elmepaunivapp.ui.announcements

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.stathis.elmepaunivapp.callbacks.AnnouncementClickListener
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement
import com.stathis.elmepaunivapp.ui.announcements.recyclerviews.AnnouncementsAdapter

class AnnouncementsViewModel(app: Application) : AndroidViewModel(app), ElmepaClickListener {

    private val repo = AnnouncementRepository(app)
    val announcements = repo.announcementList
    val error = repo.errorParsing
    private lateinit var callback: AnnouncementClickListener
    val adapter = AnnouncementsAdapter(this)

    fun bindCallback(callback: AnnouncementClickListener) {
        this.callback = callback
    }

    fun refreshData() {
        repo.getDataFromWeb()
    }

    fun observeData(owner: LifecycleOwner) {
        announcements.observe(owner, Observer {
            it?.let { adapter.submitList(it) }
        })
    }

    fun removeObserver(owner: LifecycleOwner) {
        announcements.removeObservers(owner)
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is Announcement -> callback.onAnnouncementTap(view.tag as Announcement)
        }
    }
}
