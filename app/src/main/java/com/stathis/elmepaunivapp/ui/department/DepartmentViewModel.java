package com.stathis.elmepaunivapp.ui.department;

import androidx.lifecycle.ViewModel;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.DepMembers;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.models.Programmes;
import com.stathis.elmepaunivapp.models.SocialChannels;

import java.util.ArrayList;

public class DepartmentViewModel extends ViewModel {

    private ArrayList<DeptFieldsOfStudy> fieldsOfStudyList = new ArrayList<>();
    private ArrayList<DepMembers> depMembersList = new ArrayList<>();
    private ArrayList<Programmes> programmes = new ArrayList<>();
    private ArrayList<SocialChannels> socialChannels = new ArrayList<>();

    public ArrayList<DepMembers> getDepMembersList() {
        depMembersList.add(new DepMembers("Στέλιος Παπαδάκης", R.drawable.papadakis));
        depMembersList.add(new DepMembers("Κώστας Παναγιωτάκης", R.drawable.panagiotakis));
        depMembersList.add(new DepMembers("Γιώργος Μαστοράκης", R.drawable.mastorakis));
        depMembersList.add(new DepMembers("Γιάννης Κοπανάκης", R.drawable.kopanakis));
        depMembersList.add(new DepMembers("Γιάννης Δημοτίκαλης", R.drawable.dimotikalis));
        depMembersList.add(new DepMembers("Χρήστος Λεμονάκης", R.drawable.lemonakis));
        depMembersList.add(new DepMembers("Μάνος Περακάκης", R.drawable.perakakis));
        return depMembersList;
    }

    public ArrayList<DeptFieldsOfStudy> getFieldsOfStudyList() {
        fieldsOfStudyList.add(new DeptFieldsOfStudy("Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής","", R.drawable.data));
        fieldsOfStudyList.add(new DeptFieldsOfStudy("Διοίκηση Επιχειρήσεων & Οργανισμών","", R.drawable.business));
        fieldsOfStudyList.add(new DeptFieldsOfStudy("Ψηφιακό Μάρκετινγκ και Επικοινωνία","", R.drawable.digitalmkt));
        return fieldsOfStudyList;
    }

    public ArrayList<SocialChannels> getSocialList() {
        socialChannels.add(new SocialChannels("Χάρτης", "https://www.google.gr/maps/place/Hellenic+Mediterσ46953,25.6549865,17z/data=!3m1!4b1!4m5!3m4!1s0x149a7fea00679c2f:0x8038b06fd113f3fb!8m2!3d35.1946909!4d25.6571752", R.drawable.map));
        socialChannels.add(new SocialChannels("Youtube", "UCapUQKQVrP2p4_ijj_OxvNg", R.drawable.youtube));
        socialChannels.add(new SocialChannels("LinkedIn", "https://www.linkedin.com/groups/13536369/", R.drawable.linkedin));
        socialChannels.add(new SocialChannels("Research\nGate", "https://www.researchgate.net/institution/Hellenic_Mediterranean_University/department/Department_of_Management_Science_and_Technology_Agios_Nikolaos", R.drawable.researchgate));
        return socialChannels;
    }

    public ArrayList<Programmes> getProgrammesList() {
        programmes.add(new Programmes("Προπτυχιακές Σπουδές", "ΔΙΑΡΚΕΙΑΣ 4 ΕΤΩΝ", R.drawable.ungrad));
        programmes.add(new Programmes("Μεταπτυχιακά Προγράμματα", "ΔΙΑΡΚΕΙΑΣ 2 ΕΤΩΝ", R.drawable.postgrad));
        programmes.add(new Programmes("Εκπόνηση Διδακτορικού", "ΕΛΑΧΙΣΤΗΣ ΔΙΑΡΚΕΙΑΣ 3 ΕΤΩΝ", R.drawable.phd));
        return programmes;
    }
}
