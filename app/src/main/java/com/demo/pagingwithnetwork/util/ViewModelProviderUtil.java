package com.demo.pagingwithnetwork.util;

import com.demo.pagingwithnetwork.data.CityRepository;
import com.demo.pagingwithnetwork.data.CountyRepository;
import com.demo.pagingwithnetwork.data.ProvinceRepository;
import com.demo.pagingwithnetwork.data.db.MainDatabase;
import com.demo.pagingwithnetwork.data.net.CityNetwork;
import com.demo.pagingwithnetwork.data.net.CountyNetwork;
import com.demo.pagingwithnetwork.data.net.ProvinceNetwork;
import com.demo.pagingwithnetwork.ui.city.CityModelFactory;
import com.demo.pagingwithnetwork.ui.county.CountyModelFactory;
import com.demo.pagingwithnetwork.ui.province.ProvinceModelFactory;

public class ViewModelProviderUtil {

    private static volatile ViewModelProviderUtil getInstance;

    private ViewModelProviderUtil(){}

    public static ViewModelProviderUtil create() {
        if (getInstance == null) synchronized (ViewModelProviderUtil.class) {
            if (getInstance == null) getInstance = new ViewModelProviderUtil();
        }
        return getInstance;
    }

    private static ProvinceRepository getProvinceRepository(){
        return ProvinceRepository.create(MainDatabase.newInstance().getProvinceDao(), ProvinceNetwork.create());
    }

    private static CityRepository getCityRepository(){
        return CityRepository.create(MainDatabase.newInstance().getCityDao(), CityNetwork.create());
    }

    private static CountyRepository getCountyRepository() {
        return CountyRepository.create(MainDatabase.newInstance().getCountyDao(), CountyNetwork.create());
    }

    public CityModelFactory getCityModelFactory(){
        return new CityModelFactory(getCityRepository());
    }

    public CountyModelFactory getCountyModelFactory(){
        return new CountyModelFactory(getCountyRepository());
    }

    public ProvinceModelFactory getProvinceModelFactory(){
        return new ProvinceModelFactory(getProvinceRepository());
    }
}
