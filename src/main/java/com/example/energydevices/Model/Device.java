package com.example.energydevices.Model;

import com.example.energydevices.Builder.DeviceBuilder;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String address;

    private String description;

    private int maximumConsumption;

    @ManyToOne
    @JoinColumn(name = "user_reference_id", referencedColumnName = "id")
    private UserReference userReference;

    public Device()
    {

    }

    public Device(DeviceBuilder deviceBuilder)
    {
        this.name = deviceBuilder.name;
        this.address = deviceBuilder.address;
        this.description = deviceBuilder.description;
        this.maximumConsumption = deviceBuilder.maximumConsumption;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public UserReference getUserReference() {
        return userReference;
    }

    public void setUserReference(UserReference userReference) {
        this.userReference = userReference;
    }
}
