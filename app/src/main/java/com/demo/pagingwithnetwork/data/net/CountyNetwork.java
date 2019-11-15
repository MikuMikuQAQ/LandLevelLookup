package com.demo.pagingwithnetwork.data.net;

import com.demo.pagingwithnetwork.base.BaseNetwork;
import com.demo.pagingwithnetwork.data.net.api.PlaceAPI;

public class CountyNetwork extends BaseNetwork {

    private CountyNetwork(){}

    private static volatile CountyNetwork network;

    public static CountyNetwork create(){
        if (network == null) synchronized (CountyNetwork.class) {
            if (network == null) network = new CountyNetwork();
        }
        return network;
    }

    private PlaceAPI api = new ServiceCreator().create(PlaceAPI.class);

    public Object getAllCounty(int provinceId,int cityId) throws Exception {
        return getData(api.getCounties(provinceId,cityId));
    }
}
