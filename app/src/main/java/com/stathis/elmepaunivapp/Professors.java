package com.stathis.elmepaunivapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.BaseAdapter;

import com.stathis.elmepaunivapp.recyclerview.RecAdapter;

import java.util.ArrayList;
import java.util.List;

public class Professors extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professors);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        List<String> array = new ArrayList<>();
        array.add("V");
        array.add("A");
        array.add("S");
        array.add("T");
        array.add("I");
        array.add("S");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new RecAdapter(array));
    }
}
