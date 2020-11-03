package com.stathis.elmepaunivapp.ui.department.recyclerviews;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.DepMembersClickListener;
import com.stathis.elmepaunivapp.listeners.DepartmentCardClickListener;
import com.stathis.elmepaunivapp.listeners.FieldsOfStudyListener;
import com.stathis.elmepaunivapp.listeners.ProgrammesClickListener;
import com.stathis.elmepaunivapp.listeners.SocialClickListener;
import com.stathis.elmepaunivapp.ui.department.model.EmptyModel;
import com.stathis.elmepaunivapp.ui.department.model.Research;
import com.stathis.elmepaunivapp.ui.department.model.VirtualTour;

public class DepartmentAdapter extends ListAdapter<Object, RecyclerView.ViewHolder> {

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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == R.layout.holder_empty_view){
            return new EmptyViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(viewType, parent, false));
        } else {
            return new DepartmentViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(viewType, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DepartmentViewHolder) {
            ((DepartmentViewHolder) holder).present(getItem(position),fieldsOfStudyListener,socialClickListener,depMembersClickListener,programmesClickListener,departmentCardClickListener);
        } else {
            ((EmptyViewHolder) holder).present(getItem(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(getItem(position) instanceof Research){
            return R.layout.holder_department_research_item;
        } else if (getItem(position) instanceof VirtualTour){
            return R.layout.holder_virtual_tour;
        } else if (getItem(position) instanceof EmptyModel){
            return R.layout.holder_empty_view;
        } else {
            return R.layout.holder_research_item_row;
        }
    }
}
