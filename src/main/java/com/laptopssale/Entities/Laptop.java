package com.laptopssale.Entities;


import org.springframework.lang.Nullable;

import javax.persistence.*;
//
//@Entity
//@Table(name = "product")
public class Laptop {
    @Id
    private Long id;

    private String productName;

    private Double priceToSale;

    @Nullable
    private String photo;

    private Integer countOnWarehouse;

    private Double priceToBuy;

    private Integer ramCount;

    private Integer romCount;

    private Double lenght;

    private Double width;

    private Double height;

    private Double diagonal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "videocard_id")
    private Videocard videocard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processor_id")
    private Processor processor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPriceToSale() {
        return priceToSale;
    }

    public void setPriceToSale(Double priceToSale) {
        this.priceToSale = priceToSale;
    }

    @Nullable
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(@Nullable String photo) {
        this.photo = photo;
    }

    public Integer getCountOnWarehouse() {
        return countOnWarehouse;
    }

    public void setCountOnWarehouse(Integer countOnWarehouse) {
        this.countOnWarehouse = countOnWarehouse;
    }

    public Double getPriceToBuy() {
        return priceToBuy;
    }

    public void setPriceToBuy(Double priceToBuy) {
        this.priceToBuy = priceToBuy;
    }

    public Integer getRamCount() {
        return ramCount;
    }

    public void setRamCount(Integer ramCount) {
        this.ramCount = ramCount;
    }

    public Integer getRomCount() {
        return romCount;
    }

    public void setRomCount(Integer romCount) {
        this.romCount = romCount;
    }

    public Double getLenght() {
        return lenght;
    }

    public void setLenght(Double lenght) {
        this.lenght = lenght;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(Double diagonal) {
        this.diagonal = diagonal;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Videocard getVideocard() {
        return videocard;
    }

    public void setVideocard(Videocard videocard) {
        this.videocard = videocard;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }
}
