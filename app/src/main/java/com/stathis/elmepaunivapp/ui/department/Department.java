package com.stathis.elmepaunivapp.ui.department;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stathis.elmepaunivapp.ui.department.fragments.DepMembersFragment;
import com.stathis.elmepaunivapp.ui.students.fragments.FieldsOfStudyFragment;
import com.stathis.elmepaunivapp.ui.department.fragments.FindUsFragment;
import com.stathis.elmepaunivapp.ui.professors.ProfessorsActivity;
import com.stathis.elmepaunivapp.ui.department.fragments.ProgrammesFragment;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.research.ResearchInDept;
import com.stathis.elmepaunivapp.ui.dashboard.Dashboard;
import com.stathis.elmepaunivapp.ui.students.Students;
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity;

import static android.Manifest.permission.CALL_PHONE;

public class Department extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private FloatingActionButton call, mail;
    private CardView virtual_tour;
    private Button researchInDept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        call = findViewById(R.id.fab_call);
        mail = findViewById(R.id.fab_mail);
        virtual_tour = findViewById(R.id.virtual_tour);
        researchInDept = findViewById(R.id.research_card_btn);

        //fields of Study fragment
        FragmentTransaction fieldsOfStudy = getSupportFragmentManager().beginTransaction();
        fieldsOfStudy.add(R.id.fieldsOfStudy_frag, new FieldsOfStudyFragment(), "FieldsOfStudyFragment");
        fieldsOfStudy.commit();

        //Programmes fragment
        FragmentTransaction programmes = getSupportFragmentManager().beginTransaction();
        programmes.add(R.id.programmes_frag, new ProgrammesFragment(), "ProgrammesFragment");
        programmes.commit();

        //dep members fragment
        FragmentTransaction depMembersFragment = getSupportFragmentManager().beginTransaction();
        depMembersFragment.add(R.id.depMembers_frag, new DepMembersFragment(), "TestFragment");
        depMembersFragment.commit();

        //dep members fragment
        FragmentTransaction findUsFragment = getSupportFragmentManager().beginTransaction();
        findUsFragment.add(R.id.social_frag, new FindUsFragment(), "FindUsFragment");
        findUsFragment.commit();

        //research btn
        researchInDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Department.this, ResearchInDept.class));
            }
        });

        //virtual tour
        virtual_tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Department.this, WebviewActivity.class).putExtra(
                        "URL", "https://mst.hmu.gr/hmutour/"
                ));
            }
        });

        //Fab Buttons
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAtSecretaryOffice();
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAnEmailToSecretaryOffice();
            }
        });
        //bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_uni);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(Department.this, Dashboard.class));
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_students:
                        startActivity(new Intent(Department.this, Students.class));
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_uni:
                        return true;
                    case R.id.nav_search:
                        startActivity(new Intent(Department.this, ProfessorsActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callAtSecretaryOffice();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void callAtSecretaryOffice() {
        if (ContextCompat.checkSelfPermission(Department.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Department.this, new String[]{CALL_PHONE}, REQUEST_CALL);
        } else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:2841091103")));
        }
    }

    private void sendAnEmailToSecretaryOffice() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"kalarhaki@hmu.gr"});
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Department.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}