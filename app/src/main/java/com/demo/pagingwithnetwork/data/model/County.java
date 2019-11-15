package com.demo.pagingwithnetwork.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "table_county")
public class County {

    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int countyId = 0;

    @SerializedName("name")
    private String countyName = "";

    @SerializedName("weather_id")
    private String weatherId = "";

    private int cityId;

    public County(int countyId, String countyName, String weatherId, int cityId) {
        this.countyId = countyId;
        this.countyName = countyName;
        this.weatherId = weatherId;
        this.cityId = cityId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "County{" +
                "countyId=" + countyId +
                ", countyName='" + countyName + '\'' +
                ", weatherId='" + weatherId + '\'' +
                ", cityId=" + cityId +
                '}';
    }
}
