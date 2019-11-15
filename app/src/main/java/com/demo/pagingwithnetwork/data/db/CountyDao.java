package com.demo.pagingwithnetwork.data.db;

import androidx.paging.DataSource;
import androidx.room.*;
import com.demo.pagingwithnetwork.data.model.City;
import com.demo.pagingwithnetwork.data.model.County;

import java.util.List;

@Dao
public interface CountyDao {

    @Query("SELECT * FROM table_county WHERE cityId = :id")
    DataSource.Factory<Integer, County> getAllCounty(int id);

    @Query("SELECT * FROM table_city WHERE id = :cityId")
    City getCity(int cityId);

    @Insert
    void insert(County county);

    @Insert
    void insert(List<County> counties);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(List<County> counties);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(County county);
}
