package com.example.likeherotozero.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "country", schema = "like_hero_to_zero")
public class CountryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "country_code")
    private String countryCode;
    @Basic
    @Column(name = "country_name")
    private String countryName;
    @OneToMany(mappedBy = "countryByCountryCode")
    private Collection<Co2EmissionsEntity> co2EmissionsByCountryCode;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryEntity that = (CountryEntity) o;

        if (countryCode != null ? !countryCode.equals(that.countryCode) : that.countryCode != null) return false;
        if (countryName != null ? !countryName.equals(that.countryName) : that.countryName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = countryCode != null ? countryCode.hashCode() : 0;
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return countryName;
    }

    public Collection<Co2EmissionsEntity> getCo2EmissionsByCountryCode() {
        return co2EmissionsByCountryCode;
    }

    public void setCo2EmissionsByCountryCode(Collection<Co2EmissionsEntity> co2EmissionsByCountryCode) {
        this.co2EmissionsByCountryCode = co2EmissionsByCountryCode;
    }
}
