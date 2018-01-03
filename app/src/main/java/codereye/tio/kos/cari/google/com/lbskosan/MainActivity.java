package codereye.tio.kos.cari.google.com.lbskosan;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        showBeranda();

        setupBottomNavigation();
    }


    private void showBeranda(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, Beranda.newInstance());
        transaction.commit();
    }


    private void setupBottomNavigation(){
        BottomNavigationView btmNav = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.home_page:
                        selectedFragment = Beranda.newInstance();
                        break;
                    case R.id.map_page:
                        selectedFragment = Maps.newInstance();
                        break;
                    case R.id.profil_page:
                        selectedFragment = Profile.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, selectedFragment);
                transaction.commit();
                return false;
            }
        });
    }
}
