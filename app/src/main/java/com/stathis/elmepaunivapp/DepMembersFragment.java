package com.stathis.elmepaunivapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.models.DepMembers;
import com.stathis.elmepaunivapp.recyclerview.DepMembersAdapter;
import com.stathis.elmepaunivapp.recyclerview.OnItemClickListener;

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
        return inflater.inflate(R.layout.fragment_dep_members,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createThisList();

        depMembers = view.findViewById(R.id.depMembers_recView_frag);
        depMembersAdapter = new DepMembersAdapter(depMembersList, new OnItemClickListener() {
            @Override
            public void onDepProfessorClick(DepMembers item) {
                Intent test = new Intent(getActivity(), ProfessorProfile.class);
                test.putExtra("PROFESSOR_NAME",item.getName());
                test.putExtra("PROFESSOR_IMG",item.getImg());
                startActivity(test);
            }

            @Override
            public void onClick(View v) {

            }
        });
        depMembers.setAdapter(depMembersAdapter);
    }

    private void createThisList(){
        depMembersList.add(new DepMembers("Στέλιος Παπαδάκης",
                "ΚΑΘΗΓΗΤΗΣ | ΚΟΣΜΗΤΟΡΑΣ ΣΕΔΟ",
                "Ο Δρ. Στέλιος Παπαδάκης είναι κοσμήτορας της Σχολής Επιστημών Διοίκησης και Οικονομίας και Καθηγητής στο τμήμα Διοικητικής Επιστήμης και Τεχνολογίας.",
                "https://www.linkedin.com/in/costas-panagiotakis-729b354",
                "https://www.researchgate.net/profile/Costas_Panagiotakis",
                "https://scholar.google.com/citations?user=ORyzzCQAAAAJ","COMPUTATIONAL INTELLIGENCE","PARALLEL & DISTRIBUTED ALGORITHMS","FUZZY SYSTEMS",
                R.drawable.papadakis));
        depMembersList.add(new DepMembers("Κώστας Παναγιωτάκης",
                "ΑΝΑΠΛΗΡΩΤΗΣ ΚΑΘΗΓΗΤΗΣ | ΠΡΟΕΔΡΟΣ ΤΜΗΜΑΤΟΣ",
                "Είναι πτυχιούχος του Τμήματος Επιστήμης Υπολογιστών του Πανεπιστημίου Κρήτης (2001), με Μεταπτυχιακό (2003) και Διδακτορικό Δίπλωμα (2007) από το ίδιο Τμήμα. Εργάστηκε ως Επισκέπτης Καθηγητής στο Πανεπιστήμιο Κρήτης. Το 2010 διορίστηκε Επίκουρος Καθηγητής και από το 2014 είναι Αναπληρωτής Καθηγητής.",
                "https://www.linkedin.com/in/stelios-papadakis-749110a/",
                "https://www.researchgate.net/profile/Stelios_Papadakis",
                "https://scholar.google.com/citations?user=tTj1pbcAAAAJ","SIGNAL, IMAGE PROCESSING","PATTERN RECOGNITION","MULTIMEDIA",
                R.drawable.panagiotakis));
        depMembersList.add(new DepMembers("Στέλιος Παπαδάκης",
                "ΚΑΘΗΓΗΤΗΣ | ΚΟΣΜΗΤΟΡΑΣ ΣΕΔΟ",
                "Ο Δρ. Στέλιος Παπαδάκης είναι κοσμήτορας της Σχολής Επιστημών Διοίκησης και Οικονομίας και Καθηγητής στο τμήμα Διοικητικής Επιστήμης και Τεχνολογίας.",
                "https://www.linkedin.com/in/stelios-papadakis-749110a/",
                "https://www.researchgate.net/profile/Stelios_Papadakis",
                "https://scholar.google.com/citations?user=tTj1pbcAAAAJ","COMPUTATIONAL INTELLIGENCE","PARALLEL & DISTRIBUTED ALGORITHMS","FUZZY SYSTEMS",
                R.drawable.mastorakis));
        depMembersList.add(new DepMembers("Στέλιος Παπαδάκης",
                "ΚΑΘΗΓΗΤΗΣ | ΚΟΣΜΗΤΟΡΑΣ ΣΕΔΟ",
                "Ο Δρ. Στέλιος Παπαδάκης είναι κοσμήτορας της Σχολής Επιστημών Διοίκησης και Οικονομίας και Καθηγητής στο τμήμα Διοικητικής Επιστήμης και Τεχνολογίας.",
                "https://www.linkedin.com/in/stelios-papadakis-749110a/",
                "https://www.researchgate.net/profile/Stelios_Papadakis",
                "https://scholar.google.com/citations?user=tTj1pbcAAAAJ","COMPUTATIONAL INTELLIGENCE","PARALLEL & DISTRIBUTED ALGORITHMS","FUZZY SYSTEMS",
                R.drawable.kopanakis));
        depMembersList.add(new DepMembers("Στέλιος Παπαδάκης",
                "ΚΑΘΗΓΗΤΗΣ | ΚΟΣΜΗΤΟΡΑΣ ΣΕΔΟ",
                "Ο Δρ. Στέλιος Παπαδάκης είναι κοσμήτορας της Σχολής Επιστημών Διοίκησης και Οικονομίας και Καθηγητής στο τμήμα Διοικητικής Επιστήμης και Τεχνολογίας.",
                "https://www.linkedin.com/in/stelios-papadakis-749110a/",
                "https://www.researchgate.net/profile/Stelios_Papadakis",
                "https://scholar.google.com/citations?user=tTj1pbcAAAAJ","COMPUTATIONAL INTELLIGENCE","PARALLEL & DISTRIBUTED ALGORITHMS","FUZZY SYSTEMS",
                R.drawable.dimotikalis));
        depMembersList.add(new DepMembers("Στέλιος Παπαδάκης",
                "ΚΑΘΗΓΗΤΗΣ | ΚΟΣΜΗΤΟΡΑΣ ΣΕΔΟ",
                "Ο Δρ. Στέλιος Παπαδάκης είναι κοσμήτορας της Σχολής Επιστημών Διοίκησης και Οικονομίας και Καθηγητής στο τμήμα Διοικητικής Επιστήμης και Τεχνολογίας.",
                "https://www.linkedin.com/in/stelios-papadakis-749110a/",
                "https://www.researchgate.net/profile/Stelios_Papadakis",
                "https://scholar.google.com/citations?user=tTj1pbcAAAAJ","COMPUTATIONAL INTELLIGENCE","PARALLEL & DISTRIBUTED ALGORITHMS","FUZZY SYSTEMS",
                R.drawable.lemonakis));
        depMembersList.add(new DepMembers("Μάνος Περακάκης",
                "ΛΕΚΤΟΡΑΣ",
                "Απόφοιτος του τμήματος επιστήμης υπολογιστών του Πανεπιστημίου του Essex, με MSc από το Πανεπιστήμιο του Bristol. Εκπόνησε το διδακτορικό του στο Πανεπιστήμιο του Brunel.",
                "https://www.linkedin.com/in/manosperakakis",
                "https://www.researchgate.net/profile/Emmanuel_Perakakis",
                "https://scholar.google.gr/citations?user=DY82628AAAAJ","UX DESIGN","DIGITAL MARKETING","SOCIAL INTELLIGENCE",
                R.drawable.perakakis));
    }

}
