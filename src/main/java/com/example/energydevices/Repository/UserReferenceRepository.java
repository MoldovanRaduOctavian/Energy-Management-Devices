package com.example.energydevices.Repository;

import com.example.energydevices.Model.UserReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserReferenceRepository extends JpaRepository<UserReference, UUID> {

    List<UserReference> findAll();
    Optional<UserReference> findById(@Param("id") UUID id);
}
