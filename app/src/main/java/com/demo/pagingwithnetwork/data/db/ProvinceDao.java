package com.demo.pagingwithnetwork.data.db;

import androidx.paging.DataSource;
import androidx.room.*;
import com.demo.pagingwithnetwork.data.model.Province;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProvinceDao {

    @Query("SELECT * FROM table_province")
    DataSource.Factory<Integer, Province> getAllProvince();

    @Insert
    void insert(Province province);

    @Insert(onConflict = REPLACE)
    void insert(List<Province> provinces);

    @Update(onConflict = REPLACE)
    void update(List<Province> provinces);

    @Update(onConflict = REPLACE)
    void update(Province province);

    @Update(onConflict = REPLACE)
    void update(Province... province);
}
