package org.example.lab8soplin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "equipos")
@Getter
@Setter
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", unique = true)
    private String nombre;

    @Column(name = "tag")
    private String tag;

    @Column(name = "nombre_capitan")
    private String nombreCapitan;

    @Column(name = "cantidad_jugadores")
    private Integer cantidadJugadores;

    private String juego;
    private String pais;
    private String correo;
    private String telefono;

    private Boolean estado;
}