package com.laptopssale.Entities;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table(name = "videocard")
public class Videocard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    private String modelName;

    private VideoMemory memoryType;

    private Integer memorySize;

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

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public VideoMemory getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(VideoMemory memoryType) {
        this.memoryType = memoryType;
    }

    public Integer getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(Integer memorySize) {
        this.memorySize = memorySize;
    }
}
