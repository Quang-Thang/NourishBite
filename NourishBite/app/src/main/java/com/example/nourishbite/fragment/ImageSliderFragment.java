package com.example.nourishbite.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nourishbite.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageSliderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageSliderFragment extends Fragment {
    View view;


    public ImageSliderFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_image_slider, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}