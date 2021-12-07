package com.example.backend.dataFetcher;

import com.example.backend.model.Advertisement;
import com.example.backend.model.User;
import com.example.backend.services.AdvertisementService;
import com.example.backend.services.UserService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllAdvertisementDataFetcher implements DataFetcher<List<Advertisement>>{
    private AdvertisementService advertisementService;

    @Autowired
    AllAdvertisementDataFetcher(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @Override
    public List<Advertisement> get(DataFetchingEnvironment env) {
        Advertisement advertisement =  env.getSource();
        List<Advertisement> advertisements = new ArrayList<>();
        advertisements = advertisementService.findAllAdvertisement();
        return advertisements;
    }
}
