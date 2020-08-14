package com.laptopssale.Entities;

import javax.persistence.*;

@Entity
@Table(name = "processor")
public class Processor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    private String modelName;

    private Double rate;

    private Integer numberOfCores;

    public Processor() {
    }

    public Processor(Long id, Manufacturer manufacturer, Double rate, Integer numberOfCores) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.rate = rate;
        this.numberOfCores = numberOfCores;
    }

    public Processor(Long id, Manufacturer manufacturer, String modelName, Double rate, Integer numberOfCores) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.modelName = modelName;
        this.rate = rate;
        this.numberOfCores = numberOfCores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String model) {
        this.modelName = model;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getNumberOfCores() {
        return numberOfCores;
    }

    public void setNumberOfCores(Integer numberOfCores) {
        this.numberOfCores = numberOfCores;
    }
}
