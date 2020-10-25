package com.stathis.elmepaunivapp.ui.research.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.UsefulLinkClickListener;
import com.stathis.elmepaunivapp.listeners.activity_listeners.ResearchActivityClickListener;
import com.stathis.elmepaunivapp.recyclerviews.UsefulLinksAdapter;
import com.stathis.elmepaunivapp.ui.research.model.ResearchItem;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;

public class ResearchViewHolder extends RecyclerView.ViewHolder implements UsefulLinkClickListener {

    private TextView categoryName;
    private RecyclerView researchRecycler;
    private Object data;
    private UsefulLinksAdapter researchAdapter;
    private UsefulLinkClickListener listener;
    private ResearchActivityClickListener researchListener;

    public ResearchViewHolder(@NonNull View itemView,UsefulLinkClickListener listener) {
        super(itemView);
        this.listener = listener;
        categoryName = itemView.findViewById(R.id.research_item_header);
        researchRecycler = itemView.findViewById(R.id.research_recycler);
    }

    public void present(ResearchItem researchItem){
        this.data = researchItem;
        categoryName.setText(researchItem.getCategoryName());
        researchAdapter = new UsefulLinksAdapter(this);
        researchAdapter.submitList(researchItem.getResearchItems());
        researchRecycler.setAdapter(researchAdapter);
    }

    @Override
    public void onUsefulLinksClick(UsefulLinks usefulLinks) {
       listener.onUsefulLinksClick(usefulLinks);
    }
}
