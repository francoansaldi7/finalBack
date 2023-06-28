package com.repository;

import com.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
    Optional<Domicilio> findById(Long id);
}
