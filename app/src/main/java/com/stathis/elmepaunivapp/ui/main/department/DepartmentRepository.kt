package com.stathis.elmepaunivapp.ui.main.department

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.main.department.model.*
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.StudentResponse
import java.io.IOException

class DepartmentRepository(val app : Application) {

    val departmentList = MutableLiveData<List<LocalModel>>()
    private lateinit var departmentScreenData : List<LocalModel>

    init {
        getStudentsScreenData()
    }

    fun getStudentsScreenData() {
        try {
            val jsonString = app.assets.open("department_data.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<StudentResponse>() {}.type

            departmentScreenData  = Gson().fromJson(jsonString, listPersonType)
            Log.d(app.getString(R.string.app_name),departmentScreenData.toString())

            departmentList.value = departmentScreenData
        } catch (ioException: IOException) {
            //ioException.printStackTrace()
        }
    }

    /*
     * Carousel -> Virtual Tour, Research In Dept, Programms (Undergraduate, Postgraduate, PhD)
     * This should change automatically after 2 seconds
     *
     * Syllabus -> Redesign the 3 major streams to be more UI friendly
     *
     */

//    private fun getDepMembers(): List<DepMember> {
//        return listOf(
//            DepMember("Στέλιος Παπαδάκης", R.drawable.papadakis),
//            DepMember("Κώστας Παναγιωτάκης", R.drawable.panagiotakis),
//            DepMember("Γιώργος Μαστοράκης", R.drawable.mastorakis),
//            DepMember("Γιάννης Κοπανάκης", R.drawable.kopanakis),
//            DepMember("Γιάννης Δημοτίκαλης", R.drawable.dimotikalis),
//            DepMember("Χρήστος Λεμονάκης", R.drawable.lemonakis),
//            DepMember("Μάνος Περακάκης", R.drawable.perakakis),
//            DepMember("Μάνια Μαρκάκη", R.drawable.mania_markaki),
//            DepMember("Γιάννης Φανουργιάκης", R.drawable.fanourgiakis_giannis)
//        )
//    }

//    private fun getFieldsOfStudyList(): List<FieldOfStudy> {
//        return listOf(
//            FieldOfStudy("Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής", "", R.drawable.data),
//            FieldOfStudy("Διοίκηση Επιχειρήσεων & Οργανισμών", "", R.drawable.business),
//            FieldOfStudy("Ψηφιακό Μάρκετινγκ και Επικοινωνία", "", R.drawable.digitalmkt)
//        )
//    }

//    private fun getSocialList(): List<SocialChannel> {
//        return listOf(
//            SocialChannel(
//                "Χάρτης",
//                "https://www.google.gr/maps/place/Hellenic+Mediterσ46953,25.6549865,17z/data=!3m1!4b1!4m5!3m4!1s0x149a7fea00679c2f:0x8038b06fd113f3fb!8m2!3d35.1946909!4d25.6571752",
//                R.drawable.map
//            ),
//            SocialChannel("Youtube", "UCapUQKQVrP2p4_ijj_OxvNg", R.drawable.youtube),
//            SocialChannel(
//                "LinkedIn",
//                "https://www.linkedin.com/groups/13536369/",
//                R.drawable.linkedin
//            ),
//            SocialChannel(
//                "Research\nGate",
//                "https://www.researchgate.net/institution/Hellenic_Mediterranean_University/department/Department_of_Management_Science_and_Technology_Agios_Nikolaos",
//                R.drawable.researchgate
//            )
//        )
//    }

//    private fun getProgrammes(): List<Programme> {
//        return listOf(
//            Programme(
//                "Προπτυχιακές Σπουδές",
//                "ΔΙΑΡΚΕΙΑΣ 4 ΕΤΩΝ",
//                R.drawable.ungrad,
//                "https://mst.hmu.gr/tmima/ypopshphioi-phoithtes/"
//            ),
//            Programme(
//                "Μεταπτυχιακά Προγράμματα",
//                "ΔΙΑΡΚΕΙΑΣ 2 ΕΤΩΝ",
//                R.drawable.postgrad,
//                "https://mst.hmu.gr/metaptyxiako/metaptychiako-programma/"
//            ),
//            Programme(
//                "Εκπόνηση Διδακτορικού",
//                "ΕΛΑΧΙΣΤΗΣ ΔΙΑΡΚΕΙΑΣ 3 ΕΤΩΝ",
//                R.drawable.phd,
//                "https://mst.hmu.gr/metaptyxiako/didaktorikes-spoydes/"
//            )
//        )
//    }

//    private fun createFullList() {
//        val depMembers = getDepMembers()
//        val fieldsOfStudy = getFieldsOfStudyList()
//        val socialList = getSocialList()
//        val programmes = getProgrammes()
//
//        val list = listOf(
//            FieldOfStudyParent("Γνωστικά Αντικείμενα", fieldsOfStudy),
//            ProgrammeParent("Προγράμματα", programmes),
//            VirtualTourModel(""),
//            Research(""),
//            DepMemberParent("Μέλη Δ.Ε.Π.", depMembers),
//            SocialChannelParent("Βρείτε μας Online", socialList),
//            EmptyModel()
//        )
//
//        departmentList.value = list
//    }
}