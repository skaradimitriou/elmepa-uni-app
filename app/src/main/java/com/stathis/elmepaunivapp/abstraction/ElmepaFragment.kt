package com.stathis.elmepaunivapp.abstraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class ElmepaFragment <T : ViewDataBinding>(val layoutId : Int) : Fragment() {

    lateinit var binding: T

    abstract fun init()
    abstract fun startOps()
    abstract fun stopOps()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewRoot = LayoutInflater.from(requireContext()).inflate(layoutId, container, false)
        binding = DataBindingUtil.bind(viewRoot)!!
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        startOps()
    }

    override fun onStop() {
        stopOps()
        binding.unbind()
        super.onStop()
    }
}