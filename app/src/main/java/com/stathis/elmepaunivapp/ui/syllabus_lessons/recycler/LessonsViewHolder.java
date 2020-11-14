package com.stathis.elmepaunivapp.ui.syllabus_lessons.recycler;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.LessonClickListener;
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson;
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.LessonHeader;

public class LessonsViewHolder extends RecyclerView.ViewHolder {

    private TextView lesson_name, mandatoryField, description,lessonsHeader;
    private Object data;

    public LessonsViewHolder(@NonNull final View itemView) {
        super(itemView);

        lesson_name = itemView.findViewById(R.id.lesson_name);
        mandatoryField = itemView.findViewById(R.id.mandatory_txt);
        description = itemView.findViewById(R.id.lesson_description);
        lessonsHeader = itemView.findViewById(R.id.syllabus_lessons_header);
    }

    public void present(Object data) {
        this.data = data;
        if (data instanceof Lesson) {
            lesson_name.setText(((Lesson) data).getName());
            mandatoryField.setText(((Lesson) data).getMandatory());
            description.setText(((Lesson) data).getDescription());

            switch (((Lesson) data).getDirection()) {
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
        } else if(data instanceof LessonHeader){
            lessonsHeader.setText(((LessonHeader) data).getTitle());
        }
    }

}
