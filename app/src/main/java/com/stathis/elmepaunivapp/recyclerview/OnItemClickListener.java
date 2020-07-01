package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;

import com.stathis.elmepaunivapp.models.DepMembers;

public interface OnItemClickListener extends View.OnClickListener {

    void onDepProfessorClick(DepMembers item);

}
