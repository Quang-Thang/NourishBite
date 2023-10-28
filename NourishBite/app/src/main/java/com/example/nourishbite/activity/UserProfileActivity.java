package com.example.nourishbite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nourishbite.R;
import com.example.nourishbite.api.UserRepository;
import com.example.nourishbite.model.User;
import com.example.nourishbite.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {

    UserService userService;
    EditText txtEmail, txtUserName, txtAddress, txtPassword;
    ImageView editBtn;
    Button updateBtn, shopBtn;
    int userId;
    User passUser;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Mapping();
        SetUnable();
        updateBtn.setEnabled(false);



        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            User user = (User) bundle.getSerializable("object_user");
            if(user != null){
                txtEmail.setText(user.getEmail());
                txtUserName.setText(user.getUserName());
                txtPassword.setText(user.getPassword());
                txtAddress.setText(user.getAddress());
                userId = user.getId();

                passUser = user;
            }
        }

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetEnable();
                updateBtn.setEnabled(true);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEmail = txtEmail.getText().toString();
                String newUserName = txtUserName.getText().toString();
                String newPassword = txtPassword.getText().toString();
                String newAddress = txtAddress.getText().toString();

                User newInfo = new User(newEmail ,newUserName, newPassword, newAddress);
                updateUser(userId, newInfo);

            }
        });

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, HomePageActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("passUser", passUser);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });


    }

    private void updateUser(int id, User user){
        try {

        userService = UserRepository.getUserService();
        Call<User> call = userService.updateUser(id, user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body() != null){
                    Toast.makeText(UserProfileActivity.this, "Updated successfully" + response.body(), Toast.LENGTH_SHORT).show();
                    recreate();

                }else{
                    Toast.makeText(UserProfileActivity.this, "Updated fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        }catch (Exception e){
            Log.d("Error", e.getMessage());
        }
    }

    private void SetEnable(){
        txtEmail.setEnabled(true);
        txtPassword.setEnabled(true);
        txtAddress.setEnabled(true);
        txtUserName.setEnabled(true);
    }

    private void SetUnable(){
        txtEmail.setEnabled(false);
        txtPassword.setEnabled(false);
        txtAddress.setEnabled(false);
        txtUserName.setEnabled(false);
    }

    private void Mapping(){
        txtEmail = findViewById(R.id.txtEmail);
        txtAddress = findViewById(R.id.txtAddress);
        txtPassword = findViewById(R.id.txtPassword);
        txtUserName = findViewById(R.id.txtUserName);
        editBtn = findViewById(R.id.editButton);
        updateBtn = findViewById(R.id.updateButton);
        shopBtn = findViewById(R.id.shopButton);
    }
}
