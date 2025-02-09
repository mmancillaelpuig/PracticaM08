package com.example.navigation2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.navigation2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        binding.bottomNavView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_agenda) {
                navController.navigate(R.id.bottomFragment1);
                return true;
            } else if (item.getItemId() == R.id.menu_agenda_editar) {
                navController.navigate(R.id.bottomFragment2);
                return true;
            }
            return false;
        });
    }
}