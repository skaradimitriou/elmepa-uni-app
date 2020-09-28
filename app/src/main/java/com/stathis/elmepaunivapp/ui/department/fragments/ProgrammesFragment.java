package com.stathis.elmepaunivapp.ui.department.fragments;

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
import com.stathis.elmepaunivapp.listeners.ProgrammesClickListener;
import com.stathis.elmepaunivapp.ui.department.DepartmentViewModel;
import com.stathis.elmepaunivapp.models.Programmes;
import com.stathis.elmepaunivapp.ui.department.recyclerviews.ProgrammesAdapter;
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity;

public class ProgrammesFragment extends Fragment {

    private RecyclerView programmes_recView;
    private ProgrammesAdapter programmesAdapter;
    private DepartmentViewModel departmentViewModel;

    public ProgrammesFragment() {
        //required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        departmentViewModel = new ViewModelProvider(this).get(DepartmentViewModel.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_programmes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        programmes_recView = view.findViewById(R.id.programmes_recView);
        programmesAdapter = new ProgrammesAdapter(new ProgrammesClickListener() {
            @Override
            public void onProgrammesClick(Programmes programmes) {
                switch (programmes.getName()) {
                    case "Προπτυχιακές Σπουδές":
                        String undergraduateUrl = "https://mst.hmu.gr/tmima/ypopshphioi-phoithtes/";
                        Intent undergraduates = new Intent(getActivity(), WebviewActivity.class);
                        undergraduates.putExtra("URL", undergraduateUrl);
                        startActivity(undergraduates);
                        break;
                    case "Μεταπτυχιακά Προγράμματα":
                        String postgraduatesUrl = "https://mst.hmu.gr/metaptyxiako/metaptychiako-programma/";
                        Intent postgraduates = new Intent(getActivity(), WebviewActivity.class);
                        postgraduates.putExtra("URL", postgraduatesUrl);
                        startActivity(postgraduates);
                        break;
                    case "Εκπόνηση Διδακτορικού":
                        String phdUrl = "https://mst.hmu.gr/metaptyxiako/didaktorikes-spoydes/";
                        Intent phd = new Intent(getActivity(), WebviewActivity.class);
                        phd.putExtra("URL", phdUrl);
                        startActivity(phd);
                        break;
                }
            }
        });
        programmesAdapter.submitList(departmentViewModel.getProgrammesList());
        programmes_recView.setAdapter(programmesAdapter);
    }
}