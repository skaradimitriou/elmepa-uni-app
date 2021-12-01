package com.stathis.elmepaunivapp.ui.main.students

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaFragment
import com.stathis.elmepaunivapp.callbacks.StudentsScreenCallback
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.CarouselItem
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.LinkItem
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.SyllabusItem
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusActivity
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import kotlinx.android.synthetic.main.fragment_students.*

class StudentsFragment : ElmepaFragment(R.layout.fragment_students) {

    private lateinit var viewModel : StudentsViewModel

    override fun init(view : View) {
        viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
    }

    override fun startOps() {
        students_activity_recycler.adapter = viewModel.adapter

        viewModel.observe(this)

        viewModel.bindCallbacks(object : StudentsScreenCallback {
            override fun openCarouselItem(item: CarouselItem) = openUrl(item.url)
            override fun openLink(item: LinkItem) = openUrl(item.url)
            override fun openSyllabus(item: SyllabusItem) = openSyllabusScreen(item.tabNo)
        })
    }

    override fun stopOps() {
        viewModel.release(this)
    }

    private fun openUrl(url : String){
        startActivity(Intent(requireContext(), WebviewActivity::class.java)
            .putExtra(getString(R.string.url_tag), url))
    }

    private fun openSyllabusScreen(position : Int){
        startActivity(Intent(requireContext(), SyllabusActivity::class.java)
            .putExtra("userTabChoice", position))
    }
}