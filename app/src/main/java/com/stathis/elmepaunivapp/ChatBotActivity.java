package com.stathis.elmepaunivapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.stathis.elmepaunivapp.models.Message;
import com.stathis.elmepaunivapp.recyclerview.ChatBotAdapter;
import com.stathis.elmepaunivapp.recyclerview.ChatBotListener;

import java.util.ArrayList;

import static android.Manifest.permission.CALL_PHONE;

public class ChatBotActivity extends AppCompatActivity {

    private RecyclerView userMessagesRecView;
    private ChatBotAdapter chatBotAdapter;
    private TextInputEditText user_text_field;
    String response, responseUppercase, answer;
    private ArrayList<Message> messagesList = new ArrayList<>();
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        user_text_field = findViewById(R.id.ask_questions);
        user_text_field.setOnEditorActionListener(new TextInputEditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    response = user_text_field.getText().toString();
                    responseUppercase = response.toUpperCase();
                    Log.d("TEST", responseUppercase);
                    user_text_field.getText().clear();
                    hideKeyboard(v);
                    Log.d("RESPONSE", response);
                    switch (responseUppercase) {
                        case "ΓΕΙΑ":
                            answer = "Γεία σου και εσένα!";
                            break;
                        case "ΕΥΧΑΡΙΣΤΩ":
                            answer = "Παρακαλώ!";
                            break;
                        case "ΠΡΟΓΡΑΜΜΑ ΣΠΟΥΔΩΝ":
                            answer = "Κάνε tap για να δείς \n το πρόγραμμα σπουδών!";
                            break;
                        case "ΠΡΟΓΡΑΜΜΑ ΜΑΘΗΜΑΤΩΝ":
                            answer = "Κάνε tap για να δείς το \n πρόγραμμα των μαθημάτων!";
                            break;
                        case "ΤΗΛΕΦΩΝΟ ΓΡΑΜΜΑΤΕΙΑΣ":
                            answer = "Κάνε tap για να καλέσω \n την Γραμματεία του Τμήματος!";
                            break;
                        case "EMAIL ΓΡΑΜΜΑΤΕΙΑΣ":
                            answer = "Κάνε tap για να στείλεις \n e-mail στην Γραμματεία!";
                            break;
                        case "ΚΑΘΗΓΗΤΕΣ":
                            answer = "Κάνε tap για να αναζητήσεις \n έναν καθηγητή του Τμήματος!";
                            break;
                        case "ΕΙΚΟΝΙΚΗ ΠΕΡΙΗΓΗΣΗ":
                            answer = "Κάνε tap για να δείς \n την εικονική περιήγηση!";
                            break;
                        case "ΑΝΑΚΟΙΝΩΣΕΙΣ":
                            answer = "Κάνε tap για να δεις \n τις τελευταίες ανακοινώσεις!";
                            break;
                        default:
                            answer = "Δεν γνωρίζω την απάντηση ακόμα";
                            break;
                    }
                    messagesList.add(new Message(response, answer));
                    chatBotAdapter.notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });

        userMessagesRecView = findViewById(R.id.user_messagesRecView);
        chatBotAdapter = new ChatBotAdapter(messagesList, new ChatBotListener() {
            @Override
            public void onChatReply(Message message) {

                switch (message.getQuestion().toUpperCase()) {
                    case "ΠΡΟΓΡΑΜΜΑ ΣΠΟΥΔΩΝ":
                        Intent syllabus = new Intent(ChatBotActivity.this, Students.class);
                        startActivity(syllabus);
                        break;
                    case "ΠΡΟΓΡΑΜΜΑ ΜΑΘΗΜΑΤΩΝ":
                        String scheduleUrl = "https://mst.hmu.gr/proptyxiako/%cf%89%cf%81%ce%bf%ce%bb%cf%8c%ce%b3%ce%b9%ce%bf-%cf%80%cf%81%cf%8c%ce%b3%cf%81%ce%b1%ce%bc%ce%bc%ce%b1-%ce%bc%ce%b1%ce%b8%ce%b7%ce%bc%ce%ac%cf%84%cf%89%ce%bd/";
                        Intent schedule = new Intent(Intent.ACTION_VIEW, Uri.parse(scheduleUrl));
                        startActivity(schedule);
                        break;
                    case "ΤΗΛΕΦΩΝΟ ΓΡΑΜΜΑΤΕΙΑΣ":
                        callAtSecretaryOffice();
                        break;
                    case "EMAIL ΓΡΑΜΜΑΤΕΙΑΣ":
                        sendAnEmailToSecretaryOffice();
                        break;
                    case "ΚΑΘΗΓΗΤΕΣ":
                        Intent professors = new Intent(ChatBotActivity.this, Professors.class);
                        startActivity(professors);
                        break;
                    case "ΕΙΚΟΝΙΚΗ ΠΕΡΙΗΓΗΣΗ":
                        String openUrl = "https://mst.hmu.gr/hmutour/";
                        Intent VirtualTour = new Intent(ChatBotActivity.this, VirtualTour.class);
                        VirtualTour.putExtra("VirtualTourUrl", openUrl);
                        startActivity(VirtualTour);
                        break;
                    case "ΑΝΑΚΟΙΝΩΣΕΙΣ":
                        Intent announcements = new Intent(ChatBotActivity.this, Announcements.class);
                        startActivity(announcements);
                        break;
                }
            }

            @Override
            public void onClick(View v) {
                //do nothing
            }
        });
        userMessagesRecView.setAdapter(chatBotAdapter);
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
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"kalarhaki@hmu.gr"});
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
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
            String phone = "tel:2841091103";
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(phone));
            startActivity(callIntent);
        }
    }
}
