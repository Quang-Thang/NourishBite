package com.example.nourishbite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nourishbite.R;
import com.example.nourishbite.api.UserRepository;
import com.example.nourishbite.dialog.CustomProgressDialog;
import com.example.nourishbite.model.User;
import com.example.nourishbite.service.UserService;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    UserService userService;
    EditText txtEmail, txtPassword;
    Button loginBtn;
    TextView registerLink;
    private List<User> mUserList;
    private User mUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();

        mUserList = new ArrayList<>();
        getListUsers();

        final CustomProgressDialog dialog = new CustomProgressDialog(MainActivity.this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickLogin();

                        dialog.cancel();
                    }
                }, 2000);
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickRegister();

                        dialog.cancel();
                    }
                }, 2000);

            }
        });

    }

    private void clickLogin(){
        String userName = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if(userName.trim().isEmpty()){
            Toast.makeText(this, "Please input email", Toast.LENGTH_SHORT).show();
        } else if (password.trim().isEmpty()) {
            Toast.makeText(this, "Please input password", Toast.LENGTH_SHORT).show();
        }else{
            if(mUserList == null || mUserList.isEmpty()){
                return;
            }
            boolean isHasUser = false;
            for (User user : mUserList){
                if(user.getEmail().equals(userName) && user.getPassword().equals(password)){
                    isHasUser = true;
                    mUser = user;
                    break;
                }
            }
            if(isHasUser){
                Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user", mUser);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }


    }

    private void clickRegister(){
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void getListUsers(){
        userService = UserRepository.getUserService();


        Call<List<User>> call = userService.getAllUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mUserList = response.body();
                Log.e("List user", mUserList.size() + "");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }


    public void Mapping(){
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        loginBtn = findViewById(R.id.loginButton);
        registerLink = findViewById(R.id.registerLink);
    }
}