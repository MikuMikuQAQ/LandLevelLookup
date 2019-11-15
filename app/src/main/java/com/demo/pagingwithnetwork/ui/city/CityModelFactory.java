package com.demo.pagingwithnetwork.ui.city;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.demo.pagingwithnetwork.data.CityRepository;

public class CityModelFactory implements ViewModelProvider.Factory {

    private CityRepository cityRepository;

    public CityModelFactory(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CityViewModel(cityRepository);
    }
}
