package codereye.tio.kos.cari.google.com.lbskosan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import codereye.tio.kos.cari.google.com.lbskosan.model.Kosts;

/**
 * Created by tio on 1/3/18.
 */

public class ListKost extends Fragment implements AdapterView.OnItemClickListener{

    private static final String TAG=Beranda.class.getSimpleName();
    private FirebaseDatabase database;
    private DatabaseReference myRef=null;
    private List<Kosts> kostsList;

    View RootView;
    ListView listView;
    String nama_kos[] = {"Kostan dua ", "Wijaya Residen", "Rumah ragil ajah"};
    String alamat_kos[] = {"Jl Cemaran dua way halim", "Jl Kemang Kemang way duru ah", "Jalan puter lagu dangdut yang"};


    //Testing
    boolean isDataReady = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("On Create ListKost Called");
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("On Create View ListKost Called");
        RootView = inflater.inflate(R.layout.list_kost, container, false);
        try {
            getListData();
        }catch (Exception e){
            System.out.println("Error di ListKost : " + e);
        }finally {
            if(isDataReady==true){
                RootView = fillFoodList(RootView);
                System.out.println("List nya ok");
            }
        }
        return RootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public List<Kosts> getKostsList() {
        return kostsList;
    }

    public void setKostsList(List<Kosts> kostsList) {
        this.kostsList = kostsList;
    }

    private View fillFoodList(View RootView){
        listView = (ListView) RootView.findViewById(R.id.beranda_list);
        Customadapter customadapter = new Customadapter();
        listView.setAdapter(customadapter);
        listView.setOnItemClickListener(this);
        return RootView;
    }

    private void getListData(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("kost");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long value = dataSnapshot.getChildrenCount();
                Log.d(TAG, "no of children : " + value);

                try {
                    kostsList = new ArrayList<Kosts>();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        kostsList.add(child.getValue(Kosts.class));
                    }

                    for (int i = 0; i < kostsList.size(); i++) {
                        System.out.println("Nama Kost an : " + kostsList.get(i).getNama());
                    }
                }catch (Exception e){

                }finally {
                    isDataReady = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }

    class Customadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return nama_kos.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate(R.layout.custom_list_beranda, null);
            ImageView vwGambar = (ImageView) view.findViewById(R.id.list_item_gambar);
            TextView txtNama = (TextView) view.findViewById(R.id.list_item_nama);
            TextView txtAlamat = (TextView) view.findViewById(R.id.list_item_alamat);

            vwGambar.setImageResource(R.drawable.rumah_contoh);
            txtNama.setText(kostsList.get(i).getNama());
            txtAlamat.setText(kostsList.get(i).getNama());

            return view;
        }
    }
}
