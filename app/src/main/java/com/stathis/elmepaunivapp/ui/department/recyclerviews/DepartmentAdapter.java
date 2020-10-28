package com.stathis.elmepaunivapp.ui.department.recyclerviews;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.DepMembersClickListener;
import com.stathis.elmepaunivapp.listeners.DepartmentCardClickListener;
import com.stathis.elmepaunivapp.listeners.FieldsOfStudyListener;
import com.stathis.elmepaunivapp.listeners.ProgrammesClickListener;
import com.stathis.elmepaunivapp.listeners.SocialClickListener;
import com.stathis.elmepaunivapp.ui.department.model.Research;
import com.stathis.elmepaunivapp.ui.department.model.VirtualTour;

public class DepartmentAdapter extends ListAdapter<Object, DepartmentViewHolder> {

    FieldsOfStudyListener fieldsOfStudyListener;
    SocialClickListener socialClickListener;
    DepMembersClickListener depMembersClickListener;
    ProgrammesClickListener programmesClickListener;
    DepartmentCardClickListener departmentCardClickListener;

    public DepartmentAdapter(FieldsOfStudyListener fieldsOfStudyListener,SocialClickListener socialClickListener,
                             DepMembersClickListener depMembersClickListener,
                             ProgrammesClickListener programmesClickListener,DepartmentCardClickListener departmentCardClickListener) {
        super(new DiffItemCallbackClass<Object>());
        this.fieldsOfStudyListener = fieldsOfStudyListener;
        this.socialClickListener = socialClickListener;
        this.depMembersClickListener = depMembersClickListener;
        this.programmesClickListener = programmesClickListener;
        this.departmentCardClickListener = departmentCardClickListener;
    }

    @NonNull
    @Override
    public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DepartmentViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentViewHolder holder, int position) {
        holder.present(getItem(position),fieldsOfStudyListener,socialClickListener,depMembersClickListener,programmesClickListener,departmentCardClickListener);
    }

    @Override
    public int getItemViewType(int position) {
        if(getItem(position) instanceof Research){
            return R.layout.holder_department_research_item;
        } else if (getItem(position) instanceof VirtualTour){
            return R.layout.holder_virtual_tour;
        } else {
            return R.layout.holder_research_item_row;
        }
    }
}
