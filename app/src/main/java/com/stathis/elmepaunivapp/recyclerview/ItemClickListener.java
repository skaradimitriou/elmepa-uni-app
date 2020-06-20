package com.stathis.elmepaunivapp.recyclerview;


import android.view.View;

import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;

public interface ItemClickListener extends View.OnClickListener {

    public void onItemClick(DeptFieldsOfStudy item);

}
