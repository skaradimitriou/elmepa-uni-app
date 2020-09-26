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

import com.stathis.elmepaunivapp.models.DepMembers;
import com.stathis.elmepaunivapp.recyclerviews.DepMembersAdapter;
import com.stathis.elmepaunivapp.listeners.OnItemClickListener;

import java.util.ArrayList;

public class DepMembersFragment extends Fragment {

    private RecyclerView depMembers;
    private DepMembersAdapter depMembersAdapter;
    private ArrayList<DepMembers> depMembersList = new ArrayList<>();

    public DepMembersFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dep_members, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createThisList();
        depMembers = view.findViewById(R.id.depMembers_recView_frag);
        depMembersAdapter = new DepMembersAdapter(depMembersList, new OnItemClickListener() {
            @Override
            public void onDepProfessorClick(DepMembers item) {
                Intent passProfData = new Intent(getActivity(), ProfessorProfile.class);
                String url = "https://mst.hmu.gr/prosopiko/melh-dep/";
                passProfData.putExtra("PROFESSORS_URL", url);
                startActivity(passProfData);
            }

            @Override
            public void onClick(View v) {

            }
        });
        depMembers.setAdapter(depMembersAdapter);
    }

    private void createThisList() {
        depMembersList.add(new DepMembers("Στέλιος Παπαδάκης", R.drawable.papadakis));
        depMembersList.add(new DepMembers("Κώστας Παναγιωτάκης", R.drawable.panagiotakis));
        depMembersList.add(new DepMembers("Γιώργος Μαστοράκης", R.drawable.mastorakis));
        depMembersList.add(new DepMembers("Γιάννης Κοπανάκης", R.drawable.kopanakis));
        depMembersList.add(new DepMembers("Γιάννης Δημοτίκαλης", R.drawable.dimotikalis));
        depMembersList.add(new DepMembers("Χρήστος Λεμονάκης", R.drawable.lemonakis));
        depMembersList.add(new DepMembers("Μάνος Περακάκης", R.drawable.perakakis));
    }
}
