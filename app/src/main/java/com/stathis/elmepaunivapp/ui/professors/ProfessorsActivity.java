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
import com.stathis.elmepaunivapp.listeners.activity_listeners.ProfessorActivityClickListener;
import com.stathis.elmepaunivapp.ui.professors.model.ProfessorModel;
import com.stathis.elmepaunivapp.ui.dashboard.Dashboard;
import com.stathis.elmepaunivapp.ui.department.Department;
import com.stathis.elmepaunivapp.ui.students.Students;

public class ProfessorsActivity extends AppCompatActivity implements ProfessorActivityClickListener {

    private RecyclerView recyclerView;
    private EditText search;
    private ProfessorsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professors);
        viewModel = new ViewModelProvider(this).get(ProfessorsViewModel.class);
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
                //
            }
        });

        // Professors Recycler View setup
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(viewModel.adapter);
        viewModel.setupListener(this);
        viewModel.showProfessors();

        // Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_search);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(ProfessorsActivity.this, Dashboard.class));
                        break;
                    case R.id.nav_students:
                        startActivity(new Intent(ProfessorsActivity.this, Students.class));
                        break;
                    case R.id.nav_uni:
                        startActivity(new Intent(ProfessorsActivity.this, Department.class));
                        break;
                    case R.id.nav_search:
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void sendEmailToProfessor(ProfessorModel professorModel) {
        Intent i = new Intent(Intent.ACTION_SEND)
                .setType("message/rfc822")
                .putExtra(Intent.EXTRA_EMAIL, new String[]{professorModel.getEmail()});
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ProfessorsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showDialog(final ProfessorModel professorModel) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
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
}
