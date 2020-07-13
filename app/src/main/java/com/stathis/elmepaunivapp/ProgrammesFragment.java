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
import com.stathis.elmepaunivapp.recyclerview.ItemClickListener;
import com.stathis.elmepaunivapp.recyclerview.ProgrammesAdapter;

import java.util.ArrayList;

public class ProgrammesFragment extends Fragment {

    private RecyclerView programmes_recView;
    private ProgrammesAdapter programmesAdapter;
    private ArrayList<Programmes> programmes = new ArrayList<>();

    public ProgrammesFragment() {
        //required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_programmes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createList();
        programmes_recView = view.findViewById(R.id.programmes_recView);
        programmesAdapter = new ProgrammesAdapter(programmes, new ItemClickListener() {
            @Override
            public void onItemClick(DeptFieldsOfStudy item) {

            }

            @Override
            public void onProgrammesClick(Programmes programmes) {
                switch (programmes.getName()) {
                    case "Προπτυχιακές Σπουδές":
                        Intent undergraduates = new Intent(getActivity(), UndergraduateProg.class);
                        startActivity(undergraduates);
                        break;
                    case "Μεταπτυχιακά Προγράμματα":
                        Intent postgraduates = new Intent(getActivity(), PostgraduateProg.class);
                        startActivity(postgraduates);
                        break;
                    case "Εκπόνηση Διδακτορικού":
                        Intent phd = new Intent(getActivity(), PhdProg.class);
                        startActivity(phd);
                }
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
        programmes_recView.setAdapter(programmesAdapter);

    }

    private void createList() {
        programmes.add(new Programmes("Προπτυχιακές Σπουδές", "ΔΙΑΡΚΕΙΑΣ 4 ΕΤΩΝ", R.drawable.ungrad));
        programmes.add(new Programmes("Μεταπτυχιακά Προγράμματα", "ΔΙΑΡΚΕΙΑΣ 2 ΕΤΩΝ", R.drawable.postgrad));
        programmes.add(new Programmes("Εκπόνηση Διδακτορικού", "ΕΛΑΧΙΣΤΗΣ ΔΙΑΡΚΕΙΑΣ 3 ΕΤΩΝ", R.drawable.phd));
    }
}
