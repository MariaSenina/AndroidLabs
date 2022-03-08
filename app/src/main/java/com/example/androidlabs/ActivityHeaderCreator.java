package com.example.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ActivityHeaderCreator extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public void createActivityHeader() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent nextActivity = null;

        switch( item.getItemId() ) {
            case R.id.homeOption:
                if (this.getClass() != MainActivity.class) {
                    nextActivity = new Intent(this, MainActivity.class);
                    startActivity(nextActivity);
                }
                break;
            case R.id.dadJoke:
                if (this.getClass() != DadJoke.class) {
                    nextActivity = new Intent(this, DadJoke.class);
                    startActivity(nextActivity);
                }
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
}
