package com.personal.registro.service;

import com.personal.registro.dao.PersonaDao;
import com.personal.registro.dto.PersonaDto;
import com.personal.registro.model.PersonaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonaService {

    private final PersonaDao personaDao;

    public PersonaDto getPersonaById(Long id) {
        Optional<PersonaEntity> personaOptional = personaDao.findById(id);
        return personaOptional.map(this::convertToDto).orElseThrow(() -> new RuntimeException("Persona not found with id: " + id));
    }

    public Page<PersonaDto> getAllPersonas(Pageable pageable) {
        Page<PersonaEntity> personasPage = personaDao.findAll(pageable);
        return personasPage.map(this::convertToDto);
    }

    public PersonaDto addPersona(PersonaDto personaDto) {
        Optional<PersonaEntity> existentPersona = personaDao.findByCedula(personaDto.getCedula());
        if(existentPersona.isPresent()) {
            throw new RuntimeException("Ya existe una persona con la cédula proporcionada.");
        }
        PersonaEntity persona = convertToEntity(personaDto);
        persona = personaDao.save(persona);
        return convertToDto(persona);
    }


    public PersonaDto updatePersona(Long id, PersonaDto personaDto) {
        personaDto.setId(id);
        Optional<PersonaEntity> personaOptional = personaDao.findById(id);
        if (personaOptional.isPresent()) {
            PersonaEntity personaExistente = personaOptional.get();
            BeanUtils.copyProperties(personaDto, personaExistente);
            personaExistente = personaDao.save(personaExistente);
            return convertToDto(personaExistente);
        } else {
            throw new RuntimeException("Persona not found with id: " + id);
        }
    }

    public boolean deletePersona(Long id) {
        if (personaDao.existsById(id)) {
            personaDao.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private PersonaDto convertToDto(PersonaEntity personaEntity) {
        PersonaDto personaDto = new PersonaDto();
        BeanUtils.copyProperties(personaEntity, personaDto);
        return personaDto;
    }

    private PersonaEntity convertToEntity(PersonaDto personaDto) {
        PersonaEntity personaEntity = new PersonaEntity();
        BeanUtils.copyProperties(personaDto, personaEntity);
        return personaEntity;
    }
}
