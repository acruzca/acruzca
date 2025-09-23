package com.acruzca.pradonavas.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "prado_navas")
public class PradoNava {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 120)
    private String nombre;

    @NotBlank(message = "La ubicación es obligatoria")
    @Column(nullable = false, length = 200)
    private String ubicacion;

    @NotNull(message = "La extensión es obligatoria")
    @Positive(message = "La extensión debe ser mayor que cero")
    @Column(name = "extension_hectareas", nullable = false)
    private Double extensionHectareas;

    @NotBlank(message = "El estado de conservación es obligatorio")
    @Column(name = "estado_conservacion", nullable = false, length = 100)
    private String estadoConservacion;

    @NotNull(message = "La fecha de registro es obligatoria")
    @PastOrPresent(message = "La fecha de registro no puede ser futura")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Double getExtensionHectareas() {
        return extensionHectareas;
    }

    public void setExtensionHectareas(Double extensionHectareas) {
        this.extensionHectareas = extensionHectareas;
    }

    public String getEstadoConservacion() {
        return estadoConservacion;
    }

    public void setEstadoConservacion(String estadoConservacion) {
        this.estadoConservacion = estadoConservacion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @PrePersist
    public void prePersist() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDate.now();
        }
    }
}
