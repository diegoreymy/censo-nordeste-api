package com.personal.registro.dao;

import com.personal.registro.model.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaDao extends JpaRepository<PersonaEntity, Long> {
    Optional<PersonaEntity> findByCedula(Long cedula);
}
