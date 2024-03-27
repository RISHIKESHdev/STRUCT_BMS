package com.lvbank.actions.navigate;

import com.lvbank.model.navigate.Address;
import com.lvbank.model.navigate.GeoLocation;

import java.sql.Connection;

public class NavigateService {
    public int getIdOnInsertAddressRecord(Connection connection, Address address){
        int addressId=0;

        NavigateDataLogic dataLogic = new NavigateDataLogic();

        if(address!=null && dataLogic.insertAddressRecord(connection,address)){
            addressId=address.getAddressId();
        }

        return addressId;
    }
    public int getIdOnInsertGeoLocationRecord(Connection connection, GeoLocation geoLocation){
        int geoLocationId=0;

        NavigateDataLogic dataLogic = new NavigateDataLogic();

        if(geoLocation!=null && dataLogic.insertGeoLocationRecord(connection,geoLocation)){
            geoLocationId=geoLocation.getGeoLocationId();
        }

        return geoLocationId;
    }
}
