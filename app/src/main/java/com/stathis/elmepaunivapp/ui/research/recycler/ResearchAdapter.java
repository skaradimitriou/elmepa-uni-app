package com.stathis.elmepaunivapp.ui.research.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.UsefulLinkClickListener;
import com.stathis.elmepaunivapp.ui.research.model.ResearchItem;

public class ResearchAdapter extends ListAdapter<ResearchItem, ResearchViewHolder> {

    private UsefulLinkClickListener listener;

    public ResearchAdapter(UsefulLinkClickListener listener) {
        super(new DiffItemCallbackClass<ResearchItem>());
        this.listener = listener;
    }

    @NonNull
    @Override
    public ResearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResearchViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_research_item_row, parent, false),listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ResearchViewHolder holder, int position) {
        holder.present(getItem(position));
    }
}
