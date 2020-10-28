package com.stathis.elmepaunivapp.ui.students.recycler;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.UsefulLinkClickListener;
import com.stathis.elmepaunivapp.recyclerviews.UsefulLinksAdapter;
import com.stathis.elmepaunivapp.ui.students.model.Schedule;
import com.stathis.elmepaunivapp.ui.students.model.StudentItem;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;

public class StudentsViewHolder extends RecyclerView.ViewHolder implements UsefulLinkClickListener {

    private RecyclerView recyclerView;
    private UsefulLinksAdapter adapter;
    private TextView textView;

    public StudentsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.research_item_header);
        recyclerView = itemView.findViewById(R.id.research_recycler);
    }

    void present(Object object){
        if (object instanceof StudentItem){
            textView.setText(((StudentItem) object).getHeaderTxt());
            adapter = new UsefulLinksAdapter(this);
            recyclerView.setAdapter(adapter);
            adapter.submitList(((StudentItem) object).getList());
        } else if (object instanceof Schedule){
            //inflates schedule
        }
    }

    @Override
    public void onUsefulLinksClick(UsefulLinks usefulLinks) {

    }
}
