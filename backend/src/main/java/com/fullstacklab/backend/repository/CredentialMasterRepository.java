package com.fullstacklab.backend.repository;

import com.fullstacklab.backend.entity.CredentialMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialMasterRepository extends JpaRepository<CredentialMaster, Long> {

    Optional<CredentialMaster> findByEmail(String email);

}