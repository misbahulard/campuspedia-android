package com.campuspedia.campuspedia.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.fragment.CampusListFragment;
import com.campuspedia.campuspedia.fragment.CategoryFragment;
import com.campuspedia.campuspedia.fragment.EventDetailFragment;
import com.campuspedia.campuspedia.fragment.EventListFragment;
import com.campuspedia.campuspedia.fragment.HomeFragment;
import com.campuspedia.campuspedia.fragment.MainCategoryFragment;
import com.campuspedia.campuspedia.fragment.NotificationFragment;
import com.campuspedia.campuspedia.fragment.ProfileFragment;
import com.campuspedia.campuspedia.fragment.SuggestFragment;
import com.campuspedia.campuspedia.util.SharedPrefManager;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnEventSelectedListener, MainCategoryFragment.OnMainCategorySelectedListener, CategoryFragment.OnCategorySelectedListener, CampusListFragment.OnCampusSelectedListener {

    BottomNavigationBar mBottomNavigationBar;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Menambahkan bottom navigation bar
         */
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED_NO_TITLE);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setActiveColor(R.color.colorPrimary);

        /**
         * TODO: Untuk rilis 1 ubah Suggest menjadi Campus
         */
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home, getResources().getString(R.string.text_home)))
                .addItem(new BottomNavigationItem(R.drawable.ic_category, getResources().getString(R.string.text_category)))
                .addItem(new BottomNavigationItem(R.drawable.ic_campus, getResources().getString(R.string.text_suggest)))
                // .addItem(new BottomNavigationItem(R.drawable.ic_suggest, getResources().getString(R.string.text_suggest)))
                .addItem(new BottomNavigationItem(R.drawable.ic_notification, getResources().getString(R.string.text_notification)))
                .addItem(new BottomNavigationItem(R.drawable.ic_profile, getResources().getString(R.string.text_profile)))
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {

                /**
                 * Hapus semua fragment yang ada di stack sebelum berpindah tab
                 */
                destroyAllFragment();

                switch (position) {
                    case 0:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.root_layout, HomeFragment.newInstance(), "home")
                                .commit();
                        break;
                    case 1:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.root_layout, MainCategoryFragment.newInstance(), "main_category")
                                .commit();
                        break;
                    case 2:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.root_layout, CampusListFragment.newInstance(), "campus")
                                .commit();
                        break;
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.root_layout, SuggestFragment.newInstance(), "suggest")
//                                .commit();
//                        break;
                    case 3:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.root_layout, NotificationFragment.newInstance(), "notification")
                                .commit();
                        break;
                    case 4:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.root_layout, ProfileFragment.newInstance(), "profile")
                                .commit();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        /**
         * Inisialisasi fragment awal {@link HomeFragment}
         */
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.root_layout, HomeFragment.newInstance(), "home")
                    .commit();
        }
    }

    /**
     * Method yang berfungsi untuk menghapus semua fragment yang ada di BackStack
     */
    public void destroyAllFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int backStackEntry = getSupportFragmentManager().getBackStackEntryCount();

        if (backStackEntry > 0) {
            for (int i = 0; i < backStackEntry; i++) {
                String tag = fragmentManager.getBackStackEntryAt(i).getName();
                fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
    }

    @Override
    public void onEventSelected(int id) {
        EventDetailFragment fragment = new EventDetailFragment();
        Bundle args = new Bundle();
        args.putInt(fragment.EVENT_ID, id);
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.root_layout, fragment, "event_detail");
        transaction.addToBackStack("event");
        transaction.commit();
    }

    @Override
    public void onMainCategorySelected(int id) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putInt(fragment.MAIN_CATEGORY_ID, id);
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.root_layout, fragment, "category");
        transaction.addToBackStack("main_category");
        transaction.commit();
    }

    @Override
    public void onCategorySelected(int id) {
        EventListFragment fragment = new EventListFragment();
        Bundle args = new Bundle();
        args.putInt(fragment.CATEGORY_ID, id);
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.root_layout, fragment, "event_list");
        transaction.addToBackStack("");
        transaction.commit();
    }

    @Override
    public void onCampusSelected(int id) {

    }
}
