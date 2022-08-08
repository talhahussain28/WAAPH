package waaph.gb.com.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import waaph.gb.com.responses.BusinessTypeResponse;
import waaph.gb.com.responses.CustomerGroupResponse;
import waaph.gb.com.entities.cdf.GetAllRegionResponse;
import waaph.gb.com.responses.LoginRequest;
import waaph.gb.com.responses.LoginResponse;

/**
 * Created by Muhammadtalhahussian on 24/07/2022.
 */
public interface ApiInterface {

    @Headers({"Accept: */*", "Content-Type: application/json"})
    @POST("Account/AuthenticateUser")
    Call<LoginResponse> login(@Body LoginRequest body);

    //GET
    @GET("Master/GetRegion")
    Call<GetAllRegionResponse> getAllRegion();

    @GET("Master/GetCustomerGroup")
    Call<CustomerGroupResponse> getCustomerGroup();

    @GET("Master/GetBusinessType")
    Call<BusinessTypeResponse> getBusinessType();
}
