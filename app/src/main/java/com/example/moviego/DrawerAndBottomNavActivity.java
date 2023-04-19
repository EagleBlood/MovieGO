package com.example.moviego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.moviego.databinding.ActivityDrawerAndBottomNavBinding;
import com.example.moviego.ui.form.FormFragment;
import com.example.moviego.ui.home.HomeFragment;
import com.example.moviego.ui.login.LoginFragment;
import com.example.moviego.ui.other.SettingsFragment;
import com.example.moviego.ui.profile.ProfileFragment;
import com.example.moviego.ui.ticket.TicketFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalTime;

public class DrawerAndBottomNavActivity extends AppCompatActivity {

    private ActivityDrawerAndBottomNavBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_drawer_and_bottom_nav);

        initView();
    }

    private void initView() {

        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitleTextColor(Color.WHITE);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
        binding.navView.setCheckedItem(R.id.nav_home);


        toggle = new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar, R.string.open, R.string.close);
        binding.drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();


        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navLogin:
                        fragment = new LoginFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        binding.bottomNavigationView.setVisibility(View.GONE);
                        getSupportActionBar().setTitle("Login");
                        callFragment(fragment);
                        break;

                    case R.id.navForm:
                        fragment = new FormFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        binding.bottomNavigationView.setVisibility(View.GONE);
                        getSupportActionBar().setTitle("Contact US");
                        callFragment(fragment);
                        break;

                    case R.id.navSettings:
                        fragment = new SettingsFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        binding.bottomNavigationView.setVisibility(View.GONE);
                        getSupportActionBar().setTitle("Settings");
                        callFragment(fragment);
                        break;

                }
                return true;
            }
        });


        binding.bottomNavigationView.setSelectedItemId(R.id.nav_home);
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        callFragment(new HomeFragment());
                        break;
                    case R.id.nav_profile:
                        callFragment(new ProfileFragment());
                        break;
                    case R.id.nav_tickets:
                        callFragment(new TicketFragment());
                        break;
                }
                return true;
            }
        });
    }


    private void callFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void onBackPressed() {

        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            int backStackCount = fm.getBackStackEntryCount();
            System.out.println(backStackCount);

            if (backStackCount > 0) {
                FragmentManager.BackStackEntry backStackEntry = fm.getBackStackEntryAt(backStackCount - 1);
                if (backStackEntry.getName() != null && backStackEntry.getName().equals("login")) {

                    binding.bottomNavigationView.setVisibility(View.GONE);
                    fm.popBackStack("login", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else {

                    binding.bottomNavigationView.setSelectedItemId(R.id.nav_home);
                    binding.bottomNavigationView.setVisibility(View.VISIBLE);
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }

            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}