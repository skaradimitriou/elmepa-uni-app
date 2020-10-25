package com.stathis.elmepaunivapp.recyclerviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.UsefulLinkClickListener;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;

public class UsefulLinksAdapter extends ListAdapter<UsefulLinks,UsefulLinksViewHolder> {

    private UsefulLinkClickListener listener;

    public UsefulLinksAdapter(UsefulLinkClickListener listener) {
        super(new DiffItemCallbackClass<UsefulLinks>());
        this.listener = listener;
    }

    @NonNull
    @Override
    public UsefulLinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.useful_links_item_row, parent, false);
        return new UsefulLinksViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UsefulLinksViewHolder holder, int position) {
        holder.present(getItem(position));
    }
}
