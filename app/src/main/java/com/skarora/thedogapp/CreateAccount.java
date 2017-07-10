package com.skarora.thedogapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccount extends AppCompatActivity {

    //views and widgets
     Button mCreateButton;
     EditText mUserEmailEdit, mUserPasswordEdit;

    //string Fields
    String userEmailString, userPasswordString;

    //Firebase Authentication
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //Assign ID

        mCreateButton = (Button) findViewById(R.id.button_CreateButton_create);
        mUserEmailEdit = (EditText) findViewById(R.id.editText_EmailField_create);
        mUserPasswordEdit = (EditText) findViewById(R.id.editText_PassField_Create);



        //Assign Instances
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {

                } else {
                    startActivity(new Intent(CreateAccount.this, Menu.class));
                }
            }
        };

        //On Click Listener
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmailString = mUserEmailEdit.getText().toString().trim();
                userPasswordString = mUserPasswordEdit.getText().toString().trim();

                if (!TextUtils.isEmpty(userEmailString) && !TextUtils.isEmpty(userPasswordString)) // checking if the fields exists, then only user can create account
                {
                    mAuth.createUserWithEmailAndPassword(userEmailString, userPasswordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) { //if acnt created it will be there in this task

                            if (task.isSuccessful())    //if account is created
                            {
                                Toast.makeText(CreateAccount.this, "User Account Created", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(CreateAccount.this, Menu.class));
                            } else {
                                Toast.makeText(CreateAccount.this, " Failed to Create User Account", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mAuth.removeAuthStateListener(mAuthListener);
    }
}
