package com.example.likeherotozero.service;

import com.example.likeherotozero.dao.Co2EmissionDAO;
import com.example.likeherotozero.entity.Co2EmissionsEntity;

import java.util.List;

public class Co2EmissionDataService {

    private Co2EmissionDAO co2EmissionDAO;

    public Co2EmissionDataService()
    {
        this.co2EmissionDAO = new Co2EmissionDAO();
    }

    public List<Co2EmissionsEntity> getAllCo2EmissionData()
    {
        return co2EmissionDAO.getAllCo2EmissionData();
    }

    public List<Co2EmissionsEntity> getAllCo2EmissionDataByCountry(String selectedCountry)
    {
        return co2EmissionDAO.getCo2EmissionByCountry(selectedCountry);
    }
}
