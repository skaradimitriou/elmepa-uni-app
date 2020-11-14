package com.stathis.elmepaunivapp.ui.syllabus;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.activity_listeners.SyllabusActivityListener;
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester;
import com.stathis.elmepaunivapp.ui.syllabus.recyclerview.SemesterAdapter;
import com.stathis.elmepaunivapp.ui.syllabus.repos.BusinessLessonsRepo;
import com.stathis.elmepaunivapp.ui.syllabus.repos.DataLessonsRepo;
import com.stathis.elmepaunivapp.ui.syllabus.repos.MarketingLessonsRepo;

import java.util.ArrayList;

public class SyllabusViewModel extends ViewModel implements SyllabusActivityListener {

    SemesterAdapter adapter = new SemesterAdapter(this);
    ArrayList<Semester> dataList = new ArrayList<>();
    ArrayList<Semester> baList = new ArrayList<>();
    ArrayList<Semester> mktList = new ArrayList<>();
    private SyllabusActivityListener listener;

    void initListener(SyllabusActivityListener listener) {
        this.listener = listener;
    }

    void getDataList(Context context) {
        DataLessonsRepo dataRepo = new DataLessonsRepo();

        dataList.clear();

        dataList.add(new Semester("Εξάμηνο Α'", context.getString(R.string.all_lessons_mandatory), dataRepo.getSemesterA()));
        dataList.add(new Semester("Εξάμηνο Β'", context.getString(R.string.all_lessons_mandatory), dataRepo.getSemesterB()));
        dataList.add(new Semester("Εξάμηνο Γ'", context.getString(R.string.all_lessons_mandatory), dataRepo.getSemesterC()));
        dataList.add(new Semester("Εξάμηνο Δ'", context.getString(R.string.all_lessons_mandatory), dataRepo.getSemesterD()));
        dataList.add(new Semester("Εξάμηνο Ε'", context.getString(R.string.all_lessons_mandatory), dataRepo.getSemesterE()));
        dataList.add(new Semester("Εξάμηνο ΣΤ'", context.getString(R.string.some_lessons_mandatory), dataRepo.getSemesterF()));
        dataList.add(new Semester("Εξάμηνο Ζ'", context.getString(R.string.some_lessons_mandatory), dataRepo.getSemesterG()));
        dataList.add(new Semester("Εξάμηνο Η'", context.getString(R.string.some_lessons_mandatory), dataRepo.getSemesterH()));

        adapter.submitList(dataList);
    }

    void getBusinessAdministrationList(Context context) {
        BusinessLessonsRepo baRepo = new BusinessLessonsRepo();

        baList.clear();

        baList.add(new Semester("Εξάμηνο Α'", context.getString(R.string.all_lessons_mandatory), baRepo.getSemesterA()));
        baList.add(new Semester("Εξάμηνο Β'", context.getString(R.string.all_lessons_mandatory), baRepo.getSemesterB()));
        baList.add(new Semester("Εξάμηνο Γ'", context.getString(R.string.all_lessons_mandatory), baRepo.getSemesterC()));
        baList.add(new Semester("Εξάμηνο Δ'", context.getString(R.string.all_lessons_mandatory), baRepo.getSemesterD()));
        baList.add(new Semester("Εξάμηνο Ε'", context.getString(R.string.all_lessons_mandatory), baRepo.getSemesterE()));
        baList.add(new Semester("Εξάμηνο ΣΤ'", context.getString(R.string.some_lessons_mandatory), baRepo.getSemesterF()));
        baList.add(new Semester("Εξάμηνο Ζ'", context.getString(R.string.some_lessons_mandatory), baRepo.getSemesterG()));
        baList.add(new Semester("Εξάμηνο Η'", context.getString(R.string.some_lessons_mandatory), baRepo.getSemesterH()));

        adapter.submitList(baList);
    }

    void getMarketingList(Context context) {
        MarketingLessonsRepo baRepo = new MarketingLessonsRepo();

        mktList.clear();

        mktList.add(new Semester("Εξάμηνο Α'", context.getString(R.string.all_lessons_mandatory), baRepo.getSemesterA()));
        mktList.add(new Semester("Εξάμηνο Β'", context.getString(R.string.all_lessons_mandatory), baRepo.getSemesterB()));
        mktList.add(new Semester("Εξάμηνο Γ'", context.getString(R.string.all_lessons_mandatory), baRepo.getSemesterC()));
        mktList.add(new Semester("Εξάμηνο Δ'", context.getString(R.string.all_lessons_mandatory), baRepo.getSemesterD()));
        mktList.add(new Semester("Εξάμηνο Ε'", context.getString(R.string.all_lessons_mandatory), baRepo.getSemesterE()));
        mktList.add(new Semester("Εξάμηνο ΣΤ'", context.getString(R.string.some_lessons_mandatory), baRepo.getSemesterF()));
        mktList.add(new Semester("Εξάμηνο Ζ'", context.getString(R.string.some_lessons_mandatory), baRepo.getSemesterG()));
        mktList.add(new Semester("Εξάμηνο Η'", context.getString(R.string.some_lessons_mandatory), baRepo.getSemesterH()));

        adapter.submitList(mktList);
    }


    @Override
    public void showLessons(Semester data) {
        listener.showLessons(data);
    }
}
