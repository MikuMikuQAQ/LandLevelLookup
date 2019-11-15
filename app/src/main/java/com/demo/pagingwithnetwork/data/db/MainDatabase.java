package com.demo.pagingwithnetwork.data.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.demo.pagingwithnetwork.BaseApplication;
import com.demo.pagingwithnetwork.data.model.City;
import com.demo.pagingwithnetwork.data.model.County;
import com.demo.pagingwithnetwork.data.model.Province;

@Database(entities = {City.class, County.class, Province.class},version = 5,exportSchema = false)
public abstract class MainDatabase extends RoomDatabase {

    public abstract CityDao getCityDao();

    public abstract CountyDao getCountyDao();

    public abstract ProvinceDao getProvinceDao();

    private static volatile MainDatabase getInstance;

    public static MainDatabase newInstance(){
        if (getInstance == null) synchronized (MainDatabase.class) {
            if (getInstance == null) getInstance = Room.databaseBuilder(BaseApplication.mContext,MainDatabase.class,"place").fallbackToDestructiveMigration().build();
        }
        return getInstance;
    }

}
