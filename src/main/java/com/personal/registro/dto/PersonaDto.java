package com.personal.registro.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonaDto {
    private Long id;
    private String nombre;
    private String apellido;
    private Long cedula;
    private String estadoPasaporte;
    private String ciudadResidencia;
    private String estadoResidencia;
    private Boolean recibirNotificaciones;
    private String email;
    private String telefono;
}
