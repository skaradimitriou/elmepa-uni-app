package com.stathis.feature.ui.about

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.alignText
import com.stathis.core.util.setScreenTitle
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentAboutAppBinding
import com.stathis.feature.navigation.NavigationAction
import com.stathis.feature.ui.MainViewModel
import com.stathis.feature.util.ABOUT_MOBILE_APP_URL
import com.stathis.feature.util.ELMEPA_MOBILE_APP
import com.stathis.feature.util.TITLE
import com.stathis.feature.util.URL

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