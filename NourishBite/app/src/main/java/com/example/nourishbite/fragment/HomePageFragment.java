package com.example.nourishbite.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.nourishbite.R;
import com.example.nourishbite.adapter.ProductAdapter;
import com.example.nourishbite.api.ProductRepository;
import com.example.nourishbite.model.Product;
import com.example.nourishbite.model.User;
import com.example.nourishbite.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment {
    private RecyclerView rcvProduct;

    private View view;
    private List<Product> mProductList;
    ProductService productService;

    private SearchView searchView;

    ProductAdapter productAdapter;


    public HomePageFragment() {
        // Required empty public constructor
    }

    public static HomePageFragment newInstance(){

        return new HomePageFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_page, container, false);
        Mapping();
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });


        ArrayList<SlideModel> imageList = new ArrayList<>(); // Create image list

// imageList.add(new SlideModel("String Url" or R.drawable)
// imageList.add(new SlideModel("String Url" or R.drawable, "title") You can add title

        imageList.add(new SlideModel("https://massageishealthy.com/wp-content/uploads/2018/05/cach-lam-banh-yen-mach-nuong-giam-can-15.jpg", "Life is short, eat dessert first... and make it a healthy one!", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://th.bing.com/th/id/OIP.GGlHesx4vXEbJK6IeAQ6IQHaFj?pid=ImgDet&rs=1", "Dieting doesn't mean dessert is off the table, it means you choose better.", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://getmomstrong.com/wp-content/uploads/2018/03/cookies-3.jpg", "Dessert can be a part of your diet, just choose smartly.", ScaleTypes.CENTER_CROP));

        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rcvProduct.setLayoutManager(gridLayoutManager);

/*        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvProduct.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rcvProduct.addItemDecoration(itemDecoration);*/


        mProductList = new ArrayList<>();
        showProductList();
        // Inflate the layout for this fragment
        return view;
    }

    private void filterList(String text) {
        List<Product> filteredList = new ArrayList<>();
        for (Product item : mProductList){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()) {
         //   Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
        }else{
            productAdapter.setFilteredList(filteredList);
        }
    }

    private void showProductList(){
        productService = ProductRepository.getProductService();
        Call<List<Product>> call = productService.getAllProduct();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.body() != null){
                    mProductList = response.body();
                    productAdapter = new ProductAdapter(mProductList);
                    rcvProduct.setAdapter(productAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void Mapping(){
        rcvProduct = view.findViewById(R.id.rcvProduct);
        searchView = view.findViewById(R.id.searchView);
    }
}