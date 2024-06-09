package com.stathis.common.util

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stathis.common.R


@BindingAdapter("loadImageUrl")
fun ImageView.loadImage(imageUrl: String? = null) {
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(R.drawable.placeholder)
        .into(this)
}

@BindingAdapter("textAndVisibility")
fun TextView.setTextAndVisibility(text: String) {
    if (text.isEmpty()) {
        visibility = View.GONE
    } else {
        visibility = View.VISIBLE
        this.text = text
    }
}

@BindingAdapter("setPersonnelImage", "personnelGender")
fun ImageView.setPersonnelImage(url: String, gender: String) {
    val genderImg = when (gender) {
        resources.getString(R.string.male) -> R.drawable.male
        else -> R.drawable.female
    }

    Glide.with(context).load(url).placeholder(R.drawable.placeholder).error(genderImg).into(this)
}

@BindingAdapter("setPersonnelDescription")
fun TextView.setPersonnelDescription(description: String) {
    visibility = if (description.isEmpty()) {
        View.GONE
    } else {
        View.VISIBLE
    }

    text = description
}

@BindingAdapter("animateArrow")
fun ImageView.animateArrow(isExpanded: Boolean) {
    if (isExpanded) {
        animate().rotation(90f).start()
    } else {
        animate().rotation(0f).start()
    }
}

@BindingAdapter("setHtmlText")
fun TextView.setHtmlText(text: String) {
    this.text = text.toNonHtmlText()
}

@BindingAdapter("setRibbonColor")
fun View.setRibbonColor(isMandatory: Boolean) {
    if (isMandatory) {
        setBackgroundResource(R.color.lesson_blue)
    } else {
        setBackgroundResource(R.color.lesson_orange)
    }
}

@BindingAdapter("loadHtmlContent")
fun WebView.loadHtmlContent(htmlContent: String) {
    settings.javaScriptEnabled = true

    loadDataWithBaseURL(null, htmlContent, "text/html", "utf-8", null)

    val color =
        when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                setBackgroundColor(context.getColor(R.color.dark_mode_surface))
                "#FFFFFF"
            }

            else -> {
                setBackgroundColor(context.getColor(R.color.grey_bg))
                "#000000"
            }
        }

    webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            loadUrl("javascript:document.body.style.setProperty(\"color\", \"$color\");")
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(request?.url.toString())))
            return true
        }
    }
}