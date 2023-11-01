package com.example.nourishbite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.nourishbite.R;
import com.example.nourishbite.adapter.ViewPagerAdapter;
import com.example.nourishbite.fragment.HomePageFragment;
import com.example.nourishbite.fragment.UserProfileFragment;
import com.example.nourishbite.model.User;
import com.example.nourishbite.viewmodel.UserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class HomePageActivity extends AppCompatActivity implements HomePageFragment.ProductClickListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private BottomNavigationView bottomNavigationView;
    public static int currentUserId;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Mapping();

        passUserInfo();



        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;

                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_cart).setChecked(true);
                        break;

                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menu_user).setChecked(true);
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_home){
                    mViewPager.setCurrentItem(0);
                }
                if(item.getItemId() == R.id.menu_cart){
                    mViewPager.setCurrentItem(1);
                }
                if(item.getItemId() == R.id.menu_user){
                    mViewPager.setCurrentItem(2);
                }

                return true;
            }
        });

    }

    private void Mapping(){
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    private void passUserInfo(){
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            User user = (User) bundle.getSerializable("object_user");
            if(user != null){
                UserProfileFragment userProfileFragment = UserProfileFragment.newInstance();
                userProfileFragment.setUserData(user);
                currentUserId = user.getId();
                UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
                userViewModel.setUser(user);
            }
        }
    }

    @Override
    public void onProductClicked(int productId) {

    }
}
