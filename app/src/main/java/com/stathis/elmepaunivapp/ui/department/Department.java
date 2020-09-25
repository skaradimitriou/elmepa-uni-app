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
import com.stathis.elmepaunivapp.DepMembersFragment;
import com.stathis.elmepaunivapp.FieldsOfStudyFragment;
import com.stathis.elmepaunivapp.FindUsFragment;
import com.stathis.elmepaunivapp.ui.professors.Professors;
import com.stathis.elmepaunivapp.ProgrammesFragment;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ResearchInDept;
import com.stathis.elmepaunivapp.ui.dashboard.Dashboard;
import com.stathis.elmepaunivapp.ui.students.Students;

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
                Intent research = new Intent(Department.this, ResearchInDept.class);
                startActivity(research);
            }
        });

        //virtual tour
        virtual_tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String openUrl = "https://mst.hmu.gr/hmutour/";
                Intent VirtualTour = new Intent(Department.this, com.stathis.elmepaunivapp.VirtualTour.class);
                VirtualTour.putExtra("VirtualTourUrl", openUrl);
                startActivity(VirtualTour);
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
                Intent i;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        i = new Intent(Department.this, Dashboard.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_students:
                        i = new Intent(Department.this, Students.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_uni:
                        return true;
                    case R.id.nav_search:
                        i = new Intent(Department.this, Professors.class);
                        startActivity(i);
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
            String phone = "tel:2841091103";
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(phone));
            startActivity(callIntent);
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
