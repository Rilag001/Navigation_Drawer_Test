package se.rickylagerkvist.navigationdrawertest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import se.rickylagerkvist.navigationdrawertest.R;
import se.rickylagerkvist.navigationdrawertest.SlideShowFragment;
import se.rickylagerkvist.navigationdrawertest.StartFragment;
import se.rickylagerkvist.navigationdrawertest.ToolsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Add StartFragment. Are later switched to other fragment with Drawer options
        StartFragment startFragment = new StartFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_container, startFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle camera
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(takePictureIntent);


        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);

        } else if (id == R.id.slide_show) {
            // SlideShowFragment
            // 1. Create a SlideShowFragment
            SlideShowFragment slideShow = new SlideShowFragment();
            // 2. FragmentManager
            FragmentManager manager = getSupportFragmentManager();
            // 3. Start FragmentTransaction
            FragmentTransaction transaction = manager.beginTransaction();
            // 4. Set Action
            transaction.replace(R.id.content_container, slideShow);
            transaction.addToBackStack(null);
            // Run transaction
            transaction.commit();

            // Set titel
            getSupportActionBar().setTitle(item.getTitle());

        } else if (id == R.id.nav_manage) {
            // ToolsFragment
            ToolsFragment tools = new ToolsFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.content_container, tools);
            transaction.addToBackStack(null);
            transaction.commit();

            // Set titel
            getSupportActionBar().setTitle(item.getTitle());

        } else if (id == R.id.nav_share) {
            Snackbar.make(findViewById(R.id.content_container), "You clicked on Share", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();


        } else if (id == R.id.nav_send) {
            Snackbar.make(findViewById(R.id.content_container), "You clicked on Send", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        } else if (id == R.id.preferences) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
