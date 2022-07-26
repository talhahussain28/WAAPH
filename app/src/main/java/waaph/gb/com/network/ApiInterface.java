package waaph.gb.com.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import waaph.gb.com.responses.LoginRequest;
import waaph.gb.com.responses.LoginResponse;

/**
 * Created by Muhammadtalhahussian on 24/07/2022.
 */
public interface ApiInterface {

    @Headers({"Accept: */*", "Content-Type: application/json"})
    @POST("Account/AuthenticateUser")
    Call<LoginResponse> login(@Body LoginRequest body);
}
