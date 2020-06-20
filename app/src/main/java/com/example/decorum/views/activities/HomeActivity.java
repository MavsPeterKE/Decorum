package com.example.decorum.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ctrlplusz.anytextview.AnyTextView;
import com.eightbitlab.bottomnavigationbar.BottomBarItem;
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;
import com.example.decorum.R;
import com.example.decorum.utils.GridSpacingItemDecoration;
import com.example.decorum.views.fragments.FragmentDesignCategories;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.bottom_bar)
    BottomNavigationBar bottomNavigationBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setBottomNavigation();
    }
    private void setBottomNavigation() {
        BottomBarItem pendingItem = new BottomBarItem(R.drawable.home_icon, R.string.title_home);
        bottomNavigationBar.addTab(pendingItem);
        BottomBarItem inventoryItem = new BottomBarItem(R.drawable.favourite, R.string.my_deco);
        bottomNavigationBar.addTab(inventoryItem);

        //Create Default Home View
        startHomeView();

        //Bottom Navigation Action
        bottomNavigationBar.setOnSelectListener(position -> {
            switch (position) {
                case 0:
                    startHomeView();
                    break;
                case 1:
                    //Add Action
                    break;
            }
        });
    }

    private void startHomeView() {
        changeFragment(new FragmentDesignCategories(),FragmentDesignCategories.class.getSimpleName());
    }

    public void changeFragment(Fragment fragment, String tagFragmentName) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        Fragment currentFragment = mFragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }
        Fragment fragmentTemp = mFragmentManager.findFragmentByTag(tagFragmentName);
        if (fragmentTemp == null) {
            fragmentTemp = fragment;
            fragmentTransaction.add(R.id.frame_container, fragmentTemp, tagFragmentName);
        } else {
            fragmentTransaction.show(fragmentTemp);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }
}
