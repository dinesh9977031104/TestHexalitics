package com.example.testhexalitics.network;


import com.example.testhexalitics.model.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import static com.example.testhexalitics.network.ResponseCodeUtils.GET_HELPLINE_NUMBER;
import static com.example.testhexalitics.network.ResponseCodeUtils.HEADER;

public interface ApiInterface {


    @Headers(HEADER)
    @GET(GET_HELPLINE_NUMBER)
    Call<List<DataModel>> getHelpLineList();


}





