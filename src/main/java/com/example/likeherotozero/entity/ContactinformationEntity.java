package com.example.likeherotozero.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contactinformation", schema = "like_hero_to_zero")
public class ContactinformationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "contact_id")
    private int contactId;
    @Basic
    @Column(name = "user_id", insertable = false, updatable = false)
    private int userId;
    @Basic
    @Column(name = "contact_type")
    private String contactType;
    @Basic
    @Column(name = "contact_detail")
    private String contactDetail;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userByUserId;

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(String contactDetail) {
        this.contactDetail = contactDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactinformationEntity that = (ContactinformationEntity) o;

        if (contactId != that.contactId) return false;
        if (userId != that.userId) return false;
        if (contactType != null ? !contactType.equals(that.contactType) : that.contactType != null) return false;
        if (contactDetail != null ? !contactDetail.equals(that.contactDetail) : that.contactDetail != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contactId;
        result = 31 * result + userId;
        result = 31 * result + (contactType != null ? contactType.hashCode() : 0);
        result = 31 * result + (contactDetail != null ? contactDetail.hashCode() : 0);
        return result;
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
