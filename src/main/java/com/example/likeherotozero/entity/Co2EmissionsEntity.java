package com.example.likeherotozero.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name = "co2_emissions", schema = "like_hero_to_zero")
public class Co2EmissionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "emissions_id")
    private int emissionsId;

    @Override
    public String toString() {
        return "Co2EmissionsEntity{" +
                "countryCode='" + countryCode + '\'' +
                ", date=" + date +
                ", amountValue=" + amountValue +
                '}';
    }

    @Basic
    @Column(name = "country_code", insertable = false, updatable = false)
    private String countryCode;
    @Basic
    @Column(name = "date")
    private Object date;
    @Basic
    @Column(name = "amount_value")
    private BigDecimal amountValue;
    @Basic
    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;
    @OneToMany(mappedBy = "co2EmissionsByEmissionsId")
    private Collection<ChangelogEntity> changelogsByEmissionsId;
    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "country_code", nullable = false)
    private CountryEntity countryByCountryCode;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity userByUserId;

    public int getEmissionsId() {
        return emissionsId;
    }

    public void setEmissionsId(int emissionsId) {
        this.emissionsId = emissionsId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public BigDecimal getAmountValue() {
        return amountValue;
    }

    public void setAmountValue(BigDecimal amountValue) {
        this.amountValue = amountValue;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Co2EmissionsEntity that = (Co2EmissionsEntity) o;

        if (emissionsId != that.emissionsId) return false;
        if (countryCode != null ? !countryCode.equals(that.countryCode) : that.countryCode != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (amountValue != null ? !amountValue.equals(that.amountValue) : that.amountValue != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = emissionsId;
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (amountValue != null ? amountValue.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    public Collection<ChangelogEntity> getChangelogsByEmissionsId() {
        return changelogsByEmissionsId;
    }

    public void setChangelogsByEmissionsId(Collection<ChangelogEntity> changelogsByEmissionsId) {
        this.changelogsByEmissionsId = changelogsByEmissionsId;
    }

    public CountryEntity getCountryByCountryCode() {
        return countryByCountryCode;
    }

    public void setCountryByCountryCode(CountryEntity countryByCountryCode) {
        this.countryByCountryCode = countryByCountryCode;
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
