package com.stathis.elmepaunivapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    private CardView updates,department,students,professors;
    private ImageView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        updates = findViewById(R.id.anakoinoseis);
        department = findViewById(R.id.to_tmima);
        students = findViewById(R.id.students);
        professors = findViewById(R.id.professors);
        about = findViewById(R.id.about);

        //listeners
        updates.setOnClickListener(this);
        department.setOnClickListener(this);
        students.setOnClickListener(this);
        professors.setOnClickListener(this);

        //about the app
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Dashboard.this);
                builder.setTitle("Σχετικά με την εφαρμογή");
                builder.setMessage("Η εφαρμογή αυτή αναπτύχθηκε το 2020 από τον απόφοιτο του Τμήματος \n" +
                        "Στάθη  Καραδημητρίου με την επίβλεψη του Κώστα Παναγιωτάκη, Αναπληρωτή Καθηγητή και Προέδρου του Τμήματος\n" +
                        "με σκοπό την εξυπηρέτηση των μελών του Τμήματος και την προώθηση του τμήματος σε υποψήφιους φοιτητές. \n" +
                        "Αποτελεί την επόμενη έκδοση της εφαρμογής κινητών που είχε αναπτυχθεί στα πλαίσια της πτυχιακής εργασίας \n" +
                        "των φοιτητών του τμήματος Πελοπίδα Κεφαλιανού και Μαρίας Λαγουδάκη το 2017.");
                builder.setPositiveButton("Μάθε Περισσότερα", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent learnMore = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mst.hmu.gr/ypiresies/mobile-epharmogh-tmhmatos/"));
                        startActivity(learnMore);
                    }
                });
                builder.setNegativeButton("Ακυρο", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        //cases bellow
        switch(v.getId()){
            case R.id.anakoinoseis:
                i = new Intent(Dashboard.this,Announcements.class);
                startActivity(i);
                overridePendingTransition(0, 0);
                break;
            case R.id.to_tmima:
                i=new Intent(Dashboard.this, Department.class);
                startActivity(i);
                break;
            case R.id.students:
                i=new Intent(Dashboard.this, Students.class);
                startActivity(i);
                overridePendingTransition(0, 0);
                break;
            case R.id.professors:
                i=new Intent(Dashboard.this, Professors.class);
                startActivity(i);
                overridePendingTransition(0, 0);
                break;
        }
    }
}
