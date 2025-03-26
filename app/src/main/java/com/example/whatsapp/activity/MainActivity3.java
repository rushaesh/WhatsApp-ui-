package com.example.whatsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.whatsapp.R;
import com.example.whatsapp.firstdrawe.MainActivity4;
import com.example.whatsapp.fragment.ContentFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity3 extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private TextView firstdrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        drawerLayout = findViewById(R.id.drawer_layout);
        firstdrawer = findViewById(R.id.firstdrawer);
        firstdrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(intent);// Reset the counter after showing the ad
            }
        });
        NavigationView navigationView = findViewById(R.id.navigation_view);

        // Set up ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Enable the home button for toggling the drawer
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        // Handle navigation item clicks
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String itemName = "";
                int id = item.getItemId();

                if (id == R.id.nav_inbox) {
                    itemName = "Inbox";
                } else if (id == R.id.nav_starred) {
                    itemName = "Starred";
                } else if (id == R.id.nav_sent) {
                    itemName = "Sent Email";
                } else if (id == R.id.nav_drafts) {
                    itemName = "Drafts";
                } else if (id == R.id.nav_all_email) {
                    itemName = "All Email";
                } else if (id == R.id.nav_trash) {
                    itemName = "Trash";
                } else if (id == R.id.nav_spam) {
                    itemName = "Spam";
                }

                // Replace fragment in the container
                ContentFragment fragment = ContentFragment.newInstance(itemName);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();

                // Close the drawer after handling the item click
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle the toggle button for opening/closing the drawer
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Close the drawer if open, otherwise perform the default back action
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}