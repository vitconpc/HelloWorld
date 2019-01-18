package com.example.dt10_hh4.helloworld;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener, HomeView {

    private static final String TAG = "vitcon";
    private BottomNavigationView mBottomNavigationView;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private MenuItem mPreviousMenuItem;
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mFragmentList = new ArrayList<>();
//        OneFragment oneFragment = new OneFragment();
//        oneFragment.setHomeView(this);
        mBottomNavigationView = findViewById(R.id.navigation);
        mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        mPreviousMenuItem = mBottomNavigationView.getMenu().getItem(0);
        mViewPager.addOnPageChangeListener(this);
        setviewpager();
    }

    private void setviewpager() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        OneFragment oneFragment = new OneFragment();
        oneFragment.setHomeView(this);
        mFragmentList.add(oneFragment);
        TwoFragment twoFragment = new TwoFragment();
        ThreeFragment threeFragment = new ThreeFragment();
        mViewPagerAdapter.addFragment(oneFragment);
        mViewPagerAdapter.addFragment(twoFragment);
        mViewPagerAdapter.addFragment(threeFragment);
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                if (mViewPager.getCurrentItem()==0){
                    return false;
                }
                loadFragment(mFragmentList.get(mFragmentList.size()-1));
                mViewPager.setCurrentItem(0);
                Log.d(TAG, "onNavigationItemSelected: " + mFragmentList.size() + "   -   " + mFragmentList.get(mFragmentList.size()-1).getTag());
                return true;
            case R.id.navigation_dashboard:
                if (mViewPager.getCurrentItem()==1){
                    return false;
                }
                mViewPager.setCurrentItem(1);
                return true;
            case R.id.navigation_notifications:
                if (mViewPager.getCurrentItem()==2){
                    return false;
                }
                mViewPager.setCurrentItem(2);
                return true;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        mPreviousMenuItem.setChecked(false);
        mBottomNavigationView.getMenu().getItem(i).setChecked(true);
        mPreviousMenuItem = mBottomNavigationView.getMenu().getItem(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_content, fragment,"fragment");
        ft.commit();
        Log.d(TAG, "loadFragment: ");
    }

    @Override
    public void loadTwoFragment(TwoFragment twoFragment) {
        mFragmentList.add(twoFragment);
        loadFragment(twoFragment);
        Log.d(TAG, "loadTwoFragment:");
    }

    @Override
    public void onBackPressed() {
        if (mBottomNavigationView.getMenu().getItem(0) == mPreviousMenuItem && mFragmentList.size() > 1) {
            mFragmentList.remove(mFragmentList.size() - 1);
            loadFragment(mFragmentList.get(mFragmentList.size() - 1));
            return;
        }
        super.onBackPressed();
    }
}
