package com.stathis.elmepaunivapp.recyclerview;


import android.view.View;

import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.models.Programmes;

public interface ItemClickListener extends View.OnClickListener {
    public void onItemClick(DeptFieldsOfStudy item);
    public void onProgrammesClick(Programmes programmes);
}
