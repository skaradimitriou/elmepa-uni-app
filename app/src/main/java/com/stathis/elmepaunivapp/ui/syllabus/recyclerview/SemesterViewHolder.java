package com.stathis.elmepaunivapp.ui.syllabus.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester;

public class SemesterViewHolder extends RecyclerView.ViewHolder {

    TextView semesterName;

    public SemesterViewHolder(@NonNull View itemView) {
        super(itemView);
        semesterName = itemView.findViewById(R.id.semester_item_txt);
    }

    public void present(Semester data){
        semesterName.setText(data.getSemester());
    }
}
