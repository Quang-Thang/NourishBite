package com.example.nourishbite.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.nourishbite.fragment.CartPageFragment;
import com.example.nourishbite.fragment.HomePageFragment;
import com.example.nourishbite.fragment.UserProfileFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomePageFragment();

            case 1:
                return new CartPageFragment();

            case 2:
                return new UserProfileFragment();

            default:
                return new HomePageFragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Home";
                break;

            case 1:
                title = "Cart";
                break;

            case 2:
                title = "User Profile";
                break;
        }
        return title;
    }
}
