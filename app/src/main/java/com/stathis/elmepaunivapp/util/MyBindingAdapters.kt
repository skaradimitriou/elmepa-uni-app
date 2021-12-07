package com.stathis.elmepaunivapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.stathis.elmepaunivapp.R

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
    }
}