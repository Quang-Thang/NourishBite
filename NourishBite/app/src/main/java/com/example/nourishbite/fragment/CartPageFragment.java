package com.example.nourishbite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.nourishbite.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouritePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartPageFragment extends Fragment {

    public CartPageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}