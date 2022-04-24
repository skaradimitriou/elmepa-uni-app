package com.stathis.elmepaunivapp.ui.about

import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.databinding.ActivityAboutBinding

class AboutActivity : ElmepaActivity<ActivityAboutBinding>(R.layout.activity_about) {

    //FIXME: Fix this later on

    override fun init() {
        //val aboutText = getString(R.string.about_app_text)
//        val builder = MaterialAlertDialogBuilder(context)
//        builder.setTitle(getString(R.string.about_app_title)).also {
//            it.setMessage(aboutText)
//            it.setPositiveButton(getString(R.string.about_app_learnMore)) { dialog, which ->
//                callback.learnMore()
//            }
//            it.setNegativeButton(getString(R.string.about_app_cancel)) { dialog, which ->
//                dialog.dismiss()
//            }
//        }.show()
    }

    override fun startOps() {
        //
    }

    override fun stopOps() {
        //
    }
}