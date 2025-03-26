package com.example.whatsapp.firstdrawe;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.whatsapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar; // Declare the Toolbar

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    CustomDrawerAdapter adapter;
    List<DrawerItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // Initialize the Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Use Toolbar as ActionBar

        // Initialize other components
        dataList = new ArrayList<>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // Add Drawer Items to dataList
        dataList.add(new DrawerItem(true)); // adding a spinner to the list
        dataList.add(new DrawerItem("My Favorites"));
        dataList.add(new DrawerItem("Message", R.drawable.ic_action_email));
        dataList.add(new DrawerItem("Likes", R.drawable.ic_action_good));
        dataList.add(new DrawerItem("Games", R.drawable.ic_action_gamepad));
        dataList.add(new DrawerItem("Labels", R.drawable.ic_action_labels));
        dataList.add(new DrawerItem("My items"));
        dataList.add(new DrawerItem("Rate", R.drawable.rate_rating_survey_22_svgrepo_com));
        dataList.add(new DrawerItem("Share", R.drawable.share_svgrepo_com));
        dataList.add(new DrawerItem("Privacy", R.drawable.privacy_svgrepo_com));
        dataList.add(new DrawerItem("Main Options"));
        dataList.add(new DrawerItem("Search", R.drawable.ic_action_search));
        dataList.add(new DrawerItem("Cloud", R.drawable.ic_action_cloud));
        dataList.add(new DrawerItem("Camera", R.drawable.ic_action_camera));
        dataList.add(new DrawerItem("Video", R.drawable.ic_action_video));
        dataList.add(new DrawerItem("Groups", R.drawable.ic_action_group));
        dataList.add(new DrawerItem("Import & Export", R.drawable.ic_action_import_export));
        dataList.add(new DrawerItem("Other Option"));
        dataList.add(new DrawerItem("About", R.drawable.ic_action_about));
        dataList.add(new DrawerItem("Settings", R.drawable.ic_action_settings));
        dataList.add(new DrawerItem("Help", R.drawable.ic_action_help));

        // Set the custom adapter
        adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item, dataList);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // Set up the ActionBarDrawerToggle
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, // Pass the Toolbar here
                R.string.drawer_open, R.string.drawer_close
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        // Handle the state of the drawer
        if (savedInstanceState == null) {
            if (dataList.get(0).isSpinner() && dataList.get(1).getTitle() != null) {
                SelectItem(2);
            } else if (dataList.get(0).getTitle() != null) {
                SelectItem(1);
            } else {
                SelectItem(0);
            }
        }
    }

    // Method to select the item from the list and display its fragment
    public void SelectItem(int position) {
        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (position) {
            case 2:
                fragment = new FragmentThree();
                args.putString(FragmentThree.ITEM_NAME, dataList.get(position).getItemName());
                args.putInt(FragmentThree.IMAGE_RESOURCE_ID, dataList.get(position).getImgResID());
                break;
            case 3:
                fragment = new FragmentOne();
                args.putString(FragmentOne.ITEM_NAME, dataList.get(position).getItemName());
                args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(position).getImgResID());
                break;
            case 4:
                fragment = new FragmentTwo();
                args.putString(FragmentTwo.ITEM_NAME, dataList.get(position).getItemName());
                args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(position).getImgResID());
                break;
            case 7: // Rate item
                openPlayStore();
                break;

            case 8: // Share item
                openShareOptions();
                break;

            case 9: // Privacy Policy item
                openPrivacyPolicy();
                break;
            // Add cases for other positions if needed
            default:
                Log.e("SelectItem", "Invalid position: " + position);
                return; // Early return if position is invalid
        }

        if (fragment != null) {
            fragment.setArguments(args);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        } else {
            Log.e("SelectItem", "Fragment is null for position: " + position);
        }

        mDrawerList.setItemChecked(position, true);
        setTitle(dataList.get(position).getItemName());
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggle
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    // Method to handle Rate action: Redirect to Play Store
    private void openPlayStore() {
        try {
            // Open the app in the Play Store
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            // If Play Store is not available (e.g., on some devices or emulator), open the browser
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(goToMarket);
        }
    }

    // Method to handle Share action: Open share sheet
    private void openShareOptions() {
        String shareBody = "Check out this amazing app!";
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "App Share");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(shareIntent, "Share App"));
    }

    // Method to handle Privacy Policy action: Open Privacy Policy URL
    private void openPrivacyPolicy() {
        String privacyUrl = "https://www.example.com/privacy";  // Replace with your actual privacy policy URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(privacyUrl));
        startActivity(intent);
    }

    // DrawerItem click listener to handle item clicks
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (dataList.get(position).getTitle() == null) {
                SelectItem(position);
            }
        }
    }
}
