package com.stathis.elmepaunivapp.ui.dashboard.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.DashboardOptionListener;
import com.stathis.elmepaunivapp.ui.dashboard.model.DashboardOption;

public class DashboardViewHolder extends RecyclerView.ViewHolder {

    private ImageView img;
    private TextView text;
    private DashboardOptionListener dashboardOptionListener;
    private Object data;

    public DashboardViewHolder(@NonNull View itemView, DashboardOptionListener listener) {
        super(itemView);
        img = itemView.findViewById(R.id.dashboard_option_img);
        text = itemView.findViewById(R.id.dashboard_option_text);
        dashboardOptionListener = listener;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardOptionListener.onDashboardOptionsClickListener((DashboardOption) data);
            }
        });
    }

    public void present(DashboardOption dashboardOption) {
        this.data = dashboardOption;
        img.setImageResource(dashboardOption.getDrawable());
        text.setText(dashboardOption.getTitle());
    }
}
