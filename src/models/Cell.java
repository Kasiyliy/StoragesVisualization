package models;

import java.util.Date;

public class Cell {

    private Long id;
    private String name;
    private String invNumber;
    private Date expireDate;
    private Date ownDate;
    private Double crn;
    private Double costPrice;
    private Storage storage;
    private String imagePath;

    public Cell() {
    }

    public Cell(Long id, String name, String invNumber, Date expireDate, Date ownDate, Double crn, Double costPrice, Storage storage, String imagePath) {
        this.id = id;
        this.name = name;
        this.invNumber = invNumber;
        this.expireDate = expireDate;
        this.ownDate = ownDate;
        this.crn = crn;
        this.costPrice = costPrice;
        this.storage = storage;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvNumber() {
        return invNumber;
    }

    public void setInvNumber(String invNumber) {
        this.invNumber = invNumber;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void setOwnDate(Date ownDate) {
        this.ownDate = ownDate;
    }


    public void setCrn(double crn) {
        this.crn = crn;
    }


    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public Date getExpireDate() {
        return expireDate;
    }

    public Date getOwnDate() {
        return ownDate;
    }

    public double getCrn() {
        return crn;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public Storage getStorage() {
        return storage;
    }
}
