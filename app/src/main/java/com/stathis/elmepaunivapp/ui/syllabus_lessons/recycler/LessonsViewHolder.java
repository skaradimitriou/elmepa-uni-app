package com.stathis.elmepaunivapp.ui.syllabus_lessons.recycler;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.LessonClickListener;
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson;

public class LessonsViewHolder extends RecyclerView.ViewHolder {

    private TextView lesson_name, mandatoryField, description;
    private LessonClickListener lessonClickListener;
    private Object data;

    public LessonsViewHolder(@NonNull final View itemView, LessonClickListener lessonsListener) {
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

        switch (data.getDirection()) {
            case "data":
                lesson_name.setBackgroundColor(itemView.getResources().getColor(R.color.lesson_green));
                break;
            case "mkt":
                lesson_name.setBackgroundColor(itemView.getResources().getColor(R.color.lesson_blue));
                break;
            case "ba":
                lesson_name.setBackgroundColor(itemView.getResources().getColor(R.color.dark_orange));
                break;
        }
    }

}
