package com.stathis.elmepaunivapp.ui.chatbot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.stathis.elmepaunivapp.listeners.activity_listeners.ChatBotListener;
import com.stathis.elmepaunivapp.ui.announcements.AnnouncementActivity;
import com.stathis.elmepaunivapp.ui.chatbot.model.Answer;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusActivity;
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity;

import static android.Manifest.permission.CALL_PHONE;

public class ChatBotActivity extends AppCompatActivity implements ChatBotListener {

    private RecyclerView userMessagesRecView;
    private TextInputEditText user_text_field;
    private String response;
    private static final int REQUEST_CALL = 1;
    private ChatbotViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
        viewModel = new ViewModelProvider(this).get(ChatbotViewModel.class);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        user_text_field = findViewById(R.id.ask_questions);
        userMessagesRecView = findViewById(R.id.user_messagesRecView);

        //getting messages from users
        user_text_field.setOnEditorActionListener(new TextInputEditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    response = user_text_field.getText().toString().toLowerCase();
                    Log.d("RESPONSE", response);
                    user_text_field.getText().clear();
                    hideKeyboard(v);

                    viewModel.getResponse(response);

                    //this line of code scrolls to the last item of the list that is passed to the adapter
                    // it creates an illusion of the chat interactiveness
                    if (viewModel.chatBotAdapter.getCurrentList().size() > 1) {
                        userMessagesRecView.smoothScrollToPosition(userMessagesRecView.getBottom());
                        viewModel.chatBotAdapter.notifyDataSetChanged();
                    }

                    return true;
                }
                return false;
            }
        });

        userMessagesRecView.setAdapter(viewModel.chatBotAdapter);
        viewModel.setUpListener(this);
        viewModel.initAdapter();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void sendAnEmailToSecretaryOffice() {
        try {
            startActivity(Intent.createChooser(new Intent(Intent.ACTION_SEND).setType("message/rfc822").putExtra(Intent.EXTRA_EMAIL, new String[]{"kalarhaki@hmu.gr"}), "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ChatBotActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callAtSecretaryOffice();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void callAtSecretaryOffice() {
        if (ContextCompat.checkSelfPermission(ChatBotActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ChatBotActivity.this, new String[]{CALL_PHONE}, REQUEST_CALL);
        } else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:2841091103")));
        }
    }

    @Override
    public void goToSyllabus(Answer answer) {
        startActivity(new Intent(this, SyllabusActivity.class));
    }

    @Override
    public void doNothing(Answer answer) {
        //
    }

    @Override
    public void openSchedule(Answer answer) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://mst.hmu.gr/proptyxiako/%cf%89%cf%81%ce%bf%ce%bb%cf%8c%ce%b3%ce%b9%ce%bf-%cf%80%cf%81%cf%8c%ce%b3%cf%81%ce%b1%ce%bc%ce%bc%ce%b1-%ce%bc%ce%b1%ce%b8%ce%b7%ce%bc%ce%ac%cf%84%cf%89%ce%bd/")));
    }

    @Override
    public void callSecretary(Answer answer) {
        callAtSecretaryOffice();
    }

    @Override
    public void emailToSecretary(Answer answer) {
        sendAnEmailToSecretaryOffice();
    }

    @Override
    public void virtualTour(Answer answer) {
        startActivity(new Intent(this, WebviewActivity.class).putExtra(
                "URL", "https://mst.hmu.gr/hmutour/"
        ));
    }

    @Override
    public void openAnnouncements(Answer answer) {
        startActivity(new Intent(this, AnnouncementActivity.class));
    }
}