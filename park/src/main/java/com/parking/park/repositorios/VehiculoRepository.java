package com.parking.park.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.park.modelos.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

}
