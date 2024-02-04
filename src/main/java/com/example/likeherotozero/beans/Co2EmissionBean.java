package com.example.likeherotozero.beans;

import com.example.likeherotozero.entity.Co2EmissionsEntity;
import com.example.likeherotozero.entity.CountryEntity;
import com.example.likeherotozero.service.Co2EmissionDataService;
import com.example.likeherotozero.service.CountryService;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("co2")
@ViewScoped
public class Co2EmissionBean implements Serializable {

    private final Co2EmissionDataService co2EmissionDataService = new Co2EmissionDataService();
    private final CountryService countryService = new CountryService();
    private List<Co2EmissionsEntity> co2EmissionsEntityList;
    private List<CountryEntity> countryEntityList;

    private String selectedCountry;

    public List<Co2EmissionsEntity> getAllCo2EmissionData()
    {
        if (co2EmissionsEntityList == null) {
            co2EmissionsEntityList = co2EmissionDataService.getAllCo2EmissionData();
        }
        return co2EmissionsEntityList;
    }

    public List<CountryEntity> getAllCountryData() {
        if (countryEntityList == null) {
            countryEntityList = countryService.getAllCountries();
        }
        return countryEntityList;
    }

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public List<Co2EmissionsEntity> getCo2EmissionsForSelectedCountry()
    {
        return co2EmissionDataService.getAllCo2EmissionDataByCountry(selectedCountry);
    }

    public void loadCo2Emissions(AjaxBehaviorEvent event)
    {
        co2EmissionsEntityList = getCo2EmissionsForSelectedCountry();
    }
}