package com.stathis.elmepaunivapp.ui.students.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.FieldsOfStudyListener;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.ui.department.DepartmentViewModel;
import com.stathis.elmepaunivapp.ui.department.recyclerviews.FieldsAdapter;
import com.stathis.elmepaunivapp.ui.syllabus.Syllabus;

public class FieldsOfStudyFragment extends Fragment {

    private RecyclerView fieldsOfStudy;
    private FieldsAdapter fieldsAdapter;
//    private ArrayList<DeptFieldsOfStudy> fieldsOfStudyList = new ArrayList<>();
    private DepartmentViewModel viewModel;

    public FieldsOfStudyFragment() {
        //required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Initializing my viewModel
        viewModel = new ViewModelProvider(this).get(DepartmentViewModel.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fields_of_study, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fieldsOfStudy = view.findViewById(R.id.fieldsOfStudy_recView);
        fieldsAdapter = new FieldsAdapter(new FieldsOfStudyListener() {
            @Override
            public void onFieldOfStudyClick(DeptFieldsOfStudy fieldsOfStudy) {
                switch (fieldsOfStudy.getName()) {
                    case "Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής":
                        Intent goToDataSyllabus = new Intent(getActivity(), Syllabus.class);
                        goToDataSyllabus.putExtra("DIRECTION", fieldsOfStudy.getName());
                        startActivity(goToDataSyllabus);
                        break;
                    case "Διοίκηση Επιχειρήσεων & Οργανισμών":
                        Intent goToBASyllabus = new Intent(getActivity(), Syllabus.class);
                        goToBASyllabus.putExtra("DIRECTION", fieldsOfStudy.getName());
                        startActivity(goToBASyllabus);
                        break;
                    case "Ψηφιακό Μάρκετινγκ και Επικοινωνία":
                        Intent goToMKTSyllabus = new Intent(getActivity(), Syllabus.class);
                        goToMKTSyllabus.putExtra("DIRECTION", fieldsOfStudy.getName());
                        startActivity(goToMKTSyllabus);
                        break;
                }
            }
        });
        fieldsOfStudy.setAdapter(fieldsAdapter);
        fieldsAdapter.submitList(viewModel.getFieldsOfStudyList());
    }
}
