package com.demo.pagingwithnetwork.ui.province;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.demo.pagingwithnetwork.data.ProvinceRepository;

public class ProvinceModelFactory implements ViewModelProvider.Factory {

    private ProvinceRepository provinceRepository;

    public ProvinceModelFactory(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    /**
     * Creates a new instance of the given {@code Class}.
     * <p>
     *
     * @param modelClass a {@code Class} whose instance is requested
     * @return a newly created ViewModel
     */
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProvinceViewModel(provinceRepository);
    }
}
