package com.demo.pagingwithnetwork.data.net.api;

import java.util.List;

import com.demo.pagingwithnetwork.data.model.City;
import com.demo.pagingwithnetwork.data.model.County;
import com.demo.pagingwithnetwork.data.model.Province;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlaceAPI {

    @GET("api/china")
    Observable<List<Province>> getProvinces();

    @GET("api/china/{provinceId}")
    Observable<List<City>> getCities(@Path("provinceId") int provinceId);

    @GET("api/china/{provinceId}/{cityId}")
    Observable<List<County>> getCounties(@Path("provinceId") int provinceId, @Path("cityId") int cityId);

}
