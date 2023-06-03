package com.example.sbitar.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sbitar.Adapters.ArticlesAdapter;
import com.example.sbitar.AddMaladieActivity;
import com.example.sbitar.LoginActivity;
import com.example.sbitar.MainActivity;
import com.example.sbitar.MaladieArticle;
import com.example.sbitar.Model.Maladie;
import com.example.sbitar.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeMaladieFragment extends Fragment {

    private DatabaseReference articleRef ;
    private FirebaseAuth mAuth ;


    private ArrayList<Maladie> maladies = new ArrayList<>() ;
    private ArticlesAdapter articlesAdapter;
    private RecyclerView   articleRecycler;
    private FloatingActionButton addPostBtn;
    private NestedScrollView postScrollView;
    private ImageButton logOutBtn;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_maladie_layout, container ,false);
        mAuth = FirebaseAuth.getInstance();
        articleRecycler = view.findViewById(R.id.article_recycler_view);
        addPostBtn = view.findViewById(R.id.add_post_btn);
        logOutBtn = view.findViewById(R.id.logout_btn);
        articleRef = FirebaseDatabase.getInstance().getReference().child("Maladie");

        postScrollView = view.findViewById(R.id.article_scroll_view);


        MainActivity.isPatient = MainActivity.sharedPreferences.getBoolean("isPatient" , true);
        if (MainActivity.isPatient){
            addPostBtn.setVisibility(View.GONE);
        }else {
            addPostBtn.setVisibility(View.VISIBLE);
        }


        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToAddPostActivity();
            }
        });

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
               sendUserToLoginActivity();


            }
        });

        handlArticles();
        handlHidingOnScroll();

        return view;

    }

    private void sendUserToAddPostActivity() {
        Intent intent = new Intent(getActivity() , AddMaladieActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    public void sendUserToLoginActivity(){
        Intent loginActivity = new Intent(getActivity() , LoginActivity.class);
        startActivity(loginActivity);
        getActivity().finish();
    }

    private void handlArticles() {
        articleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                maladies.clear();
                if (snapshot.hasChildren()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        maladies.add(0,dataSnapshot.getValue(Maladie.class));
                    }

                    articlesAdapter = new ArticlesAdapter(getActivity(), maladies, new ArticlesAdapter.MyClickInterface() {
                        @Override
                        public void onItemClicked(View view, int position) {
                            Intent intent = new Intent(getActivity() , MaladieArticle.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("title" , maladies.get(position).getTitle());
                            intent.putExtra("description" , maladies.get(position).getDescription());
                            intent.putExtra("symptomes" , maladies.get(position).getSymptomes());
                            intent.putExtra("key" , maladies.get(position).getKey());
                            startActivity(intent);
                            getActivity().finish();

                        }

                    });
                    articleRecycler.setNestedScrollingEnabled(false);
                    articleRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                    articleRecycler.setAdapter(articlesAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void handlHidingOnScroll(){


        postScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    addPostBtn.hide();
                } else {
                    addPostBtn.show();
                }
            }
        });
    }
}
