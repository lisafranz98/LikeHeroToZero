package com.example.likeherotozero.beans;

import com.example.likeherotozero.entity.Co2EmissionsEntity;
import com.example.likeherotozero.entity.CountryEntity;
import com.example.likeherotozero.entity.UserEntity;
import com.example.likeherotozero.service.Co2EmissionDataService;
import com.example.likeherotozero.service.CountryService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import org.primefaces.event.CellEditEvent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named("co2")
@ViewScoped
public class Co2EmissionBean implements Serializable {

    private final Co2EmissionDataService co2EmissionDataService = new Co2EmissionDataService();
    private final CountryService countryService = new CountryService();
    private List<Co2EmissionsEntity> co2EmissionsEntityList;
    private List<CountryEntity> countryEntityList;

    private String selectedCountry;

    private String countryCode;

    private int date;

    private BigDecimal amountValue;

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

    public Integer getLoggedInUserId() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        //return (Integer) context.getExternalContext().getSessionMap().get("userId");
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null)
        {
            UserEntity user = (UserEntity) session.getAttribute("user");
            if (user != null)
            {
                return user.getUserId();
            }
        }

        return null;
    }

    public void addEmission()
    {
        Co2EmissionsEntity newEmission = new Co2EmissionsEntity();
        newEmission.setCountryCode(countryCode);
        newEmission.setDate(date);
        newEmission.setAmountValue(amountValue);
        Integer userId = getLoggedInUserId();
        if(userId != null)
        {
            newEmission.setUserId(userId);
            co2EmissionDataService.addCo2Emission(newEmission);
        }
    }

    public void onCellEdit(CellEditEvent<Object> event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (!newValue.equals(oldValue)) {
            Co2EmissionsEntity editedEmission = co2EmissionsEntityList.get(event.getRowIndex());
            if (event.getColumn().getColumnKey().equals("countryCode")) {
                editedEmission.setCountryCode((String) newValue);
            } else if (event.getColumn().getColumnKey().equals("date")) {
                editedEmission.setDate((Integer) newValue);
            } else if (event.getColumn().getColumnKey().equals("amountValue")) {
                editedEmission.setAmountValue((BigDecimal) newValue);
            }
            co2EmissionDataService.updateCo2Emission(editedEmission);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zelle geändert", "Alter Wert: " + oldValue + ", Neuer Wert: " + newValue));
        }
    }

    public void deleteEmission(Co2EmissionsEntity emission) {
        co2EmissionDataService.deleteCo2Emission(emission.getEmissionsId());
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public BigDecimal getAmountValue() {
        return amountValue;
    }

    public void setAmountValue(BigDecimal amountValue) {
        this.amountValue = amountValue;
    }
}