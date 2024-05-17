package dev.lab.electricalc;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;

    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing widgets
        frameLayout = findViewById(R.id.frameLayout);
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        //Setting default fragment
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new CalculatorFragment()).commit();
        }

        //Bottom Navigation
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //Getting MenuItem id in a variable
                int id = menuItem.getItemId();

                //If else if statement for fragment
                if (id == R.id.calculator){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new CalculatorFragment()).commit();
                } else if (id == R.id.help){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HelpFragment()).commit();
                } else if (id == R.id.aboutPage){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new AboutPageFragment()).commit();
                }

                return true;
            }
        });
    }

}