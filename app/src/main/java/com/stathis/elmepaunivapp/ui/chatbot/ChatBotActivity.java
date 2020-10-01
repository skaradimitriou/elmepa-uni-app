package com.stathis.elmepaunivapp.ui.chatbot;

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

import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.stathis.elmepaunivapp.listeners.ItemClickListener;
import com.stathis.elmepaunivapp.ui.chatbot.model.Answer;
import com.stathis.elmepaunivapp.ui.chatbot.model.Question;
import com.stathis.elmepaunivapp.ui.professors.Professors;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.chatbot.model.Message;
import com.stathis.elmepaunivapp.recyclerviews.ChatBotAdapter;
import com.stathis.elmepaunivapp.listeners.ChatBotListener;
import com.stathis.elmepaunivapp.ui.announcements.Announcements;
import com.stathis.elmepaunivapp.ui.students.Students;
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity;

import java.util.ArrayList;

import static android.Manifest.permission.CALL_PHONE;

public class ChatBotActivity extends AppCompatActivity {

    private RecyclerView userMessagesRecView;
    private ChatBotAdapter chatBotAdapter;
    private TextInputEditText user_text_field;
    String response, responseLowercase, answer;
    private ArrayList<Object> messagesList = new ArrayList<>();
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
                    responseLowercase = response.toLowerCase();
                    Log.d("TEST", responseLowercase);
                    user_text_field.getText().clear();
                    hideKeyboard(v);
                    Log.d("RESPONSE", response);
                    switch (responseLowercase) {
                        case "γεια":
                            answer = "Γεία σου και εσένα!";
                            break;
                        case "γειά":
                            answer = "Γεία σου και εσένα!";
                            break;
                        case "ευχαριστώ":
                            answer = "Παρακαλώ!";
                            break;
                        case "ευχαριστω":
                            answer = "Παρακαλώ!";
                            break;
                        case "προγραμμα σπουδων":
                            answer = "Κάνε tap για να δείς \n το πρόγραμμα σπουδών!";
                            break;
                        case "πρόγραμμα σπουδών":
                            answer = "Κάνε tap για να δείς \n το πρόγραμμα σπουδών!";
                            break;
                        case "ωρολογιο προγραμμα":
                            answer = "Κάνε tap για να δείς το \n πρόγραμμα των μαθημάτων!";
                            break;
                        case "ωρολόγιο πρόγραμμα":
                            answer = "Κάνε tap για να δείς το \n πρόγραμμα των μαθημάτων!";
                            break;
                        case "τηλέφωνο γραμματείας":
                            answer = "Κάνε tap για να καλέσω \n την Γραμματεία του Τμήματος!";
                            break;
                        case "τηλεφωνο γραμματειας":
                            answer = "Κάνε tap για να καλέσω \n την Γραμματεία του Τμήματος!";
                            break;
                        case "τηλεφωνο γραμματειασ":
                            answer = "Κάνε tap για να καλέσω \n την Γραμματεία του Τμήματος!";
                            break;
                        case "email γραμματειας":
                            answer = "Κάνε tap για να στείλεις \n e-mail στην Γραμματεία!";
                            break;
                        case "email γραμματείας":
                            answer = "Κάνε tap για να στείλεις \n e-mail στην Γραμματεία!";
                            break;
                        case "email γραμματειασ":
                            answer = "Κάνε tap για να στείλεις \n e-mail στην Γραμματεία!";
                            break;
                        case "καθηγητές":
                            answer = "Κάνε tap για να αναζητήσεις \n έναν καθηγητή του Τμήματος!";
                            break;
                        case "προσωπικό":
                            answer = "Κάνε tap για να αναζητήσεις \n έναν καθηγητή του Τμήματος!";
                            break;
                        case "καθηγητες":
                            answer = "Κάνε tap για να αναζητήσεις \n έναν καθηγητή του Τμήματος!";
                            break;
                        case "προσωπικο":
                            answer = "Κάνε tap για να αναζητήσεις \n έναν καθηγητή του Τμήματος!";
                            break;
                        case "εικονική περιήγηση":
                            answer = "Κάνε tap για να δείς \n την εικονική περιήγηση!";
                            break;
                        case "εικονικη περιηγηση":
                            answer = "Κάνε tap για να δείς \n την εικονική περιήγηση!";
                            break;
                        case "ανακοινώσεις":
                            answer = "Κάνε tap για να δεις \n τις τελευταίες ανακοινώσεις!";
                            break;
                        case "ανακοινωσεις":
                            answer = "Κάνε tap για να δεις \n τις τελευταίες ανακοινώσεις!";
                            break;
                        default:
                            answer = "Δεν γνωρίζω την απάντηση ακόμα";
                            break;
                    }
                    messagesList.add(new Question(response));
                    messagesList.add(new Answer(answer));
                    chatBotAdapter.notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });

        userMessagesRecView = findViewById(R.id.user_messagesRecView);
        chatBotAdapter = new ChatBotAdapter(new ItemClickListener() {
            @Override
            public void onMessageClick(Object message) {
                //
            }

            @Override
            public void onAnswerClick(Answer answer) {
//
            }
        });
        chatBotAdapter.submitList(messagesList);
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
