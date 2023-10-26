package com.example.nourishbite.service;

import com.example.nourishbite.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
String USER  = "users";

@GET(USER)
Call<List<User>> getAllUser();

@PUT(USER + "/{id}")
Call<User> updateUser(@Path("id") Object id, @Body User user);

    @POST(USER)
    Call<User> createUser(@Body User user);
}


