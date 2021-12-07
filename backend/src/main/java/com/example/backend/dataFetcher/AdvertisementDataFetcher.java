package com.example.backend.dataFetcher;

import com.example.backend.model.Advertisement;
import com.example.backend.model.User;
import com.example.backend.services.AdvertisementService;
import com.example.backend.services.UserService;
import graphql.schema.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Map;

@Component
public class AdvertisementDataFetcher implements DataFetcher<Advertisement>{

    private final AdvertisementService advertisementService;

    @Autowired
    AdvertisementDataFetcher(AdvertisementService advertisementService){
        this.advertisementService = advertisementService;
    }

    @Override
    public Advertisement get(DataFetchingEnvironment env) {
        Map args = env.getArguments();
        Advertisement advertisement = advertisementService.findOneById(new ObjectId(String.valueOf(args.get("id"))));
        return null;
    }
}
