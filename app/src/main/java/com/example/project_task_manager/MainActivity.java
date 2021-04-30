package com.example.project_task_manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project_task_manager.fragments.ProjectTaskListFragment;
import com.example.project_task_manager.fragments.TaskFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> testStrings = new ArrayList<>();
        testStrings.add("One");
        testStrings.add("Two");
        testStrings.add("Three");

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_frag_container, ProjectTaskListFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        MaterialToolbar toolbar = findViewById(R.id.main_toolbar);
        NavigationView navigationView = findViewById(R.id.navigations_view);

        toolbar.setNavigationOnClickListener(v -> {
            drawerLayout.open();
        });

        LinearLayout navMenu = findViewById(R.id.nav_menu);
        for (String num : testStrings){
            TextView textView = new TextView(this);
            textView.setText(num);
            navMenu.addView(textView);
        }

    }
}