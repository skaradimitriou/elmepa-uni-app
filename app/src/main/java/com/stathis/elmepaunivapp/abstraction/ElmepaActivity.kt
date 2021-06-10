package com.stathis.elmepaunivapp.abstraction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class ElmepaActivity(layoutId : Int) : AppCompatActivity(layoutId) {

    abstract fun init()
    abstract fun startOps()
    abstract fun stopOps()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        init()
    }

    override fun onPostResume() {
        super.onPostResume()
        startOps()
    }

    override fun onStop() {
        stopOps()
        super.onStop()
    }
}