package com.stathis.elmepaunivapp.ui.dashboard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stathis.elmepaunivapp.ui.department.Department;
import com.stathis.elmepaunivapp.ui.professors.Professors;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.announcements.Announcements;
import com.stathis.elmepaunivapp.ui.chatbot.ChatBotActivity;
import com.stathis.elmepaunivapp.ui.students.Students;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    private CardView updates, department, students, professors;
    private ImageView about;
    private FloatingActionButton chatbot_btn;
    private DashboardViewModel dashboardViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        // <!---- Initializing the ViewModel for this activity ----->
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        updates = findViewById(R.id.anakoinoseis);
        department = findViewById(R.id.to_tmima);
        students = findViewById(R.id.students);
        professors = findViewById(R.id.professors);
        about = findViewById(R.id.about);
        chatbot_btn = findViewById(R.id.fab_chatbot);

        //listeners
        updates.setOnClickListener(this);
        department.setOnClickListener(this);
        students.setOnClickListener(this);
        professors.setOnClickListener(this);
        chatbot_btn.setOnClickListener(this);

        //about the app
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Dashboard.this);
                builder.setTitle(dashboardViewModel.getTitle());
                builder.setMessage(dashboardViewModel.getAboutText());
                builder.setPositiveButton("Μάθε Περισσότερα", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://mst.hmu.gr/ypiresies/mobile-epharmogh-tmhmatos/")));
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
        //cases bellow
        switch (v.getId()) {
            case R.id.anakoinoseis:
                // <!--- check for internet connection and then proceed to announcements --->
                // TODO("Implement internet connection check first")
                startActivity(new Intent(Dashboard.this, Announcements.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.to_tmima:
                startActivity(new Intent(Dashboard.this, Department.class));
                break;
            case R.id.students:
                startActivity(new Intent(Dashboard.this, Students.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.professors:
                startActivity(new Intent(Dashboard.this, Professors.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.fab_chatbot:
                startActivity(new Intent(Dashboard.this, ChatBotActivity.class));
                overridePendingTransition(0, 0);
                break;
        }
    }
}