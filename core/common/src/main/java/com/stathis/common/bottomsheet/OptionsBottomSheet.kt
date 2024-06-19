package com.stathis.common.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.stathis.common.bottomsheet.options.OptionsAdapter
import com.stathis.common.bottomsheet.options.OptionsCallback
import com.stathis.common.databinding.OptionsBottomsheetBinding
import com.stathis.common.util.getSerializableFromBundle

class OptionsBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: OptionsBottomsheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OptionsBottomsheetBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list =
            arguments?.getSerializableFromBundle<Array<BottomSheetOption>>(LIST_ARG)?.toList()
                ?: listOf()

        val listener = arguments?.getSerializableFromBundle(LISTENER_ARG) as? OptionsCallback

        val adapter = OptionsAdapter {
            listener?.onOptionTap(it)
            dismiss()
        }
        adapter.submitList(list)

        binding.adapter = adapter
    }


    data class Builder(
        var list: List<BottomSheetOption>? = null,
        var listener: OptionsCallback? = null
    ) {
        fun setOptions(list: List<BottomSheetOption>) = apply { this.list = list }

        fun setListener(listener: OptionsCallback) = apply { this.listener = listener }

        fun build() = OptionsBottomSheet().apply {
            arguments = Bundle().apply {
                putSerializable(LIST_ARG, list?.toTypedArray())
                putSerializable(LISTENER_ARG, listener)
            }
        }
    }

    companion object {
        private const val LIST_ARG = "list_arg"
        private const val LISTENER_ARG = "listener_arg"

        const val GENERIC_BS_TAG = "OptionsBottomSheet"
    }
}