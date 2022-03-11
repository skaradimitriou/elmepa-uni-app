package com.stathis.elmepaunivapp.delete

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class ElmepaFragment(layoutId : Int) : Fragment(layoutId) {

    abstract fun init(view : View)
    abstract fun startOps()
    abstract fun stopOps()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    override fun onResume() {
        super.onResume()
        startOps()
    }

    override fun onStop() {
        stopOps()
        super.onStop()
    }
}