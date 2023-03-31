package edu.ncsu.csc540.s23.backend.model;

public class Sponsor {

    private Long sponsorId;

    private String sponsorFirstName;

    private String sponsorLastName;

    private String organization;

    private String sponsorEmail;

    private String sponsorCity;

    public Long getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Long sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getSponsorFirstName() {
        return sponsorFirstName;
    }

    public void setSponsorFirstName(String sponsorFirstName) {
        this.sponsorFirstName = sponsorFirstName;
    }

    public String getSponsorLastName() {
        return sponsorLastName;
    }

    public void setSponsorLastName(String sponsorLastName) {
        this.sponsorLastName = sponsorLastName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getSponsorEmail() {
        return sponsorEmail;
    }

    public void setSponsorEmail(String sponsorEmail) {
        this.sponsorEmail = sponsorEmail;
    }

    public String getSponsorCity() {
        return sponsorCity;
    }

    public void setSponsorCity(String sponsorCity) {
        this.sponsorCity = sponsorCity;
    }
}
