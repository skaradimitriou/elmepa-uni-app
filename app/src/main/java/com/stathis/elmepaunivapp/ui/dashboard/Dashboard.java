package com.stathis.elmepaunivapp.ui.dashboard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stathis.elmepaunivapp.listeners.activity_listeners.DashboardActivityClickListener;
import com.stathis.elmepaunivapp.ui.dashboard.model.DashboardOption;
import com.stathis.elmepaunivapp.ui.department.Department;
import com.stathis.elmepaunivapp.ui.professors.ProfessorsActivity;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.announcements.Announcements;
import com.stathis.elmepaunivapp.ui.chatbot.ChatBotActivity;
import com.stathis.elmepaunivapp.ui.students.Students;

public class Dashboard extends AppCompatActivity implements View.OnClickListener, DashboardActivityClickListener {

    private RecyclerView dashboardOptions;
    private ImageView about;
    private FloatingActionButton chatbot_btn;
    private DashboardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        chatbot_btn = findViewById(R.id.fab_chatbot);
        about = findViewById(R.id.about);
        dashboardOptions = findViewById(R.id.dashboard_options_recview);

        dashboardOptions.setAdapter(viewModel.dashboardAdapter);
        viewModel.initListener(this);
        viewModel.displayData();

        about.setOnClickListener(this);
        chatbot_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_chatbot:
                startActivity(new Intent(Dashboard.this, ChatBotActivity.class));
                break;
            case R.id.about:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Dashboard.this);
        builder.setTitle(viewModel.getTitle());
        builder.setMessage(viewModel.getAboutText());
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

    @Override
    public void goToAnnouncementScreen(DashboardOption dashboardOption) {
        startActivity(new Intent(Dashboard.this, Announcements.class));
    }

    @Override
    public void goToDepartmentScreen(DashboardOption dashboardOption) {
        startActivity(new Intent(Dashboard.this, Department.class));
    }

    @Override
    public void goToProfessorScreen(DashboardOption dashboardOption) {
        startActivity(new Intent(Dashboard.this, ProfessorsActivity.class));
    }

    @Override
    public void goToStudentsScreen(DashboardOption dashboardOption) {
        startActivity(new Intent(Dashboard.this, Students.class));
    }
}