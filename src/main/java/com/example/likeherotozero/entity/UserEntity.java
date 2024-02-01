package com.example.likeherotozero.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "user", schema = "like_hero_to_zero")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "institution")
    private String institution;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "is_scientist")
    private Byte isScientist;
    @Basic
    @Column(name = "is_admin")
    private Byte isAdmin;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<ChangelogEntity> changelogsByUserId;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Co2EmissionsEntity> co2EmissionsByUserId;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<ContactinformationEntity> contactinformationsByUserId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getIsScientist() {
        return isScientist;
    }

    public void setIsScientist(Byte isScientist) {
        this.isScientist = isScientist;
    }

    public Byte getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Byte isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (institution != null ? !institution.equals(that.institution) : that.institution != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (isScientist != null ? !isScientist.equals(that.isScientist) : that.isScientist != null) return false;
        if (isAdmin != null ? !isAdmin.equals(that.isAdmin) : that.isAdmin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (institution != null ? institution.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (isScientist != null ? isScientist.hashCode() : 0);
        result = 31 * result + (isAdmin != null ? isAdmin.hashCode() : 0);
        return result;
    }

    public Collection<ChangelogEntity> getChangelogsByUserId() {
        return changelogsByUserId;
    }

    public void setChangelogsByUserId(Collection<ChangelogEntity> changelogsByUserId) {
        this.changelogsByUserId = changelogsByUserId;
    }

    public Collection<Co2EmissionsEntity> getCo2EmissionsByUserId() {
        return co2EmissionsByUserId;
    }

    public void setCo2EmissionsByUserId(Collection<Co2EmissionsEntity> co2EmissionsByUserId) {
        this.co2EmissionsByUserId = co2EmissionsByUserId;
    }

    public Collection<ContactinformationEntity> getContactinformationsByUserId() {
        return contactinformationsByUserId;
    }

    public void setContactinformationsByUserId(Collection<ContactinformationEntity> contactinformationsByUserId) {
        this.contactinformationsByUserId = contactinformationsByUserId;
    }
}
