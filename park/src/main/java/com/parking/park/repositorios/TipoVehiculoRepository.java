package com.parking.park.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.park.modelos.TipoVehiculo;

public interface TipoVehiculoRepository extends JpaRepository<TipoVehiculo, Long> {

}
