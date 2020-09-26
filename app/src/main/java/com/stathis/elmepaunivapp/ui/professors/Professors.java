package com.stathis.elmepaunivapp.ui.professors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.ProfessorClickListener;
import com.stathis.elmepaunivapp.ui.professors.model.ProfessorModel;
import com.stathis.elmepaunivapp.ui.professors.recyclerview.ProfessorAdapter;
import com.stathis.elmepaunivapp.ui.dashboard.Dashboard;
import com.stathis.elmepaunivapp.ui.department.Department;
import com.stathis.elmepaunivapp.ui.students.Students;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Professors extends AppCompatActivity {

    private ProfessorAdapter adapter;
    private RecyclerView recyclerView;
    private EditText search;
    private ProfessorsViewModel professorsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professors);
        //Initializing the professors ViewModel
        professorsViewModel = new ViewModelProvider(this).get(ProfessorsViewModel.class);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //Search in recview
        search = findViewById(R.id.search_action);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO("Implement search filtering in ListAdapter")
            }
        });

        //  <----- Professors Recycler View setup start ------->

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ProfessorAdapter(new ProfessorClickListener() {
            @Override
            public void onProfessorClick(ProfessorModel professorModel) {
                showDialogue(professorModel);
            }
        });
        adapter.submitList(professorsViewModel.createProfessorList());
        recyclerView.setAdapter(adapter);

        //  <----- Professors Recycler View setup end ------->
        //  <----- Bottom Navigation & Listener start ------->

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_search);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        i = new Intent(Professors.this, Dashboard.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_students:
                        i = new Intent(Professors.this, Students.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_uni:
                        i = new Intent(Professors.this, Department.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_search:
                        return true;
                }
                return false;
            }
        });
        //  <----- Bottom Navigation & Listener end ------->
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private void showDialogue(final ProfessorModel professorModel) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Professors.this);
        builder.setTitle("Νέο e-mail");
        if (professorModel.getGender().equals("male")) {
            builder.setMessage("Είσαι σίγουρος πως θέλεις να στείλεις e-mail στον κ. " + professorModel.getVocative() + ";");
        } else if (professorModel.getGender().equals("female")) {
            builder.setMessage("Είσαι σίγουρος πως θέλεις να στείλεις e-mail στην κ. " + professorModel.getVocative() + ";");
        }

        builder.setPositiveButton("Ναι", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendEmailToProfessor(professorModel);
            }
        });
        builder.setNegativeButton("Άκυρο", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void sendEmailToProfessor(ProfessorModel professorModel) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{professorModel.getEmail()});
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Professors.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
