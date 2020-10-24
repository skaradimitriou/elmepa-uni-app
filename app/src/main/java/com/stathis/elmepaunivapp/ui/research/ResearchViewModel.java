package com.stathis.elmepaunivapp.ui.research;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.activity_listeners.ResearchActivityClickListener;
import com.stathis.elmepaunivapp.listeners.UsefulLinkClickListener;
import com.stathis.elmepaunivapp.recyclerviews.UsefulLinksAdapter;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;

import java.util.ArrayList;

public class ResearchViewModel extends AndroidViewModel implements UsefulLinkClickListener {

    UsefulLinksAdapter researchAdapter = new UsefulLinksAdapter(this);
    UsefulLinksAdapter researchLabsAdapter = new UsefulLinksAdapter(this);
    private ArrayList<UsefulLinks> researchItemList = new ArrayList<>();
    private ArrayList<UsefulLinks> researchLabList = new ArrayList<>();
    private ResearchActivityClickListener listener;

    public ResearchViewModel(@NonNull Application application) {
        super(application);

    }

    void initListener(ResearchActivityClickListener listener){
        this.listener = listener;
    }

    void displayData(){
        researchAdapter.submitList(researchItemList);
        researchLabsAdapter.submitList(researchLabList);
    }

    void createLists() {
        researchItemList.add(new UsefulLinks("Ινστιτούτο Οικονομικής Ανάλυσης", "https://mst.hmu.gr/ereuna/institoyto-oikonomikhs-analyshs-epicheirhmatikothtas-kai-toyrismoy/", R.drawable.institute));
        researchItemList.add(new UsefulLinks("Ερευνητικά Επιτεύγματα", "https://mst.hmu.gr/ereuna/ereynhtika-epiteygmata/", R.drawable.achievements));
        researchItemList.add(new UsefulLinks("Δημοσιεύσεις", "https://mst.hmu.gr/ereuna/dhmosieyseis/", R.drawable.papers));
        researchItemList.add(new UsefulLinks("Στατιστικά Στοιχεία", "https://mst.hmu.gr/ereuna/statistika-stoicheia/", R.drawable.analytics));

        researchLabList.add(new UsefulLinks("Εργαστήριο Διοικητικής Οικονομικής και Συστημάτων Αποφάσεων", "https://mst.hmu.gr/ereuna/adeds/", R.drawable.lab));
        researchLabList.add(new UsefulLinks("Εργαστήριο Επιστήμης Δεδομένων, Πολυμέσων και Μοντελοποίησης", "https://mst.hmu.gr/ereuna/datalab/", R.drawable.lab));
        researchLabList.add(new UsefulLinks("Εργαστήριο Ηλεκτρονικής Επιχειρηματικής Ευφυΐας", "https://www.e-bilab.gr/", R.drawable.lab));
    }

    @Override
    public void onUsefulLinksClick(UsefulLinks usefulLinks) {
        listener.itemClicked(usefulLinks);
    }
}
