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
import com.stathis.elmepaunivapp.models.DepMembers;
import com.stathis.elmepaunivapp.ui.department.recyclerviews.DepMembersAdapter;
import com.stathis.elmepaunivapp.listeners.DepMembersClickListener;
import com.stathis.elmepaunivapp.ui.department.DepartmentViewModel;
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity;

public class DepMembersFragment extends Fragment {

    private RecyclerView depMembers;
    private DepMembersAdapter depMembersAdapter;
    private DepartmentViewModel departmentViewModel;

    public DepMembersFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        departmentViewModel = new ViewModelProvider(this).get(DepartmentViewModel.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dep_members, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        depMembers = view.findViewById(R.id.depMembers_recView_frag);
        depMembersAdapter = new DepMembersAdapter(new DepMembersClickListener() {
            @Override
            public void onDepProfessorClick(DepMembers item) {
                Intent passProfData = new Intent(getActivity(), WebviewActivity.class);
                String url = "https://mst.hmu.gr/prosopiko/melh-dep/";
                passProfData.putExtra("URL", url);
                startActivity(passProfData);
            }
        });
        depMembers.setAdapter(depMembersAdapter);
        depMembersAdapter.submitList(departmentViewModel.getDepMembersList());
    }
}
