package com.demo.pagingwithnetwork.data;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.demo.pagingwithnetwork.data.db.ProvinceDao;
import com.demo.pagingwithnetwork.data.model.Province;
import com.demo.pagingwithnetwork.data.net.ProvinceNetwork;

import java.util.ArrayList;
import java.util.List;

public class ProvinceRepository {

    private ProvinceRepository(ProvinceDao provinceDao, ProvinceNetwork provinceNetwork) {
        this.provinceDao = provinceDao;
        this.provinceNetwork = provinceNetwork;
    }

    private static volatile ProvinceRepository repository;

    private ProvinceDao provinceDao;

    private ProvinceNetwork provinceNetwork;

    public static ProvinceRepository create(ProvinceDao provinceDao, ProvinceNetwork provinceNetwork) {
        if (repository == null) synchronized (ProvinceRepository.class) {
            if (repository == null) repository = new ProvinceRepository(provinceDao, provinceNetwork);
        }
        return repository;
    }

    public LiveData<PagedList<Province>> getAllProvince() {
        writeDb();
        return getList();
    }

    public void refreshData() {
        writeDb();
    }

    private LiveData<PagedList<Province>> getList() {
        return new LivePagedListBuilder<>(provinceDao.getAllProvince(),
                new PagedList.Config.Builder()
                        .setPageSize(6)                         //配置分页加载的数量
                        .setEnablePlaceholders(true)     //配置是否启动PlaceHolders
                        .setInitialLoadSizeHint(8)              //初始化加载的数量
                        .build()).build();
    }

    private void writeDb(){
        new Thread(() -> {
            List<Province> provinceList = new ArrayList<>();
            try {
                provinceList.addAll((List<Province>) provinceNetwork.getAllProvince());
                provinceDao.insert(provinceList);
            } catch (Exception e) {
                if (provinceList != null && provinceList.size() > 0) provinceDao.update(provinceList);
                Log.e("TAG", "getAllProvince: ", e);
            }
        }).start();
    }
}
