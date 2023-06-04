package com.example.sbitar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.sbitar.Fragments.HomeMaladieFragment;
import com.example.sbitar.Fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Menu menu;
    private MenuItem homeItem , searchItem, qstItem , profileItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        bottomNavigationView = findViewById(R.id.btm_nav_bar);
//        bottomNavigationView.setItemIconTintList(null);

//        menu = bottomNavigationView.getMenu();
//        homeItem = menu.findItem(R.id.item_home).setIcon(R.drawable.ic_selected_home);

//
//        bottomNavigationView.setSelectedItemId(R.id.item_home);
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                handleNavItemSelected(item);
//                return true;
//            }
//        });



        replaceFragment(new HomeMaladieFragment());
    }


//    private void handleNavItemSelected(MenuItem item){
//        switch (item.getItemId()) {
//            case R.id.item_home:
//
//
//                replaceFragment(new HomeMaladieFragment());
//                item.setIcon(R.drawable.ic_selected_home);
//                profileItem.setIcon(R.drawable.ic_profile);
//                break;
//
//
//
//        }
//
//    }

    private void replaceFragment(Fragment fragment){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container , fragment);
        transaction.commit();

    }
}