package com.example.ergasia;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    BottomNavigationView navigationView;



    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        navigationView = findViewById(R.id.bottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_calendar:
                        fragment = new CalendarFragment();
                        break;
                    case R.id.nav_favorites:
                        fragment = new FavoritesFragment();
                        break;
                    case R.id.nav_map:
                        fragment = new MapFragment();
                        break;
                    case R.id.nav_search:
                        fragment = new SearchFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_profil:
                Toast.makeText(this, "Επιτυχής σύνδεση", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_language:
                Toast.makeText(this, "Επιτυχής αλλαγή γλώσσας", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_terms:
                Toast.makeText(this, "Όροι και προηποθέσεις χρήσης", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_signout:
                Toast.makeText(this, "Επιτυχής αποσύνδεση", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }


}