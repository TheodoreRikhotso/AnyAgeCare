package com.example.user.anyagecare;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragmentManager = getSupportFragmentManager();
            fragment =new HomeFragment();

            switch (item.getItemId()) {

                case R.id.navigation_home:

                    fragment = new HomeFragment();
                    toolbar.setTitle("Home");

                    break;
//
                case R.id.navigation_tips:

                    fragment = new TipsFragment();
                    toolbar.setTitle("Tips");

                    break;

                case R.id.navigation_emergency:

                    fragment = new EmergencyFragment();
                    toolbar.setTitle("Emergency");

                    break;

                case R.id.navigation_appointments:

                    fragment = new AppointmetFragment();
                    toolbar.setTitle("Appointmet");

                    break;

                case R.id.navigation_progress:

                    fragment = new ProgressFragment();
                    toolbar.setTitle("Progress");

                    break;




            }
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content, fragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment f = new HomeFragment();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

         toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        transaction.add(R.id.content,f);
        transaction.commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.setSelectedItemId(R.id.navigation_home);
    }

}
