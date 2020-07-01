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
                Intent passProfData = new Intent(getActivity(), ProfessorProfile.class);
                passProfData.putExtra("PROFESSOR_NAME",item.getName());
                passProfData.putExtra("PROFESSOR_TITLE",item.getTitle());
                passProfData.putExtra("PROFESSOR_IMG",item.getImg());
                passProfData.putExtra("PROFESSOR_DESC", item.getDescription());
                passProfData.putExtra("PROFESSOR_LINKEDIN", item.getLinkedin());
                passProfData.putExtra("PROFESSOR_RG", item.getResearchGate());
                passProfData.putExtra("PROFESSOR_SCHOLAR", item.getGoogleScholar());
                passProfData.putExtra("PROFESSOR_SKILL_ONE", item.getSkill_one());
                passProfData.putExtra("PROFESSOR_SKILL_TWO", item.getSkill_two());
                passProfData.putExtra("PROFESSOR_SKILL_THREE", item.getSkill_three());
                startActivity(passProfData);
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
                "https://www.linkedin.com/in/stelios-papadakis-749110a/",
                "https://www.researchgate.net/profile/Stelios_Papadakis",
                "https://scholar.google.com/citations?user=tTj1pbcAAAAJ","COMPUTATIONAL INTELLIGENCE","PARALLEL & DISTRIBUTED ALGORITHMS","FUZZY SYSTEMS",
                R.drawable.papadakis));
        depMembersList.add(new DepMembers("Κώστας Παναγιωτάκης",
                "ΑΝΑΠΛΗΡΩΤΗΣ ΚΑΘΗΓΗΤΗΣ | ΠΡΟΕΔΡΟΣ ΤΜΗΜΑΤΟΣ",
                "Είναι πτυχιούχος του Τμήματος Επιστήμης Υπολογιστών του Πανεπιστημίου Κρήτης (2001), με Μεταπτυχιακό (2003) και Διδακτορικό Δίπλωμα (2007) από το ίδιο Τμήμα. Εργάστηκε ως Επισκέπτης Καθηγητής στο Πανεπιστήμιο Κρήτης. Το 2010 διορίστηκε Επίκουρος Καθηγητής και από το 2014 είναι Αναπληρωτής Καθηγητής.",
                "https://www.linkedin.com/in/costas-panagiotakis-729b354",
                "https://www.researchgate.net/profile/Costas_Panagiotakis",
                "https://scholar.google.com/citations?user=ORyzzCQAAAAJ","SIGNAL, IMAGE PROCESSING","PATTERN RECOGNITION","MULTIMEDIA",
                R.drawable.panagiotakis));
        depMembersList.add(new DepMembers("Γιώργος Μαστοράκης",
                "ΑΝΑΠΛΗΡΩΤΗΣ ΚΑΘΗΓΗΤΗΣ | ΑΝΤΙΠΡΟΕΔΡΟΣ ΤΜΗΜΑΤΟΣ",
                "Είναι Αναπληρωτής Καθηγητής του τμήματος και διευθυντής του Εργαστηρίου Επιχειρηματικής Ευφυΐας (e-bi lab) του Ελληνικού Μεσογειακού Πανεπιστημίου. Έχει συγγράψει περισσότερα από 300 ερευνητικά άρθρα σε έγκριτα επιστημονικά περιοδικά, πρακτικά διεθνών συνεδρίων και κεφάλαια βιβλίων.",
                "https://www.linkedin.com/in/georgemastorakis",
                "https://www.researchgate.net/profile/George_Mastorakis",
                "https://scholar.google.gr/citations?user=NVh4Bt0AAAAJ","DATA NETWORKING TECHNOLOGIES","DIGITAL MARKETING","MOBILE COMPUTING",
                R.drawable.mastorakis));
        depMembersList.add(new DepMembers("Γιάννης Κοπανάκης",
                "ΚΑΘΗΓΗΤΗΣ",
                "Είναι πτυχιούχος του Τμήματος Επιστήμης Υπολογιστών του Πανεπιστημίου Κρήτης, κατέχει μεταπτυχιακό από το Πανεπιστήμιο του Μάντσεστερ και Διδακτορικό τίτλο επίσης από το ίδιο Πανεπιστήμιο.",
                "https://www.linkedin.com/in/kopanakis/",
                "https://www.researchgate.net/profile/Ioannis_Kopanakis",
                "https://scholar.google.gr/citations?user=StW9K5UAAAAJ","BUSINESS INTELLIGENCE","DATA ANALYTICS","INNOVATION",
                R.drawable.kopanakis));
        depMembersList.add(new DepMembers("Γιάννης Δημοτίκαλης",
                "ΕΠΙΚΟΥΡΟΣ ΚΑΘΗΓΗΤΗΣ",
                "Είναι επίκουρος καθηγητής του τμήματος με Δίπλωμα Μηχανικού Παραγωγής & Διοίκησης από το Πολυτεχνείο Κρήτης και Διδακτορικό (PhD) από το ίδιο τμήμα.",
                "https://www.linkedin.com/in/giannis-dimotikalis-07427a44/",
                "https://www.researchgate.net/profile/Yiannis_Dimotikalis",
                "https://scholar.google.com/citations?user=a0WZKmIAAAAJ","BUSINESS ANALYTICS","ECONOMIC MODELING","STATISTICS",
                R.drawable.dimotikalis));
        depMembersList.add(new DepMembers("Χρήστος Λεμονάκης",
                "ΕΠΙΚΟΥΡΟΣ ΚΑΘΗΓΗΤΗΣ",
                "Έλαβε το Δίπλωμα του Μηχανικού Παραγωγής και Διοίκησης από το Πολυτεχνείο Κρήτης, το πτυχίο των Οικονομικών Επιστημών από το Πανεπιστήμιο Κρήτης, το Μεταπτυχιακό Δίπλωμα Ειδίκευσης και το διδακτορικό από το Τμήμα Μηχανικών Παραγωγής και Διοίκησης του Πολυτεχνείου Κρήτης.",
                "https://www.linkedin.com/in/christos-lemonakis-811b34148/",
                "https://www.researchgate.net/profile/Christos_Lemonakis",
                "https://scholar.google.com/citations?user=Lz3vQVsAAAAJ","SMALL BUSINESS MANAGEMENT","RISK MANAGEMENT","COST ACCOUNTING",
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
