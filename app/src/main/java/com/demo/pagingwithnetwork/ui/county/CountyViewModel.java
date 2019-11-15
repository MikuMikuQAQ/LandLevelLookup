package com.demo.pagingwithnetwork.ui.county;

import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import com.demo.pagingwithnetwork.data.CountyRepository;
import com.demo.pagingwithnetwork.data.model.County;

public class CountyViewModel extends ViewModel {

    private CountyRepository countyRepository;

    private int provinceId = -1;

    private int cityId = -1;

    public MutableLiveData<String> cityName = new MutableLiveData<>();

    CountyViewModel(CountyRepository countyRepository) {
        this.countyRepository = countyRepository;
    }

    void setBundle(Bundle bundle) {
        provinceId = CountyFragmentArgs.fromBundle(bundle).getProvinceId();
        cityId = CountyFragmentArgs.fromBundle(bundle).getCityId();
    }

    LiveData<PagedList<County>> getAllCounty() {
        return countyRepository.getAllCounty(provinceId, cityId);
    }

    void refreshList() {
        countyRepository.refreshData(provinceId,cityId);
    }

    void getCityName() {
        try {
            cityName.postValue(countyRepository.getCityName(cityId));
        } catch (Exception e) {
            Log.e("CountyViewModel", "getCityName: ",e );
        }
    }
}
