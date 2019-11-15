package com.demo.pagingwithnetwork.data;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.demo.pagingwithnetwork.data.db.CountyDao;
import com.demo.pagingwithnetwork.data.model.County;
import com.demo.pagingwithnetwork.data.net.CountyNetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CountyRepository {

    private CountyDao countyDao;

    private CountyNetwork network;

    private CountyRepository(CountyDao countyDao, CountyNetwork network) {
        this.countyDao = countyDao;
        this.network = network;
    }

    private static volatile CountyRepository repository;

    public static CountyRepository create(CountyDao countyDao, CountyNetwork network) {
        if (repository == null) synchronized (CountyRepository.class) {
            if (repository == null) repository = new CountyRepository(countyDao,network);
        }
        return repository;
    }

    public LiveData<PagedList<County>> getAllCounty(int provinceId,int cityId) {
        writeDb(provinceId, cityId);
        return getList(cityId);
    }

    public void refreshData(int provinceId,int cityId) {
        writeDb(provinceId, cityId);
    }

    public String getCityName(int cityId) throws Exception {
        FutureTask<String> task = new FutureTask(()->countyDao.getCity(cityId).getCityName());
        new Thread(task).start();
        return task.get();
    }

    private LiveData<PagedList<County>> getList(int cityId) {
        return new LivePagedListBuilder<>(countyDao.getAllCounty(cityId),
                new PagedList.Config.Builder()
                        .setPageSize(6)                         //配置分页加载的数量
                        .setEnablePlaceholders(true)     //配置是否启动PlaceHolders
                        .setInitialLoadSizeHint(8)              //初始化加载的数量
                        .build()).build();
    }

    private void writeDb(int provinceId,int cityId){
        new Thread(() -> {
            List<County> countyList = new ArrayList<>();
            try {
                for (County c:(List<County>) network.getAllCounty(provinceId, cityId)) {
                    countyList.add(new County(c.getCountyId(),c.getCountyName(),c.getWeatherId(),cityId));
                }
                countyDao.insert(countyList);
            } catch (Exception e) {
                if (countyList != null && countyList.size() > 0) countyDao.update(countyList);
                Log.e("TAG", "getAllProvince: ", e);
            }
        }).start();
    }
}
