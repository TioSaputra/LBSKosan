package codereye.tio.kos.cari.google.com.lbskosan;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tio on 1/3/18.
 */

public class Beranda extends Fragment {


    ListKost listKost;
    MapsActivity mapsActivity;


    Button btnDaftar;
    Button btnPeta;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("Beranda On Create Called");

        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("Beranda On Create View Called");

        return inflater.inflate(R.layout.beranda, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeFragment();
    }

    private void initializeFragment(){
        System.out.println("Initialize Beranda Fragment");
        listKost = new ListKost();
        mapsActivity = new MapsActivity();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.beranda_fragment_container, listKost);
        transaction.commit();


        btnDaftar = (Button) getView().findViewById(R.id.btnDaftar);
        btnPeta = (Button) getView().findViewById(R.id.btnPeta);

        btnDaftar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.beranda_fragment_container, listKost);
                transaction.commit();
            }
        });

        btnPeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.beranda_fragment_container, mapsActivity);
                transaction.commit();
            }
        });
    }
}
