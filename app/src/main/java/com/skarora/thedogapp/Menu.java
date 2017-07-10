package com.skarora.thedogapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {

    //views and widgets
    Button mgetPuppy;
    Button mfindVet;

    //Firebase Authentication
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Assign ID

        mgetPuppy = (Button) findViewById(R.id.button_getPuppy);
        mfindVet = (Button) findViewById(R.id.button_FindVet);


    }
}
