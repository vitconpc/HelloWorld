package com.example.dt10_hh4.helloworld;

import android.support.v4.app.Fragment;


public interface HomeView {
    void loadFragment(Fragment fragment);

//    void loadOneFragment(OneFragment registerFragment);

    void loadTwoFragment(TwoFragment twoFragment);

//    void loadThreeFragment(ThreeFragment forgotPasswordFragment);
}
