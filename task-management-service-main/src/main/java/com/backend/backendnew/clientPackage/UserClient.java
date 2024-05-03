package com.backend.backendnew.clientPackage;

import com.backend.backendnew.AuthTokenVerifyPackage.AuthTokenService;
import com.backend.backendnew.model.User;
import com.backend.backendnew.service.AdminService.ResourceManagementService.ResourceMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;



public class UserClient {

    private static final String USER_API_URL = "http://localhost:8081/server/";

    private final RestTemplate restTemplate=new RestTemplate();


    public List<User> getUsers() {
        return List.of(Objects.requireNonNull(restTemplate.getForObject(USER_API_URL+"listofuser", User[].class)));
    }


    public Boolean doesUserExist(String UserName, Integer role) {
        return restTemplate.getForObject(
                USER_API_URL + "checkUser/{UserName}/{role}",
                Boolean.class,
                UserName,
                role
        );
    }


}
