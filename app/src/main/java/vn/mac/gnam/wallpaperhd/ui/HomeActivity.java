package vn.mac.gnam.wallpaperhd.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import vn.mac.gnam.wallpaperhd.R;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private FrameLayout fragmentContainer;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new LatestFragment()).commit();
            navView.setCheckedItem(R.id.nav_latest);
        }
    }

    private void init() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Latest");
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_latest:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LatestFragment()).commit();
                getSupportActionBar().setTitle("Latest");
                break;
            case R.id.nav_category:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CategoryFragment()).commit();
                getSupportActionBar().setTitle("Category");
                break;
            case R.id.nav_gifs:
                Toast.makeText(this, "Gifs", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_favoriest:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FavoriteFragment()).commit();
                getSupportActionBar().setTitle("Favorite");
                break;
            case R.id.nav_rate_app:
                Toast.makeText(this, "Rate app", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_more_app:
                Toast.makeText(this, "More app", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_about_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutUsFragment()).commit();
                getSupportActionBar().setTitle("About us");
                break;
            case R.id.nav_setting:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_privacy_police:
                Toast.makeText(this, "Privacy police", Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
