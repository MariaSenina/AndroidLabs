package com.example.androidlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = setUpToolbar();
        setUpDrawerLayout(toolbar);
        setUpNavigationView();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch( item.getItemId() ) {
            case R.id.homeOption:
                setContentView(R.layout.activity_main);

                Toolbar toolbar = setUpToolbar();
                setUpDrawerLayout(toolbar);
                setUpNavigationView();
                break;
            case R.id.dadJoke:
                Fragment dadJoke = new DadJoke();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment, dadJoke)
                        .commit();
                break;
            case R.id.exit:
                this.finishAffinity();
                break;
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        makeToastMessage(item);

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu_layout, menu);

        return true;
    }

    public void makeToastMessage(MenuItem item) {
        String message = null;

        switch( item.getItemId() ) {
            case R.id.emailOption:
                message = "You clicked item 1";
                break;
            case R.id.cartOption:
                message = "You clicked item 2";
                break;
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public Toolbar setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    public void setUpDrawerLayout(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void setUpNavigationView() {
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }
}