package com.example.nourishbite.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nourishbite.R;
import com.example.nourishbite.api.ProductRepository;
import com.example.nourishbite.dialog.CustomProgressDialog;
import com.example.nourishbite.model.Product;
import com.example.nourishbite.service.ProductService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    ImageButton returnBtn, addBtn, minusBtn;
    TextView tvAmount;
    private static int amount;
    private Product mProduct;
    private TextView tvProductName, tvMainMaterial, tvDescription;
    private ImageView productImage;
    ProductService productService;

    int currentProductId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Mapping();

        final CustomProgressDialog dialog = new CustomProgressDialog(ProductDetailActivity.this);

        currentProductId = getIntent().getIntExtra("currentProductId", 1);

        getProductById(currentProductId);


        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount = 1;

                dialog.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(ProductDetailActivity.this, HomePageActivity.class);
                        startActivity(intent);
                        dialog.cancel();
                    }
                }, 2000);

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount++;
                tvAmount.setText(amount+"");
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount == 1){
                    return;
                }
                amount--;
                tvAmount.setText(amount+"");
            }
        });
    }

    private void getProductById(int id){
        productService = ProductRepository.getProductService();
        Call<Product> call = productService.getProductById(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.body() != null){
                    Toast.makeText(ProductDetailActivity.this, "Ngon", Toast.LENGTH_SHORT).show();
                    tvProductName.setText(response.body().getName());
                    tvDescription.setText(response.body().getDescription());
                    tvMainMaterial.setText(response.body().getMaterial().getName());

                    String imageUrl = response.body().getImage();

                    Picasso.get().load(imageUrl).into(productImage);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }


    private void Mapping(){
        returnBtn = findViewById(R.id.returnButton);
        addBtn = findViewById(R.id.addButton);
        minusBtn = findViewById(R.id.minusButton);
        tvAmount = findViewById(R.id.tvAmount);
        tvProductName = findViewById(R.id.tvProductName);
        tvMainMaterial = findViewById(R.id.tvMainMaterial);
        tvDescription = findViewById(R.id.tvDescription);
        productImage = findViewById(R.id.productImage);

    }
}
