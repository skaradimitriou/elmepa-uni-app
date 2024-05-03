package com.stathis.feature.ui.syllabus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.core.base.UiModel
import com.stathis.core.util.setupItemDecoration
import com.stathis.feature.databinding.HolderOrientationItemBinding
import com.stathis.model.syllabus.Orientation
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.Semester

class OrientationAdapter(
    private val callback: OrientationCallback
) : ListAdapter<UiModel, OrientationViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrientationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HolderOrientationItemBinding.inflate(inflater, parent, false)
        return OrientationViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: OrientationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class OrientationViewHolder(
    private val binding: HolderOrientationItemBinding,
    private val callback: OrientationCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is Orientation -> {
                val adapter = SemesterAdapter { selectedSemester ->
                    callback.onSemesterTap(data.type, selectedSemester)
                }

                binding.model = data
                binding.adapter = adapter

                binding.semesterRecycler.setupItemDecoration(bottom = 30)
                adapter.submitList(data.semesters)

                binding.expandCollapseBtn.setOnClickListener {
                    data.isExpanded = !data.isExpanded
                    binding.model = data
                }
            }
        }
    }
}

fun interface OrientationCallback {
    fun onSemesterTap(orientation: OrientationType, semester: Semester)
}