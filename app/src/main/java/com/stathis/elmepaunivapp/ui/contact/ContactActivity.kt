package com.stathis.elmepaunivapp.ui.contact

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.activity.viewModels
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.callbacks.ContactClickListener
import com.stathis.elmepaunivapp.databinding.ActivityContactBinding
import com.stathis.elmepaunivapp.ui.contact.model.ContactItem
import com.stathis.elmepaunivapp.util.EMAIL
import com.stathis.elmepaunivapp.util.TELEPHONE
import com.stathis.elmepaunivapp.util.setupBar

class ContactActivity : ElmepaActivity<ActivityContactBinding>(R.layout.activity_contact) {

    private val viewModel: ContactViewModel by viewModels()

    override fun init() {
        supportActionBar?.setupBar(getString(R.string.contact_title))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.observe(this)
        viewModel.listen(object : ContactClickListener {
            override fun onItemTap(item: ContactItem) = when (item.title) {
                EMAIL -> sendMail()
                TELEPHONE -> callOffice()
                else -> Unit

            }
        })
    }

    override fun stopOps() {
        viewModel.release(this)
    }

    private fun sendMail() {
        val i = Intent(Intent.ACTION_SEND).setType(resources.getString(R.string.email_type))
            .putExtra(Intent.EXTRA_EMAIL, arrayOf(resources.getString(R.string.secretary_mail)))

        try {
            startActivity(Intent.createChooser(i, resources.getString(R.string.sending_email)))
        } catch (ex: ActivityNotFoundException) {
            //
        }
    }

    private fun callOffice() {
        startActivity(Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse(resources.getString(R.string.secretary_tel))
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}