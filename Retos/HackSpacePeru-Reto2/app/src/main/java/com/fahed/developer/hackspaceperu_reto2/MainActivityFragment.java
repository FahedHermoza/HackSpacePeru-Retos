package com.fahed.developer.hackspaceperu_reto2;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private View viewRoot;
    private EditText editTextEmail;
    private EditText editTextName;
    private Button buttonSend;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_main, container, false);
        editTextEmail = (EditText) viewRoot.findViewById(R.id.editTextEmail);
        editTextName = (EditText) viewRoot.findViewById(R.id.editTextName);
        buttonSend = (Button) viewRoot.findViewById(R.id.buttonNext);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextEmail.getText().toString().trim().equals("") &&
                        !editTextName.getText().toString().trim().equals("") ){

                    Intent intent = EmailActivityFragment.newIntent(getContext(),
                            editTextEmail.getText().toString(),
                            editTextName.getText().toString());
                    getContext().startActivity(intent);

                }else{
                    Snackbar.make(viewRoot, R.string.error_message_main, Snackbar.LENGTH_LONG).show();
                }
            }
        });

        return viewRoot;
    }
}
