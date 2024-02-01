package com.example.likeherotozero.beans;

import com.example.likeherotozero.entity.Co2EmissionsEntity;
import com.example.likeherotozero.service.Co2EmissionDataService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("co2")
@ViewScoped
public class Co2EmissionBean implements Serializable {

    private final Co2EmissionDataService co2EmissionDataService = new Co2EmissionDataService();

    private List<Co2EmissionsEntity> co2EmissionsEntityList;

    public List<Co2EmissionsEntity> getAllCo2EmissionData()
    {
        if (co2EmissionsEntityList == null) {
            co2EmissionsEntityList = co2EmissionDataService.getAllCo2EmissionData();
        }
        return co2EmissionsEntityList;
    }
}
