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
import com.stathis.elmepaunivapp.abstraction.AbstractActivity;
import com.stathis.elmepaunivapp.listeners.activity_listeners.DashboardActivityClickListener;
import com.stathis.elmepaunivapp.ui.dashboard.model.DashboardOption;
import com.stathis.elmepaunivapp.ui.department.DepartmentActivity;
import com.stathis.elmepaunivapp.ui.professors.ProfessorsActivity;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.announcements.AnnouncementActivity;
import com.stathis.elmepaunivapp.ui.chatbot.ChatBotActivity;
import com.stathis.elmepaunivapp.ui.students.StudentsActivity;
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity;

public class DashboardActivity extends AbstractActivity implements View.OnClickListener, DashboardActivityClickListener {

    private RecyclerView dashboardOptions;
    private ImageView about;
    private FloatingActionButton chatbot_btn;
    private DashboardViewModel viewModel;

    public DashboardActivity() {
        super(R.layout.activity_dashboard);
    }

    @Override
    public void initial() {
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        chatbot_btn = findViewById(R.id.fab_chatbot);
        about = findViewById(R.id.about);
        dashboardOptions = findViewById(R.id.dashboard_options_recview);
    }

    @Override
    public void running() {
        dashboardOptions.setAdapter(viewModel.dashboardAdapter);
        viewModel.initListener(this);
        viewModel.displayData();

        about.setOnClickListener(this);
        chatbot_btn.setOnClickListener(this);
    }

    @Override
    public void stopped() {}

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_chatbot:
                startActivity(new Intent(DashboardActivity.this, ChatBotActivity.class));
                break;
            case R.id.about:
                viewModel.showDialog(this);
                break;
        }
    }

    @Override
    public void goToAnnouncementScreen(DashboardOption dashboardOption) {
        startActivity(new Intent(DashboardActivity.this, AnnouncementActivity.class));
    }

    @Override
    public void goToDepartmentScreen(DashboardOption dashboardOption) {
        startActivity(new Intent(DashboardActivity.this, DepartmentActivity.class));
    }

    @Override
    public void goToProfessorScreen(DashboardOption dashboardOption) {
        startActivity(new Intent(DashboardActivity.this, ProfessorsActivity.class));
    }

    @Override
    public void goToStudentsScreen(DashboardOption dashboardOption) {
        startActivity(new Intent(DashboardActivity.this, StudentsActivity.class));
    }

    @Override
    public void learnMore() {
        startActivity(new Intent(DashboardActivity.this, WebviewActivity.class).putExtra("URL", "https://mst.hmu.gr/ypiresies/mobile-epharmogh-tmhmatos/"));
    }
}