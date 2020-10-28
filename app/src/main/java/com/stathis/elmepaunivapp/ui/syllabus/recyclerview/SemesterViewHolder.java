package com.stathis.elmepaunivapp.ui.syllabus.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.activity_listeners.SyllabusActivityListener;
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester;

public class SemesterViewHolder extends RecyclerView.ViewHolder {

    TextView semesterName;
    private SyllabusActivityListener listener;
    private Object data;

    public SemesterViewHolder(@NonNull View itemView, final SyllabusActivityListener listener) {
        super(itemView);
        semesterName = itemView.findViewById(R.id.semester_item_txt);
        this.listener = listener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.showLessons((Semester) data);
            }
        });
    }

    public void present(Semester data){
        this.data = data;
        semesterName.setText(data.getSemester());
    }
}
