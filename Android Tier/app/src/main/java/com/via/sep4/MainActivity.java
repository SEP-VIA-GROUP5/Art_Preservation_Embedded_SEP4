package com.via.sep4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.via.sep4.view.HomeFragment;
import com.via.sep4.view.RegisterFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private View headerView;
    private TextView username;
    private TextView email;

    private NavController navController;
    private AppBarConfiguration configuration;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        loadInfo();
        setupNavigation();
    }

    private void initView() {
        drawerLayout = findViewById(R.id.main_drawer);
        navigationView = findViewById(R.id.side_navmenu);
        toolbar = findViewById(R.id.toolbar);
        headerView = navigationView.getHeaderView(0);
        username = headerView.findViewById(R.id.username_menu);
        email = headerView.findViewById(R.id.email_menu);
    }

    private void loadInfo() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            email.setText(user.getEmail());
        }

    }

    private void setupNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setSupportActionBar(toolbar);

        configuration = new AppBarConfiguration.Builder(R.id.nav_home)
                .setOpenableLayout(drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this, navController, configuration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, configuration) || super.onSupportNavigateUp();
    }

}