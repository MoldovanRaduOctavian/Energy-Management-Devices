package com.example.energydevices.Service;

import com.example.energydevices.Builder.DeviceBuilder;
import com.example.energydevices.Dto.DeviceIdDto;
import com.example.energydevices.Dto.NewDeviceDto;
import com.example.energydevices.Dto.UpdateDeviceDto;
import com.example.energydevices.Dto.UserReferenceDto;
import com.example.energydevices.Model.Device;
import com.example.energydevices.Model.UserReference;
import com.example.energydevices.Repository.DeviceRepository;
import com.example.energydevices.Repository.UserReferenceRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserReferenceRepository userReferenceRepository;
    public List<Device> findAllDevices() { return deviceRepository.findAll(); }

    public Optional<Device> findDeviceById(UUID id) { return deviceRepository.findById(id); }

    public Optional<UserReference> findUserReferenceById(UUID id) { return userReferenceRepository.findById(id); }

    public List<Device> findDevicesByUserReference(UserReference userReference) {
        return deviceRepository.findAllByUserReference(userReference);
    }

    public boolean updateDeviceUserReferenceUponUserDeletion(UserReferenceDto requestBody)
    {
        UserReference userReference = this.findUserReferenceById(requestBody.getUserReferenceId()).orElse(null);
        if (userReference == null)
            return false;

        List<Device> ownedDevices = this.findDevicesByUserReference(userReference);
        List<Device> updatedDevices = ownedDevices.stream()
                .peek(device -> device.setUserReference(null)).toList();

        deviceRepository.saveAll(updatedDevices);
        return true;
    }

    public Optional<Device> findDeviceByUserReference(UserReference userReference) {
        return deviceRepository.findByUserReference(userReference);
    }

    public List<Device> processGetAllDevices()
    {
        return this.findAllDevices();
    }

    public List<Device> processGetDevicesForUserReferenceRequest(UserReferenceDto requestBody)
    {
        UUID userReferenceId = requestBody.getUserReferenceId();
        UserReference userReference = userReferenceRepository.findById(userReferenceId).orElse(null);

        if (userReference == null)
            return null;

        List<Device> devices = this.findDevicesByUserReference(userReference);
        return devices;
    }

    public boolean processCreateDeviceRequest(NewDeviceDto requestBody)
    {
        if (requestBody == null)
            return false;

        // Introduce an api call to the other backend to add the new record to the Device References table
        Device device = new DeviceBuilder().withAddress(requestBody.getAddress()).withName(requestBody.getName())
                .withDescription(requestBody.getDescription()).withMaximumConsumption(requestBody.getMaximumConsumption())
                .withUserReference(null).build();

        Device savedDevice = deviceRepository.save(device);
        if (savedDevice == null)
            return false;

        return true;
    }

    public boolean processUpdateDeviceRequest(UpdateDeviceDto requestBody)
    {
        if (requestBody == null)
            return false;

        Device device = deviceRepository.findById(requestBody.getDeviceId()).orElse(null);
        if (device == null)
            return false;

        System.out.println(requestBody.getUserReferenceId());
        UserReference userReference = userReferenceRepository.findById(requestBody.getUserReferenceId()).orElse(null);
        if (userReference == null)
            return false;

        device.setName(requestBody.getName());
        device.setAddress(requestBody.getAddress());
        device.setDescription(requestBody.getDescription());
        device.setUserReference(userReference);
        device.setMaximumConsumption(requestBody.getMaximumConsumption());

        Device savedDevice = deviceRepository.save(device);
        if (savedDevice == null)
            return false;

        return true;
    }

    public boolean processDeleteDeviceRequest(DeviceIdDto requestBody)
    {
        UUID deviceId = requestBody.getDeviceId();
        Device device = this.findDeviceById(deviceId).orElse(null);

        if (device == null)
            return false;

        // Introudce an api call to the other backend to delete the record from the Device References table

        deviceRepository.delete(device);
        return true;
    }

    public boolean processCreateUserReferenceRequest(UserReferenceDto requestBody)
    {
        UUID userReferenceId = requestBody.getUserReferenceId();
        UserReference userReference = this.findUserReferenceById(userReferenceId).orElse(null);

        if (userReference != null)
            return false;

        UserReference newUserReference = new UserReference();
        newUserReference.setId(userReferenceId);

        UserReference savedUserReference = userReferenceRepository.save(newUserReference);
        if (savedUserReference == null)
            return false;

        return true;
    }

    public boolean processDeleteUserReferenceRequest(UserReferenceDto requestBody)
    {
        UUID userReferenceId = requestBody.getUserReferenceId();
        UserReference userReference = this.findUserReferenceById(userReferenceId).orElse(null);

        if (userReference == null)
            return false;

        if(!updateDeviceUserReferenceUponUserDeletion(requestBody))
            return false;
        userReferenceRepository.delete(userReference);
        return true;
    }
}
