package com.stathis.elmepaunivapp.abstraction;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

abstract public class AbstractActivity extends AppCompatActivity {

    // app lifecycle simplification
    public abstract void initial();

    public abstract void running();

    public abstract void stopped();

    public AbstractActivity(int contentLayoutId) {
        super(contentLayoutId);
    }

    // app lifecycle primary stages
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initial();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        running();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopped();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
