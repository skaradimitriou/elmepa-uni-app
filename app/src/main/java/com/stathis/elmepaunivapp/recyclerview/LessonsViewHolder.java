package com.stathis.elmepaunivapp.recyclerview;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.Lesson;

public class LessonsViewHolder extends RecyclerView.ViewHolder {

    private TextView lesson_name, mandatoryField, description;
    private LessonClickListener lessonClickListener;
    private Object data;

    public LessonsViewHolder(@NonNull View itemView, LessonClickListener lessonsListener) {
        super(itemView);

        lesson_name = itemView.findViewById(R.id.lesson_name);
        mandatoryField = itemView.findViewById(R.id.mandatory_txt);
        description = itemView.findViewById(R.id.lesson_description);

        lessonClickListener = lessonsListener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessonClickListener.onLessonClick((Lesson) data);
            }
        });
    }

    public void present(Lesson data) {
        this.data = data;
        lesson_name.setText(data.getName());
        mandatoryField.setText(data.getMandatory());
        description.setText(data.getDescription());

        if (data.getDirection().equals("data")){
            lesson_name.setBackgroundColor(Color.parseColor("#2a8c3f"));
        } else if(data.getDirection().equals("mkt")){
            lesson_name.setBackgroundColor(Color.parseColor("#417ba3"));
        } else if (data.getDirection().equals("ba")) {
            lesson_name.setBackgroundColor(Color.parseColor("#f07721"));
        }
    }

}
