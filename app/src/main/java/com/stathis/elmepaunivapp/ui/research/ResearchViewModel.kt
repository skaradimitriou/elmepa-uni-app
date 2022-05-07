package com.stathis.elmepaunivapp.ui.research

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.callbacks.ResearchClickListener
import com.stathis.elmepaunivapp.ui.research.model.ResearchItem
import com.stathis.elmepaunivapp.ui.research.model.ResearchResponse
import com.stathis.elmepaunivapp.ui.research.recycler.ResearchAdapter
import com.stathis.elmepaunivapp.util.readLocalJsonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResearchViewModel(val app: Application) : ElmepaViewModel(app), ElmepaClickListener {

    val adapter = ResearchAdapter(this)
    private lateinit var callback: ResearchClickListener
    val data = MutableLiveData<List<ResearchResponse>>()

    init {
        getResearchData()
    }

    fun getResearchData() {
        viewModelScope.launch(Dispatchers.IO){
            app.readLocalJsonList<ResearchResponse>("research.json", data = {
                data.postValue(it)
            })
        }
    }

    fun observe(owner: LifecycleOwner, callback: ResearchClickListener) {
        this.callback = callback

        data.observe(owner) {
            adapter.submitList(it)
        }
    }

    fun release(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is ResearchItem -> callback.onItemTap(view.tag as ResearchItem)
        }
    }
}
