package com.stathis.elmepaunivapp.ui.department.recyclerviews;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.DepMembersClickListener;
import com.stathis.elmepaunivapp.listeners.DepartmentCardClickListener;
import com.stathis.elmepaunivapp.listeners.FieldsOfStudyListener;
import com.stathis.elmepaunivapp.listeners.ProgrammesClickListener;
import com.stathis.elmepaunivapp.listeners.SocialClickListener;
import com.stathis.elmepaunivapp.ui.department.model.DepMembers;
import com.stathis.elmepaunivapp.ui.department.model.DepMembersParent;
import com.stathis.elmepaunivapp.ui.department.model.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.ui.department.model.EmptyModel;
import com.stathis.elmepaunivapp.ui.department.model.FieldsOfStudyParent;
import com.stathis.elmepaunivapp.ui.department.model.Programmes;
import com.stathis.elmepaunivapp.ui.department.model.ProgrammesParent;
import com.stathis.elmepaunivapp.ui.department.model.Research;
import com.stathis.elmepaunivapp.ui.department.model.SocialChannels;
import com.stathis.elmepaunivapp.ui.department.model.SocialChannelsParent;
import com.stathis.elmepaunivapp.ui.department.model.VirtualTour;

public class DepartmentViewHolder extends RecyclerView.ViewHolder implements FieldsOfStudyListener,
        SocialClickListener, DepMembersClickListener, ProgrammesClickListener {

    private FieldsAdapter adapter;
    private DepMembersAdapter depMembersAdapter;
    private ProgrammesAdapter programmesAdapter;
    private SocialChannelAdapter socialChannelAdapter;
    private RecyclerView recyclerView;
    private TextView headerTxt;
    private FieldsOfStudyListener fieldsOfStudyListener;
    private SocialClickListener socialClickListener;
    private DepMembersClickListener depMembersClickListener;
    private ProgrammesClickListener programmesClickListener;
    private DepartmentCardClickListener departmentCardClickListener;

    public DepartmentViewHolder(@NonNull View itemView) {
        super(itemView);
        recyclerView = itemView.findViewById(R.id.research_recycler);
        headerTxt = itemView.findViewById(R.id.research_item_header);
    }

    public void present(final Object object, FieldsOfStudyListener fieldsOfStudyListener, SocialClickListener socialClickListener,
                        DepMembersClickListener depMembersClickListener,
                        ProgrammesClickListener programmesClickListener, final DepartmentCardClickListener departmentCardClickListener) {
        this.fieldsOfStudyListener = fieldsOfStudyListener;
        this.socialClickListener = socialClickListener;
        this.depMembersClickListener = depMembersClickListener;
        this.programmesClickListener = programmesClickListener;
        this.departmentCardClickListener = departmentCardClickListener;

        if (object instanceof FieldsOfStudyParent) {
            headerTxt.setText(((FieldsOfStudyParent) object).getHeaderTxt());
            adapter = new FieldsAdapter(this);
            recyclerView.setAdapter(adapter);
            adapter.submitList(((FieldsOfStudyParent) object).getList());
        } else if (object instanceof DepMembersParent) {
            headerTxt.setText(((DepMembersParent) object).getHeader());
            depMembersAdapter = new DepMembersAdapter(this);
            recyclerView.setAdapter(depMembersAdapter);
            depMembersAdapter.submitList(((DepMembersParent) object).getList());
        } else if (object instanceof ProgrammesParent) {
            headerTxt.setText(((ProgrammesParent) object).getHeader());
            programmesAdapter = new ProgrammesAdapter(this);
            recyclerView.setAdapter(programmesAdapter);
            programmesAdapter.submitList(((ProgrammesParent) object).getList());
        } else if (object instanceof SocialChannelsParent) {
            headerTxt.setText(((SocialChannelsParent) object).getHeader());
            socialChannelAdapter = new SocialChannelAdapter(this);
            recyclerView.setAdapter(socialChannelAdapter);
            socialChannelAdapter.submitList(((SocialChannelsParent) object).getList());
        } else if (object instanceof VirtualTour) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    departmentCardClickListener.onVirtualTourClick((VirtualTour) object);
                }
            });
        } else if (object instanceof Research) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    departmentCardClickListener.onResearchClick((Research) object);
                }
            });
        } else if (object instanceof EmptyModel){

        }
    }

    @Override
    public void onFieldOfStudyClick(DeptFieldsOfStudy fieldsOfStudy) {
        fieldsOfStudyListener.onFieldOfStudyClick(fieldsOfStudy);
    }

    @Override
    public void onDepProfessorClick(DepMembers item) {
        depMembersClickListener.onDepProfessorClick(item);
    }

    @Override
    public void onProgrammesClick(Programmes programmes) {
        programmesClickListener.onProgrammesClick(programmes);
    }

    @Override
    public void onSocialItemClick(SocialChannels socialChannels) {
        socialClickListener.onSocialItemClick(socialChannels);
    }
}
