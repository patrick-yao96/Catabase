package com.example.catabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements SearchRecyclerFragment.OnFragmentInteractionListener
{
        BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Implementing a fragment during start up
        Fragment fragment = new SearchRecyclerFragment();
        swapFragment(fragment);
        //Switching between fragments
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        if (menuItem.getItemId() == R.id.nav_search) {
                            Fragment fragment = new SearchRecyclerFragment();
                            swapFragment(fragment);
                            return true;
                        }  else if (menuItem.getItemId() == R.id.nav_fav) {
                            Fragment fragment = new FavouriteRecyclerFragment();
                            swapFragment(fragment);
                            return true;
                        }
                        return false;
                    }
                }
        );




    }

    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot,fragment);
        fragmentTransaction.commit();
    }



    @Override
    public void onFragmentInteraction(String string) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
