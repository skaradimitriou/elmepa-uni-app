package com.stathis.elmepaunivapp.ui.department;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stathis.elmepaunivapp.abstraction.AbstractActivity;
import com.stathis.elmepaunivapp.listeners.activity_listeners.DepartmentActivityListener;
import com.stathis.elmepaunivapp.ui.department.model.DepMembers;
import com.stathis.elmepaunivapp.ui.department.model.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.ui.department.model.Programmes;
import com.stathis.elmepaunivapp.ui.department.model.Research;
import com.stathis.elmepaunivapp.ui.department.model.SocialChannels;
import com.stathis.elmepaunivapp.ui.department.model.VirtualTour;
import com.stathis.elmepaunivapp.ui.professors.ProfessorsActivity;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.dashboard.DashboardActivity;
import com.stathis.elmepaunivapp.ui.research.ResearchActivity;
import com.stathis.elmepaunivapp.ui.students.StudentsActivity;
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusActivity;
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity;

import static android.Manifest.permission.CALL_PHONE;

public class DepartmentActivity extends AbstractActivity implements View.OnClickListener, DepartmentActivityListener {

    private static final int REQUEST_CALL = 1;
    private FloatingActionButton call, mail;
    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;
    private DepartmentViewModel viewModel;

    public DepartmentActivity() {
        super(R.layout.activity_department);
    }

    @Override
    public void initial() {
        viewModel = new ViewModelProvider(this).get(DepartmentViewModel.class);

        call = findViewById(R.id.fab_call);
        mail = findViewById(R.id.fab_mail);
        call.setOnClickListener(this);
        mail.setOnClickListener(this);

        recyclerView = findViewById(R.id.department_recycler);
        bottomNavigationView = findViewById(R.id.bottom_nav);

        viewModel.initAdapter(this);
    }

    @Override
    public void running() {
        recyclerView.setAdapter(viewModel.adapter);

        bottomNavigationView.setSelectedItemId(R.id.nav_uni);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(DepartmentActivity.this, DashboardActivity.class));
                        break;
                    case R.id.nav_students:
                        startActivity(new Intent(DepartmentActivity.this, StudentsActivity.class));
                        break;
                    case R.id.nav_uni:
                        return true;
                    case R.id.nav_search:
                        startActivity(new Intent(DepartmentActivity.this, ProfessorsActivity.class));
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void stopped() {

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
        if (ContextCompat.checkSelfPermission(DepartmentActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(DepartmentActivity.this, new String[]{CALL_PHONE}, REQUEST_CALL);
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
            Toast.makeText(DepartmentActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_mail: {
                sendAnEmailToSecretaryOffice();
                break;
            }
            case R.id.fab_call: {
                callAtSecretaryOffice();
                break;
            }
        }
    }

    @Override
    public void goToSyllabus(DeptFieldsOfStudy data) {
        switch(data.getName()){
            case "Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής" : {
                startActivity(new Intent(this, SyllabusActivity.class).putExtra("userTabChoice", 0));
                break;
            }
            case "Διοίκηση Επιχειρήσεων & Οργανισμών" : {
                startActivity(new Intent(this, SyllabusActivity.class).putExtra("userTabChoice",1));
                break;
            }
            case "Ψηφιακό Μάρκετινγκ και Επικοινωνία" : {
                startActivity(new Intent(this, SyllabusActivity.class).putExtra("userTabChoice",2));
                break;
            }
        }
    }

    @Override
    public void openProgrammes(Programmes data) {
        startActivity(new Intent(this, WebviewActivity.class).putExtra("URL", data.getUrl()));
    }

    @Override
    public void openDepMembers(DepMembers data) {
        startActivity(new Intent(this, WebviewActivity.class).putExtra("URL", "https://mst.hmu.gr/prosopiko/melh-dep/"));
    }

    @Override
    public void openSocial(SocialChannels data) {
        if (data.getImg() == R.drawable.youtube) {
            try {
                //goes to channel in youtube app
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube.com/channel/" + data.getUrl())));
            } catch (Exception e) {
                //goes to channel in web view (opens browser)
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/" + data.getUrl())));
            }
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(data.getUrl())));
        }
    }

    @Override
    public void goToVirtualTour(VirtualTour data) {
        startActivity(new Intent(this,WebviewActivity.class).putExtra("URL","https://mst.hmu.gr/hmutour/"));
    }

    @Override
    public void goToResearch(Research data) {
        startActivity(new Intent(this, ResearchActivity.class));
    }
}