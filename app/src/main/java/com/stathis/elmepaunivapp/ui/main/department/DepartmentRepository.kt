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

    private lateinit var departmentScreenData : DepartmentResponse

    fun getStudentsScreenData(data : MutableLiveData<DepartmentResponse>,error : MutableLiveData<Boolean>) {
        try {
            val jsonString = app.assets.open("department_data.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<DepartmentResponse>() {}.type

            departmentScreenData  = Gson().fromJson(jsonString, listPersonType)
            Log.d(app.getString(R.string.app_name),departmentScreenData.toString())

            data.value = departmentScreenData
            error.value = false
        } catch (ioException: IOException) {
            error.value = true
        }
    }
}