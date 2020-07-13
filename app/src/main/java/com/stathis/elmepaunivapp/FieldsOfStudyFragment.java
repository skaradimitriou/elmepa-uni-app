package com.stathis.elmepaunivapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.models.ProfessorModel;
import com.stathis.elmepaunivapp.models.Programmes;
import com.stathis.elmepaunivapp.models.SocialChannels;
import com.stathis.elmepaunivapp.models.UsefulLinks;
import com.stathis.elmepaunivapp.recyclerview.FieldsAdapter;
import com.stathis.elmepaunivapp.recyclerview.ItemClickListener;

import java.util.ArrayList;

public class FieldsOfStudyFragment extends Fragment {

    private RecyclerView fieldsOfStudy;
    private FieldsAdapter fieldsAdapter;
    private ArrayList<DeptFieldsOfStudy> fieldsOfStudyList = new ArrayList<>();

    public FieldsOfStudyFragment() {
        //required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fields_of_study, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createTheList();

        fieldsOfStudy = view.findViewById(R.id.fieldsOfStudy_recView);
        fieldsAdapter = new FieldsAdapter(fieldsOfStudyList, new ItemClickListener() {
            @Override
            public void onItemClick(DeptFieldsOfStudy item) {
                switch (item.getName()) {
                    case "Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής":
                        Intent goToDataSyllabus = new Intent(getActivity(), Syllabus.class);
                        goToDataSyllabus.putExtra("DIRECTION", item.getName());
                        startActivity(goToDataSyllabus);
                        break;
                    case "Διοίκηση Επιχειρήσεων & Οργανισμών":
                        Intent goToBASyllabus = new Intent(getActivity(), Syllabus.class);
                        goToBASyllabus.putExtra("DIRECTION", item.getName());
                        startActivity(goToBASyllabus);
                        break;
                    case "Ψηφιακό Μάρκετινγκ και Επικοινωνία":
                        Intent goToMKTSyllabus = new Intent(getActivity(), Syllabus.class);
                        goToMKTSyllabus.putExtra("DIRECTION", item.getName());
                        startActivity(goToMKTSyllabus);
                        break;
                }
            }

            @Override
            public void onProgrammesClick(Programmes programmes) {

            }

            @Override
            public void onProfessorClick(ProfessorModel professorModel) {

            }

            @Override
            public void onUsefulLinksClick(UsefulLinks usefulLinks) {

            }

            @Override
            public void onSocialItemClick(SocialChannels socialChannels) {

            }

            @Override
            public void onClick(View v) {

            }
        });
        fieldsOfStudy.setAdapter(fieldsAdapter);
    }

    private void createTheList() {
        fieldsOfStudyList.add(new DeptFieldsOfStudy("Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής", R.drawable.data));
        fieldsOfStudyList.add(new DeptFieldsOfStudy("Διοίκηση Επιχειρήσεων & Οργανισμών", R.drawable.business));
        fieldsOfStudyList.add(new DeptFieldsOfStudy("Ψηφιακό Μάρκετινγκ και Επικοινωνία", R.drawable.digitalmkt));
    }
}
