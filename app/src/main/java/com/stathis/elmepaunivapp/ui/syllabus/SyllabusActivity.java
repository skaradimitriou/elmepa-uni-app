package com.stathis.elmepaunivapp.ui.syllabus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.stathis.elmepaunivapp.R;

public class SyllabusActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SyllabusViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        viewModel = new ViewModelProvider(this).get(SyllabusViewModel.class);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        recyclerView = findViewById(R.id.syllabus_recycler);
        recyclerView.setAdapter(viewModel.adapter);
        viewModel.populateList();
    }
}
