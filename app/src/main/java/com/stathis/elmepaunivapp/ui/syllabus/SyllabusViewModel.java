package com.stathis.elmepaunivapp.ui.syllabus;

import androidx.lifecycle.ViewModel;

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

    void initListener(SyllabusActivityListener listener){
        this.listener = listener;
    }

    void getDataList(){
        DataLessonsRepo dataRepo = new DataLessonsRepo();

        dataList.clear();

        dataList.add(new Semester("Εξάμηνο Α'", dataRepo.getSemesterA()));
        dataList.add(new Semester("Εξάμηνο Β'", dataRepo.getSemesterB()));
        dataList.add(new Semester("Εξάμηνο Γ'", dataRepo.getSemesterC()));
        dataList.add(new Semester("Εξάμηνο Δ'", dataRepo.getSemesterD()));
        dataList.add(new Semester("Εξάμηνο Ε'", dataRepo.getSemesterE()));
        dataList.add(new Semester("Εξάμηνο ΣΤ'", dataRepo.getSemesterF()));
        dataList.add(new Semester("Εξάμηνο Ζ'", dataRepo.getSemesterG()));
        dataList.add(new Semester("Εξάμηνο Η'", dataRepo.getSemesterH()));

        adapter.submitList(dataList);
    }

    void getBusinessAdministrationList(){
        BusinessLessonsRepo baRepo = new BusinessLessonsRepo();

        baList.clear();

        baList.add(new Semester("Εξάμηνο Α'", baRepo.getSemesterA()));
        baList.add(new Semester("Εξάμηνο Β'", baRepo.getSemesterB()));
        baList.add(new Semester("Εξάμηνο Γ'", baRepo.getSemesterC()));
        baList.add(new Semester("Εξάμηνο Δ'", baRepo.getSemesterD()));
        baList.add(new Semester("Εξάμηνο Ε'", baRepo.getSemesterE()));
        baList.add(new Semester("Εξάμηνο ΣΤ'", baRepo.getSemesterF()));
        baList.add(new Semester("Εξάμηνο Ζ'", baRepo.getSemesterG()));
        baList.add(new Semester("Εξάμηνο Η'", baRepo.getSemesterH()));

        adapter.submitList(baList);
    }

    void getMarketingList(){
        MarketingLessonsRepo baRepo = new MarketingLessonsRepo();

        mktList.clear();

        mktList.add(new Semester("Εξάμηνο Α'", baRepo.getSemesterA()));
        mktList.add(new Semester("Εξάμηνο Β'", baRepo.getSemesterB()));
        mktList.add(new Semester("Εξάμηνο Γ'", baRepo.getSemesterC()));
        mktList.add(new Semester("Εξάμηνο Δ'", baRepo.getSemesterD()));
        mktList.add(new Semester("Εξάμηνο Ε'", baRepo.getSemesterE()));
        mktList.add(new Semester("Εξάμηνο ΣΤ'", baRepo.getSemesterF()));
        mktList.add(new Semester("Εξάμηνο Ζ'", baRepo.getSemesterG()));
        mktList.add(new Semester("Εξάμηνο Η'", baRepo.getSemesterH()));

        adapter.submitList(mktList);
    }



    @Override
    public void showLessons(Semester data) {
        listener.showLessons(data);
    }
}
