package com.demo.pagingwithnetwork.data.db;

import androidx.paging.DataSource;
import androidx.room.*;
import com.demo.pagingwithnetwork.data.model.City;
import com.demo.pagingwithnetwork.data.model.Province;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CityDao {

    @Query("SELECT * FROM table_city WHERE provinceId == :id")
    DataSource.Factory<Integer, City> getAllCity(int id);
    
    @Query("SELECT * FROM table_province WHERE id == :provinceId")
    Province getProvince(int provinceId);

    @Insert(onConflict = REPLACE)
    void insert(City county);

    @Insert(onConflict = REPLACE)
    void insert(List<City> counties);

    @Update(onConflict = REPLACE)
    void update(List<City> counties);

    @Update(onConflict = REPLACE)
    void update(City county);
}
