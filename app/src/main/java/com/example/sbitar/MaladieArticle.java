package com.example.sbitar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MaladieArticle extends AppCompatActivity {
    private String title , description , symptomes , key;
    private EditText showTitle , showDescription , showSymptomes;
    private Button modifyBtn ,  deleteBtn ;
    private DatabaseReference  maladieRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maladie_article);

        maladieRef = FirebaseDatabase.getInstance().getReference().child("Maladie");

        showTitle = findViewById(R.id.show_title);
        showDescription = findViewById(R.id.show_description);
        showSymptomes = findViewById(R.id.show_symptomes);

        deleteBtn = findViewById(R.id.delete_btn);
        modifyBtn = findViewById(R.id.modifier_btn);

        Intent intent = getIntent();
        title =  intent.getStringExtra("title");
        description =  intent.getStringExtra("description");
        symptomes = intent.getStringExtra("symptomes");
        key = intent.getStringExtra("key");


        showTitle.setText(title);
        showDescription.setText(description);
        showSymptomes.setText(symptomes);

        MainActivity.isPatient = MainActivity.sharedPreferences.getBoolean("isPatient" , true);

        if (MainActivity.isPatient){
            showTitle.setEnabled(false);
            showTitle.setFocusable(false);

            showDescription.setEnabled(false);
            showDescription.setFocusable(false);

            showSymptomes.setEnabled(false);
            showSymptomes.setFocusable(false);

            modifyBtn.setVisibility(View.GONE);
            deleteBtn.setVisibility(View.GONE);
        }else {
            showTitle.setEnabled(true);
            showTitle.setFocusable(true);

            showDescription.setEnabled(true);
            showDescription.setFocusable(true);

            showSymptomes.setEnabled(true);
            showSymptomes.setFocusable(true);

            modifyBtn.setVisibility(View.VISIBLE);
            deleteBtn.setVisibility(View.VISIBLE);
        }


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maladieRef.child(key).removeValue();
                sendUserToHomeActivity();
            }
        });


        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyContent();
            }
        });



    }

    private void modifyContent() {
        title = showTitle.getText().toString();
        description = showDescription.getText().toString();
        symptomes = showSymptomes.getText().toString();

        HashMap hashMap = new HashMap();
        hashMap.put("title" , title);
        hashMap.put("description" , description);
        hashMap.put("symptomes" , symptomes);

        maladieRef.child(key).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){
                    Toast.makeText(MaladieArticle.this, "information saved !", Toast.LENGTH_SHORT).show();
                    sendUserToHomeActivity();
                }

            }
        });
    }


    private void sendUserToHomeActivity() {
        Intent homeActivity = new Intent(this , HomeActivity.class);
        startActivity(homeActivity);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sendUserToHomeActivity();
    }
}