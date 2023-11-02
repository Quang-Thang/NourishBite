package com.example.nourishbite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nourishbite.R;
import com.example.nourishbite.api.UserRepository;
import com.example.nourishbite.dialog.CustomProgressDialog;
import com.example.nourishbite.model.User;
import com.example.nourishbite.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    UserService userService;

    EditText txtEmail, txtPassword, txtCfPass, txtUserName;
    TextView haveAccLink;
    Button registerBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Mapping();
        final CustomProgressDialog dialog = new CustomProgressDialog(RegisterActivity.this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });


        haveAccLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogin();
            }
        });


    }

    private void createUser(){
        String email = txtEmail.getText().toString();
        String pass = txtPassword.getText().toString();
        String cfPass = txtCfPass.getText().toString();
        String userName = txtUserName.getText().toString();

        User createUser = new User(email, userName, pass);

        if(!pass.equals(cfPass)){
            Toast.makeText(this, "The Confirm Password is not match", Toast.LENGTH_SHORT).show();
        } else if (email.trim().isEmpty()) {
            Toast.makeText(this, "Please input Email", Toast.LENGTH_SHORT).show();
        }else if (userName.trim().isEmpty()){
            Toast.makeText(this, "Please input User Name", Toast.LENGTH_SHORT).show();
        }else{
            try {
                userService = UserRepository.getUserService();
                Call<User> call = userService.createUser(createUser);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.body() != null){
                            final CustomProgressDialog dialog = new CustomProgressDialog(RegisterActivity.this);

                            dialog.show();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                                    User newUser = new User(response.body().getEmail(), response.body().getPassword());
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("newUser", newUser);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            }, 2000);


                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "User Created Fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e){
                Log.d("Error ", e.getMessage());
            }


        }

    }

    private void clickLogin(){
        final CustomProgressDialog dialog = new CustomProgressDialog(RegisterActivity.this);

        dialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                dialog.cancel();
            }
        }, 2000);


    }

    public void Mapping(){
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtCfPass = findViewById(R.id.txtCfPassword);
        txtUserName = findViewById(R.id.txtUserName);
        haveAccLink = findViewById(R.id.haveAccLink);
        registerBtn = findViewById(R.id.registerButton);
    }
}