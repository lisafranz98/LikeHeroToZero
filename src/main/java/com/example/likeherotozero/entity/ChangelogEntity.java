package com.example.likeherotozero.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "changelog", schema = "like_hero_to_zero")
public class ChangelogEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "changelog_id")
    private int changelogId;
    @Basic
    @Column(name = "emissions_id", insertable = false, updatable = false)
    private Integer emissionsId;
    @Basic
    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;
    @Basic
    @Column(name = "change_date")
    private Timestamp changeDate;
    @Basic
    @Column(name = "change_type")
    private Object changeType;
    @ManyToOne
    @JoinColumn(name = "emissions_id", referencedColumnName = "emissions_id")
    private Co2EmissionsEntity co2EmissionsByEmissionsId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity userByUserId;

    public int getChangelogId() {
        return changelogId;
    }

    public void setChangelogId(int changelogId) {
        this.changelogId = changelogId;
    }

    public Integer getEmissionsId() {
        return emissionsId;
    }

    public void setEmissionsId(Integer emissionsId) {
        this.emissionsId = emissionsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Timestamp changeDate) {
        this.changeDate = changeDate;
    }

    public Object getChangeType() {
        return changeType;
    }

    public void setChangeType(Object changeType) {
        this.changeType = changeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChangelogEntity that = (ChangelogEntity) o;

        if (changelogId != that.changelogId) return false;
        if (emissionsId != null ? !emissionsId.equals(that.emissionsId) : that.emissionsId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (changeDate != null ? !changeDate.equals(that.changeDate) : that.changeDate != null) return false;
        if (changeType != null ? !changeType.equals(that.changeType) : that.changeType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = changelogId;
        result = 31 * result + (emissionsId != null ? emissionsId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (changeDate != null ? changeDate.hashCode() : 0);
        result = 31 * result + (changeType != null ? changeType.hashCode() : 0);
        return result;
    }

    public Co2EmissionsEntity getCo2EmissionsByEmissionsId() {
        return co2EmissionsByEmissionsId;
    }

    public void setCo2EmissionsByEmissionsId(Co2EmissionsEntity co2EmissionsByEmissionsId) {
        this.co2EmissionsByEmissionsId = co2EmissionsByEmissionsId;
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
