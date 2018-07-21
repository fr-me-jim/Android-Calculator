package com.jediupc.calculator;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {

    @POST("v1/categorias")
    Call<PostPuntuaciones> createCategory(@Body PostPuntuaciones category);

    @GET("v1/products")
    Call<GetPuntuaciones> getProduct();
}
