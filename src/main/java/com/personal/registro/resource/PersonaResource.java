package com.personal.registro.resource;

import com.personal.registro.dto.PersonaDto;
import com.personal.registro.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/personas")
public class PersonaResource {

    private final PersonaService personaService;

    @Autowired
    public PersonaResource(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("get/{id}")
    public ResponseEntity<PersonaDto> getPersonaById(@PathVariable Long id) {
        PersonaDto personaDto = personaService.getPersonaById(id);
        if (personaDto != null) {
            return new ResponseEntity<>(personaDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getAll")
    public ResponseEntity<Page<PersonaDto>> getAllPersonas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<PersonaDto> personasPage = personaService.getAllPersonas(pageable);

        return new ResponseEntity<>(personasPage, HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<?> addPersona(@RequestBody PersonaDto personaDto) {
        try {
            PersonaDto nuevaPersonaDto = personaService.addPersona(personaDto);
            return new ResponseEntity<>(nuevaPersonaDto, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<PersonaDto> updatePersona(@PathVariable Long id, @RequestBody PersonaDto personaDto) {
        PersonaDto personaActualizada = personaService.updatePersona(id, personaDto);
        if (personaActualizada != null) {
            return new ResponseEntity<>(personaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        boolean resultado = personaService.deletePersona(id);
        if (resultado) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}