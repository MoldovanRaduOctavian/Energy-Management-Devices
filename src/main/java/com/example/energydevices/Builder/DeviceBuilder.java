package com.example.energydevices.Builder;

import com.example.energydevices.Model.Device;
import com.example.energydevices.Model.UserReference;

public class DeviceBuilder {
    public String name;
    public String address;
    public String description;
    public int maximumConsumption;

    public UserReference userReference;

    public DeviceBuilder()
    {

    }

    public DeviceBuilder withName(String name)
    {
        this.name = name;
        return this;
    }

    public DeviceBuilder withAddress(String address)
    {
        this.address = address;
        return this;
    }

    public DeviceBuilder withDescription(String description)
    {
        this.description = description;
        return this;
    }

    public DeviceBuilder withMaximumConsumption(int maximumConsumption)
    {
        this.maximumConsumption = maximumConsumption;
        return this;
    }

    public DeviceBuilder withUserReference(UserReference userReference)
    {
        this.userReference = userReference;
        return this;
    }

    public Device build() { return new Device(this); }

}
