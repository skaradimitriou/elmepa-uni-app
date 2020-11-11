package com.stathis.elmepaunivapp.ui.professors;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.stathis.elmepaunivapp.listeners.activity_listeners.ProfessorActivityClickListener;
import com.stathis.elmepaunivapp.listeners.ProfessorClickListener;
import com.stathis.elmepaunivapp.ui.professors.model.ProfessorModel;
import com.stathis.elmepaunivapp.ui.professors.recyclerview.ProfessorAdapter;
import com.stathis.elmepaunivapp.ui.professors.repo.ProfessorsRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProfessorsViewModel extends AndroidViewModel implements ProfessorClickListener {

    private ProfessorsRepo repo = new ProfessorsRepo();
    private ArrayList<ProfessorModel> professors = repo.getProfessors();
    ProfessorAdapter adapter = new ProfessorAdapter(this);
    private ProfessorActivityClickListener listener;


    public ProfessorsViewModel(@NonNull Application application) {
        super(application);
    }

    void setupListener(ProfessorActivityClickListener listener){
        this.listener = listener;
    }

    void showProfessors() {
        adapter.submitList(professors);
    }

    public void filter(String text) {
        ArrayList<ProfessorModel> filteredList = new ArrayList<>();
        for (ProfessorModel item : professors) {
            if (item.getFullName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.submitList(filteredList);
        adapter.notifyDataSetChanged();
    }

    private void showDialogue(final ProfessorModel professorModel) {
        listener.showDialog(professorModel);
    }


    @Override
    public void onProfessorClick(ProfessorModel professorModel) {
        showDialogue(professorModel);
    }
}
