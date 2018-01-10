package codereye.tio.kos.cari.google.com.lbskosan;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    Beranda berandaFragment;
    Profile profileFragment;
    MapsActivity mapsFragment;
    LoginActivity loginFragment;
    ListKost listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initializeFragment();

        setupBottomNavigation();
    }


    private void initializeFragment(){
        berandaFragment = new Beranda();
        profileFragment = new Profile();
        mapsFragment = new MapsActivity();
        loginFragment = new LoginActivity();
        listFragment = new ListKost();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, listFragment);
        transaction.commit();
    }


    private void setupBottomNavigation(){
        BottomNavigationView btmNav = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.home_page:
                        transaction.replace(R.id.fragment_container, listFragment);
                        break;
                    case R.id.map_page:
                        transaction.replace(R.id.fragment_container, mapsFragment);
                        break;
                    case R.id.profil_page:
                        transaction.replace(R.id.fragment_container, profileFragment);
                        break;
                }
                transaction.commit();
                return false;
            }
        });
    }
}
