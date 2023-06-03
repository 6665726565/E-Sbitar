package com.example.sbitar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddMaladieActivity extends AppCompatActivity {

    private TextInputEditText maladieTitle , maladieDescription , maladieSymptomes ;
    private Button addArticleBtn;

    private FirebaseAuth mAuth ;
    private String currentUserId ;
    private DatabaseReference  maladieRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_maladie);
        
        maladieRef = FirebaseDatabase.getInstance().getReference().child("Maladie");

        maladieTitle = findViewById(R.id.article_title);
        maladieDescription = findViewById(R.id.article_description);
        maladieSymptomes = findViewById(R.id.article_symptomes);

        addArticleBtn = findViewById(R.id.add_article);
        
        addArticleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addingArticle();
            }
        });





    }

    private void addingArticle() {
        String description = maladieDescription.getText().toString();
        String title = maladieTitle.getText().toString();
        String symptomes = maladieSymptomes.getText().toString();
        if (!TextUtils.isEmpty(description)){
            HashMap hashMap = new HashMap();
            String key = maladieRef.push().getKey();


            hashMap.put("description" , description);
            hashMap.put("title" , title);
            hashMap.put("symptomes" , symptomes);
            hashMap.put("key" , key);

            maladieRef.child( key).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()){

                        sendUserToHomeActivity();


                    }else{
                        Toast.makeText(AddMaladieActivity.this, "Problme occured : " + task.getException().getMessage() , Toast.LENGTH_SHORT).show();

                    }
                }

            });
        }else {
            Toast.makeText(this, "Description required!", Toast.LENGTH_SHORT).show();
        }
    }


    private void sendUserToHomeActivity() {
        Intent intent = new Intent(AddMaladieActivity.this , HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sendUserToHomeActivity();
    }
}


