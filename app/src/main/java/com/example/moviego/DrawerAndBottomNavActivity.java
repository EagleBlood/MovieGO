package com.example.moviego;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.moviego.databinding.ActivityDrawerAndBottomNavBinding;
import com.example.moviego.ui.form.FormFragment;
import com.example.moviego.ui.home.HomeFragment;
import com.example.moviego.ui.login.LoginFragment;
import com.example.moviego.ui.profile.ProfileFragment;
import com.example.moviego.ui.ticket.TicketFragment;
import com.google.android.material.navigation.NavigationView;

public class DrawerAndBottomNavActivity extends AppCompatActivity implements BottomNavigation {

    private ActivityDrawerAndBottomNavBinding binding;
    public ActionBarDrawerToggle toggle;
    private int USER_ID;
    private String USER_LOGIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_drawer_and_bottom_nav);
        USER_ID = MyApp.getInstance().getUSER_ID();
        USER_LOGIN = MyApp.getInstance().getUSER_LOGIN();

        initView();

    }

    private void initView() {
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitleTextColor(Color.WHITE);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
        binding.navView.setCheckedItem(R.id.nav_home);


        toggle = new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar, R.string.open, R.string.close);
        binding.drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this, R.color.white));
        toggle.syncState();


        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                ActionBar actionBar = getSupportActionBar();
                if (itemId == R.id.navLogin) {
                    fragment = new LoginFragment();
                    binding.drawer.closeDrawer(GravityCompat.START);
                    binding.bottomNavigationView.setVisibility(View.GONE);
                    if (actionBar != null ) actionBar.setTitle("Login");
                    callFragment(fragment);
                } else if (itemId == R.id.navForm){
                    fragment = new FormFragment();
                    binding.drawer.closeDrawer(GravityCompat.START);
                    binding.bottomNavigationView.setVisibility(View.GONE);
                    if (actionBar != null ) actionBar.setTitle("Contact Us");
                    callFragment(fragment);
                } else if (itemId == R.id.navHomeDrawer){
                    binding.bottomNavigationView.setVisibility(View.VISIBLE);
                    binding.drawer.closeDrawer(GravityCompat.START);
                    getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                }
                return true;
            }
        });


        binding.bottomNavigationView.setSelectedItemId(R.id.nav_home);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home){
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                return true;
            } else if (itemId == R.id.nav_profile){
                callFragment(new ProfileFragment());
                return true;
            } else if (itemId == R.id.nav_tickets){
                callFragment(new TicketFragment());
                return true;
            }
            return false;
        });
    }

    private void callFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
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
                } else if (backStackEntry.getName() != null && backStackEntry.getName().equals("hall")) {

                    binding.bottomNavigationView.setVisibility(View.GONE);
                    fm.popBackStack("hall", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else if (backStackEntry.getName() != null && backStackEntry.getName().equals("resetPass")) {

                    binding.bottomNavigationView.setVisibility(View.GONE);
                    fm.popBackStack("resetPass", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else if (backStackEntry.getName() != null && backStackEntry.getName().equals("startAfterBookedSeats")) {

                    binding.bottomNavigationView.setVisibility(View.VISIBLE);
                    fm.popBackStack("startAfterBookedSeats", FragmentManager.POP_BACK_STACK_INCLUSIVE);
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

    public void reloadApp() {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    public void hideElement() {
        binding.bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}