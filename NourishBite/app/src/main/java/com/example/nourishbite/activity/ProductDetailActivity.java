package com.example.nourishbite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nourishbite.R;

public class ProductDetailActivity extends AppCompatActivity {
    ImageButton returnBtn, addBtn, minusBtn;
    TextView tvAmount;
    private static int amount;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Mapping();

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, HomePageActivity.class);
                startActivity(intent);
                amount = 1;
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
                if (amount == 0){
                    return;
                }
                amount--;
                tvAmount.setText(amount+"");
            }
        });
    }

    private void Mapping(){
        returnBtn = findViewById(R.id.returnButton);
        addBtn = findViewById(R.id.addButton);
        minusBtn = findViewById(R.id.minusButton);
        tvAmount = findViewById(R.id.tvAmount);
    }
}
