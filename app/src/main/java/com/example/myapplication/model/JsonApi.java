package com.example.myapplication.model;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonApi {

    @GET("api/v2/problemcontroller")
    Call<List<Post>> getPosts();  //lista jsons

    @POST("api/v2/problemcontroller/response")
    Call<List<String>> createPost(@Body String raspuns);

}
