package com.demo.pagingwithnetwork.data.net;

import com.demo.pagingwithnetwork.base.BaseNetwork;
import com.demo.pagingwithnetwork.data.net.api.PlaceAPI;

public class CityNetwork extends BaseNetwork {

    private CityNetwork(){}

    private static volatile CityNetwork network;

    public static CityNetwork create() {
        if (network == null) synchronized (CityNetwork.class) {
            if (network == null) network = new CityNetwork();
        }
        return network;
    }

    private PlaceAPI api = new ServiceCreator().create(PlaceAPI.class);

    public Object getAllCity(int proninceId) throws Exception {
        return getData(api.getCities(proninceId));
    }
}
