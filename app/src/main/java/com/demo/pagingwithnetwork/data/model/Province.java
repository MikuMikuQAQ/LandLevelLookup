package com.demo.pagingwithnetwork.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "table_province")
public class Province {

    @SerializedName("name")
    private String provinceName = "";

    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int provinceCode = 0;

    public Province(String provinceName, int provinceCode) {
        this.provinceName = provinceName;
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Override
    public String toString() {
        return "Province{" +
                "provinceName='" + provinceName + '\'' +
                ", provinceCode=" + provinceCode +
                '}';
    }
}
