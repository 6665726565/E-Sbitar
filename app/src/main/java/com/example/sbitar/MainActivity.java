package com.example.sbitar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private CardView prestataireCard , patientCard;

    public static SharedPreferences sharedPreferences;
    public static boolean isPatient;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        prestataireCard = findViewById(R.id.prestataire_card);
        patientCard = findViewById(R.id.patient_card);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isPatient = sharedPreferences.getBoolean("isPatient" , true);



        prestataireCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().putBoolean("isPatient",false).apply();
                sendUserToLoginActivity();

            }
        });

        patientCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().putBoolean("isPatient",true).apply();
                sendUserToLoginActivity();

            }
        });

        checkUserExistance();

    }

    private void checkUserExistance() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            sendUserToHomeActivity();
        }
    }

    private void sendUserToHomeActivity() {
        Intent homeActivity = new Intent(this , HomeActivity.class);
        startActivity(homeActivity);
        finish();
    }

    public void sendUserToLoginActivity(){
        Intent loginActivity = new Intent(this , LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }
}