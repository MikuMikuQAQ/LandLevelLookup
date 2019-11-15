package com.demo.pagingwithnetwork.ui.city;

import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import com.demo.pagingwithnetwork.data.CityRepository;
import com.demo.pagingwithnetwork.data.model.City;

public class CityViewModel extends ViewModel {

    private CityRepository cityRepository;
    private int id = -1;

    public MutableLiveData<String> cityName = new MutableLiveData<>();

    void setId(Bundle bundle) {
        this.id = CityFragmentArgs.fromBundle(bundle).getProvinceId();
    }

    CityViewModel(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    LiveData<PagedList<City>> getAllCity() {
        return cityRepository.getAllCity(id);
    }

    void refreshList() {
        cityRepository.refreshData(id);
    }

    void getTitle() {
        try {
            cityName.postValue(cityRepository.getProvinceName(id));
        } catch (Exception e) {
            Log.e("CityViewModel", "getTitle: ", e);
        }
    }
}
