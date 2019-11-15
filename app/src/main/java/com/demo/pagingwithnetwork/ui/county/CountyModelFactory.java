package com.demo.pagingwithnetwork.ui.county;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.demo.pagingwithnetwork.data.CountyRepository;

public class CountyModelFactory implements ViewModelProvider.Factory {

    private CountyRepository countyRepository;

    public CountyModelFactory(CountyRepository countyRepository) {
        this.countyRepository = countyRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CountyViewModel(countyRepository);
    }
}
