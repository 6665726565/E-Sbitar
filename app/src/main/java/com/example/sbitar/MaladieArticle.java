package com.example.sbitar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MaladieArticle extends AppCompatActivity {
    private String title , description , symptomes , key;
    private TextView showTitle , showDescription , showSymptomes;
    private Button deleteBtn ;
    private DatabaseReference maladieRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maladie_article);

        maladieRef = FirebaseDatabase.getInstance().getReference().child("Maladie");

        showTitle = findViewById(R.id.show_title);
        showDescription = findViewById(R.id.show_description);
        showSymptomes = findViewById(R.id.show_symptomes);

        deleteBtn = findViewById(R.id.delete_btn);

        Intent intent = getIntent();
        title =  intent.getStringExtra("title");
        description =  intent.getStringExtra("description");
        symptomes = intent.getStringExtra("symptomes");
        key = intent.getStringExtra("key");


        showTitle.setText(title);
        showDescription.setText(description);
        showSymptomes.setText(symptomes);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maladieRef.child(key).removeValue();
                sendUserToHomeActivity();
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