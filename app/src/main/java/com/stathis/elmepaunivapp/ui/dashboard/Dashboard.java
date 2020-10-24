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
import com.stathis.elmepaunivapp.listeners.DashboardOptionListener;
import com.stathis.elmepaunivapp.ui.dashboard.model.DashboardOption;
import com.stathis.elmepaunivapp.ui.dashboard.recyclerview.DashboardAdapter;
import com.stathis.elmepaunivapp.ui.department.Department;
import com.stathis.elmepaunivapp.ui.professors.ProfessorsActivity;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.announcements.Announcements;
import com.stathis.elmepaunivapp.ui.chatbot.ChatBotActivity;
import com.stathis.elmepaunivapp.ui.students.Students;

public class Dashboard extends AppCompatActivity implements View.OnClickListener,DashboardOptionListener {

    private RecyclerView dashboardOptions;
    private DashboardAdapter dashboardAdapter;
    private ImageView about;
    private FloatingActionButton chatbot_btn;
    private DashboardViewModel dashboardViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        chatbot_btn = findViewById(R.id.fab_chatbot);
        about = findViewById(R.id.about);

        dashboardOptions = findViewById(R.id.dashboard_options_recview);
        dashboardAdapter = new DashboardAdapter(this);
        dashboardAdapter.submitList(dashboardViewModel.getDashboardOptions());
        dashboardOptions.setAdapter(dashboardAdapter);

        about.setOnClickListener(this);
        chatbot_btn.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
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

    @Override
    public void onDashboardOptionsClickListener(DashboardOption dashboardOption) {
        switch(dashboardOption.getDrawable()){
            case R.drawable.ic_announcement:
                startActivity(new Intent(Dashboard.this, Announcements.class));
                break;
            case R.drawable.ic_books:
                startActivity(new Intent(Dashboard.this, Department.class));
                break;
            case R.drawable.ic_student:
                startActivity(new Intent(Dashboard.this, Students.class));
                break;
            case R.drawable.ic_teacher:
                startActivity(new Intent(Dashboard.this, ProfessorsActivity.class));
                break;
        }
    }

    private void showDialog(){
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
}