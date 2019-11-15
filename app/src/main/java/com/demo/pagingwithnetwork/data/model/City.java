package com.demo.pagingwithnetwork.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "table_city")
public class City {

    private int provinceId = 0;

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey()
    private int cityCode = 0;

    @SerializedName("name")
    private String cityName = "";

    public City(int provinceId, int cityCode, String cityName) {
        this.provinceId = provinceId;
        this.cityCode = cityCode;
        this.cityName = cityName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "provinceId=" + provinceId +
                ", cityCode=" + cityCode +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
