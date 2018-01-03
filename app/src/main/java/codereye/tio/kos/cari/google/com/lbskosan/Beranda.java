package codereye.tio.kos.cari.google.com.lbskosan;

import android.app.Activity;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tio on 1/3/18.
 */

public class Beranda extends Fragment {

    private MainActivity mainActivity;
    private TabLayout tabLayout;
    private ViewPager vwPager;

    @Override
    public void onAttach(Activity activity) {
        mainActivity = (MainActivity) activity;
        super.onAttach(activity);
    }


    public static Beranda newInstance(){
        Beranda fragment = new Beranda();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initializeMap();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.beranda, container, false);

        vwPager = (ViewPager) RootView.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) RootView.findViewById(R.id.beranda_tabs);

        setupViewPager(vwPager);
        tabLayout.setupWithViewPager(vwPager);
        return RootView;
    }

//    private void initializeMap(){
//        FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.beranda_container, MapsActivity.newInstance());
//        transaction.commit();
//    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(mainActivity.getSupportFragmentManager());
        adapter.addFragment(new ListKost(), "DAFTAR");
        adapter.addFragment(new MapsActivity(), "PETA");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
