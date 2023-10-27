package com.example.energydevices.Repository;

import com.example.energydevices.Model.Device;
import com.example.energydevices.Model.UserReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {

    List<Device> findAll();

    Optional<Device> findById(@Param("id") UUID id);

    Optional<Device> findByUserReference(@Param("userReference") UserReference userReference);

    List<Device> findAllByUserReference(@Param("userReference") UserReference userReference);
}
