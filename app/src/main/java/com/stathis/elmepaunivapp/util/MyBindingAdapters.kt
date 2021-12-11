package com.stathis.elmepaunivapp.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.stathis.elmepaunivapp.R

class MyBindingAdapters {

    companion object {
        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun ImageView.loadImg(url: String) {
            Picasso.get().load(url).into(this)
        }

        @BindingAdapter("setImgResource")
        @JvmStatic
        fun setImage(img : ImageView,gender: String){
            when(gender){
                img.context.resources.getString(R.string.male) -> img.setImageResource(R.drawable.male_professor)
                img.context.resources.getString(R.string.female) -> img.setImageResource(R.drawable.female_professor)
            }
        }

        @BindingAdapter("setImageSrc")
        @JvmStatic
        fun setImageSrc(img : ImageView,drawable: Int){
            img.setImageResource(drawable)
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