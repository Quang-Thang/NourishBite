package com.example.nourishbite.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nourishbite.R;
import com.example.nourishbite.model.User;

public class LoginActivity extends AppCompatActivity {

    TextView userInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userInfo = findViewById(R.id.tvUserInfo);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            User user = (User) bundle.getSerializable("object_user");
            if(user != null){
                userInfo.setText(user.toString());
            }
        }


    }
}
