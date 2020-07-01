package com.stathis.elmepaunivapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfessorProfile extends AppCompatActivity {

    private CircleImageView prof_img;
    private TextView prof_name, prof_desc, prof_title, prof_skillOne, prof_skillTwo, prof_skillThree;
    private ImageView profLinkedin, profRG, profScholar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_profile);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Intent getProfData = getIntent();
        String name = getProfData.getStringExtra("PROFESSOR_NAME");
        String title = getProfData.getStringExtra("PROFESSOR_TITLE");
        Drawable img = getResources().getDrawable(getProfData.getIntExtra("PROFESSOR_IMG", -1));
        String description = getProfData.getStringExtra("PROFESSOR_DESC");
        final String linkedin = getProfData.getStringExtra("PROFESSOR_LINKEDIN");
        final String researchGate = getProfData.getStringExtra("PROFESSOR_RG");
        final String scholar = getProfData.getStringExtra("PROFESSOR_SCHOLAR");
        String skill_one = getProfData.getStringExtra("PROFESSOR_SKILL_ONE");
        String skill_two = getProfData.getStringExtra("PROFESSOR_SKILL_TWO");
        String skill_three = getProfData.getStringExtra("PROFESSOR_SKILL_THREE");

        prof_img = findViewById(R.id.prof_prof_img);
        prof_name = findViewById(R.id.professor_prof_name);
        prof_desc = findViewById(R.id.prof_txt_desc);
        prof_title = findViewById(R.id.professor_title);
        prof_skillOne = findViewById(R.id.skill_one);
        prof_skillTwo = findViewById(R.id.skill_two);
        prof_skillThree = findViewById(R.id.skill_three);
        profLinkedin = findViewById(R.id.linkedin);
        profRG = findViewById(R.id.researchGate);
        profScholar = findViewById(R.id.scholar);

        prof_img.setImageDrawable(img);
        prof_name.setText(name);
        prof_title.setText(title);
        prof_skillOne.setText(skill_one);
        prof_skillTwo.setText(skill_two);
        prof_skillThree.setText(skill_three);
        prof_desc.setText(description);

        profLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLinkedin = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedin));
                startActivity(gotoLinkedin);
            }
        });

        profRG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRG = new Intent(Intent.ACTION_VIEW, Uri.parse(researchGate));
                startActivity(goToRG);
            }
        });

        profScholar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGscholar = new Intent(Intent.ACTION_VIEW, Uri.parse(scholar));
                startActivity(goToGscholar);
            }
        });

    }

}
