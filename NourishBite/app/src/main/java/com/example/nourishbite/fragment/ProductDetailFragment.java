package com.example.nourishbite.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nourishbite.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailFragment extends Fragment {
    View view;
    TextView productName;
    Button backBtn;


    public ProductDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        Mapping();

        // Inflate the layout for this fragment
        return view;
    }

    private void Mapping(){
        productName = view.findViewById(R.id.tv_name);
        backBtn = view.findViewById(R.id.back_button);
    }
}