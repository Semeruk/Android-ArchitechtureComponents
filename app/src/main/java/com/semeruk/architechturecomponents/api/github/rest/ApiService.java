package com.semeruk.architechturecomponents.api.github.rest;

import com.semeruk.architechturecomponents.data.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/users/{user}")
    Call<User> getUser(@Path("user") String userId);
}