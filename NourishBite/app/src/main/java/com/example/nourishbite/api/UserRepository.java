package com.example.nourishbite.api;

import com.example.nourishbite.service.UserService;

public class UserRepository {
public static UserService getUserService(){
    return APIClient.getClient().create(UserService.class);
}
}
