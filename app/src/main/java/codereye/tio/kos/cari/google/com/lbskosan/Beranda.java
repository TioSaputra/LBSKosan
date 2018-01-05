package codereye.tio.kos.cari.google.com.lbskosan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import codereye.tio.kos.cari.google.com.lbskosan.model.Kosts;

/**
 * Created by tio on 1/3/18.
 */

public class Beranda extends Fragment {

    private static final String TAG=Beranda.class.getSimpleName();
    private FirebaseDatabase database;
    private DatabaseReference myRef=null;

    ListKost listKost;
    MapsActivity mapsActivity;


    Button btnDaftar;
    Button btnPeta;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("Beranda On Create Called");
        super.onCreate(savedInstanceState);
        getListData();
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

    private void getListData(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("kost");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long value = dataSnapshot.getChildrenCount();
                Log.d(TAG, "no of children : " + value);

                List<Kosts> kostsList = new ArrayList<Kosts>();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    kostsList.add(child.getValue(Kosts.class));
                }

                for (int i =0; i<kostsList.size(); i++){
                    System.out.println("Nama Kost an : " + kostsList.get(i).getNama());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }
}
