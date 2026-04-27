package com.parking.park.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class TipoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

}
