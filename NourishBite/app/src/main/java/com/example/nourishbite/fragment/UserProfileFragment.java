package com.example.nourishbite.fragment;

import static com.example.nourishbite.activity.HomePageActivity.currentUserId;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.nourishbite.R;
import com.example.nourishbite.api.UserRepository;
import com.example.nourishbite.model.User;
import com.example.nourishbite.service.UserService;
import com.example.nourishbite.viewmodel.UserViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserProfileFragment extends Fragment {

    UserService userService;
    EditText txtEmail, txtUserName, txtAddress, txtPassword;
    ImageView editBtn;
    Button updateBtn;
    int userId;




    public UserProfileFragment() {
        // Required empty public constructor
    }

    public static UserProfileFragment newInstance(){
        return new UserProfileFragment();
    }

    public void setUserData(User user){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        txtEmail = view.findViewById(R.id.txtEmail);
        txtAddress = view.findViewById(R.id.txtAddress);
        txtPassword = view.findViewById(R.id.txtPassword);
        txtUserName = view.findViewById(R.id.txtUserName);
        updateBtn = view.findViewById(R.id.updateButton);
        editBtn = view.findViewById(R.id.editButton);

        SetUnable();

        userInfo();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEmail = txtEmail.getText().toString();
                String newUsername = txtUserName.getText().toString();
                String newPassword = txtPassword.getText().toString();
                String newAddress = txtAddress.getText().toString();

                int userId1 = currentUserId;

                User newUser = new User(newEmail, newUsername, newPassword, newAddress);
                updateUser(userId1, newUser);

            }
        });


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetEnable();
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    private void SetUnable(){
        txtUserName.setEnabled(false);
        txtAddress.setEnabled(false);
        txtPassword.setEnabled(false);
        txtEmail.setEnabled(false);
    }

    private void SetEnable(){
        txtUserName.setEnabled(true);
        txtAddress.setEnabled(true);
        txtPassword.setEnabled(true);
        txtEmail.setEnabled(true);
    }

    private void userInfo(){
        UserViewModel userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        userViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                txtEmail.setText(user.getEmail());
                txtAddress.setText(user.getAddress());
                txtPassword.setText(user.getPassword());
                txtUserName.setText(user.getUserName());

                userId = user.getId();
            }
        });
    }

    private void updateUser(int id, User user){
        userService = UserRepository.getUserService();
        Call<User> call = userService.updateUser(id, user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null){
                    Toast.makeText(getContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getContext(), "Updated fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

}