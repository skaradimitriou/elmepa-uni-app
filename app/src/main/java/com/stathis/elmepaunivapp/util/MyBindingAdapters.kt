package com.stathis.elmepaunivapp.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.util.MyBindingAdapters.Companion.setBackground

class MyBindingAdapters {

    companion object {
        @BindingAdapter("setImgResource")
        @JvmStatic
        fun setImage(img : ImageView,gender: String){
            when(gender){
                img.context.resources.getString(R.string.male) -> img.setImageResource(R.drawable.male_professor)
                img.context.resources.getString(R.string.female) -> img.setImageResource(R.drawable.female_professor)
            }
        }

        @BindingAdapter("setBackground")
        @JvmStatic
        fun setBackground(textView : TextView,type: String){
            when(type){
                textView.context.resources.getString(R.string.data) -> textView.setBackgroundColor(textView.context.resources.getColor(R.color.lesson_green))
                textView.context.resources.getString(R.string.mkt) -> textView.setBackgroundColor(textView.context.resources.getColor(R.color.lesson_blue))
                textView.context.resources.getString(R.string.ba) -> textView.setBackgroundColor(textView.context.resources.getColor(R.color.dark_orange))
            }
        }
    }
}