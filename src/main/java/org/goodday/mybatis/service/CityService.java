package org.goodday.mybatis.service;


import org.goodday.mybatis.config.CountryMapConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private CountryMapConfig countryMapConfig;

    public List<String> getCityList() {
        return countryMapConfig.getCities();
    }
}
