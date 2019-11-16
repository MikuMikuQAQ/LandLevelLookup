package com.demo.pagingwithnetwork.data;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.demo.pagingwithnetwork.data.db.CityDao;
import com.demo.pagingwithnetwork.data.model.City;
import com.demo.pagingwithnetwork.data.net.CityNetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CityRepository {

    private CityDao cityDao;
    private CityNetwork network;

    private CityRepository(CityDao cityDao, CityNetwork network) {
        this.cityDao = cityDao;
        this.network = network;
    }

    private static volatile CityRepository repository;

    public static CityRepository create(CityDao cityDao, CityNetwork network) {
        if (repository == null) synchronized (CityRepository.class) {
            if (repository == null) repository = new CityRepository(cityDao,network);
        }
        return repository;
    }

    public LiveData<PagedList<City>> getAllCity(int provinceId) {
        writeDb(provinceId);
        return getList(provinceId);
    }

    public void refreshData(int provinceId) {
        writeDb(provinceId);
    }

    public String getProvinceName(int provinceId) throws Exception {
        FutureTask<String> task = new FutureTask(()-> cityDao.getProvince(provinceId).getProvinceName());
        new Thread(task).start();
        return task.get();
    }

    private LiveData<PagedList<City>> getList(int provinceId) {
        return new LivePagedListBuilder<>(cityDao.getAllCity(provinceId),
                new PagedList.Config.Builder()
                        .setPageSize(6)                         //配置分页加载的数量
                        .setEnablePlaceholders(true)     //配置是否启动PlaceHolders
                        .setInitialLoadSizeHint(8)              //初始化加载的数量
                        .build()).build();
    }

    private void writeDb(int provinceId){
        new Thread(()->{
            List<City> cities = new ArrayList<>();
            try {
                for (City m:(List<City>)network.getAllCity(provinceId)) {
                    cities.add(new City(provinceId,m.getCityCode(),m.getCityName()));
                }
                cityDao.insert(cities);
            } catch (Exception e) {
                if (cities != null && cities.size() > 0) cityDao.update(cities);
                Log.e("CityRepository", "getAllCity: ",e );
            }
        }).start();
    }
}
