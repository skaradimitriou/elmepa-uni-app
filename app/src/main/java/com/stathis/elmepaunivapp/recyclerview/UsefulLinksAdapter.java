package com.stathis.elmepaunivapp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.ItemClickListener;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;

import java.util.List;

public class UsefulLinksAdapter extends RecyclerView.Adapter<UsefulLinksViewHolder> {

    private List<UsefulLinks> usefulLinks;
    private ItemClickListener listener;

    public UsefulLinksAdapter(List<UsefulLinks> usefulLinks, ItemClickListener listener) {
        this.usefulLinks = usefulLinks;
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
        holder.present(usefulLinks.get(position));
    }

    @Override
    public int getItemCount() {
        return usefulLinks.size();
    }
}
