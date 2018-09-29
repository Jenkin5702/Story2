package com.kisetsu.story;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.kisetsu.story.activities.ActivityLogin;
import com.kisetsu.story.activities.ActivityWelcome;
import com.kisetsu.story.fragments.FragmentCommunity;
import com.kisetsu.story.fragments.FragmentFavorite;
import com.kisetsu.story.fragments.FragmentFriends;
import com.kisetsu.story.fragments.FragmentHome;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
//        FloatingActionButton fab=findViewById(R.id.fab);
        DrawerLayout drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sp=getSharedPreferences("config",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("signed",true);
        editor.apply();

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentHome fragmentHome=new FragmentHome();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_container_main,fragmentHome);
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
            startActivity(new Intent(MainActivity.this, ActivityLogin.class));
//            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            toolbar.setTitle("首页");
            FragmentHome fragmentHome=new FragmentHome();
            transaction.replace(R.id.fl_container_main,fragmentHome);
            transaction.commit();
        } else if (id == R.id.nav_favorite) {
            toolbar.setTitle("收藏");
            FragmentFavorite fragmentFavorite=new FragmentFavorite();
            transaction.replace(R.id.fl_container_main,fragmentFavorite);
            transaction.commit();
        } else if (id == R.id.nav_community) {
            toolbar.setTitle("动态");
            FragmentCommunity fragmentCommunity=new FragmentCommunity();
            transaction.replace(R.id.fl_container_main,fragmentCommunity);
            transaction.commit();
        } else if (id == R.id.nav_friends) {
            toolbar.setTitle("好友");
            FragmentFriends fragmentFriends=new FragmentFriends();
            transaction.replace(R.id.fl_container_main,fragmentFriends);
            transaction.commit();
        } else if (id == R.id.nav_share) {
            Toast.makeText(MainActivity.this,"share~",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_settings) {
            Toast.makeText(MainActivity.this,"share~",Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
