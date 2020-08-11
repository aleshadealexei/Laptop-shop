package com.laptopssale.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean videoManufacturer;

    private boolean processorManufacturer;


    private boolean laptopManufacturer;





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

    public boolean isVideoManufacturer() {
        return videoManufacturer;
    }

    public void setVideoManufacturer(boolean videoManufacturer) {
        this.videoManufacturer = videoManufacturer;
    }

    public boolean isProcessorManufacturer() {
        return processorManufacturer;
    }

    public void setProcessorManufacturer(boolean processorManufacturer) {
        this.processorManufacturer = processorManufacturer;
    }

    public boolean isLaptopManufacturer() {
        return laptopManufacturer;
    }

    public void setLaptopManufacturer(boolean laptopManufacturer) {
        this.laptopManufacturer = laptopManufacturer;
    }
}
