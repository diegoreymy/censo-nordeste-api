package com.personal.registro.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "sn_persona")
public class PersonaEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_sn_persona")
    @SequenceGenerator(name="sec_sn_persona", sequenceName = "sec_sn_persona", allocationSize=1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name="cedula", nullable = false, unique = true)
    private Long cedula;

    @Column(name="ciudadResidencia")
    private String ciudadResidencia;

    @Column(name="estadoPasaporte")
    private String estadoPasaporte;

    @Column(name="estadoResidencia")
    private String estadoResidencia;

    @Column(name="recibirNotificaciones")
    private Boolean recibirNotificaciones = false;

    @Column(name="email")
    private String email;

    @Column(name="telefono")
    private String telefono;

}
