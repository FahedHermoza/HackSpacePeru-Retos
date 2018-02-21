package com.fahed.developer.hackspaceperu_reto2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class EmailActivityFragment extends Fragment {

    private View viewRoot;
    private static final String EXTRA_ANSWER_EMAIL = "com.fahed.developer.hackspaceperu_reto2.email";
    private static final String EXTRA_ANSWER_NAME = "com.fahed.developer.hackspaceperu_reto2.name";
    private String answerEmail;
    private String answerName;
    private EditText editTextAfair;
    private EditText editTextMessage;
    private Button buttonSend;

    public static Intent newIntent(Context packageContext, String answerEmail,String answerName ){
        Intent intent = new Intent(packageContext, EmailActivity.class);
        intent.putExtra(EXTRA_ANSWER_EMAIL, answerEmail);
        intent.putExtra(EXTRA_ANSWER_NAME, answerName);
        return intent;
    }

    private void getAnswer(Intent result){
        answerEmail = result.getStringExtra(EXTRA_ANSWER_EMAIL);
        answerName = result.getStringExtra(EXTRA_ANSWER_NAME);
    }

    public EmailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getAnswer(getActivity().getIntent());
        viewRoot = inflater.inflate(R.layout.fragment_email, container, false);
        editTextAfair = (EditText) viewRoot.findViewById(R.id.editTextAfair);
        editTextMessage = (EditText) viewRoot.findViewById(R.id.editTextMessage);
        buttonSend = (Button) viewRoot.findViewById(R.id.buttonSend);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextAfair.getText().toString().trim().equals("") &&
                        !editTextMessage.getText().toString().trim().equals("") ) {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("message/rfc822");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, answerEmail);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, editTextAfair.getText().toString());
                    emailIntent.putExtra(Intent.EXTRA_TEXT, editTextMessage.getText().toString());
                    try {
                        startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Snackbar.make(viewRoot, R.string.error_intent_main, Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(viewRoot, R.string.error_message_main, Snackbar.LENGTH_LONG).show();
                }
            }
        });

        String messageUser = "Bienvenido " + answerName + " ingresa los campos para enviar un email";
        Toast.makeText(viewRoot.getContext(), messageUser.toString(), Toast.LENGTH_LONG).show();

        return viewRoot;
    }
}
