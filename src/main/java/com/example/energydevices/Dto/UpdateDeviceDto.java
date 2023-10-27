package com.example.energydevices.Dto;

import java.util.UUID;

public class UpdateDeviceDto {

    private UUID deviceId;
    private String name;
    private String address;
    private String description;
    private int maximumConsumption;

    private UUID userReferenceId;

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaximumConsumption() {
        return maximumConsumption;
    }

    public void setMaximumConsumption(int maximumConsumption) {
        this.maximumConsumption = maximumConsumption;
    }

    public UUID getUserReferenceId() {
        return userReferenceId;
    }

    public void setUserReferenceId(UUID userReferenceId) {
        this.userReferenceId = userReferenceId;
    }
}
