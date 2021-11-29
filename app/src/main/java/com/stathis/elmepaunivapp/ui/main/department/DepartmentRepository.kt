package com.stathis.elmepaunivapp.ui.main.department

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.ui.main.department.model.DepartmentResponse
import java.io.IOException

class DepartmentRepository(val app : Application) {

    val departmentList = MutableLiveData<DepartmentResponse>()
    private lateinit var departmentScreenData : DepartmentResponse

    init {
        getStudentsScreenData()
    }

    fun getStudentsScreenData() {
        try {
            val jsonString = app.assets.open("department_data.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<DepartmentResponse>() {}.type

            departmentScreenData  = Gson().fromJson(jsonString, listPersonType)
            Log.d(app.getString(R.string.app_name),departmentScreenData.toString())

            departmentList.value = departmentScreenData
        } catch (ioException: IOException) {
            //FIXME: Add livedata to get error to screen
        }
    }
}