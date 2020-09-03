package com.laptopssale.Entities;


import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "videocard_id")
    private Videocard videocard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processor_id")
    private Processor processor;

    private Integer ramCount;

    private Integer romCount;


    private Double lenght;

    private Double width;

    private Double height;

    private Double diagonal;


    private Integer countOnWarehouse;

    private Double priceToBuy;

    private Double priceToSale;



    public Laptop() {
    }

    public Laptop(Long id,
                  String productName,
                  Manufacturer manufacturer,
                  Videocard videocard,
                  Processor processor,
                  Integer ramCount,
                  Integer romCount,
                  Double lenght,
                  Double width,
                  Double height,
                  Double diagonal,
                  Integer countOnWarehouse,
                  Double priceToBuy,
                  Double priceToSale) {
        this.id = id;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.videocard = videocard;
        this.processor = processor;
        this.ramCount = ramCount;
        this.romCount = romCount;
        this.lenght = lenght;
        this.width = width;
        this.height = height;
        this.diagonal = diagonal;
        this.countOnWarehouse = countOnWarehouse;
        this.priceToBuy = priceToBuy;
        this.priceToSale = priceToSale;
    }

    public Laptop(Long id, String productName, Manufacturer manufacturer) {
        this.id = id;
        this.productName = productName;
        this.manufacturer = manufacturer;
    }

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

    public String getFullProductName() {
        return processor.getManufacturer().getName() + " "
                + processor.getModelName() + " "
                + manufacturer.getName() + " "
                + productName + " "
                + ramCount + "GB RAM///"
                + romCount + "GB ROM";
    }

    public Double getPriceToSale() {
        return priceToSale;
    }

    public void setPriceToSale(Double priceToSale) {
        this.priceToSale = priceToSale;
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
