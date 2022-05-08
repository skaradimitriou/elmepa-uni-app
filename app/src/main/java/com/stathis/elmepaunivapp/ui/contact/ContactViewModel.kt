package com.stathis.elmepaunivapp.ui.contact

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.ContactClickListener
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.ui.contact.adapter.ContactAdapter
import com.stathis.elmepaunivapp.ui.contact.model.ContactItem
import com.stathis.elmepaunivapp.util.readLocalJsonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(val app : Application) : ElmepaViewModel(app), ElmepaClickListener {

    val data = MutableLiveData<List<ContactItem>>()
    val adapter = ContactAdapter(this)
    private lateinit var callback : ContactClickListener

    init {
        getData()
    }

    fun getData(){
        viewModelScope.launch(Dispatchers.IO){
            app.readLocalJsonList<ContactItem>("contact_info.json",data = {
                data.postValue(it)
            })
        }
    }

    fun listen(callback : ContactClickListener){
        this.callback = callback
    }

    fun observe(owner: LifecycleOwner){
        data.observe(owner){
            adapter.submitList(it)
        }
    }

    fun release(owner: LifecycleOwner){
        data.removeObservers(owner)
    }

    override fun onItemClick(view: View) {
        when(view.tag){
            is ContactItem -> callback.onItemTap(view.tag as ContactItem)
        }
    }
}