package com.demo.pagingwithnetwork.ui.province;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import com.demo.pagingwithnetwork.data.ProvinceRepository;
import com.demo.pagingwithnetwork.data.model.Province;

public class ProvinceViewModel extends ViewModel {

    private ProvinceRepository repository;

    ProvinceViewModel(ProvinceRepository provinceRepository) {
        repository = provinceRepository;
    }

    LiveData<PagedList<Province>> loadData() {
        return repository.getAllProvince();
    }

    void refreshList() {
        repository.refreshData();
    }

}
