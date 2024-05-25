package com.stathis.support.ui.about

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Organization.TITLE
import androidx.fragment.app.activityViewModels
import com.stathis.core.MainViewModel
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.URL
import com.stathis.core.util.alignText
import com.stathis.core.util.setScreenTitle
import com.stathis.model.navigation.NavigationAction
import com.stathis.support.R
import com.stathis.support.databinding.FragmentAboutAppBinding
import com.stathis.support.util.ABOUT_MOBILE_APP_URL
import com.stathis.support.util.ELMEPA_MOBILE_APP

class AboutAppFragment : BaseFragment<FragmentAboutAppBinding>(R.layout.fragment_about_app) {

    private val activityVM by activityViewModels<MainViewModel>()

    override fun init() {
        setScreenTitle(getString(com.stathis.core.R.string.about_app_title))
    }

    override fun startOps() {
        binding.aboutAppTxtView.alignText()
        binding.aboutDataTxtView.alignText()
        binding.learnMoreTxtView.setOnClickListener {
            val args = Bundle().apply {
                putString(URL, ABOUT_MOBILE_APP_URL)
                putString(TITLE, ELMEPA_MOBILE_APP)
            }
            activityVM.navigateWithAction(NavigationAction.WEBVIEW, args)
        }
    }

    override fun stopOps() {}
}