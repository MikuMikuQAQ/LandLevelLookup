package com.demo.pagingwithnetwork.data.net;

import com.demo.pagingwithnetwork.base.BaseNetwork;
import com.demo.pagingwithnetwork.data.net.api.PlaceAPI;

public class ProvinceNetwork extends BaseNetwork {

    private ProvinceNetwork(){}

    private static volatile ProvinceNetwork network;

    public static ProvinceNetwork create() {
        if (network == null) synchronized (ProvinceNetwork.class) {
            if (network == null) network = new ProvinceNetwork();
        }
        return network;
    }

    private PlaceAPI api = new ServiceCreator().create(PlaceAPI.class);

    public Object getAllProvince() throws Exception {
        return getData(api.getProvinces());
    }
}
