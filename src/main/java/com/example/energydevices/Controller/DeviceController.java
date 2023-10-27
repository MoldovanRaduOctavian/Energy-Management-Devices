package com.example.energydevices.Controller;

import com.example.energydevices.Dto.DeviceIdDto;
import com.example.energydevices.Dto.NewDeviceDto;
import com.example.energydevices.Dto.UpdateDeviceDto;
import com.example.energydevices.Dto.UserReferenceDto;
import com.example.energydevices.Model.Device;
import com.example.energydevices.Service.DeviceService;
import com.example.energydevices.Utility.JsonUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/devices")
public class DeviceController {

    @Autowired
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService)
    {
        this.deviceService = deviceService;
    }

    @GetMapping("/getAllDevices")
    public ResponseEntity<String> getAllDevices() throws JsonProcessingException {
        List<Device> devices = deviceService.findAllDevices();
        if (devices == null)
            return ResponseEntity.badRequest().body("Retrieving devices has failed!");

        return JsonUtility.createJsonResponse(devices);
    }

    @PostMapping("/getDevicesForUserReference")
    public ResponseEntity<String> getAllDevicesForUserReference(@RequestBody UserReferenceDto requestBody) throws JsonProcessingException {
        List<Device> devices = deviceService.processGetDevicesForUserReferenceRequest(requestBody);
        if (devices == null)
            return ResponseEntity.badRequest().body("Retrieving devices for user reference failed!");

        return JsonUtility.createJsonResponse(devices);
    }

    @PostMapping("/createDevice")
    public ResponseEntity<String> createDevice(@RequestBody NewDeviceDto requestBody)
    {
        boolean status = deviceService.processCreateDeviceRequest(requestBody);
        if (!status)
            return ResponseEntity.badRequest().body("New device could not be created!");

        return ResponseEntity.ok("The device was created successfully");
    }

    @PostMapping("/updateDevice")
    public ResponseEntity<String> updateDevice(@RequestBody UpdateDeviceDto requestBody)
    {
        boolean status = deviceService.processUpdateDeviceRequest(requestBody);
        if (!status)
            return ResponseEntity.badRequest().body("The device update has failed!");

        return ResponseEntity.ok("The device was updated successfully");
    }

    @PostMapping("/deleteDevice")
    public ResponseEntity<String> deleteDevice(@RequestBody DeviceIdDto requestBody)
    {
        boolean status = deviceService.processDeleteDeviceRequest(requestBody);
        if (!status)
            return ResponseEntity.badRequest().body("The device could not be deleted!");

        return ResponseEntity.ok("The device was deleted successfully");
    }

    @PostMapping("/createUserReference")
    public ResponseEntity<String> createUserReference(@RequestBody UserReferenceDto requestBody)
    {
        boolean status = deviceService.processCreateUserReferenceRequest(requestBody);
        if (!status)
            return ResponseEntity.badRequest().body("New user reference could not be created!");

        return ResponseEntity.ok("The user reference was created successfully");
    }

    @PostMapping("/deleteUserReference")
    public ResponseEntity<String> deleteUserReference(@RequestBody UserReferenceDto requestBody)
    {
        boolean status = deviceService.processDeleteUserReferenceRequest(requestBody);
        if (!status)
            return ResponseEntity.badRequest().body("The user reference could not be deleted!");

        return ResponseEntity.ok("The user reference was deleted successfully");
    }
}
