package com.example.nourishbite.adapter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nourishbite.activity.ProductDetailActivity;
import com.example.nourishbite.api.ProductRepository;
import com.example.nourishbite.service.ProductService;
import com.squareup.picasso.Picasso;

import com.example.nourishbite.R;
import com.example.nourishbite.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private List<Product> mListProduct;
    public static int currentProductId;

    public ProductAdapter(List<Product> mListProduct) {
        this.mListProduct = mListProduct;
    }

    public void setFilteredList(List<Product> filteredList){
        this.mListProduct = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_product, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Product product = mListProduct.get(position);
        if(product == null){
            return;
        }
        SpannableString spannale = new SpannableString("$" + String.valueOf(product.getPrice()));
        spannale.setSpan(new StyleSpan(Typeface.BOLD), 0 ,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        SpannableString spannable1 = new SpannableString("" + product.getMaterial().getName());


        holder.tvName.setText(product.getName());
        holder.tvMaterial.setText("Made from " + product.getMaterial().getName());
        holder.tvPrice.setText(spannale);

        String imageUrl = product.getImage();

        Picasso.get().load(imageUrl).into(holder.productImg);
        holder.productCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductDetailActivity.class);
                intent.putExtra("currentProductId", product.getProductId());
                v.getContext().startActivity(intent);
                ProgressDialog progressDialog = ProgressDialog.show(v.getContext(), "Loading...", "");
            }
        });


    }

    @Override
    public int getItemCount() {
        if ((mListProduct != null)){
            return mListProduct.size();
        }
        return 0;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvName, tvMaterial, tvPrice;
        private CardView productCV;
        private final ImageView productImg;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvProductName);
            tvMaterial = itemView.findViewById(R.id.tvMaterial);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            productImg = itemView.findViewById(R.id.productImage);
            productCV = itemView.findViewById(R.id.productCardView);
        }
    }

}
