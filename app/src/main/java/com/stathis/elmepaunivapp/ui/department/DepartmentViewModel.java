package com.stathis.elmepaunivapp.ui.department;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.DepMembersClickListener;
import com.stathis.elmepaunivapp.listeners.DepartmentCardClickListener;
import com.stathis.elmepaunivapp.listeners.FieldsOfStudyListener;
import com.stathis.elmepaunivapp.listeners.ProgrammesClickListener;
import com.stathis.elmepaunivapp.listeners.SocialClickListener;
import com.stathis.elmepaunivapp.listeners.activity_listeners.DepartmentActivityListener;
import com.stathis.elmepaunivapp.ui.department.model.DepMembers;
import com.stathis.elmepaunivapp.ui.department.model.DepMembersParent;
import com.stathis.elmepaunivapp.ui.department.model.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.ui.department.model.EmptyModel;
import com.stathis.elmepaunivapp.ui.department.model.Programmes;
import com.stathis.elmepaunivapp.ui.department.model.ProgrammesParent;
import com.stathis.elmepaunivapp.ui.department.model.Research;
import com.stathis.elmepaunivapp.ui.department.model.SocialChannels;
import com.stathis.elmepaunivapp.ui.department.model.FieldsOfStudyParent;
import com.stathis.elmepaunivapp.ui.department.model.SocialChannelsParent;
import com.stathis.elmepaunivapp.ui.department.model.VirtualTour;
import com.stathis.elmepaunivapp.ui.department.recyclerviews.DepartmentAdapter;

import java.util.ArrayList;

public class DepartmentViewModel extends ViewModel implements FieldsOfStudyListener,
        SocialClickListener, DepMembersClickListener, ProgrammesClickListener, DepartmentCardClickListener {

    private ArrayList<DeptFieldsOfStudy> fieldsOfStudyList = new ArrayList<>();
    private ArrayList<DepMembers> depMembersList = new ArrayList<>();
    private ArrayList<Programmes> programmes = new ArrayList<>();
    private ArrayList<SocialChannels> socialChannels = new ArrayList<>();
    DepartmentAdapter adapter = new DepartmentAdapter(this, this, this, this,this);
    private ArrayList<Object> list = new ArrayList<>();
    private DepartmentActivityListener listener;

    void initAdapter(DepartmentActivityListener listener) {
        this.listener = listener;

        adapter.submitList(list);

        getFieldsOfStudyList();
        getDepMembersList();
        getProgrammesList();
        getSocialList();

        list.add(new FieldsOfStudyParent("Γνωστικά Αντικείμενα", fieldsOfStudyList));
        list.add(new ProgrammesParent("Προγράμματα", programmes));
        list.add(new VirtualTour(""));
        list.add(new Research(""));
        list.add(new DepMembersParent("Μέλη Δ.Ε.Π.", depMembersList));
        list.add(new SocialChannelsParent("Βρείτε μας Online", socialChannels));
        list.add(new EmptyModel());

        adapter.notifyDataSetChanged();
    }

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
        fieldsOfStudyList.add(new DeptFieldsOfStudy("Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής", "", R.drawable.data));
        fieldsOfStudyList.add(new DeptFieldsOfStudy("Διοίκηση Επιχειρήσεων & Οργανισμών", "", R.drawable.business));
        fieldsOfStudyList.add(new DeptFieldsOfStudy("Ψηφιακό Μάρκετινγκ και Επικοινωνία", "", R.drawable.digitalmkt));
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
        programmes.add(new Programmes("Προπτυχιακές Σπουδές", "ΔΙΑΡΚΕΙΑΣ 4 ΕΤΩΝ", R.drawable.ungrad, "https://mst.hmu.gr/tmima/ypopshphioi-phoithtes/"));
        programmes.add(new Programmes("Μεταπτυχιακά Προγράμματα", "ΔΙΑΡΚΕΙΑΣ 2 ΕΤΩΝ", R.drawable.postgrad, "https://mst.hmu.gr/metaptyxiako/metaptychiako-programma/"));
        programmes.add(new Programmes("Εκπόνηση Διδακτορικού", "ΕΛΑΧΙΣΤΗΣ ΔΙΑΡΚΕΙΑΣ 3 ΕΤΩΝ", R.drawable.phd, "https://mst.hmu.gr/metaptyxiako/didaktorikes-spoydes/"));
        return programmes;
    }

    @Override
    public void onDepProfessorClick(DepMembers item) {
        listener.openDepMembers(item);
    }

    @Override
    public void onFieldOfStudyClick(DeptFieldsOfStudy fieldsOfStudy) {
        listener.goToSyllabus(fieldsOfStudy);
    }

    @Override
    public void onSocialItemClick(SocialChannels socialChannels) {
        listener.openSocial(socialChannels);
    }

    @Override
    public void onProgrammesClick(Programmes programmes) {
        listener.openProgrammes(programmes);
    }

    @Override
    public void onVirtualTourClick(VirtualTour data) {
        listener.goToVirtualTour(data);
    }

    @Override
    public void onResearchClick(Research data) {
        listener.goToResearch(data);
    }
}
