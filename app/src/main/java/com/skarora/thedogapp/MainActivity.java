package com.skarora.thedogapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    //Views and Widgets
    Button mCreateAccount;
    Button mGoButton;
    EditText mEmailField;
    EditText mPasswordField;

    //String Field
    String userEmailString , userPassString;

    //Firebase Authentication
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign ID
        mCreateAccount = (Button) findViewById(R.id.button_CreateAccount);
        mGoButton= (Button) findViewById(R.id.button_go);
        mEmailField= (EditText) findViewById(R.id.editText_EmailField);
        mPasswordField = (EditText) findViewById(R.id.editText_PasswordField);

        //Assign Instances
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null)
                {

                }else
                {
                    startActivity(new Intent(MainActivity.this,Menu.class));
                }
            }
        };

        //Go to Login
          mGoButton.setOnClickListener(new View.OnClickListener() {


              @Override
              public void onClick(View view) {
                  //Perform login operation
                  userEmailString = mEmailField.getText().toString().trim();
                  userPassString = mPasswordField.getText().toString().trim();

                  if(!TextUtils.isEmpty(userEmailString) && !TextUtils.isEmpty(userPassString)) {
                      // checking if the fields exists, then only user can create account

                      mAuth.signInWithEmailAndPassword(userEmailString,userPassString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {

                              if(task.isSuccessful())
                              {
                                  startActivity(new Intent(MainActivity.this,Menu.class));
                              }
                              else
                              {
                                  Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();

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