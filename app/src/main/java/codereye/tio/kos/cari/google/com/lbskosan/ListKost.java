package codereye.tio.kos.cari.google.com.lbskosan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by tio on 1/3/18.
 */

public class ListKost extends Fragment implements AdapterView.OnItemClickListener{


    ListView listView;
    String nama_kos[] = {"Kostan dua ", "Wijaya Residen", "Rumah ragil ajah"};
    String alamat_kos[] = {"Jl Cemaran dua way halim", "Jl Kemang Kemang way duru ah", "Jalan puter lagu dangdut yang"};
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("On Create ListKost Called");
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("On Create View ListKost Called");
        View RootView = inflater.inflate(R.layout.list_kost, container, false);
        try {
            RootView = fillFoodList(RootView);
            System.out.println("List nya ok");
        }catch (Exception e){
            System.out.println("Error di ListKost : " + e);
        }


        return RootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private View fillFoodList(View RootView){
        listView = (ListView) RootView.findViewById(R.id.beranda_list);
        Customadapter customadapter = new Customadapter();
        listView.setAdapter(customadapter);
        listView.setOnItemClickListener(this);
        return RootView;
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
            txtNama.setText(nama_kos[i]);
            txtAlamat.setText(alamat_kos[i]);
            return view;
        }
    }
}
